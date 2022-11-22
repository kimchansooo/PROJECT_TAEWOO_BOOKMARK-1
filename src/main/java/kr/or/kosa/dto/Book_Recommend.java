package kr.or.kosa.dto;

public class Book_Recommend {
	private Book book;
	private String recommend_content;

	public Book_Recommend(Book book, String recommend_content) {
		super();
		this.book = book;
		this.recommend_content = recommend_content;
	}

	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public String getRecommend_content() {
		return recommend_content;
	}
	public void setRecommend_content(String recommend_content) {
		this.recommend_content = recommend_content;
	}

	@Override
	public String toString() {
		return "Book_Recommend [book=" + book + ", recommend_content=" + recommend_content + "]";
	}

}
