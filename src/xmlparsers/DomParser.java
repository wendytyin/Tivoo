package xmlparsers;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.hp.gagawa.java.elements.Body;
import com.hp.gagawa.java.elements.Head;
import com.hp.gagawa.java.elements.Html;
import com.hp.gagawa.java.elements.Table;
import com.hp.gagawa.java.elements.Td;
import com.hp.gagawa.java.elements.Text;
import com.hp.gagawa.java.elements.Title;
import com.hp.gagawa.java.elements.Tr;

//hardcoded for simpletest.xml
//from a tutorial
// http://www.mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/
public class DomParser {

    private Document document;
    
    public DomParser(String filename) throws ParserConfigurationException{
        // create document
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory
                .newInstance();

        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        try {
            document = builder
                    .parse(new FileInputStream("src\\simpletest.xml"));
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeXMLtoFile() throws IOException {
        FileWriter fstream = new FileWriter("firstnamesonly.html");
        BufferedWriter out = new BufferedWriter(fstream);

        // write header
        Html html = new Html();
        Head head = new Head();
        html.appendChild(head);
        Title title = new Title();
        title.appendChild(new Text("only first name"));
        head.appendChild(title);
        Body body = new Body();
        Table table = new Table();
        table.setBorder("1 px solid black");

        // write body of table
        Element rootelement = document.getDocumentElement();
        NodeList nodes = rootelement.getChildNodes();

        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {

                Element child = (Element) node;
                Tr tr = new Tr();
                Td td = new Td();
                td.appendText(getTagValue("firstname", child));
                tr.appendChild(td);
                table.appendChild(tr);
            }
        }
        
        //close out html file
        body.appendChild(table);
        html.appendChild(body);
        out.write(html.write());
        out.close();
        System.out.println("done writing file");

    }

    public void printXML() {
        Element rootelement = document.getDocumentElement();
        NodeList nodes = rootelement.getChildNodes();

        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                //TODO: BE CAREFUL OF NAMESPACES
                // a child element to process
                Element child = (Element) node;
                System.out.println("First Name : "
                        + getTagValue("firstname", child));
                System.out.println("Last Name : "
                        + getTagValue("lastname", child));
                System.out.println("Nick Name : "
                        + getTagValue("nickname", child));
                System.out.println("Salary : "
                        + getTagValue("salary", child));
            }
        }
    }

    private String getTagValue(String sTag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(sTag).item(0)
                .getChildNodes();

        Node nValue = (Node) nlList.item(0);

        return nValue.getNodeValue();
    }
}
