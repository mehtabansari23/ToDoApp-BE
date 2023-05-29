package com.mehtab.todoapp.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Service;

import com.mehtab.todoapp.model.ToDo;

@Service
public interface ToDoRepo extends ListCrudRepository<ToDo, Integer> {

}
	