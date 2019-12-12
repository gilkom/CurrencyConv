package Converter;

import java.io.*;
import java.net.*;
import java.util.*;

import javax.xml.parsers.*;
import javax.xml.transform.*;

import org.w3c.dom.Document;
import org.xml.sax.*;

import Converter.Currency;

//class gets xml file and parses it 
public class GetXML {
	//private Currency cur;
	private Map<String,Currency> currencyMap;
	private String link;
	private URL url;
	private DocumentBuilderFactory factory;
	private DocumentBuilder builder;
	private InputStream stream;
	private Document doc;
	private Parser pars;
	
	
	public GetXML(){
	
		currencyMap = new HashMap<String,Currency>();

		link = "http://api.nbp.pl/api/exchangerates/tables/A/?format=xml";
		createDoc(link);

		try {
			pars = new Parser(getDoc());
		} catch (TransformerException e) {
			e.printStackTrace();
		}
		setCurrencyMap(pars);
	}
	public void createDoc(String link) {
		try {
			url = new URL(link);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		factory = DocumentBuilderFactory.newInstance();
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		try {
			stream = url.openStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		setDoc(stream);
		//doc = builder.parse(stream);
		
	}

	public void setCurrencyMap(Parser pars) {
		this.currencyMap = pars.getParserMap();
	}
	public Map<String,Currency> getCurrenMap() {
		return currencyMap;
	}
	public void setDoc(InputStream stream) {
		try {
			this.doc = builder.parse(stream);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public Document getDoc() {
		return doc;
	}
}
