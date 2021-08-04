package platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class APIController {

    @Autowired
    private CodeRepository codeRepository;

    @GetMapping("/api/code/latest")
    public ResponseEntity<List<Code>> getAPILatestCodes() {
        List<Code> response = codeRepository.findTop10ByRestrictedViewsFalseAndRestrictedTimeFalseOrderByDateDesc();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/api/code/{id}")
    public ResponseEntity<Map> getAPICode(@PathVariable String id) {
        Optional<Code> codeOptional = codeRepository.findById(id);
        Map response = new HashMap();
        if (codeOptional.isPresent()) {
            Code code = codeOptional.get();
            if (!code.isRestrictedTime() && !code.isRestrictedViews()) {
                response.put("code", code.getCode());
                response.put("date", code.getDate());
                response.put("time", 0);
                response.put("views", 0);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                if (code.shouldBeDeleted()) {
                    codeRepository.delete(code);
                    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
                } else {
                    response.put("code", code.getCode());
                    response.put("date", code.getDate());
                    response.put("time", code.getTime());
                    if (code.isRestrictedViews()) {
                        response.put("views", code.getViews() - 1);
                        code.takeOneView();
                        codeRepository.save(code);
                    } else {
                        response.put("views", 0);
                    }
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }
            }
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/api/code/new", consumes = "application/json")
    public ResponseEntity<Map> postCode(@RequestBody Code code) {
        Map response = new HashMap();

        if (code.getCode() == null) {
            response.put("error", "Code can't be empty");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else {
            Code savedCode = codeRepository.save(code);
            response.put("id", "" + savedCode.getId());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

    }
}