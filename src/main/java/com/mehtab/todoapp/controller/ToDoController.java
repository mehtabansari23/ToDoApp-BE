package com.mehtab.todoapp.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mehtab.todoapp.model.ToDo;
import com.mehtab.todoapp.service.ToDoService;

@RestController
@RequestMapping("/ToDo")
public class ToDoController {

	@Autowired
	ToDoService toDoService;
	
	@GetMapping("/index")
	public String index() {
		return "ToDo App";
	}
	
	@GetMapping("/")
	public ResponseEntity<List<ToDo>> listAll() {
		List<ToDo> toDos = toDoService.listAll();
		if(toDos == null || toDos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(toDos);
	}
	
	@PostMapping("/")
	public ResponseEntity<ToDo> create(@RequestBody ToDo toDo) {
		ToDo createdToDo = toDoService.create(toDo);
		return new ResponseEntity<ToDo>(createdToDo, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/deleteByIds/{ids}")
	public ResponseEntity<Boolean> deleteByIds(@PathVariable("ids") Set<Integer> ids) {
		if(ids != null && !ids.isEmpty()) {
			toDoService.deleteByIds(ids);
			return ResponseEntity.ok(true);
		}
		return ResponseEntity.badRequest().build();
	}
	
	@PatchMapping("/update")
	public ResponseEntity<ToDo> update(@RequestBody ToDo toDo) {
		if(toDo.getId() != null && toDo.getId() > 0){
			ToDo updatedToDo = toDoService.update(toDo);
			return ResponseEntity.ok(updatedToDo);
		}
		return ResponseEntity.badRequest().build();
	}
}
