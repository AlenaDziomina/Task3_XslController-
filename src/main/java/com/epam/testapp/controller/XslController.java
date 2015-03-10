package com.epam.testapp.controller;

import com.epam.testapp.util.source.TemplateSourceName;
import com.epam.testapp.transformer.TransformerHelper;
import com.epam.testapp.util.validation.ProductValidationForm;
import com.epam.testapp.util.validation.ProductValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

@Controller
public class XslController {
    private static final Logger logger = Logger.getLogger(XslController.class);
    private static final String CATEGORY_NAME = "categoryName";
    private static final String SUBCATEGORY_NAME = "subcategoryName";

    @Autowired
    TransformerHelper transformerHelper;
    @Autowired
    private ProductValidator validator;


    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.HEAD} )
	public void emptyCommand(Writer writer) throws IOException {
        Transformer transformer = transformerHelper.getTransformer(TemplateSourceName.CATEGORY);
        Resource resource = new ClassPathResource(TemplateSourceName.XML_SOURCE.getCurrentSourceName());
        StreamSource streamSource = new StreamSource(resource.getFile());
        StreamResult streamResult = new StreamResult(writer);
        try {
            transformer.transform(streamSource, streamResult);
        } catch (TransformerException e) {
            logger.error(e);
        }
	}

    @RequestMapping(value = "/{categoryName}", method = {RequestMethod.GET, RequestMethod.HEAD} )
    public void viewCategoryCommand(@PathVariable String categoryName, Writer writer) throws IOException {
        Transformer transformer = transformerHelper.getTransformer(TemplateSourceName.SUBCATEGORY);
        Resource resource = new ClassPathResource(TemplateSourceName.XML_SOURCE.getCurrentSourceName());
        StreamSource streamSource = new StreamSource(resource.getFile());
        StreamResult streamResult = new StreamResult(writer);
        try {
            transformer.setParameter(CATEGORY_NAME, categoryName);
            transformer.transform(streamSource, streamResult);
        } catch (TransformerException e) {
            logger.error(e);
        }
    }

    @RequestMapping(value = "/{categoryName}/{subcategoryName}", method = {RequestMethod.GET, RequestMethod.HEAD} )
    public void viewSubcategoryCommand(@PathVariable String categoryName, @PathVariable String subcategoryName,
                                       Writer writer) throws IOException {
        Transformer transformer = transformerHelper.getTransformer(TemplateSourceName.PRODUCT_LIST);
        Resource resource = new ClassPathResource(TemplateSourceName.XML_SOURCE.getCurrentSourceName());
        StreamSource streamSource = new StreamSource(resource.getFile());
        StreamResult streamResult = new StreamResult(writer);
        try {
            transformer.setParameter(CATEGORY_NAME, categoryName);
            transformer.setParameter(SUBCATEGORY_NAME, subcategoryName);
            transformer.transform(streamSource, streamResult);
        } catch (TransformerException e) {
            logger.error(e);
        }
    }

    @RequestMapping(value = "/{categoryName}/{subcategoryName}/add", method = {RequestMethod.GET, RequestMethod.HEAD} )
    public void addProductCommand(@PathVariable String categoryName, @PathVariable String subcategoryName,
                                  Writer writer) throws IOException {
        Transformer transformer = transformerHelper.getTransformer(TemplateSourceName.ADD_PRODUCT);
        Resource resource = new ClassPathResource(TemplateSourceName.XML_SOURCE.getCurrentSourceName());
        StreamSource streamSource = new StreamSource(resource.getFile());
        StreamResult streamResult = new StreamResult(writer);
        try {
            transformer.setParameter(CATEGORY_NAME, categoryName);
            transformer.setParameter(SUBCATEGORY_NAME, subcategoryName);
            ProductValidationForm product = new ProductValidationForm();
            transformer.setParameter("product", product);
            transformer.setParameter("validator", validator);
            transformer.transform(streamSource, streamResult);
        } catch (TransformerException e) {
            logger.error(e);
        }
    }

    @RequestMapping(value = "/{categoryName}/{subcategoryName}/save", method = {RequestMethod.GET, RequestMethod.HEAD} )
    public void saveProductCommand(@PathVariable String categoryName, @PathVariable String subcategoryName,
                                  Writer writer) throws IOException {

    }



    @RequestMapping(value = "/aaa", method = {RequestMethod.GET, RequestMethod.HEAD} )
    public String printHello(ModelMap model, HttpServletRequest request) throws IOException {

        Resource resource = new ClassPathResource("/xml/products.xml");
        File resourceFile = resource.getFile();


        File file = new File("/xml/products.xml");
        if (resourceFile.exists()) {
            model.addAttribute("message", "HELLO!!!");
        } else {
            model.addAttribute("message", "not");
        }
        return "hello";
    }



}