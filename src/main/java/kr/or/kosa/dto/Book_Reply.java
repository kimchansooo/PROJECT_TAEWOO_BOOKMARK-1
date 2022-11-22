package kr.or.kosa.dto;

import java.util.Date;

public class Book_Reply implements ReplyInterface{
	//책에 대한 댓글 DTO
	private int book_reply_no; //댓글 번호
	private String isbn; //책 코드
	private Date reply_date; //작성일
	private String reply_content; //댓글 내용
	private String id; //작성자
	public Book_Reply(int book_reply_no, String isbn, Date reply_date, String reply_content, String id) {
		super();
		this.book_reply_no = book_reply_no;
		this.isbn = isbn;
		this.reply_date = reply_date;
		this.reply_content = reply_content;
		this.id = id;
	}
	public Book_Reply() {}
	public int getBook_reply_no() {
		return book_reply_no;
	}
	public void setBook_reply_no(int book_reply_no) {
		this.book_reply_no = book_reply_no;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Book_Reply [book_reply_no=" + book_reply_no + ", isbn=" + isbn + ", reply_date=" + reply_date
				+ ", reply_content=" + reply_content + ", id=" + id + "]";
	}
}
