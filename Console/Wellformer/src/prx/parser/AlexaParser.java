/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prx.parser;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import prx.config.SystemConfig;
import prx.constant.CommonConstant;
import prx.dao.CategoryDAO;
import prx.dao.IGenericDAO;
import prx.dao.SiteDAO;
import prx.data.Site;
import prx.entity.EntityContext;
import prx.map.SiteMap;
import prx.utils.XMLUtils;

/**
 *
 * @author Gia Bảo Hoàng
 */
public class AlexaParser extends Parser {

    private Set<String> pageDetailLinkSet;
    private Set<String> categoryLinkSet;
    private Set<String> domainSet;
    private String categoryXPath;
    private String domainXPath;

    public AlexaParser() {
        this.pageDetailLinkSet = new HashSet();
        this.categoryLinkSet = new HashSet();
        this.domainSet = new HashSet<>();
    }

    public AlexaParser(Set<String> pageDetailLinkSet, Set<String> categoryLinkSet, Set<String> domainSet, String categoryXPath, String domainXPath, String baseURL, String navigationPath, String urlXPath, String xslPath, String xsdPath) {
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
            Logger.getLogger(AlexaParser.class.getName()).log(Level.SEVERE, e.getMessage());
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
                //TODO parse Page Details in Category & pass category name to XSL
                String category = getCategoryName(categoryPath);
                // insert Category
                EntityContext context = EntityContext.newInstance();
                EntityManager em = context.getEntityManager();
                try {
                    context.beginTransaction();
                    CategoryDAO categoryDAO = new CategoryDAO(em);
                    categoryDAO.createCategory(category);
                    context.commitTransaction();
                } catch (Exception e) {
                    Logger.getLogger(AlexaParser.class.getName()).log(Level.SEVERE, e.getMessage());
                    context.rollBackTransaction();
                }// end insert Category

                // get Transformer
                Transformer transformer = XMLUtils.getTransformer(xslPath);
                transformer.setParameter("categoryName", category);
                List<Site> siteList = parsePageSet(Site.class, pageDetailLinkSet, transformer);
                insertSiteList(siteList);
                // pageDetailLinkSet = new HashSet();
                System.out.println("Finish parsing page: " + constructLink(categoryPath));
            } catch (IOException | XPathExpressionException | ParserConfigurationException | SAXException | TransformerConfigurationException e) {
                System.out.println("!!!Parsing Category Page ERROR!!! " + constructLink(categoryPath));
                Logger.getLogger(AlexaParser.class.getName()).log(Level.SEVERE, e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private String getCategoryName(String categoryPath) {
        return categoryPath.substring(categoryPath.lastIndexOf(CommonConstant.SLASH) + CommonConstant.POSITION_GAP);
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
        System.out.println("Finish parsing " + baseURL);
        System.out.println("================================================");
    }

    private void insertSiteList(List<Site> siteList) {
        EntityContext context = EntityContext.newInstance();
        try {
            SiteDAO siteDAO = new SiteDAO(context.getEntityManager());
            context.beginTransaction();
            SiteMap siteMap = new SiteMap();
            List<prx.entity.Site> entityList = siteMap.mapList(siteList);
            siteDAO.create(entityList);
        } catch (Exception e) {
            Logger.getLogger(AlexaParser.class.getName()).log(Level.SEVERE, e.getMessage());
            context.rollBackTransaction();
        }
    }
}
