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

import by.zhuk.dao.PersonDao;
import by.zhuk.models.Person;
import by.zhuk.utils.PersonValidator;

@Controller
@RequestMapping("/people")
public class PersonController {

	private PersonDao personDao;
	private final PersonValidator personValidator;

	@Autowired
	public PersonController(PersonDao personDao, PersonValidator personValidator) {
		this.personDao = personDao;
		this.personValidator = personValidator;
	}

	@GetMapping()
	public String index(Model model) {
		model.addAttribute("people", personDao.index());
		return "people/index";
	}

	@GetMapping("/new")
	public String newPerson(@ModelAttribute("person") Person person) {
		return "people/new";
	}

	@GetMapping("/{id}")
	public String show(@PathVariable("id") int id, Model model) {
		model.addAttribute("person", personDao.show(id));
		model.addAttribute("books", personDao.showBooks(id));
		return "people/show";
	}

	@PostMapping
	public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
		personValidator.validate(person, bindingResult);
		if (bindingResult.hasErrors()) {
			return "people/new";
		}
		personDao.save(person);
		return "redirect:/people";
	}

	@GetMapping("/{id}/edit")
	public String edit(Model model, @PathVariable("id") int id) {
		model.addAttribute("person", personDao.show(id));
		return "people/edit";
	}

	@PatchMapping("/{id}")
	public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult,
			@PathVariable("id") int id) {
		personValidator.validate(person, bindingResult);
		if (bindingResult.hasErrors()) {
			return "people/edit";
		}
		personDao.update(id, person);
		return "redirect:/people";
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") int id) {
		personDao.delete(id);
		return "redirect:/people";
	}
}
