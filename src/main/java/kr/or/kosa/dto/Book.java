package kr.or.kosa.dto;

public class Book {
	//책 DTO
	private String isbn; //책코드
	private String book_name; //책이름
	private String description; //책설명
	private String file_name;//파일이름
	private int price;//가격
	private String book_filename;//표지파일이름
	public Book() {}
	public Book(String isbn, String book_name, String description, String file_name, int price, String book_filename) {
		super();
		this.isbn = isbn;
		this.book_name = book_name;
		this.description = description;
		this.file_name = file_name;
		this.price = price;
		this.book_filename = book_filename;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getBook_name() {
		return book_name;
	}
	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getBook_filename() {
		return book_filename;
	}
	public void setBook_filename(String book_filename) {
		this.book_filename = book_filename;
	}
	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", book_name=" + book_name + ", description=" + description + ", file_name="
				+ file_name + ", price=" + price + ", book_filename=" + book_filename + "]";
	}
}
