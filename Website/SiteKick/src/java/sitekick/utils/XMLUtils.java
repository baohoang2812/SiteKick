/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sitekick.utils;

import com.sun.codemodel.JCodeModel;
import com.sun.tools.xjc.api.S2JJAXBModel;
import com.sun.tools.xjc.api.SchemaCompiler;
import com.sun.tools.xjc.api.XJC;
import org.eclipse.persistence.internal.oxm.ByteArraySource;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gia Bảo Hoàng
 */
public class XMLUtils {

    public static Document parseStringToDOM(String content) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        StringReader reader = new StringReader(content);
        InputSource is = new InputSource(reader);
        Document doc = builder.parse(is);
        return doc;
    }

    public static XPath getXPath() {
        XPathFactory factory = XPathFactory.newInstance();
        XPath xpath = factory.newXPath();
        return xpath;
    }

    public static void TransformDOMToFile(Node node, String filePath) throws TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();
        Source src = new DOMSource(node);
        Result result = new StreamResult(filePath);
        transformer.transform(src, result);
    }

    //JAXB
    //file: schema/ dtd
    public static void generateClass(String packageName, File file, String outputPath) throws SAXException, IOException {
        SchemaCompiler sc = XJC.createSchemaCompiler();
        sc.forcePackageName(packageName);
        InputSource is = new InputSource(file.toURI().toString());
        sc.parseSchema(is);
        S2JJAXBModel model = sc.bind();
        JCodeModel code = model.generateCode(null, null);
        code.build(new File(outputPath));
    }

    public static <T> T unmarshall(Class<T> clazz, File xmlFile) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return clazz.cast(unmarshaller.unmarshal(xmlFile));
    }

    // Unmarshall from string
    public static <T> T unmarshall(Class<T> clazz, String source) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return clazz.cast(unmarshaller.unmarshal(new InputSource(new StringReader(source))));
    }

    // marshall to File
    public static <T> void marshall(T obj, File xmlFile) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, StandardCharsets.UTF_8.toString());
        marshaller.marshal(obj, xmlFile);
    }

    public static <T> String marshallToString(T obj) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_ENCODING, StandardCharsets.UTF_8.toString());
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        StringWriter stringWriter = new StringWriter();
        marshaller.marshal(obj, stringWriter);
        return stringWriter.toString();
    }

    //TrAX 
    public static void transformXML(String xslPath, String xmlPath, String outputPath)
            throws IOException, TransformerConfigurationException, TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        StreamSource xslFile = new StreamSource(xslPath);
        Templates template = transformerFactory.newTemplates(xslFile);
        Transformer transformer = template.newTransformer();
        StreamSource xmlFile = new StreamSource(xmlPath);
        StreamResult output = new StreamResult(new FileOutputStream(outputPath));
        transformer.transform(xmlFile, output);
    }

    public static String transformFromString(Transformer transformer, String content)
            throws IOException, TransformerException {
        StringReader reader = new StringReader(content);
        StringWriter writer = new StringWriter();
        StreamSource source = new StreamSource(reader);
        StreamResult output = new StreamResult(writer);
        transformer.transform(source, output);
        return writer.toString();
    }

    public static Transformer getTransformer(String xslPath) throws TransformerConfigurationException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        StreamSource xslFile = new StreamSource(xslPath);
        Templates template = transformerFactory.newTemplates(xslFile);
        return template.newTransformer();
    }

    public static boolean isXMLValidateFromFilePath(String xsdPath, String xmlPath) {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdPath));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlPath)));
        } catch (SAXException | IOException e) {
            Logger.getLogger(XMLUtils.class.getName()).log(Level.SEVERE, e.getMessage());
            System.out.println(xmlPath + " is NOT validate, error: " + e.getMessage());
            return false;
        }
        return true;
    }

    public static boolean isXMLValidate(String xsdPath, String source) {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdPath));
            Validator validator = schema.newValidator();
            validator.validate(new ByteArraySource(source.getBytes()));
        } catch (SAXException | IOException e) {
            System.out.println("NOT validate, error: " + e.getMessage());
            return false;
        }
        return true;
    }

    public static boolean isWellformXML(String src) {
        // use DocumentBuilder + SAX parser to check 
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(false);
        factory.setNamespaceAware(true);

        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            Logger.getLogger(XMLUtils.class.getName()).log(Level.SEVERE, e.getMessage());
            return false;
        }
        // TODO[BHG, refactor ErrorHandler]
        builder.setErrorHandler(new ErrorHandler() {
            @Override
            public void warning(SAXParseException exception) throws SAXException {
                System.out.println(exception.getMessage());
            }

            @Override
            public void error(SAXParseException exception) throws SAXException {
                System.out.println(exception.getMessage());
            }

            @Override
            public void fatalError(SAXParseException exception) throws SAXException {
                System.out.println(exception.getMessage());
            }
        });
        try {
            builder.parse(new InputSource(new StringReader(src)));
            return true;
        } catch (SAXException | IOException e) {
            return false;
        }
    }
}
