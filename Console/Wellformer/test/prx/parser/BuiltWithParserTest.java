/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prx.parser;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import prx.data.TechStack.Site;
import prx.data.TechStack;
import prx.data.TechStack.TechnologyGroup;
import prx.data.TechStack.TechnologyGroup.Technology;

/**
 *
 * @author Gia Bảo Hoàng
 */
public class BuiltWithParserTest {
    
    public BuiltWithParserTest() {
    }

    /**
     * Test of parse method, of class BuiltWithParser.
     */
    @Test
    public void testParse() {
//        System.out.println("parse");
//        BuiltWithParser instance = new BuiltWithParser();
//        instance.parse();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of insertTechnology method, of class BuiltWithParser.
     */
    @Test
    public void testInsertTechnology() {
        System.out.println("insertTechnology");
        
        List<TechStack> stackList = new ArrayList();
        //prepare data techStack 1
        TechStack techStack = new TechStack();
        Site site = new Site();
        site.setName("tuoitre.vn");
        techStack.setSite(site);
        TechnologyGroup techGroup = new TechnologyGroup();
        techGroup.setGroupName("Backend");
        Technology tech = new Technology();
        tech.setTechName("C#");
        tech.setDescription("Powerful Language for Backend Developer");
        techGroup.getTechnology().add(tech);
        techStack.getTechnologyGroup().add(techGroup);
        stackList.add(techStack);
        BuiltWithParser instance = new BuiltWithParser();
        instance.insertTechnology(stackList);
    }
    
}
