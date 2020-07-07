/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prx.config;

/**
 *
 * @author Gia Bảo Hoàng
 */
public class SystemConfig {

    public static final int TIME_OUT = 30000;
    public static final String SIMILAR_WEB_BASE_URL = "https://www.similarweb.com";
    public static final String BUILT_WITH__BASE_URL = "https://builtwith.com";

    public static final String SITE_XSL_PATH = "src/prx/xsl/similarwebStyleSheet.xsl";
    public static final String TECH_XSL_PATH = "src/prx/xsl/builtwithStyleSheet.xsl";
    public static final String SITE_XSD_PATH = "src/prx/schema/siteSchema.xsd";
    public static final String TECH_XSD_PATH = "src/prx/schema/builtwithSchema.xsd";

    public static final String SITE_ALL_CATEGORY_NAVIGATION_PATH = "/top-websites";
    public static final String SITE_CATEGORY_NAVIGATION_PATH = "/top-websites/category";
    public static final String SITE_DETAIL_NAVIGATION_PATH = "/website";

    public static final String SITE_DETAIL_URL_XPATH = "//td[contains(@class,'topWebsitesGrid-cellWebsite')]//*[@itemprop='url' and @data-analytics-category='Internal Link']/@href";
    public static final String SITE_DOMAIN_XPATH = "//td[contains(@class,'topWebsitesGrid-cellWebsite')]//*[@itemprop='url' and contains(@class,'linkout')]/@href";
    public static final String SITE_CATEGORY_XPATH = "//*[contains(@class,'topRankingHeader-dropdownItem')]"
            + "//select[contains(@class,'category')]/option[not(contains(@data-group-parent,'true')) and not(contains(@value,'ALL'))]/@value";
    
    public static final int BREAK_TIME_CRAWLING = 10;
    public static final String PU_NAME = "CrawlerPU";
}
