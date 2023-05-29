package com.mehtab.todoapp.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.mehtab.todoapp.model.ToDo;

@Service
public interface ToDoService {
	List<ToDo> listAll();
	
	ToDo create(ToDo toDo);
	
	ToDo update(ToDo toDo);
	
	void deleteByIds(Set<Integer> ids);
}
