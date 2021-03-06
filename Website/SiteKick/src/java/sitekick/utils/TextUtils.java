/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sitekick.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import sitekick.constant.CommonConstant;
import sitekick.constant.ExpressionConstant;
import sitekick.wellformer.SyntaxWellformer;

/**
 *
 * @author Eden
 */
public class TextUtils {
    
    // transform HTML to XML
    public static String refineHtml(String src) {
        src = getBody(src);
        src = removeMiscellaneousTags(src);
        
        SyntaxWellformer syntaxWellformer = new SyntaxWellformer();
        src = syntaxWellformer.check(src);
        
        //crop one more time 
        src = getBody(src);
        return src;
    }

    // get Body section of document using RegEx
    public static String getBody(String src) {
        String result = src; 
        String expression =  ExpressionConstant.BODY_EXPRESSION;
        Pattern pattern = Pattern.compile(expression);
        
        Matcher matcher = pattern.matcher(result);
        if(matcher.find()){
            result = matcher.group(0);
        }
        return result;
    }

    // remove unecessary Tag as Comment or Script
    public static String removeMiscellaneousTags(String src) {
        String result = src;
        
        // remove all <script> tags
        String expression = ExpressionConstant.SCRIPT_EXPRESSION;
        result = result.replaceAll(expression, CommonConstant.EMPTY);
        
        // remove all comments
        expression = ExpressionConstant.COMMENT_EXPRESSION;
        result = result.replaceAll(expression, CommonConstant.EMPTY);
        
        // remove all whitespace 
        expression =  ExpressionConstant.SPACE_EXPRESSION;
        result = result.replaceAll(expression, CommonConstant.EMPTY);
        
        return result;
    }
}
