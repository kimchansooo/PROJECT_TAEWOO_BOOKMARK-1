package kr.or.kosa.dto;

import java.util.Date;

public class Question_Board {
	//문의사항 게시판 게시글
	private int question_no; //문의사항 게시글 번호
	private String id; //작성자
	private String question_title; //제목
	private String question_content; //내용
	private int hits; //조회수
	private Date question_date; //작성일
	private int refer; // 게시글 그룹번호
	private int depth; //들여쓰기 정도
	private int step; //그룹 내 순번
	private int notice_no; //공지사항 유무( 0 일반게시글 | 1 공지사항)
	public Question_Board() {}
	public Question_Board(int question_no, String id, String question_title, String question_content, int hits,
			Date question_date, int refer, int depth, int step, int notice_no) {
		super();
		this.question_no = question_no;
		this.id = id;
		this.question_title = question_title;
		this.question_content = question_content;
		this.hits = hits;
		this.question_date = question_date;
		this.refer = refer;
		this.depth = depth;
		this.step = step;
		this.notice_no = notice_no;
	}
	public int getQuestion_no() {
		return question_no;
	}
	public void setQuestion_no(int question_no) {
		this.question_no = question_no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getQuestion_title() {
		return question_title;
	}
	public void setQuestion_title(String question_title) {
		this.question_title = question_title;
	}
	public String getQuestion_content() {
		return question_content;
	}
	public void setQuestion_content(String question_content) {
		this.question_content = question_content;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	public Date getQuestion_date() {
		return question_date;
	}
	public void setQuestion_date(Date question_date) {
		this.question_date = question_date;
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
	public int getNotice_no() {
		return notice_no;
	}
	public void setNotice_no(int notice_no) {
		this.notice_no = notice_no;
	}
	@Override
	public String toString() {
		return "Question_Board [question_no=" + question_no + ", id=" + id + ", question_title=" + question_title
				+ ", question_content=" + question_content + ", hits=" + hits + ", question_date=" + question_date
				+ ", refer=" + refer + ", depth=" + depth + ", step=" + step + ", notice_no=" + notice_no + "]";
	}
}
