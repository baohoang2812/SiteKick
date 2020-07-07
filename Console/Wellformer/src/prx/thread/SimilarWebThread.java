/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prx.thread;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import prx.config.SystemConfig;
import prx.parser.SimilarWebCategoriesParser;
import prx.parser.SimilarWebParser;

/**
 *
 * @author Gia Bảo Hoàng
 */
public class SimilarWebThread extends BaseThread implements Runnable {

    @Override
    public void run() {
        while (true) {
            try {
                Set<String> categoriesLink = getCategoriesLink();
                for (String link : categoriesLink) {
                    // category
                    SimilarWebParser categoryParser = new SimilarWebParser();
                    categoryParser.setNavigationPath(SystemConfig.SITE_CATEGORY_NAVIGATION_PATH);
                    categoryParser.setBaseURL(SystemConfig.SIMILAR_WEB_BASE_URL);
                    categoryParser.setDomainXPath(SystemConfig.SITE_DOMAIN_XPATH);
                    categoryParser.setUrlXPath(SystemConfig.SITE_DETAIL_URL_XPATH);
                    Thread categoryParserThread = new Thread(categoryParser);
                    categoryParserThread.start();

                    synchronized (BaseThread.getInstance()) {
                        while (BaseThread.isSuspended()) {
                            BaseThread.getInstance().wait();
                        }
                    }// End parsing each category
                }
                //set wait time
//                    SimilarWebThread.sleep(TimeUnit.DAYS.toMillis(SystemConfig.BREAK_TIME_CRAWLING));
                synchronized (BaseThread.getInstance()) {
                    while (BaseThread.isSuspended()) {
                        BaseThread.getInstance().wait();
                    }
                }
            } catch (InterruptedException e) {
                Logger.getLogger(SimilarWebThread.class.getName()).log(Level.SEVERE, e.getMessage());
            }
        }
    }

    private Set<String> getCategoriesLink() {
        SimilarWebCategoriesParser categoriesParser = new SimilarWebCategoriesParser();
        categoriesParser.setBaseURL(SystemConfig.SIMILAR_WEB_BASE_URL);
        categoriesParser.setCategoryXPath(SystemConfig.SITE_CATEGORY_XPATH);
        categoriesParser.setNavigationPath(SystemConfig.SITE_ALL_CATEGORY_NAVIGATION_PATH);
        return categoriesParser.getCategories();
    }
}
