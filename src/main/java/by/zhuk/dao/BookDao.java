package by.zhuk.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import by.zhuk.models.Book;

@Component
public class BookDao {
	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public BookDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public Book show(int id) {
		return jdbcTemplate
				.query("SELECT * FROM book WHERE id=?", new Object[] { id }, new BeanPropertyRowMapper<>(Book.class))
				.stream().findAny().orElse(null);
	}

	public List<Book> index() {
		return jdbcTemplate.query("SELECT * FROM book", new BeanPropertyRowMapper<>(Book.class));
	}

	public Optional<Book> index(int person_id) {
		return jdbcTemplate.query("SELECT * FROM book WHERE person_id=?", new Object[] { person_id },
				new BeanPropertyRowMapper<>(Book.class)).stream().findAny();
	}

	public void save(Book book) {
		jdbcTemplate.update("INSERT INTO book (name, author, year) VALUES (?, ?)", book.getName(), book.getAuthor(),
				book.getYear());
	}

	public void update(int id, Book updatedBook) {
		jdbcTemplate.update("UPDATE book SET name=?, author=?, year=? WHERE id=?", updatedBook.getName(),
				updatedBook.getAuthor(), updatedBook.getYear(), id);
	}

	public void delete(int id) {
		jdbcTemplate.update("DELETE FROM book WHERE id=?", id);
	}

}
