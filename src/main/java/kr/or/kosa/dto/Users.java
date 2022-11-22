package kr.or.kosa.dto;

public class Users {
	//회원정보 DTO
	private String id; //아이디
	private String password; //비밀번호
	private String name; //이름
	private String nickname;//별명
	private int state;//관리자여부( 0 일반유저 | 1 관리자
	private String addr; //주소
	private String detail_addr; //상세주소
	private String regist_no; //주민번호
	private String phone;//전화번호
	public Users(String id, String password, String name, String nickname, int state, String addr, String detail_addr,
			String regist_no, String phone) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.nickname = nickname;
		this.state = state;
		this.addr = addr;
		this.detail_addr = detail_addr;
		this.regist_no = regist_no;
		this.phone = phone;
	}
	public Users() {}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getDetail_addr() {
		return detail_addr;
	}
	public void setDetail_addr(String detail_addr) {
		this.detail_addr = detail_addr;
	}
	public String getRegist_no() {
		return regist_no;
	}
	public void setRegist_no(String regist_no) {
		this.regist_no = regist_no;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "Users [id=" + id + ", password=" + password + ", name=" + name + ", nickname=" + nickname + ", state="
				+ state + ", addr=" + addr + ", detail_addr=" + detail_addr + ", regist_no=" + regist_no + ", phone="
				+ phone + "]";
	}
	
}
