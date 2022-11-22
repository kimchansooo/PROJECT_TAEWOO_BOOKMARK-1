package kr.or.kosa.dto;

import java.util.Date;

public class Blog_Board{
	//블로그 게시글 DTO
	private int blog_no; //게시글 번호
	private String id; //작성자 id
	private String blog_title; //게시글 제목
	private String blog_content; //게시글 내용
	private int hits; //조회수
	private Date blog_date; //작성일
	private String blog_filename; //첨부 파일 이름
	public Blog_Board(int blog_no, String id, String blog_title, String blog_content, int hits, Date blog_date,
			String blog_filename) {
		super();
		this.blog_no = blog_no;
		this.id = id;
		this.blog_title = blog_title;
		this.blog_content = blog_content;
		this.hits = hits;
		this.blog_date = blog_date;
		this.blog_filename = blog_filename;
	}
	public Blog_Board() {}
	public int getBlog_no() {
		return blog_no;
	}
	public void setBlog_no(int blog_no) {
		this.blog_no = blog_no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBlog_title() {
		return blog_title;
	}
	public void setBlog_title(String blog_title) {
		this.blog_title = blog_title;
	}
	public String getBlog_content() {
		return blog_content;
	}
	public void setBlog_content(String blog_content) {
		this.blog_content = blog_content;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	public Date getBlog_date() {
		return blog_date;
	}
	public void setBlog_date(Date blog_date) {
		this.blog_date = blog_date;
	}
	public String getBlog_filename() {
		return blog_filename;
	}
	public void setBlog_filename(String blog_filename) {
		this.blog_filename = blog_filename;
	}
	@Override
	public String toString() {
		return "Blog_Board [blog_no=" + blog_no + ", id=" + id + ", blog_title=" + blog_title + ", blog_content="
				+ blog_content + ", hits=" + hits + ", blog_date=" + blog_date + ", blog_filename=" + blog_filename
				+ "]";
	}
	
}
