/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prx.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.fail;
import org.junit.Test;
import prx.constant.HttpContentTypeConstant;

/**
 *
 * @author Gia Bảo Hoàng
 */
public class HttpUtilsTest {

    public HttpUtilsTest() {
    }

    /**
     * Test of getContent method, of class HttpUtils.
     */
    @Test
    public void testGetContent() throws IOException {
        String result = null;
        List<String> urls = new ArrayList();
        urls.add("https://www.alexa.com/topsites/category/Top/Home");
        for (String link : urls) {
            result = HttpUtils.getContent(link, HttpContentTypeConstant.TEXT_HTML);
            result = TextUtils.refineHtml(result);
            boolean isWellform=XMLUtils.isWellformXML(result);
            PrintWriter pw = new PrintWriter("src/test/alexa_category" + ".html");
            pw.print(result);
            pw.close();
            if (null == result || result.isEmpty()) {
                fail("GET Content Failed!!");
            }
            if(!isWellform){
                fail("Not Well form");
            }
        }
    }

}
