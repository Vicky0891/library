package by.vicky.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {

	@NotEmpty(message = "Name should not be empty")
	private String name;

	@NotEmpty(message = "Author should not be empty")
	private String author;

	@NotEmpty(message = "Year should not be empty")
	@Size(min = 4, max = 4)
	private int year;

	public Book(String name, String author, int year) {
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

}
