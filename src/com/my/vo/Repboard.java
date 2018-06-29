package com.my.vo;

	// 매개변수가다른 Repboard 모두 부를수있는 
public class Repboard {
	private int level;
	private int no;           // 글번호
	private int parent_no;
	private String content;
	private String subject;
	private String password;
	private int click_cnt;
	
	
	public int getClick_cnt() {
		return click_cnt;
	}

	public void setClick_cnt(int click_cnt) {
		this.click_cnt = click_cnt;
	}

	public Repboard() {
		super();
		
	}
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	
	// 글 들여쓸 때
	public Repboard(int level, int no, int parent_no, String subject, String content,  String password,int click_cnt) {
		super();
		this.level = level;
		this.no = no;
		this.parent_no = parent_no;
		this.content = content;
		this.subject = subject;
		this.password = password;
		this.click_cnt = click_cnt;
	}
	

	// 글 들여쓸 때
	public Repboard(int level, int no, int parent_no, String subject, String content,  String password) {
		super();
		this.level = level;
		this.no = no;
		this.parent_no = parent_no;
		this.content = content;
		this.subject = subject;
		this.password = password;
	}
	
	// 글을 검색해서 반환할때
	public Repboard(int no, int parent_no, String subject, String content,  String password) {
		super();
		this.no = no;
		this.parent_no = parent_no;
		this.content = content;
		this.subject = subject;
		this.password = password;
	}
	
	//답글쓰기할때
	public Repboard(int parent_no, String subject, String content, String password) {
		super();
		this.parent_no = parent_no;
		this.content = content;
		this.subject = subject;
		this.password = password;
	}

	// 글쓰기할때
	public Repboard(String subject, String content,  String password) {
		super();
		this.content = content;
		this.subject = subject;
		this.password = password;

	}
		
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getParent_no() {
		return parent_no;
	}
	public void setParent_no(int parent_no) {
		this.parent_no = parent_no;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	// 문자열을 만들어서 반환해 줌
	@Override
	public String toString() {
		return "Repboard [no=" + no + ", parent_no=" + parent_no + ", content=" + content + ", subject=" + subject
				+ ", password=" + password + "]";
	}
}
