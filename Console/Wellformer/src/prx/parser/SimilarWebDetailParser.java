/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prx.parser;

import data.Site;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Gia Bảo Hoàng
 */
public class SimilarWebDetailParser extends BaseParser implements Runnable {

    private String detailURL;

    public SimilarWebDetailParser() {
    }

    public SimilarWebDetailParser(String detailURL, String baseURL, String navigationPath, String urlXPath, String xslPath, String xsdPath) {
        super(baseURL, navigationPath, urlXPath, xslPath, xsdPath);
        this.detailURL = detailURL;
    }

    public String getDetailURL() {
        return detailURL;
    }

    public void setDetailURL(String detailURL) {
        this.detailURL = detailURL;
    }

    @Override
    public void parse() throws IOException {
//        parsePageSet(Site.class, pageDetailLinkSet);

    }

    @Override
    public void run() {
        //TODO
        List<Site> siteList = parsePageDetail(detailURL, Site.class);
        // load to DB
    }

}
