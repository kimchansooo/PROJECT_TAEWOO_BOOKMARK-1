package kr.or.kosa.dto;

import java.util.Date;

public class Blog_Reply implements ReplyInterface{
	//블로그 댓글 DTO
	private int blog_reply_no; //댓글 번호
	private int blog_no; //게시글 번호
	private String id; //작성자 아이디
	private Date reply_date; //작성일
	private String reply_content; //댓글 내용
	private int refer; //댓글 그룹
	private int depth; //들여쓰기 정도
	private int step; //그룹내 순서
	private int del; //삭제 상태 (0 정상 | 1 삭제됨)
	public Blog_Reply(int blog_reply_no, int blog_no, String id, Date reply_date, String reply_content, int refer,
			int depth, int step, int del) {
		super();
		this.blog_reply_no = blog_reply_no;
		this.blog_no = blog_no;
		this.id = id;
		this.reply_date = reply_date;
		this.reply_content = reply_content;
		this.refer = refer;
		this.depth = depth;
		this.step = step;
		this.del = del;
	}
	public Blog_Reply() {}
	public int getBlog_reply_no() {
		return blog_reply_no;
	}
	public void setBlog_reply_no(int blog_reply_no) {
		this.blog_reply_no = blog_reply_no;
	}
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
	public int getRefer() {
		return refer;
	}
	public void setRefer(int refer) {
		this.refer = refer;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public int getDel() {
		return del;
	}
	public void setDel(int del) {
		this.del = del;
	}
	@Override
	public String toString() {
		return "Blog_Reply [blog_reply_no=" + blog_reply_no + ", blog_no=" + blog_no + ", id=" + id + ", reply_date="
				+ reply_date + ", reply_content=" + reply_content + ", refer=" + refer + ", depth=" + depth + ", step="
				+ step + ", del=" + del + "]";
	}
	
}
