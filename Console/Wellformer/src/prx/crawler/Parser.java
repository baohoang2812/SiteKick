/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prx.crawler;

import java.io.IOException;
import prx.utils.HttpUtils;
import prx.utils.TextUtils;

/**
 *
 * @author Gia Bảo Hoàng
 */
public class Parser {

    protected String baseURL;
    protected String navigationPath;

    public Parser() {
    }

    public String getBaseURL() {
        return baseURL;
    }

    public String getBaseUrl() {
        return baseURL;
    }

    public void setBaseURL(String baseURL) {
        this.baseURL = baseURL;
    }

    public String getNavigationPath() {
        return navigationPath;
    }

    public void setNavigationPath(String navigationPath) {
        this.navigationPath = navigationPath;
    }

    public void parse() throws IOException {
    }

    public String preprocessPageContent(String url) throws IOException {
        String pageContent = null;
        pageContent = HttpUtils.getContent(url);
        return TextUtils.refineHtml(pageContent);
    }

    public String constructLink() {
        return baseURL + navigationPath;
    }

}
