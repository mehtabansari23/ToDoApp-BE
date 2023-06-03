package com.mehtab.todoapp.dao;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Set;

import org.assertj.core.util.Sets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.mehtab.todoapp.daoimpl.ToDoDaoImpl;
import com.mehtab.todoapp.model.ToDo;
import com.mehtab.todoapp.repository.ToDoRepo;

@ExtendWith({SpringExtension.class})
public class ToDoDaoTest {

    @InjectMocks
    ToDoDao toDoDao = new ToDoDaoImpl();

    @Mock
    ToDoRepo toDoRepo;
    
    private ToDo toDo;
    
    @BeforeEach
    void setup() {
    	toDo = new ToDo(1, "test1", false);
    }
    
    @Test
    void listAll_test_success() throws Exception {
    	when(toDoRepo.findAll()).thenReturn(List.of(toDo));
    	
    	List<ToDo> toDos = toDoDao.listAll();
    	assertEquals(toDos.get(0).getName(), "test1");
    }
    
    @Test
    void listAll_test_failure() throws Exception {
    	when(toDoRepo.findAll()).thenReturn(List.of());
    	
    	List<ToDo> toDos = toDoDao.listAll();
    	assertTrue(toDos.isEmpty());
    }
    
    @Test
    void create_test_success() throws Exception {
    	when(toDoRepo.save(any(ToDo.class))).thenReturn(toDo);
    	
    	ToDo toDo2 = toDoDao.create(toDo);
    	assertEquals(toDo2.getName(), "test1");
    }
    
    @Test
    void create_test_failure() throws Exception {
    	when(toDoRepo.save(any(ToDo.class))).thenReturn(null);
    	ToDo toDo = toDoDao.create(new ToDo());
    	assertNull(toDo);
    }
    
    @Test
    void update_test_success() throws Exception {
    	when(toDoRepo.saveAll(anyList())).thenReturn(List.of(toDo));
    	
    	List<ToDo> toDo2 = toDoDao.update(List.of(toDo));
    	assertEquals(toDo2.get(0).getName(), "test1");
    }
    
    @Test
    void update_test_failure() throws Exception {
    	when(toDoRepo.save(any(ToDo.class))).thenReturn(null);
    	
    	List<ToDo> toDo = toDoDao.update(List.of(new ToDo()));
    	assertNull(toDo);
    }
    
    @Test
    void delete_test_success() throws Exception {
    	doNothing().when(toDoRepo).deleteAllById(anySet());
    	assertDoesNotThrow(()-> toDoDao.deleteByIds(Set.of(1,2)));
    }
    
    @Test
    void delete_test_failure() throws Exception {
    	doThrow(IllegalArgumentException.class).when(toDoRepo).deleteAllById(anySet());
    	IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, ()->{ 
    		toDoDao.deleteByIds(Sets.set(1,2));
    	});
    	assertEquals(null, ex.getMessage());
    }
    
}

