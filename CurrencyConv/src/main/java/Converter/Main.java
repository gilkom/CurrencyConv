package Converter;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

public class Main {
	public static void main(String args[])
			throws ParserConfigurationException, SAXException, IOException, TransformerException {	
		new ConvertersPanel();
	}
}
