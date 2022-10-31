package by.zhuk.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import by.zhuk.dao.BookDao;
import by.zhuk.dao.PersonDao;
import by.zhuk.models.Book;
import by.zhuk.models.Person;
import by.zhuk.utils.BookValidator;

@Controller
@RequestMapping("/books")
public class BookController {

	private final PersonDao personDao;
	private final BookDao bookDao;
	private final BookValidator bookValidator;

	@Autowired
	public BookController(BookDao bookDao, PersonDao personDao, BookValidator bookValidator) {
		this.bookDao = bookDao;
		this.personDao = personDao;
		this.bookValidator = bookValidator;
	}

	@GetMapping()
	public String index(Model model) {
		model.addAttribute("books", bookDao.index());
		return "books/index";
	}

	@GetMapping("/new")
	public String newBook(@ModelAttribute("book") Book book) {
		return "books/new";
	}

	@GetMapping("/{id}")
	public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
		model.addAttribute("book", bookDao.show(id));
		if (bookDao.showPerson(id) != null) {
			model.addAttribute("owner", bookDao.showPerson(id));
		} else {
			model.addAttribute("people", personDao.index());
		}
		return "books/show";
	}

	@PostMapping
	public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
		bookValidator.validate(book, bindingResult);
		if (bindingResult.hasErrors()) {
			return "books/new";
		}
		bookDao.save(book);
		return "redirect:/books";
	}

	@GetMapping("/{id}/edit")
	public String edit(Model model, @PathVariable("id") int id) {
		model.addAttribute("book", bookDao.show(id));
		return "books/edit";
	}

	@PatchMapping("/{id}")
	public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
			@PathVariable("id") int id) {
		bookValidator.validate(book, bindingResult);
		if (bindingResult.hasErrors()) {
			return "books/edit";
		}
		bookDao.update(id, book);
		return "redirect:/books";
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") int id) {
		bookDao.delete(id);
		return "redirect:/books";
	}

	@PatchMapping("/{id}/free")
	public String free(@PathVariable("id") int id) {
		bookDao.free(id);
		return "redirect:/books/" + id;
	}

	@PatchMapping("/{id}/assign")
	public String assign(@ModelAttribute("person") Person person, @PathVariable("id") int id) {
		bookDao.assign(id, person);
		return "redirect:/books/" + id;
	}

}
