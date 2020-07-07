/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prx.parser;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import prx.utils.XMLUtils;

/**
 *
 * @author Gia Bảo Hoàng
 */
public class SimilarWebCategoriesParser extends BaseParser {

    private String categoryXPath;

    public SimilarWebCategoriesParser() {
    }

    public SimilarWebCategoriesParser(String categoryXPath, String baseURL, String navigationPath, String urlXPath, String xslPath, String xsdPath) {
        super(baseURL, navigationPath, urlXPath, xslPath, xsdPath);
        this.categoryXPath = categoryXPath;
    }

    
    public String getCategoryXPath() {
        return categoryXPath;
    }

    public void setCategoryXPath(String categoryXPath) {
        this.categoryXPath = categoryXPath;
    }

    public Set<String> getCategories() {
        Set<String> categoryLinkSet = new HashSet();
        String homePage = constructLink(null);
        try {
            String homePageContent = preprocessPageContent(homePage);
            Document doc = XMLUtils.parseStringToDOM(homePageContent);
            retrieveLinks(doc, categoryXPath, categoryLinkSet);
        } catch (IOException | XPathExpressionException | ParserConfigurationException | SAXException e) {
            System.out.println("!!!Parsing Home Page ERROR!!!");
            Logger.getLogger(SimilarWebParser.class.getName()).log(Level.SEVERE, e.getMessage());
            e.printStackTrace();
        }
        return categoryLinkSet;
    }

}
