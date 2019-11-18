
import java.util.*;
import javax.xml.transform.*;
import org.w3c.dom.*;

//class gets the data from xml file and creates list of Currencies and its values
public class Parser {
	private Map<String,Currency> currMap;
	private Currency curren;
	private String key;
	private String repl;
	private NodeList nodeLst;
	private Node fstNode;
	private Node nazwaNode;
	private Element El;
	private NodeList nazwaNodeList;
	private Element nazwaNodeEl;
	private NodeList nazwaN;
	private NodeList kodNodeList;
	private Element kodNodeEl;
	private NodeList kodN;
	private NodeList kursNodeList;
	private Element kursNodeEl;
	private NodeList kursN;
	
	
	Parser(Document doc) throws TransformerException {
		nodeLst = doc.getElementsByTagName("Rate");
		currMap = new HashMap<String, Currency>();
		
		for(int i = 0; i < nodeLst.getLength(); i ++) {
			fstNode = nodeLst.item(i);
			nazwaNode = nodeLst.item(i);
			if(fstNode.getNodeType() == Node.ELEMENT_NODE) {

				El = (Element)nazwaNode;
				
				nazwaNodeList = El.getElementsByTagName("Currency");
				nazwaNodeEl = (Element) nazwaNodeList.item(0);
				nazwaN = nazwaNodeEl.getChildNodes();

				kodNodeList = El.getElementsByTagName("Code");
				kodNodeEl = (Element) kodNodeList.item(0);
				kodN = kodNodeEl.getChildNodes();
				
				kursNodeList = El.getElementsByTagName("Mid");
				kursNodeEl = (Element) kursNodeList.item(0);
				kursN = kursNodeEl.getChildNodes();
				
				repl = kursN.item(0).getNodeValue();
				repl = repl.replaceAll(",",".");
					
				key = nazwaN.item(0).getNodeValue();
				curren = new Currency(nazwaN.item(0).getNodeValue(),
						kodN.item(0).getNodeValue(),
						Double.parseDouble(repl));
				setMap(key,curren);
			}
			
		}
	}
	public void setMap(String key, Currency cur ) {
		this.currMap.put(key, cur);
	}
	public Map<String,Currency> getMap() {
		return currMap;
		
	}
}
