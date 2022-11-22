package kr.or.kosa.action;

/*
servlet(front)요청 받아요
1.화면보여주세요
2.로직처리해주세요

화면 아니면 로직
 */

public class ActionForward {
	private boolean isRedirect = false; //뷰의 전환 여부 ... redirect or forward
	private String path = null; //이동경로 (뷰의 주소)
	
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	
}
