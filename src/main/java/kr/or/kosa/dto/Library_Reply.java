package kr.or.kosa.dto;

import java.util.Date;

public class Library_Reply implements ReplyInterface{
	//도서관 댓글 DTO
	private int library_reply_no; //댓글번호
	private String library_name; //도서관 이름
	private String id;//작성자
	private Date reply_date;//작성일
	private String reply_content;//댓글내용
	public Library_Reply(int library_reply_no, String library_name, String id, Date reply_date, String reply_content) {
		super();
		this.library_reply_no = library_reply_no;
		this.library_name = library_name;
		this.id = id;
		this.reply_date = reply_date;
		this.reply_content = reply_content;
	}
	public Library_Reply() {}
	public int getLibrary_reply_no() {
		return library_reply_no;
	}
	public void setLibrary_reply_no(int library_reply_no) {
		this.library_reply_no = library_reply_no;
	}
	public String getLibrary_name() {
		return library_name;
	}
	public void setLibrary_name(String library_name) {
		this.library_name = library_name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getReply_date() {
		return reply_date;
	}
	public void setReply_date(Date reply_date) {
		this.reply_date = reply_date;
	}
	public String getReply_content() {
		return reply_content;
	}
	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}
	@Override
	public String toString() {
		return "Library_Reply [library_reply_no=" + library_reply_no + ", library_name=" + library_name + ", id=" + id
				+ ", reply_date=" + reply_date + ", reply_content=" + reply_content + "]";
	}
}
