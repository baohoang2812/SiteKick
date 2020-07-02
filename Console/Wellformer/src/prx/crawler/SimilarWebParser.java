/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prx.crawler;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import prx.utils.XMLUtils;

/**
 *
 * @author Gia Bảo Hoàng
 */
public class SimilarWebParser extends Parser {

    private Set<String> pageDetailLinkSet;
    private String urlXPath;
    private int pageCount;

    public SimilarWebParser(Set<String> pageDetailLinkSet, String expression) {
        this.pageDetailLinkSet = pageDetailLinkSet;
        this.urlXPath = expression;
    }

    public SimilarWebParser() {
        this.pageDetailLinkSet = new HashSet();
    }

    public Set<String> getPageDetailLinkSet() {
        return pageDetailLinkSet;
    }

    public void setPageDetailLinkSet(Set<String> pageDetailLinkSet) {
        this.pageDetailLinkSet = pageDetailLinkSet;
    }

    public String getExpression() {
        return urlXPath;
    }

    public void setExpression(String expression) {
        this.urlXPath = expression;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    // TODO change to parse All Category
    private void parseAllPageCount() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
        for (int pageIndex = 0; pageIndex <= pageCount; pageIndex++) {
            String pageContent = preprocessPageContent(constructLink());
            Document doc = XMLUtils.parseStringToDOM(pageContent);
            retrieveDetailLinks(doc, this.urlXPath);
        }
    }

    // TODO Test parsePageDetail
    private void parsePageDetail() {
        System.out.println("Parsing Page " + baseURL + " Detail");
        for (String link : pageDetailLinkSet) {
            try {
                String content = preprocessPageContent(link);
                // TODO apply xslt from HTML to XML
                // TODO output of apply is only 1 list items not 1 item/ xml
                XMLUtils.transformFromString("", content);
            } catch (IOException | TransformerException e) {
                System.out.println("!!! Parsing Page Detail ERROR !!!");
                System.out.println("Page: " + link);
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    // TODO change Name
    // Get all links in page
    private void retrieveDetailLinks(Document doc, String expression) throws XPathExpressionException {
        XPath xpath = XMLUtils.getXPath();
        // list of attribute nodes
        NodeList linkList = (NodeList) xpath.evaluate(expression, doc, XPathConstants.NODESET);
        int i = 0;
        while (i < linkList.getLength()) {
            // TODO check for position
            pageDetailLinkSet.add(linkList.item(i).getNodeValue());
            i++;
        }
    }

  

}
