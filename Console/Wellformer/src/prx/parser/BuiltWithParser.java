/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prx.parser;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import prx.data.TechStack;
import prx.utils.XMLUtils;

/**
 *
 * @author Gia Bảo Hoàng
 */
public class BuiltWithParser extends Parser {

    Set<String> domainSet;

    public BuiltWithParser() {
    }

    public BuiltWithParser(Set<String> domainSet) {
        this.domainSet = domainSet;
    }

    public Set<String> getLinkSet() {
        return domainSet;
    }

    public void setLinkSet(Set<String> domainSet) {
        this.domainSet = domainSet;
    }

    @Override
    public void parse() {
        System.out.println("================================================");
        System.out.println("Parsing " + baseURL + " . . .");
        //config transformer
        Transformer transformer = null;
        try {
            transformer = XMLUtils.getTransformer(xslPath);
        } catch (TransformerConfigurationException e) {
            Logger.getLogger(BuiltWithParser.class.getName()).log(Level.SEVERE, e.getMessage());
        }
        parsePageSet(TechStack.class, domainSet, transformer);
        System.out.println("Finish parsing " + baseURL);
        System.out.println("================================================");
    }
}
