/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prx.parser;

import data.TechStack;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Gia Bảo Hoàng
 */
public class BuiltWithParser extends BaseParser {

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
        parsePageSet(TechStack.class, domainSet);
        System.out.println("Finish parsing " + baseURL);
        System.out.println("================================================");
    }
}
