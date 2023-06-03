package com.mehtab.todoapp.serviceimpl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mehtab.todoapp.dao.ToDoDao;
import com.mehtab.todoapp.model.ToDo;
import com.mehtab.todoapp.service.ToDoService;

@Service
public class ToDoServiceImpl implements ToDoService {

	@Autowired ToDoDao toDoDao;
	
	@Override
	public List<ToDo> listAll() {
		return toDoDao.listAll();
	}

	@Override
	public ToDo create(ToDo toDo) {
		return toDoDao.create(toDo);
	}

	@Override
	public List<ToDo> update(List<ToDo> toDos) {
		return toDoDao.update(toDos);
	}

	@Override
	public void deleteByIds(Set<Integer> ids) {
		toDoDao.deleteByIds(ids);
		
	}

}
