/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prx.utils;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.ScriptException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.javascript.JavaScriptErrorListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import prx.config.SystemConfig;

/**
 *
 * @author Gia Bảo Hoàng
 */
public class HttpUtils {

    private static InputStream getInputStream(String urlString) throws MalformedURLException, IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "text/html");
        connection.setRequestProperty("Content-Language", "en-US");
        connection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) "
                + "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36");
        connection.setConnectTimeout(SystemConfig.TIME_OUT);
        connection.setReadTimeout(SystemConfig.TIME_OUT);
        connection.setUseCaches(false);
        return connection.getInputStream();
    }

    public static String getContent(String urlString) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        try (InputStreamReader isr = new InputStreamReader(getInputStream(urlString), StandardCharsets.UTF_8);
                BufferedReader bufferedReader = new BufferedReader(isr)) {
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            return stringBuilder.toString();
        }
    }

    // htmlUnit
    public static WebClient getHtmlUnitClient() {
        WebClient webClient;
        Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.SEVERE);
        Logger.getLogger("org.apache.http").setLevel(Level.SEVERE);
        webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(false);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.setJavaScriptErrorListener(new JavaScriptErrorListener() {
            @Override
            public void scriptException(HtmlPage hp, ScriptException se) {
                System.out.println("Script Exception: " + se.getMessage());
            }

            @Override
            public void timeoutError(HtmlPage hp, long l, long l1) {
                System.out.println("Time out");
            }

            @Override
            public void malformedScriptURL(HtmlPage hp, String string, MalformedURLException murle) {
                System.out.println("MalformedURLException: " + murle.getMessage());
            }

            @Override
            public void loadScriptError(HtmlPage hp, URL url, Exception excptn) {
                System.out.println("Load script error: " + excptn.getMessage());
            }

            @Override
            public void warn(String string, String string1, int i, String string2, int i1) {
            }
        });
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
        return webClient;
    }

    public static String getPageContent(String url) throws IOException {
        WebClient client = getHtmlUnitClient();
        client.waitForBackgroundJavaScript(5000);
        HtmlPage page = client.getPage(url);
//        WebResponse response = page.getWebResponse();
//        return response.getContentAsString();
//        JavaScriptJobManager manager = page.getEnclosingWindow().getJobManager();
//        while (manager.getJobCount() > 0) {
//            try {
//                Thread.sleep(10000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        return page.asXml();
    }
}
