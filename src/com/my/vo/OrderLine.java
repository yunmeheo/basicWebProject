package com.my.vo;

public class OrderLine {
	
		
//	line_info_no	line_prod_no 	line_quantity
	
	private int line_info_no;
	//private String line_prod_no;
	private Product line_prod_no;
	private int line_quantity;
	
	
	public OrderLine() {
		super();
	}
	
	public OrderLine(int line_info_no, Product line_prod_no, int line_quantity) {
		super();
		this.line_info_no = line_info_no;
		this.line_prod_no = line_prod_no;
		this.line_quantity = line_quantity;
	}
	
	
	public int getLine_info_no() {
		return line_info_no;
	}
	public void setLine_info_no(int line_info_no) {
		this.line_info_no = line_info_no;
	}
	
	
	
	public Product getLine_prod_no() {
		return line_prod_no;
	}
	public void setLine_prod_no(Product line_prod_no) {
		this.line_prod_no = line_prod_no;
	}
	
	
	
	public int getLine_quantity() {
		return line_quantity;
	}
	public void setLine_quantity(int line_quantity) {
		this.line_quantity = line_quantity;
	}

	@Override
	public String toString() {
		return "OrderLine [line_info_no=" + line_info_no + ", line_prod_no=" + line_prod_no + ", line_quantity="
				+ line_quantity + "]";
	}
	
	
	
	



}
