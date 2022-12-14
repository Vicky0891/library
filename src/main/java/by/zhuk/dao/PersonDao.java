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
public class PersonDao {
	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public PersonDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public Person show(int id) {
		return jdbcTemplate.query("SELECT * FROM person WHERE id=?", new Object[] { id },
				new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
	}

	public List<Book> showBooks(int id) {
		return jdbcTemplate.query("SELECT * FROM person p JOIN book b ON p.id=b.person_id WHERE p.id=?",
				new Object[] { id }, new BeanPropertyRowMapper<>(Book.class)).stream().toList();
	}

	public Optional<Person> show(String fullName) {
		return jdbcTemplate.query("SELECT * FROM person WHERE full_name=?", new Object[] { fullName },
				new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
	}

	public List<Person> index() {
		return jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person.class));
	}

	public void save(Person person) {
		jdbcTemplate.update("INSERT INTO person (full_name, year_of_birth) VALUES (?, ?)", person.getFullName(),
				person.getYearOfBirth());
	}

	public void update(int id, Person updatedPerson) {
		jdbcTemplate.update("UPDATE person SET full_name=?, year_of_birth=? WHERE id=?", updatedPerson.getFullName(),
				updatedPerson.getYearOfBirth(), id);
	}

	public void delete(int id) {
		jdbcTemplate.update("DELETE FROM person WHERE id=?", id);
	}

}
