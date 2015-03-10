package com.epam.testapp.transformer;
import com.epam.testapp.util.source.TemplateSourceName;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Alena_Grouk on 2/18/2015.
 */

public class TransformerHelper {

    @Autowired
    private TransformerFactory transformerFactory;

    private Map<TemplateSourceName, Templates> xslTemplates;
    private static final Logger logger = Logger.getLogger(TransformerHelper.class);

    public void init() {
        xslTemplates = new ConcurrentHashMap<TemplateSourceName, Templates>();
    }

    public Transformer getTransformer(TemplateSourceName templateSourceName) throws IOException {
        Templates template = getTemplate(templateSourceName);
        Transformer transformer = null;
        try {
            transformer = template.newTransformer();
        } catch (TransformerConfigurationException exc) {
            logger.error(exc);
        }
        return transformer;
    }

    private Templates getTemplate(TemplateSourceName templateSourceName) throws IOException {
        Templates templates = xslTemplates.get(templateSourceName);
        if (templates == null) {
            initTemplate(templateSourceName);
            templates = xslTemplates.get(templateSourceName);
        }
        return templates;
    }

    private void initTemplate(TemplateSourceName templateSourceName) throws IOException {
        String xslSource = templateSourceName.getCurrentSourceName();

        Resource resource = new ClassPathResource(xslSource);
        File resourceFile = resource.getFile();

        Source xsltSource = new StreamSource(resourceFile);
        Templates template = null;
        try {
            template = transformerFactory.newTemplates(xsltSource);
            xslTemplates.put(templateSourceName, template);
        } catch (TransformerConfigurationException e) {
            logger.error(e);
        }
    }

}
