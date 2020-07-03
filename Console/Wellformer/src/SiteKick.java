/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import prx.utils.HttpUtils;
import prx.utils.TextUtils;

/**
 *
 * @author Eden
 */
public class SiteKick {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
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
                boolean check = checkWellformXML(pageContent);
                if (check) {
                    System.out.println("Well-formed");

                } else {
                    System.out.println("Unwell-form");
                }
//                PrintWriter pw = new PrintWriter("src/test/builtwith_" + "bilibili" + ".html");
//                pw.print(pageContent);
//                pw.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Crawling finished...");

    }
    //[BHG] TODO consider remove

    public static boolean checkWellformXML(String src) {
        // use DocumentBuilder + SAX parser to check 
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(false);
        factory.setNamespaceAware(true);

        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            return false;
        }
        // TODO[BHG, refactor ErrorHandler]
        builder.setErrorHandler(new ErrorHandler() {
            @Override
            public void warning(SAXParseException exception) throws SAXException {
                System.out.println(exception.getMessage());
            }

            @Override
            public void error(SAXParseException exception) throws SAXException {
                System.out.println(exception.getMessage());
            }

            @Override
            public void fatalError(SAXParseException exception) throws SAXException {
                System.out.println(exception.getMessage());
            }
        });
        try {
            builder.parse(new ByteArrayInputStream(src.getBytes(StandardCharsets.UTF_8)));
            return true;
        } catch (SAXException | IOException e) {
            return false;
        }
    }

}
