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
import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import prx.config.SystemConfig;
import prx.thread.BaseThread;
import prx.utils.XMLUtils;

/**
 *
 * @author Gia Bảo Hoàng
 */
public class SimilarWebParser extends BaseParser implements Runnable {

    private Set<String> categoryLinkSet;
    private Set<String> domainSet;
    private String domainXPath;

    public SimilarWebParser() {
        this.categoryLinkSet = new HashSet();
        this.domainSet = new HashSet<>();
    }

    public SimilarWebParser(Set<String> categoryLinkSet, Set<String> domainSet, String domainXPath, String baseURL, String navigationPath, String urlXPath, String xslPath, String xsdPath) {
        super(baseURL, navigationPath, urlXPath, xslPath, xsdPath);
        this.categoryLinkSet = categoryLinkSet;
        this.domainSet = domainSet;
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

    private Set<String> parseCategory() {
        Set<String> siteDetailLinkSet = new HashSet();
        for (String categoryPath : categoryLinkSet) {
            try {
                System.out.println("Parsing Category: " + constructLink(categoryPath));
                String categoryPageContent = preprocessPageContent(constructLink(categoryPath));
                Document document = XMLUtils.parseStringToDOM(categoryPageContent);
                // get site Detail URLs
                retrieveLinks(document, urlXPath, siteDetailLinkSet);
                // get site Domain Set
                retrieveLinks(document, domainXPath, domainSet);
                System.out.println("Finish parsing page: " + constructLink(categoryPath));
            } catch (IOException | XPathExpressionException | ParserConfigurationException | SAXException e) {
                System.out.println("!!!Parsing Category Page ERROR!!! " + constructLink(categoryPath));
                Logger.getLogger(SimilarWebParser.class.getName()).log(Level.SEVERE, e.getMessage());
                e.printStackTrace();
            }
        }
        return siteDetailLinkSet;
    }

    @Override
    public void parse() {
        System.out.println("================================================");
        System.out.println("Parsing " + baseURL + " . . .");
        System.out.println("Getting Page " + baseURL + " category list . . .");
        //getCategories()
//        getCategoryItemLinks();
//        parsePageSet(Site.class, pageDetailLinkSet);
        System.out.println("Finish parsing " + baseURL);
        System.out.println("================================================");
    }

    @Override
    public void run() {
        //TODO save category to DB
        try {
            synchronized (BaseThread.getInstance()) {
                while (BaseThread.isSuspended()) {
                    BaseThread.getInstance().wait();
                }
            }
        } catch (InterruptedException e) {
            Logger.getLogger(SimilarWebParser.class.getName()).log(Level.SEVERE, e.getMessage());
        }
        Set<String> siteDetailLinkSet = parseCategory();
        for (String detailLink : siteDetailLinkSet) {
            Thread detailThread = createDetailPageParserThread(detailLink);
            detailThread.start();
            try {
                synchronized (BaseThread.getInstance()) {
                    while (BaseThread.isSuspended()) {
                        BaseThread.getInstance().wait();
                    }
                }
            } catch (InterruptedException e) {
                Logger.getLogger(SimilarWebParser.class.getName()).log(Level.SEVERE, e.getMessage());
            }
        }

    }

    private Thread createDetailPageParserThread(String url) {
        SimilarWebDetailParser detailPageParser = new SimilarWebDetailParser();
        // set prop
        detailPageParser.setBaseURL(SystemConfig.SIMILAR_WEB_BASE_URL);
        detailPageParser.setNavigationPath(SystemConfig.SITE_DETAIL_NAVIGATION_PATH);
        detailPageParser.setXsdPath(SystemConfig.SITE_XSD_PATH);
        detailPageParser.setXslPath(SystemConfig.SITE_XSL_PATH);
        detailPageParser.setDetailURL(constructLink(url));
        return new Thread(detailPageParser);
    }

}
