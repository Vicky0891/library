package by.vicky.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Person {

	@NotEmpty(message = "Full name should not be empty")
	@Size(min = 2, max = 30, message = "Name should be between 2 and 40 characters")
	private String fullName;

	@Max(value = 2010, message = "Year should be more than 2010")
	private int yearOfBirth;

	public Person(String fullName, int yearOfBirth) {
		this.fullName = fullName;
		this.yearOfBirth = yearOfBirth;
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

}
