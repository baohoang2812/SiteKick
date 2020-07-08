/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prx.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import org.junit.Test;
import static org.junit.Assert.*;
import prx.config.SystemConfig;
import prx.data.Site;
import prx.data.TechStack;

/**
 *
 * @author Gia Bảo Hoàng
 */
public class XMLUtilsTest {

    public XMLUtilsTest() {
    }

    /**
     * Test of parseStringToDOM method, of class XMLUtils.
     */
    @Test
    public void testParseStringToDOM() throws Exception {
//        System.out.println("parseStringToDOM");
//        String content = "";
//        Document expResult = null;
//        Document result = XMLUtils.parseStringToDOM(content);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getXPath method, of class XMLUtils.
     */
    @Test
    public void testGetXPath() {
//        System.out.println("getXPath");
//        XPath expResult = null;
//        XPath result = XMLUtils.getXPath();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of TransformDOMToFile method, of class XMLUtils.
     */
    @Test
    public void testTransformDOMToFile() throws Exception {
//        System.out.println("TransformDOMToFile");
//        Node node = null;
//        String filePath = "";
//        XMLUtils.TransformDOMToFile(node, filePath);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of generateClass method, of class XMLUtils.
     */
    @Test
    public void testGenerateClass() throws Exception {
//        System.out.println("Test Generate Class");
//        String packageName = "prx.data";
//        String builtWithXsdPath = "src/prx/schema/builtwithSchema.xsd";
//        String similarWebXsdPath = "src/prx/schema/siteSchema.xsd";
//        File builtWithSchemaFile = new File(builtWithXsdPath);
//        File similarWebSchemaFile = new File(similarWebXsdPath);
//        String outputPath = "src/";
//        XMLUtils.generateClass(packageName, builtWithSchemaFile, outputPath);
//        XMLUtils.generateClass(packageName, similarWebSchemaFile, outputPath);
    }

    /**
     * Test of unmarshall method, of class XMLUtils.
     */
    @Test
    public void testUnmarshall_Site() throws Exception {
        System.out.println("Test XML Unmarshall Alexa");
        String pageContent = HttpUtils.getContent("https://www.alexa.com/siteinfo/tuoitre.vn");
        pageContent = TextUtils.refineHtml(pageContent);
        Transformer transformer = XMLUtils.getTransformer(SystemConfig.SITE_XSL_PATH);
        transformer.setParameter("categoryName", "News");
        String xml = XMLUtils.transformFromString(transformer, pageContent);
        String xsdPath = SystemConfig.SITE_XSD_PATH;
        boolean validate = XMLUtils.isXMLValidate(xsdPath, xml);
        if (validate) {
            try {
                Site site = XMLUtils.unmarshall(Site.class, xml);
                assertNotEquals(site, null);
            } catch (Exception e) {
                e.printStackTrace();
                PrintWriter pw = new PrintWriter("src/test/alexa_detail_test" + ".xml");
                pw.print(pageContent);
                pw.close();
                fail("Unmarshall Exception");
            }
        } else {
            PrintWriter pw = new PrintWriter("src/test/alexa_detail_test" + ".xml");
            pw.print(pageContent);
            pw.close();
            fail("Not validate");
        }
    }

    /**
     * Test of marshall method, of class XMLUtils.
     */
    @Test
    public void testMarshall_BuiltWith() throws Exception {
        System.out.println("Test XML Unmarshall BuiltWith");
        String pageContent = HttpUtils.getContent("https://builtwith.com/tuoitre.vn");
        pageContent = TextUtils.refineHtml(pageContent);
        Transformer transformer = XMLUtils.getTransformer(SystemConfig.TECH_XSL_PATH);
        String xml = XMLUtils.transformFromString(transformer, pageContent);
        String xsdPath = SystemConfig.TECH_XSD_PATH;
        boolean validate = XMLUtils.isXMLValidate(xsdPath, xml);
        if (validate) {
            TechStack techStack = XMLUtils.unmarshall(TechStack.class, xml);
            assertNotEquals(techStack, null);
        } else {
            PrintWriter pw = new PrintWriter("src/test/builtwith_detail_test" + ".xml");
            pw.print(pageContent);
            pw.close();
            fail("Not validate");
        }
    }

    /**
     * Test of transformXML method, of class XMLUtils.
     */
    @Test
    public void testTransformXML() throws Exception {
//        System.out.println("transformXML");
//        String xslPath = "";
//        String xmlPath = "";
//        String outputPath = "";
//        XMLUtils.transformXML(xslPath, xmlPath, outputPath);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of transformFromString method, of class XMLUtils.
     */
    @Test
    public void testTransformFromString() throws Exception {
//        System.out.println("transformFromString");
//        String xslPath = "";
//        String content = "";
//        String expResult = "";
//        String result = XMLUtils.transformFromString(xslPath, content);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of isXMLValidate method, of class XMLUtils.
     */
    @Test
    public void testIsXMLValidate_BuiltWith() {
        System.out.println("Test XML Validate Built With");
        String xsdPath = "src/prx/schema/builtwithSchema.xsd";
        String xmlPath = "src/test/builtwith_bilibili_output.xml";
        boolean expResult = true;
        boolean result = XMLUtils.isXMLValidateFromFilePath(xsdPath, xmlPath);
        assertEquals(expResult, result);
    }

    @Test
    public void testIsXMLValidate_SimilarWeb() {
        System.out.println("Test XML Validate Similar Web");
        String xsdPath = "src/prx/schema/siteSchema.xsd";
        String xmlPath = "src/test/site_bilibili_output.xml";
        boolean expResult = true;
        boolean result = XMLUtils.isXMLValidateFromFilePath(xsdPath, xmlPath);
        assertEquals(expResult, result);
    }

    @Test
    public void testIsXMLValidate_Alexa_StringInput() throws IOException, TransformerException {
        System.out.println("Test XML Validate Alexa");
        String pageContent = HttpUtils.getContent("https://www.alexa.com/siteinfo/tuoitre.vn");
        pageContent = TextUtils.refineHtml(pageContent);
        Transformer transformer = XMLUtils.getTransformer(SystemConfig.SITE_XSL_PATH);
        transformer.setParameter("categoryName", "News");
        String xml = XMLUtils.transformFromString(transformer, pageContent);
        String xsdPath = "src/prx/schema/siteSchema.xsd";
        boolean expResult = true;
        boolean result = XMLUtils.isXMLValidate(xsdPath, xml);
        assertEquals(expResult, result);
    }

    @Test
    public void testIsXMLValidate_BuiltWith_StringInput() throws IOException, TransformerException {
        System.out.println("Test XML Validate Similar Web");
        String pageContent = HttpUtils.getContent("https://builtwith.com/bilibili.com");
        pageContent = TextUtils.refineHtml(pageContent);
        Transformer transformer = XMLUtils.getTransformer(SystemConfig.TECH_XSL_PATH);
        String xml = XMLUtils.transformFromString(transformer, pageContent);
        String xsdPath = "src/prx/schema/builtwithSchema.xsd";
        boolean expResult = true;
        boolean result = XMLUtils.isXMLValidate(xsdPath, xml);
        assertEquals(expResult, result);
    }

    @Test
    public void testIsWellform() {
        System.out.println("Test Wellform");
        String xmlPath = "src/test/builtwith_bilibili.xml";
        boolean expResult = true;
        try {
            String data = new String(Files.readAllBytes(Paths.get(xmlPath)));
            boolean result = XMLUtils.isWellformXML(data);
            assertEquals(expResult, result);

        } catch (Exception e) {
            e.printStackTrace();
            fail("Not well form: " + e.getMessage());
        }

    }
}
