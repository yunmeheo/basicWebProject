package com.my.vo;

public class Product {

	
//	
//	PROD_NO ,PROD_NAME , PROD_PRICE

	private String prod_no;
	private String prod_name;
	private int prod_price;
	
	
	public Product() {
		super();
	}
	
	
	public Product(String prod_no, String prod_name, int prod_price) {
		super();
		this.prod_no = prod_no;
		this.prod_name = prod_name;
		this.prod_price = prod_price;
	}
	
	
	// 맵 자료구조에서 product가 키의 역할을 하기위해서 상품번호만 같으면 true 반환될 수 있도록 
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((prod_no == null) ? 0 : prod_no.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (prod_no == null) {
			if (other.prod_no != null)
				return false;
		} else if (!prod_no.equals(other.prod_no))
			return false;
		return true;
	}


	public String getProd_no() {
		return prod_no;
	}


	public void setProd_no(String prod_no) {
		this.prod_no = prod_no;
	}


	public String getProd_name() {
		return prod_name;
	}


	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}


	public int getProd_price() {
		return prod_price;
	}


	public void setProd_price(int prod_price) {
		this.prod_price = prod_price;
	}

	//system.out.println할때 자동호출될 메서드이기 때문에 상품.참조변수로 바로 호출할 수 있다.
	@Override
	public String toString() {
		return "Product [prod_no=" + prod_no + ", prod_name=" + prod_name + ", prod_price=" + prod_price + "]";
	}
	
	
	
	

	
	

}
