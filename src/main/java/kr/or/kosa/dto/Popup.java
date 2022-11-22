package kr.or.kosa.dto;

import java.util.Date;

public class Popup {
	//팝업 공지사항 DTO
	private int popup_no;//팝업공지사항번호
	private String id;//작성자
	private String popup_title;//팝업제목(관리용)
	private String popup_filename;//팝업이미지(공지내용)
	private Date popup_date;//기간 ~11.30
	public Popup(int popup_no, String id, String popup_title, String popup_filename, Date popup_date) {
		super();
		this.popup_no = popup_no;
		this.id = id;
		this.popup_title = popup_title;
		this.popup_filename = popup_filename;
		this.popup_date = popup_date;
	}
	public Popup() {}
	public int getPopup_no() {
		return popup_no;
	}
	public void setPopup_no(int popup_no) {
		this.popup_no = popup_no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPopup_title() {
		return popup_title;
	}
	public void setPopup_title(String popup_title) {
		this.popup_title = popup_title;
	}
	public String getPopup_filename() {
		return popup_filename;
	}
	public void setPopup_filename(String popup_filename) {
		this.popup_filename = popup_filename;
	}
	public Date getPopup_date() {
		return popup_date;
	}
	public void setPopup_date(Date popup_date) {
		this.popup_date = popup_date;
	}
	@Override
	public String toString() {
		return "Popup [popup_no=" + popup_no + ", id=" + id + ", popup_title=" + popup_title + ", popup_filename="
				+ popup_filename + ", popup_date=" + popup_date + "]";
	}
	
}
