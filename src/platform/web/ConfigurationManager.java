package platform.web;

import freemarker.core.ParseException;
import freemarker.template.*;
import org.springframework.stereotype.Component;

@Component
public class ConfigurationManager {

    private final Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);

    public ConfigurationManager() {
        try {
            cfg.setDirectoryForTemplateLoading(new java.io.File("C:\\Users\\Michal\\Downloads\\demo (1)\\Code Sharing Platform\\Code Sharing Platform\\task\\src\\platform\\web\\templates"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);
        cfg.setFallbackOnNullLoopVariable(false);
    }

    public Template getTemplate(String template) {
        try {
            return cfg.getTemplate(template);
        } catch (TemplateNotFoundException e) {
            e.printStackTrace();
        } catch (MalformedTemplateNameException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
