/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prx.parser;

import data.Site;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import prx.config.SystemConfig;
import prx.utils.XMLUtils;

/**
 *
 * @author Gia Bảo Hoàng
 */
public class SimilarWebParser extends Parser {

    private Set<String> pageDetailLinkSet;
    private Set<String> categoryLinkSet;
    private Set<String> domainSet;
    private String categoryXPath;
    private String domainXPath;

    public SimilarWebParser() {
        this.pageDetailLinkSet = new HashSet();
        this.categoryLinkSet = new HashSet();
        this.domainSet = new HashSet<>();
    }

    public SimilarWebParser(Set<String> pageDetailLinkSet, Set<String> categoryLinkSet, Set<String> domainSet, String categoryXPath, String domainXPath, String baseURL, String navigationPath, String urlXPath, String xslPath, String xsdPath) {
        super(baseURL, navigationPath, urlXPath, xslPath, xsdPath);
        this.pageDetailLinkSet = pageDetailLinkSet;
        this.categoryLinkSet = categoryLinkSet;
        this.domainSet = domainSet;
        this.categoryXPath = categoryXPath;
        this.domainXPath = domainXPath;
    }

    public Set<String> getDomainSet() {
        return domainSet;
    }

    public void setDomainSet(Set<String> domainSet) {
        this.domainSet = domainSet;
    }

    public String getDomainXPath() {
        return domainXPath;
    }

    public void setDomainXPath(String domainXPath) {
        this.domainXPath = domainXPath;
    }

    public Set<String> getCategoryLinkSet() {
        return categoryLinkSet;
    }

    public void setCategoryLinkSet(Set<String> categoryLinkSet) {
        this.categoryLinkSet = categoryLinkSet;
    }

    public Set<String> getPageDetailLinkSet() {
        return pageDetailLinkSet;
    }

    public void setPageDetailLinkSet(Set<String> pageDetailLinkSet) {
        this.pageDetailLinkSet = pageDetailLinkSet;
    }

    public String getCategoryXPath() {
        return categoryXPath;
    }

    public void setCategoryXPath(String categoryXPath) {
        this.categoryXPath = categoryXPath;
    }

    private void parseAllCategory() {
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

        this.setNavigationPath(SystemConfig.SITE_CATEGORY_NAVIGATION_PATH);
        for (String categoryPath : categoryLinkSet) {
            try {
                System.out.println("Parsing Page: " + constructLink(categoryPath));
                String categoryPageContent = preprocessPageContent(constructLink(categoryPath));
                Document document = XMLUtils.parseStringToDOM(categoryPageContent);
                // get site Detail URLs
                retrieveLinks(document, urlXPath, pageDetailLinkSet);
                // get site Domain Set
                retrieveLinks(document, domainXPath, domainSet);
                System.out.println("Finish parsing page: " + constructLink(categoryPath));
            } catch (IOException | XPathExpressionException | ParserConfigurationException | SAXException e) {
                System.out.println("!!!Parsing Category Page ERROR!!! " + constructLink(categoryPath));
                Logger.getLogger(SimilarWebParser.class.getName()).log(Level.SEVERE, e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private void retrieveLinks(Document document, String expression, Set<String> linkSet)
            throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {

        XPath xpath = XMLUtils.getXPath();
        // list of attribute nodes
        NodeList linkList = (NodeList) xpath.evaluate(expression, document, XPathConstants.NODESET);
        int i = 0;
        while (i < linkList.getLength()) {
            linkSet.add(linkList.item(i).getNodeValue());
            i++;
        }
    }

    @Override
    public void parse() {
        System.out.println("================================================");
        System.out.println("Parsing " + baseURL + " . . .");
        System.out.println("Getting Page " + baseURL + " category list . . .");
        parseAllCategory();
        parsePageSet(Site.class, pageDetailLinkSet);
        System.out.println("Finish parsing " + baseURL);
        System.out.println("================================================");
    }

}
