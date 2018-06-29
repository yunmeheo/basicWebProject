package com.my.vo;

public class Customer extends Person{
		            //필드
				
		private String id;
		private String password;
		private String name;
		public Customer(){}
		
		
		
		
		public Customer(String id, String password, String name) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
	}
		
		
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		@Override
		public String toString() {
			return id+"," +password+"," +name;
		}
		

}
