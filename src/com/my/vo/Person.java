package com.my.vo;
import java.io.Serializable;

public class Person implements Serializable{
	/**
	 * 
	 */
	
	//Serializable 한 클래스들은 꼭 워닝 제거해주세요
	
	private static final long serialVersionUID = 7421685912565278401L;
	
	private String name;
	
	
	
	public String getName() {
		return name;
	}
	
	
	
	public void setName(String name) {
		this.name = name;
	}
}	