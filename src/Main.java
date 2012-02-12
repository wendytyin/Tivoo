import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.w3c.dom.*;
import org.xml.sax.XMLReader;

import xmlparsers.DomParser;
import xmlparsers.SaxParser;


public class Main {

    private static Document document;

    public static void main(String[] args){
        DomParser domparse;
        try {
            domparse = new DomParser("src\\simpletest.xml");
            
            domparse.printXML();
            domparse.writeXMLtoFile();
        } catch (ParserConfigurationException e) {
            System.out.println("i have no idea what i'm doing");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


}
