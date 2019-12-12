import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import Converter.ConvertersPanel;
import Converter.Currency;

public class ConvertersPanelTest {
	private ConvertersPanel panel;
	private Currency currency;
	private String result;
	
	@Before
	public void setUp() {
		//given
		panel = new ConvertersPanel();
		currency = new Currency();
		currency.setCurrencyValue("euro", "EUR", 4.2777);		
	}
	
	@Test
	public void shouldSayResultIs116() {
		//when
		result = panel.calculate("5",currency);
		//then
		assertEquals("1.16", result);
	}
	@Test
	public void shouldSayResultIsZero() {
		//when
		result = panel.calculate("0", currency);
		//then
		assertEquals("0.0", result);
	}
	/*@Test(expected = NumberFormatException.class)
	public void shouldThrowNumberFormatException() throws Exception{
		//when
		result = panel.calculate("123", currency);
		//then
		//assertEquals(" ", result);
		fail("This method should throw NumberFormatException");
	}*/
}
