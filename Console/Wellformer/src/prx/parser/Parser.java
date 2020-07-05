/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prx.parser;

import data.Site;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;
import prx.constant.CommonConstant;
import prx.utils.HttpUtils;
import prx.utils.TextUtils;
import prx.utils.XMLUtils;

/**
 *
 * @author Gia Bảo Hoàng
 */
public class Parser {

    protected String baseURL;
    protected String navigationPath;
    protected String urlXPath;
    protected String xslPath;
    protected String xsdPath;

    public Parser() {
    }

    public Parser(String baseURL, String navigationPath, String urlXPath, String xslPath, String xsdPath) {
        this.baseURL = baseURL;
        this.navigationPath = navigationPath;
        this.urlXPath = urlXPath;
        this.xslPath = xslPath;
        this.xsdPath = xsdPath;
    }

    public String getBaseURL() {
        return baseURL;
    }

    public String getBaseUrl() {
        return baseURL;
    }

    public String getNavigationPath() {
        return navigationPath;
    }

    public void setNavigationPath(String navigationPath) {
        this.navigationPath = navigationPath;
    }

    public String getUrlXPath() {
        return urlXPath;
    }

    public void setUrlXPath(String urlXPath) {
        this.urlXPath = urlXPath;
    }

    public String getXslPath() {
        return xslPath;
    }

    public void setXslPath(String xslPath) {
        this.xslPath = xslPath;
    }

    public void setBaseURL(String baseURL) {
        this.baseURL = baseURL;
    }

    public String getXsdPath() {
        return xsdPath;
    }

    public void setXsdPath(String xsdPath) {
        this.xsdPath = xsdPath;
    }

    public void parse() throws IOException {
    }

    public String preprocessPageContent(String url) throws IOException {
        String pageContent = null;
        pageContent = HttpUtils.getContent(url);
        return TextUtils.refineHtml(pageContent);
    }

    public String constructLink(String param) {
        String navPath = getSlashPrefixPath(navigationPath);
        String paramPath = isNullOrEmpty(param) ? CommonConstant.EMPTY : getSlashPrefixPath(param);
        String result = baseURL + navPath + paramPath;
        result = result.replaceAll(CommonConstant.UNDERSCORE_STRING, CommonConstant.HYPHEN_STRING);
        return result;
    }

    public boolean isStartWithSlash(String path) {
        return path.startsWith(CommonConstant.SLASH_STRING);
    }

    public String getSlashPrefixPath(String path) {
        return isStartWithSlash(path) ? path : CommonConstant.SLASH_STRING + path;
    }

    private boolean isNullOrEmpty(String path) {
        return null == path || path.isEmpty();
    }

    protected <T> List<T> parsePageDetail(String link, Class<T> klass) {
        System.out.println("Parsing Page " + baseURL + " Detail");
        List<T> dataList = new ArrayList();
        try {
            String content = preprocessPageContent(link);
            // Transform
            String xmlContent = XMLUtils.transformFromString(xslPath, content);
            // Validate XML with Schema
            boolean isValid = XMLUtils.isXMLValidate(xsdPath, xmlContent);
            // JAXB
            if (isValid) {
                T data = XMLUtils.unmarshall((Class<T>) klass.getClass(), xmlContent);
                dataList.add(data);
            } else {
                // TODO do what when invalid
                System.out.println("INVALID XML");
            }

        } catch (IOException | TransformerException | JAXBException e) {
            System.out.println("!!! Parsing Page Detail ERROR !!!");
            System.out.println("Page: " + link);
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage());
        }
        return dataList;
    }

    //TODO load To Database JPA
    protected <T> void loadToDatabase(List<T> dataList) {
        List<T> result = dataList;
    }

    protected <T> void parsePageSet(Class<T> klass, Set<String> linkSet) {
        List<T> dataList = null;
        for (String link : linkSet) {
            dataList = parsePageDetail(link, (Class<T>) klass.getClass());
        }
        // JPA 
        if (null != dataList && !dataList.isEmpty()) {
            loadToDatabase(dataList);
        }
    }

}
