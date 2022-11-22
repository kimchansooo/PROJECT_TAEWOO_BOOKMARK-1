package kr.or.kosa.dto;

import java.util.Date;

public class Calendar {
	//일정 DTO
	private int calendar_no; //일정번호
	private String id; //작성자
	private Date calendar_start; //시작일
	private Date calendar_end; //종료일
	private String calendar_content; //일정 내용
	private int calendar_status; //상태 (진행중 0 | 성공 1 | 실패 2)
	public Calendar(){}
	public Calendar(int calendar_no, String id, Date calendar_start, Date calendar_end, String calendar_content,
			int calendar_status) {
		super();
		this.calendar_no = calendar_no;
		this.id = id;
		this.calendar_start = calendar_start;
		this.calendar_end = calendar_end;
		this.calendar_content = calendar_content;
		this.calendar_status = calendar_status;
	}
	public int getCalendar_no() {
		return calendar_no;
	}
	public void setCalendar_no(int calendar_no) {
		this.calendar_no = calendar_no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getCalendar_start() {
		return calendar_start;
	}
	public void setCalendar_start(Date calendar_start) {
		this.calendar_start = calendar_start;
	}
	public Date getCalendar_end() {
		return calendar_end;
	}
	public void setCalendar_end(Date calendar_end) {
		this.calendar_end = calendar_end;
	}
	public String getCalendar_content() {
		return calendar_content;
	}
	public void setCalendar_content(String calendar_content) {
		this.calendar_content = calendar_content;
	}
	public int getCalendar_status() {
		return calendar_status;
	}
	public void setCalendar_status(int calendar_status) {
		this.calendar_status = calendar_status;
	}
	@Override
	public String toString() {
		return "Calendar [calendar_no=" + calendar_no + ", id=" + id + ", calendar_start=" + calendar_start
				+ ", calendar_end=" + calendar_end + ", calendar_content=" + calendar_content + ", calendar_status="
				+ calendar_status + "]";
	}
}
