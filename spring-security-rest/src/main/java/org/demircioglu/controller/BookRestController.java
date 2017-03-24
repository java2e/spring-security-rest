package org.demircioglu.controller;

import java.util.Date;
import java.util.List;

import org.demircioglu.model.Book;
import org.demircioglu.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * CRUD Controller
 * @author mdemircioglu
 *
 */

@RestController
@RequestMapping("/api/book")
public class BookRestController {

	/**
	 * IoC jpa spec
	 */
	@Autowired
	private BookRepo bookRepository;


	/**
	 * retrieve get all list of model by jpa findAll method
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET)
	public List<Book> findAll()
	{
		return bookRepository.findAll();
	}
	
	/**
	 * add a model data by saveAndFlush method
	 * @param book
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST)
	public Book add(@RequestBody Book book)
	{
		Book model=new  Book();
		model.setCreatedAt(new Date());
		model.setTitle(book.getDescription());
		model.setDescription(book.getDescription());
		
		return bookRepository.saveAndFlush(model);
		
	}
	/**
	 * return one model with id parameter by JPA findOne method
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public Book findOne(@PathVariable long id)
	{
		return bookRepository.findOne(id);
	}
	
	/**
	 * update and retrieve book model with id and given Book data by JPA
	 * @param id
	 * @param book
	 * @return
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public Book update(@PathVariable long id,@RequestBody Book book)
	{
		Book model=bookRepository.findOne(id);
		
		if(model!=null)
		{
			model.setTitle(book.getTitle());
			model.setDescription(book.getDescription());
			return bookRepository.saveAndFlush(model);
		}
		
		return null;
		
	}
	
	/**
	 * delete model in order to id by JPA delete method
	 * @param id
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public void delete(@PathVariable long id)
	{
		bookRepository.delete(id);
	}
	
	
	
	
	
	
	
	
	





}
