/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prx.utils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.Test;
import static org.junit.Assert.*;

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
        System.out.println("Test Generate Class");
        String packageName = "";
        File file = null;
        String outputPath = "";
        XMLUtils.generateClass(packageName, file, outputPath);
    }

    /**
     * Test of unmarshall method, of class XMLUtils.
     */
    @Test
    public void testUnmarshall() throws Exception {
//        System.out.println("unmarshall");
//        Object expResult = null;
////        Object result = XMLUtils.unmarshall(null);
////        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of marshall method, of class XMLUtils.
     */
    @Test
    public void testMarshall() throws Exception {
//        System.out.println("marshall");
//        Object obj = null;
//        File xmlFile = null;
//        XMLUtils.marshall(obj, xmlFile);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
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
        boolean result = XMLUtils.isXMLValidate(xsdPath, xmlPath);
        assertEquals(expResult, result);
    }

    @Test
    public void testIsXMLValidate_SimilarWeb() {
        System.out.println("Test XML Validate Similar Web");
        String xsdPath = "src/prx/schema/siteSchema.xsd";
        String xmlPath = "src/test/site_bilibili_output.xml";
        boolean expResult = true;
        boolean result = XMLUtils.isXMLValidate(xsdPath, xmlPath);
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
            fail("Not well form: "+e.getMessage());
        }

    }
}
