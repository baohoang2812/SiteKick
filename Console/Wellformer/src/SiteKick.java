/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.xml.sax.SAXException;
import prx.config.SystemConfig;
import prx.constant.CommonConstant;
import prx.parser.BuiltWithParser;
import prx.parser.SimilarWebParser;
import prx.utils.HttpUtils;
import prx.utils.TextUtils;
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
        generateCode();
        Set<String> domainSet = parseSimilarWeb();
//        parseBuiltWith(domainSet);
    }

    public static Set<String> parseSimilarWeb() {
        SimilarWebParser similarWebParser = new SimilarWebParser();
        similarWebParser.setCategoryXPath(SystemConfig.SITE_CATEGORY_XPATH);
        similarWebParser.setUrlXPath(SystemConfig.SITE_DETAIL_URL_XPATH);
        similarWebParser.setBaseURL(SystemConfig.SIMILAR_WEB_BASE_URL);
        similarWebParser.setXslPath(SystemConfig.SITE_XSL_PATH);
        similarWebParser.setXsdPath(SystemConfig.SITE_XSD_PATH);
        similarWebParser.setNavigationPath(SystemConfig.SITE_ALL_CATEGORY_NAVIGATION_PATH);
        similarWebParser.setDomainXPath(SystemConfig.SITE_DOMAIN_XPATH);
        similarWebParser.parse();
        return similarWebParser.getDomainSet();
    }

    public static void parseBuiltWith(Set<String> domainSet) {
        BuiltWithParser builtWithParser = new BuiltWithParser();
        builtWithParser.setBaseURL(SystemConfig.BUILT_WITH__BASE_URL);
        builtWithParser.setXsdPath(SystemConfig.TECH_XSD_PATH);
        builtWithParser.setXslPath(SystemConfig.TECH_XSL_PATH);
        builtWithParser.setNavigationPath(CommonConstant.EMPTY);
        builtWithParser.setLinkSet(domainSet);
        builtWithParser.parse();
    }

    public static void generateCode() {
        String packageName = "data";
        String builtWithXsdPath = SystemConfig.TECH_XSD_PATH;
        String similarWebXsdPath = SystemConfig.SITE_XSD_PATH;
        File builtWithSchemaFile = new File(builtWithXsdPath);
        File similarWebSchemaFile = new File(similarWebXsdPath);
        String outputPath = "src/prx/";
        try {
            XMLUtils.generateClass(packageName, builtWithSchemaFile, outputPath);
            XMLUtils.generateClass(packageName, similarWebSchemaFile, outputPath);
        } catch (SAXException | IOException e) {
            Logger.getLogger(SiteKick.class.getName()).log(Level.SEVERE, e.getMessage());
        }

    }

    public static void testOutput() {
        System.out.println("Crawling data...");
        List<String> urlList = new ArrayList();
//        urlList.add("https://www.similarweb.com/website/bilibili.com/");
        urlList.add("https://builtwith.com/bilibili.com");
        String pageContent = null;
        try {
            for (int i = 0; i < urlList.size(); i++) {
                pageContent = HttpUtils.getContent(urlList.get(i));
                // test output
                // [BHG] TODO Delete on finish project
                pageContent = TextUtils.refineHtml(pageContent);
//                boolean check = checkWellformXML(pageContent);
//                if (check) {
//                    System.out.println("Well-formed");
//
//                } else {
//                    System.out.println("Unwell-form");
//                }
//                PrintWriter pw = new PrintWriter("src/test/builtwith_" + "bilibili" + ".html");
//                pw.print(pageContent);
//                pw.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Crawling finished...");
    }
}
