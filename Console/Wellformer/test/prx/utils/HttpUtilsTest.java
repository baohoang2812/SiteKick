/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prx.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import static org.junit.Assert.fail;
import org.junit.Test;

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
        urls.add("https://www.similarweb.com/top-websites/category/finance/insurance");
        urls.add("https://www.similarweb.com/top-websites/category/hobbies-and-leisure/photography");
        for (String link : urls) {
            result = HttpUtils.getContent(link);
            result = TextUtils.refineHtml(result);
            if (null == result || result.isEmpty()) {
                fail("GET Content Failed!!");
            }
        }
    }

}
