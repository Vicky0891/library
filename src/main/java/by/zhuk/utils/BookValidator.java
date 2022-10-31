package by.zhuk.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import by.zhuk.dao.BookDao;
import by.zhuk.models.Book;

@Component
public class BookValidator implements Validator{

	private final BookDao bookDao;
	
	@Autowired
	public BookValidator(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Book.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Book book = (Book) target;
		if(bookDao.show(book.getName()).isPresent() && (bookDao.show(book.getAuthor()).isPresent())) {
			errors.rejectValue("Name", "", "Book with same name and author already exists");
		}
	}
}
