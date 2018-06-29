package com.my.vo;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;




public class OrderInfo {

	
	
	private int info_no; 
	private Date info_date;
	// DB 와의 관계도 객체지향의 릴레이션에도 반영 되어야 한다.
	// orderinfo 테이블에서 customer를 사용해야하기 때문에
	// private int info_id; 를 커스터머 타입으로 변환해야 한다.
	private Customer info_c;
	private List<OrderLine>lines; 
	
	public OrderInfo() {

		super();

	}

	public OrderInfo(int info_no, Date info_date, Customer info_c) {

		this(info_no, info_date, info_c, new ArrayList<OrderLine>());

	}
	public OrderInfo(int info_no, Date info_date, Customer info_c, List<OrderLine> lines) {

		super();		this.info_no = info_no;
		this.info_date = info_date;
		this.info_c = info_c;
		this.lines = lines;

	}

	
	public List<OrderLine> getLines() {

		return lines;

	}

	public void setLines(List<OrderLine> lines) {
		this.lines = lines;

	}

	public int getInfo_no() {

		return info_no;

	}

	public void setInfo_no(int info_no) {

		this.info_no = info_no;

	}

	public Date getInfo_date() {

		return info_date;

	}

	public void setInfo_date(Date info_date) {

		this.info_date = info_date;

	}

	public Customer getInfo_c() {

		return info_c;

	}

	public void setInfo_c(Customer info_c) {

		this.info_c = info_c;

	}

	@Override
	public String toString() {
		return "OrderInfo [info_no=" + info_no + ", info_date=" + info_date + ", info_c=" + info_c + ", lines=" + lines
				+ "]";
	}
	
	
	
	
}