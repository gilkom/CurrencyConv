import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;
import java.util.regex.*;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;


public class ConvertersPanel {
	private JFrame frame;
	private JPanel panel;
	private JPanel north;
	private JPanel center;
	private JPanel south;
	private JLabel comboLabel;
	private JLabel currFieldLabel;
	private JComboBox currChoserCombo;
	private JTextField currField;
	private JTextField resultField;
	private JButton calculate;
	private GetXML getX;
	private String[] table;
	private Map<String,Currency> currMap;
	private Set<String> names;
	private Double result;
	private Pattern pat;
	private Matcher mat;
	private Currency curRes;
	private String fieldVal;
	private String p = "\\d+\\.?\\d*";

ConvertersPanel() 
		throws ParserConfigurationException, SAXException, IOException, TransformerException{
	frame = new JFrame("Currency Converter");
	panel = new JPanel(new BorderLayout());
	north = new JPanel();
	center = new JPanel();
	south = new JPanel();
	
	//getting the data
	getX = new GetXML();
	setCurrencyMap(getX);
	names = currMap.keySet();
	table = names.toArray(new String[names.size()]);
	//main panel elements:
	comboLabel = new JLabel("Choose currency:");
	currChoserCombo = new JComboBox(table);
	currFieldLabel = new JLabel("Enter value (z³oty):");
	currField = new JTextField((20));
	resultField = new JTextField(20);
	resultField.setEditable(false);
	
	calculate = new JButton("Calculate");
	calculate.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			calculate(currField.getText());
		}
	});
	
	north.add(currFieldLabel);
	north.add(currField);
	center.add(comboLabel);
	center.add(currChoserCombo);
	south.add(calculate);
	south.add(resultField);
	
	panel.add(north, BorderLayout.NORTH);
	panel.add(center,BorderLayout.CENTER);
	panel.add(south, BorderLayout.SOUTH);
	frame.add(panel);
	
	
	
	SwingUtilities.invokeLater(new Runnable() {
		public void run() {
		frame.pack();
		frame.setVisible(true);
		frame.setLocation(100,100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	});
}

	public void setCurrencyMap(GetXML getX) {
		this.currMap = getX.getCurrencyMap();
	}
	public Map<String,Currency> getCurrencyMap(){
		return currMap;
	}
	
	public void calculate(String enteredValue) {
		//replacing commas with dots
		fieldVal = enteredValue;
		fieldVal = fieldVal.replace(',', '.');

		//checking for wrong input value
		try {				
			pat = Pattern.compile(p);
			mat = pat.matcher(fieldVal);
			curRes = currMap.get((String) currChoserCombo.getSelectedItem());
			result =(Double.valueOf(fieldVal)/curRes.getMid());
			result = (double) Math.floor(result * 100d)/100d;
			resultField.setText(String.valueOf(result) +" " + curRes.getCode());
		}catch(NumberFormatException ex) {
			JOptionPane.showMessageDialog(null, "Wrong pattern");
		}
	}
}