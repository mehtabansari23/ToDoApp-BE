package com.mehtab.todoapp.daoimpl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mehtab.todoapp.dao.ToDoDao;
import com.mehtab.todoapp.model.ToDo;
import com.mehtab.todoapp.repository.ToDoRepo;

@Repository
public class ToDoDaoImpl implements ToDoDao {

	@Autowired
	ToDoRepo toDoRepo;
	
	@Override
	public List<ToDo> listAll() {
		return toDoRepo.findAll();
	}

	@Override
	public ToDo create(ToDo toDo) {
		return toDoRepo.save(toDo);
	}

	@Override
	public List<ToDo> update(List<ToDo> toDos) {
		return toDoRepo.saveAll(toDos);
	}

	@Override
	public void deleteByIds(Set<Integer> ids) {
		toDoRepo.deleteAllById(ids);	
	}
		
}
