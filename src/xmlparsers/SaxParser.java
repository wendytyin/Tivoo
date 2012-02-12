package xmlparsers;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import com.hp.gagawa.java.elements.Body;
import com.hp.gagawa.java.elements.Head;
import com.hp.gagawa.java.elements.Html;
import com.hp.gagawa.java.elements.Table;
import com.hp.gagawa.java.elements.Td;
import com.hp.gagawa.java.elements.Text;
import com.hp.gagawa.java.elements.Title;
import com.hp.gagawa.java.elements.Tr;

//http://www.saxproject.org/quickstart.html
public class SaxParser extends DefaultHandler {

    private ArrayList<Tags> myGames = new ArrayList<Tags>();
    private Tags temp;
    private String tempVal;

    public static void main(String[] args) throws Exception {
        XMLReader xr = XMLReaderFactory.createXMLReader();
        SaxParser handler = new SaxParser();
        xr.setContentHandler(handler);
        xr.setErrorHandler(handler);

        // Parse each file provided on the
        // command line.
        // for (int i = 0; i < args.length; i++) {
        // FileReader r = new FileReader(args[i]);
        // xr.parse(new InputSource(r));
        // }
        xr.parse(new InputSource("DukeBasketBall.xml"));
        handler.writeXMLtoFile();
    }

    private void writeXMLtoFile() throws IOException {
        FileWriter fstream = new FileWriter("BasketballSched.html");
        BufferedWriter out = new BufferedWriter(fstream);

        // write header
        Html html = new Html();
        Head head = new Head();
        html.appendChild(head);
        Title title = new Title();
        title.appendChild(new Text("Basketball"));
        head.appendChild(title);
        Body body = new Body();
        Table table = new Table();
        table.setBorder("1 px solid black");

        // write body of table
        Iterator<Tags> it = myGames.iterator();
        while (it.hasNext()) {
            Tags info = it.next();
            Tr tr = new Tr();
            Td td = new Td();
            td.appendText(info.getDate());
            tr.appendChild(td);
            td = new Td();
            td.appendText(info.getSubject());
            tr.appendChild(td);
            td = new Td();
            td.appendText(info.getLoc());
            tr.appendChild(td);
            table.appendChild(tr);
        }

        // close out html file
        body.appendChild(table);
        html.appendChild(body);
        out.write(html.write());
        out.close();
        System.out.println("done writing file");

    }

    public void startDocument() {
        System.out.println("Start document");
    }

    public void endDocument() {
        System.out.println("End document");
    }

    public void startElement(String uri, String name, String qName,
            Attributes atts) {

        if ("".equals(uri)) {
            System.out.println("Start element: " + qName);
        } else
            System.out.println("Start element: {" + uri + "}" + name);
        if (qName.equals("Calendar")) {
            temp = new Tags();
            // temp.setType(atts.getValue("type"));
            // System.out.println(atts.getValue("type"));
        }
    }

    public void endElement(String uri, String name, String qName) {
        if ("".equals(uri))
            System.out.println("End element: " + qName);
        else
            System.out.println("End element:   {" + uri + "}" + name);
        if (qName.equals("Calendar")) {
            myGames.add(temp);
        } else if (qName.equals("Subject")) {
            temp.setSubject(tempVal);
        } else if (qName.equals("StartDate")) {
            temp.setDate(tempVal);
        } else if (qName.equals("Location")) {
            temp.setLoc(tempVal);
        }
    }

    public void characters(char[] ch, int start, int length)
            throws SAXException {
        tempVal = new String(ch, start, length);
    }

}
