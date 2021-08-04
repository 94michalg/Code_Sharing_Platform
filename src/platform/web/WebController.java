package platform.web;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import platform.Code;
import platform.CodeRepository;
import platform.web.templates.HTMLTemplates;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class WebController {

    @Autowired
    private ConfigurationManager configurationManager;

    @Autowired
    private CodeRepository codeRepository;

    @GetMapping("/code/latest")
    public ResponseEntity<String> getLatestCodes() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "text/html");

        List<Code> response = codeRepository.findTop10ByRestrictedViewsFalseAndRestrictedTimeFalseOrderByDateDesc();

        Map root = new HashMap();
        root.put("arrayList", response);

        try {
            Template temp = configurationManager.getTemplate("list.ftlh");
            StringWriter stringWriter = new StringWriter();
            temp.process(root, stringWriter);

            return ResponseEntity.ok()
                    .headers(responseHeaders)
                    .body(stringWriter.toString());
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }

        return null;
    }

    @GetMapping("/code/{id}")
    public ResponseEntity<String> getCode(@PathVariable String id) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type",
                "text/html");

        Optional<Code> optionalCode = codeRepository.findById(id);
        if (optionalCode.isPresent()) {
            Code root = optionalCode.get();
            if (root.shouldBeDeleted()) {
                codeRepository.delete(root);
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            } else {
                if (root.isRestrictedViews()) {
                    root.takeOneView();
                    codeRepository.save(root);
                }
                try {
                    Template temp = configurationManager.getTemplate("getCode.ftlh");
                    StringWriter stringWriter = new StringWriter();
                    temp.process(root, stringWriter);

                    return ResponseEntity.ok()
                            .headers(responseHeaders)
                            .body(stringWriter.toString());
                } catch (IOException | TemplateException e) {
                    e.printStackTrace();
                }
            }

        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/code/new")
    public ResponseEntity<String> getCodeNew() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type",
                "text/html");

        String body = HTMLTemplates.POST_CODE;

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(body);
    }
}
