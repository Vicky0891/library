package by.zhuk.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import by.zhuk.models.Book;
import by.zhuk.models.Person;

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

	public Person showPerson(int id) {
		return jdbcTemplate.query("SELECT * FROM book b JOIN person p ON p.id=b.person_id WHERE b.id=?",
				new Object[] { id }, new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
	}

	public Optional<Book> show(String name) {
		return jdbcTemplate.query("SELECT * FROM book WHERE name=?", new Object[] { name },
				new BeanPropertyRowMapper<>(Book.class)).stream().findAny();
	}

	public List<Book> index() {
		return jdbcTemplate.query("SELECT * FROM book", new BeanPropertyRowMapper<>(Book.class));
	}

	public Optional<Book> index(int person_id) {
		return jdbcTemplate.query("SELECT * FROM book WHERE person_id=?", new Object[] { person_id },
				new BeanPropertyRowMapper<>(Book.class)).stream().findAny();
	}

	public void save(Book book) {
		jdbcTemplate.update("INSERT INTO book (name, author, year) VALUES (?, ?, ?)", book.getName(), book.getAuthor(),
				book.getYear());
	}

	public void update(int id, Book updatedBook) {
		jdbcTemplate.update("UPDATE book SET name=?, author=?, year=? WHERE id=?", updatedBook.getName(),
				updatedBook.getAuthor(), updatedBook.getYear(), id);
	}

	public void assign(int id, Person assignedPerson) {
		jdbcTemplate.update("UPDATE book SET person_id=? WHERE id=?", assignedPerson.getId(), id);
	}

	public void delete(int id) {
		jdbcTemplate.update("DELETE FROM book WHERE id=?", id);
	}

	public void free(int id) {
		jdbcTemplate.update("UPDATE book SET person_id=NULL WHERE id=?", id);
		
	}

}
