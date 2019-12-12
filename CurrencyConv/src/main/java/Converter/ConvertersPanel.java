package Converter;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.regex.*;
 
import javax.swing.*;


public class ConvertersPanel {
	private JFrame frame;
	private JPanel panel;
	private JPanel north;
	private JPanel center;
	private JPanel south;
	private JLabel comboLabel;
	private JLabel currFieldLabel;
	private JComboBox<String> currChoserCombo;
	private JTextField currField;
	private JTextField resultField;
	private JButton calculateBut;
	private GetXML getX;
	private String[] table;
	private Map<String,Currency> currMap;
	private Set<String> names;
	private String result;
	private Pattern pat;
	private Currency curRes;
	private String fieldVal;
	private String p = "\\d+\\.?\\d*";

	public ConvertersPanel() 
		{
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
	currChoserCombo = new JComboBox<String>(table);
	currFieldLabel = new JLabel("Enter value (z³oty):");
	currField = new JTextField((20));
	resultField = new JTextField(20);
	resultField.setEditable(false);
	
	calculateBut = new JButton("Calculate");
	calculateBut.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			curRes = currMap.get((String) currChoserCombo.getSelectedItem());
			result = calculate(currField.getText(),curRes);
			resultField.setText(String.valueOf(result) +" " + curRes.getCode());
		}
	});
	
	north.add(currFieldLabel);
	north.add(currField);
	center.add(comboLabel);
	center.add(currChoserCombo);
	south.add(calculateBut);
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
		this.currMap = getX.getCurrenMap();
	}
	public Map<String,Currency> getCurrencyMap(){
		return currMap;
	}
	
	public String calculate(String enteredValue, Currency curResult) {
		Double calcResult = 0.0;
		//replacing commas with dots
		fieldVal = enteredValue;
		fieldVal = fieldVal.replace(',', '.');

		//checking for wrong input value
		try {				
			pat = Pattern.compile(p);
			pat.matcher(fieldVal);
			calcResult =(Double.valueOf(fieldVal)/curResult.getMid());
			calcResult = (double) Math.floor(calcResult * 100d)/100d;
			//resultField.setText(String.valueOf(result) +" " + curRes.getCode());
			
		}catch(NumberFormatException ex) {
			JOptionPane.showMessageDialog(null, "Wrong pattern");
		}
		return Double.toString(calcResult);
	}
}