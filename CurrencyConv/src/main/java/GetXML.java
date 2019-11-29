import java.io.*;
import java.net.*;
import java.util.Map;

import javax.xml.parsers.*;
import javax.xml.transform.*;

import org.w3c.dom.Document;
import org.xml.sax.*;

//class gets xml file and parses it 
public class GetXML {
	private Map<String,Currency> currMap;
	private String link;
	private URL url;
	private DocumentBuilderFactory factory;
	private DocumentBuilder builder;
	private InputStream stream;
	private Document doc;
	private Parser pars;
	
	
	GetXML() throws ParserConfigurationException, SAXException, IOException, TransformerException{
		
		link = "http://api.nbp.pl/api/exchangerates/tables/A/?format=xml";
		url = new URL(link);
		
		factory = DocumentBuilderFactory.newInstance();
		builder = factory.newDocumentBuilder();
		stream = url.openStream();
		
		doc = builder.parse(stream);
		pars = new Parser(doc);
		setCurrencyMap(pars);
	}

	public void setCurrencyMap(Parser pars) {
		this.currMap = pars.getMap();
	}
	public Map<String, Currency> getCurrencyMap(){
		return currMap;
	}
}
