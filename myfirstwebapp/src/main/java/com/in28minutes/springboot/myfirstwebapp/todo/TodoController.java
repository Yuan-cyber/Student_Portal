package com.in28minutes.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("name") //put into session scope
public class TodoController {
	
	private TodoService todoService;
	
	public TodoController(TodoService todoService) {
		super();
		this.todoService = todoService;
	}

	@RequestMapping("list-todos")
	public String listAllTodos(ModelMap model) {
		String username=getLoggedInUsername(model);
		
		List<Todo> todos=todoService.findTodos(username);
		model.addAttribute("todos",todos);
		
		return "listTodos";
	}
	
	@RequestMapping(value="add-todo", method=RequestMethod.GET)
	public String showNewTodoPage(ModelMap model) {
		
		//add an attribute called todo
		
		String username=getLoggedInUsername(model);
		//set default value
		Todo todo = new Todo(0,username,"",LocalDate.now().plusYears(1),false);
		
		model.put("todo", todo);
		
	    return "todo"; //todo.jsp
	}
	
	/**
	 * 
	 * @param description
	 * @param model
	 * @return
	 *
	@RequestMapping(value="add-todo", method=RequestMethod.POST)
	public String addNewTodo(@RequestParam String description,ModelMap model) {
	
		//get the username
		String username=(String)model.get("name");
		//add into the list
		todoService.addTodo(username,description,LocalDate.now().plusYears(1),false);
		
		//redirect to the url
		return "redirect:list-todos";
	}
	*/
		
	//use command bean, bind request to todo bean
	
	@RequestMapping(value="add-todo", method=RequestMethod.POST)
	public String addNewTodo(ModelMap model, @Valid Todo todo,BindingResult result) { 
		//this todo is the same as what in jsp page
		
		//validation
		if(result.hasErrors()) {
			return "todo";
		}
			
		String username=getLoggedInUsername(model);
		//add into the list
		todoService.addTodo(username,todo.getDescription(),todo.getTargetDate(),false);
		
		//redirect to the url
		return "redirect:list-todos";
	}
	
	@RequestMapping("delete-todo")
	public String deleteTodo(@RequestParam int id) { 
		
		todoService.deleteById(id);
		return "redirect:list-todos";
	}
	
	@RequestMapping("update-todo")
	public String showUpdateTodoPage(@RequestParam int id,ModelMap model) { 
		Todo todo=todoService.findById(id);
		model.addAttribute("todo",todo);
		return "todo";
	}
	
	@RequestMapping(value="update-todo",method=RequestMethod.POST)
	public String UpdateTodo(ModelMap model, @Valid Todo todo,BindingResult result) { 
		
		if(result.hasErrors()) {
			return "todo";
		}
			
		String username=getLoggedInUsername(model);
		todo.setUsername(username);
		todoService.updateTodo(todo);
		
		//redirect to the url
		return "redirect:list-todos";
	}
	
	private String getLoggedInUsername(ModelMap model) {
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		
		return authentication.getName();
	}
}
