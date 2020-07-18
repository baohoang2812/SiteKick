/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sitekick.crawler;


import java.io.File;
import java.io.IOException;
import java.util.Set;
import javax.servlet.ServletContext;
import javax.xml.bind.JAXBException;
import org.xml.sax.SAXException;
import prx.config.Config;
import prx.constant.ConfigConstant;
import prx.exception.InvalidException;
import prx.parser.AlexaParser;
import prx.parser.BuiltWithParser;
import prx.utils.XMLUtils;

/**
 *
 * @author Eden
 */
public class SiteKickCrawler {

    public static void generateConfigurationClass() throws IOException, SAXException {
        XMLUtils.generateClass(ConfigConstant.GEN_PACKAGE_NAME, new File(ConfigConstant.XSD_PATH), ConfigConstant.GEN_OUTPUT_PATH);
    }

    public Config loadConfiguration(String xsdPath, String xmlPath) throws JAXBException, InvalidException {
        Config config = null;
        boolean isValid = XMLUtils.isXMLValidateFromFilePath(xsdPath, xmlPath);
        if (isValid) {
            // unmarshall
            config = XMLUtils.unmarshall(Config.class, new File(xmlPath));
        } else {
            throw new InvalidException("Invalid Configuration File");
        }
        return config;
    }

    public Set<String> parseSite(Config.Alexa alexaConfig, ServletContext context) {
        AlexaParser alexaParser = new AlexaParser();
        Config.Alexa.XPath xPath = alexaConfig.getXPath();
        alexaParser.setCategoryXPath(xPath.getCategory());
        alexaParser.setUrlXPath(xPath.getDetailUrl());
        alexaParser.setBaseURL(alexaConfig.getBaseUrl());
        alexaParser.setXslPath(context.getRealPath(alexaConfig.getXslPath()));
        alexaParser.setXsdPath(context.getRealPath(alexaConfig.getXsdPath()));
        alexaParser.setNavigationPath(alexaConfig.getAllCategoryNavigationPath());
        alexaParser.setDomainXPath(xPath.getDomain());
        alexaParser.setCategoryNavigationPath(alexaConfig.getCategoryNavigationPath());
        alexaParser.parse();
        System.out.println("Parse Alexa Finished");
        return alexaParser.getDomainSet();
    }

    public void parseBuiltWith(Set<String> domainSet, Config.BuiltWith builtWithConfig, ServletContext servletContext) {
        BuiltWithParser builtWithParser = new BuiltWithParser();
        builtWithParser.setBaseURL(builtWithConfig.getBaseUrl());
        builtWithParser.setXsdPath(servletContext.getRealPath(builtWithConfig.getXsdPath()));
        builtWithParser.setXslPath(servletContext.getRealPath(builtWithConfig.getXslPath()));
        builtWithParser.setNavigationPath(builtWithConfig.getNavigationPath());
        builtWithParser.setLinkSet(domainSet);
        builtWithParser.parse();
        System.out.println("Parse Built With Finished");
    }
    
}
