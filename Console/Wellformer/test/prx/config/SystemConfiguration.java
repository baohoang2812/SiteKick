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
public class SystemConfiguration {

    public static final String ALEXA_BASE_URL = "https://www.alexa.com";
    public static final String BUILT_WITH_BASE_URL = "https://builtwith.com";

    public static final String SITE_XSL_PATH = "src/prx/xsl/alexaStyleSheet.xsl";
    public static final String SITE_XSD_PATH = "src/prx/schema/siteSchema.xsd";

    public static final String TECH_XSL_PATH = "src/prx/xsl/builtwithStyleSheet.xsl";
    public static final String TECH_XSD_PATH = "src/prx/schema/builtwithSchema.xsd";

    public static final String SITE_ALL_CATEGORY_NAVIGATION_PATH = "/topsites/category";
    public static final String SITE_CATEGORY_NAVIGATION_PATH = "";

    public static final String SITE_DETAIL_URL_XPATH = "//*[contains(@class,'listings')]//*[contains(@class,'DescriptionCell')]//a/@href";
    public static final String SITE_DOMAIN_XPATH = "//*[contains(@class,'listings')]//*[contains(@class,'DescriptionCell')]//a/text()";
    public static final String SITE_CATEGORY_XPATH = "//*[@class='categories top']//li/a/@href";
}
