
package sitekick.config;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the prx.config package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: prx.config
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Config }
     * 
     */
    public Config createConfig() {
        return new Config();
    }

    /**
     * Create an instance of {@link Config.Alexa }
     * 
     */
    public Config.Alexa createConfigAlexa() {
        return new Config.Alexa();
    }

    /**
     * Create an instance of {@link Config.BuiltWith }
     * 
     */
    public Config.BuiltWith createConfigBuiltWith() {
        return new Config.BuiltWith();
    }

    /**
     * Create an instance of {@link Config.Alexa.XPath }
     * 
     */
    public Config.Alexa.XPath createConfigAlexaXPath() {
        return new Config.Alexa.XPath();
    }

}
