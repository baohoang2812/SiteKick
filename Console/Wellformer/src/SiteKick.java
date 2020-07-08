/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.xml.sax.SAXException;
import prx.config.SystemConfig;
import prx.constant.CommonConstant;
import prx.parser.BuiltWithParser;
import prx.parser.AlexaParser;
import prx.utils.XMLUtils;

/**
 *
 * @author Eden
 */
public class SiteKick {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        Set<String> domainSet = parseSite();
        Set<String> domainSet = new HashSet();
        domainSet.add("tuoitre.vn");
        domainSet.add("kenh14.vn");
        domainSet.add("steam.com");
        parseBuiltWith(domainSet);
    }

    public static Set<String> parseSite() {
        AlexaParser alexaParser = new AlexaParser();
        alexaParser.setCategoryXPath(SystemConfig.SITE_CATEGORY_XPATH);
        alexaParser.setUrlXPath(SystemConfig.SITE_DETAIL_URL_XPATH);
        alexaParser.setBaseURL(SystemConfig.ALEXA_BASE_URL);
        alexaParser.setXslPath(SystemConfig.SITE_XSL_PATH);
        alexaParser.setXsdPath(SystemConfig.SITE_XSD_PATH);
        alexaParser.setNavigationPath(SystemConfig.SITE_ALL_CATEGORY_NAVIGATION_PATH);
        alexaParser.setDomainXPath(SystemConfig.SITE_DOMAIN_XPATH);
        alexaParser.parse();
        return alexaParser.getDomainSet();
    }

    public static void parseBuiltWith(Set<String> domainSet) {
        BuiltWithParser builtWithParser = new BuiltWithParser();
        builtWithParser.setBaseURL(SystemConfig.BUILT_WITH_BASE_URL);
        builtWithParser.setXsdPath(SystemConfig.TECH_XSD_PATH);
        builtWithParser.setXslPath(SystemConfig.TECH_XSL_PATH);
        //TODO remove navigation Path on empty or null
        builtWithParser.setNavigationPath(CommonConstant.EMPTY);
        builtWithParser.setLinkSet(domainSet);
        builtWithParser.parse();
    }

    public static void generateCode() {
        String packageName = "prx.data";
        String builtWithXsdPath = SystemConfig.TECH_XSD_PATH;
        String similarWebXsdPath = SystemConfig.SITE_XSD_PATH;
        File builtWithSchemaFile = new File(builtWithXsdPath);
        File alexaSchemaFile = new File(similarWebXsdPath);
        String outputPath = "src/";
        try {
            XMLUtils.generateClass(packageName, builtWithSchemaFile, outputPath);
            XMLUtils.generateClass(packageName, alexaSchemaFile, outputPath);
        } catch (SAXException | IOException e) {
            Logger.getLogger(SiteKick.class.getName()).log(Level.SEVERE, e.getMessage());
        }

    }
}
