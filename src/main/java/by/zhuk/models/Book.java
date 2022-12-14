package by.zhuk.models;

import javax.validation.constraints.NotEmpty;

public class Book {

	private int id;

	@NotEmpty(message = "Name should not be empty")
	private String name;

	@NotEmpty(message = "Author should not be empty")
	private String author;

	private int year;

	public Book() {
	}

	public Book( String name, String author, int year) {
		this.name = name;
		this.author = author;
		this.year = year;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
