/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import org.xml.sax.SAXException;
import prx.config.Config;
import prx.config.Config.BuiltWith;
import prx.constant.ConfigConstant;
import prx.exception.InvalidException;
import prx.parser.BuiltWithParser;
import prx.parser.AlexaParser;
import prx.utils.XMLUtils;

/**
 *
 * @author Eden
 */
public class SiteKick {

    public static void main(String[] args) {
        try {
            Config config = loadConfiguration();
            if (config != null) {
                parse(config);
            } 
        } catch (InvalidException | JAXBException e) {
            Logger.getLogger(SiteKick.class.getName()).log(Level.SEVERE, e.getMessage());
        }

    }

    public static Set<String> parseSite(Config.Alexa alexaConfig) {
        AlexaParser alexaParser = new AlexaParser();
        Config.Alexa.XPath xPath = alexaConfig.getXPath();
        alexaParser.setCategoryXPath(xPath.getCategory());
        alexaParser.setUrlXPath(xPath.getDetailUrl());
        alexaParser.setBaseURL(alexaConfig.getBaseUrl());
        alexaParser.setXslPath(alexaConfig.getXslPath());
        alexaParser.setXsdPath(alexaConfig.getXsdPath());
        alexaParser.setNavigationPath(alexaConfig.getAllCategoryNavigationPath());
        alexaParser.setDomainXPath(xPath.getDomain());
        alexaParser.setCategoryNavigationPath(alexaConfig.getCategoryNavigationPath());
        alexaParser.parse();
        System.out.println("Parse Alexa Finished");
        return alexaParser.getDomainSet();
    }

    public static void parseBuiltWith(Set<String> domainSet, BuiltWith builtWithConfig) {
        BuiltWithParser builtWithParser = new BuiltWithParser();
        builtWithParser.setBaseURL(builtWithConfig.getBaseUrl());
        builtWithParser.setXsdPath(builtWithConfig.getXsdPath());
        builtWithParser.setXslPath(builtWithConfig.getXslPath());
        builtWithParser.setNavigationPath(builtWithConfig.getNavigationPath());
        builtWithParser.setLinkSet(domainSet);
        builtWithParser.parse();
        System.out.println("Parse Built With Finished");
    }

    public static void generateCode(Config config) {
        String packageName = "prx.data";
        String builtWithXsdPath = config.getBuiltWith().getXsdPath();
        String alexaXsdPath = config.getAlexa().getXsdPath();
        File builtWithSchemaFile = new File(builtWithXsdPath);
        File alexaSchemaFile = new File(alexaXsdPath);
        String outputPath = "src/";
        try {
            XMLUtils.generateClass(packageName, builtWithSchemaFile, outputPath);
            XMLUtils.generateClass(packageName, alexaSchemaFile, outputPath);
        } catch (SAXException | IOException e) {
            Logger.getLogger(SiteKick.class.getName()).log(Level.SEVERE, e.getMessage());
        }

    }

    public static Config loadConfiguration() throws JAXBException, InvalidException {
        Config config = null;
        boolean isValid = XMLUtils.isXMLValidateFromFilePath(ConfigConstant.XSD_PATH, ConfigConstant.XML_PATH);
        if (isValid) {
            // unmarshall
            config = XMLUtils.unmarshall(Config.class, new File(ConfigConstant.XML_PATH));
        } else {
            throw new InvalidException("Invalid Configuration File");
        }
        return config;
    }

    public static void parse(Config config) {
        Set<String> domainSet = parseSite(config.getAlexa());
        parseBuiltWith(domainSet, config.getBuiltWith());
    }

    private static void generateConfigurationClass() {
        try {
            XMLUtils.generateClass(ConfigConstant.GEN_PACKAGE_NAME, new File(ConfigConstant.XSD_PATH), ConfigConstant.GEN_OUTPUT_PATH);
        } catch (IOException | SAXException ex) {
            Logger.getLogger(SiteKick.class.getName()).log(Level.SEVERE, ex.getMessage());
        }
    }
}
