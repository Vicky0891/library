package by.zhuk.models;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Person {

	private int id;

	@NotEmpty(message = "Full name should not be empty")
	@Size(min = 2, max = 30, message = "Name should be between 2 and 40 characters")
	private String fullName;

	@Max(value = 2010, message = "Year should be more than 2010")
	private int yearOfBirth;

	private List<Book> books;

	public Person() {
	}

	public Person(int id, String fullName, int yearOfBirth, List<Book> books) {
		this.id = id;
		this.fullName = fullName;
		this.yearOfBirth = yearOfBirth;
		this.books = books;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getYearOfBirth() {
		return yearOfBirth;
	}

	public void setYearOfBirth(int yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

}
