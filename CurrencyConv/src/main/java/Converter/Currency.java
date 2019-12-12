package Converter;

public class Currency {
	private String curren;
	private String code;
	private double mid;
	
	
	public void setCurrencyValue(String curren, String code, double mid) {
		this.curren = curren;
		this.code = code;
		this.mid = mid;
	}
	public String getCurren() {
		return curren;
	}
	public void setCurren(String curren) {
		this.curren = curren;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public double getMid() {
		return mid;
	}
	public void setMid(double mid) {
		this.mid = mid;
	}
	public Currency() {}
	public Currency(String curren,String code, double mid) {
		this.curren = curren;
		this.code = code;
		this.mid = mid;
	}
	@Override
	public String toString() {
		return "Waluta: " + curren + "("+ code + "), kurs: " + mid;
	}
}
