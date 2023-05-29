package com.mehtab.todoapp.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
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

import com.mehtab.todoapp.dao.ToDoDao;
import com.mehtab.todoapp.model.ToDo;
import com.mehtab.todoapp.serviceimpl.ToDoServiceImpl;

@ExtendWith({SpringExtension.class})
public class ToDoServiceTest {

    @InjectMocks
    ToDoService toDoService = new ToDoServiceImpl();

    @Mock
    ToDoDao toDoDao;

    private ToDo toDo;
    
    @BeforeEach
    void setup() {
    	toDo = new ToDo(1, "test1", false);
    }
    
    @Test
    void listAll_test_success() throws Exception {
    	when(toDoDao.listAll()).thenReturn(List.of(toDo));
    	
    	List<ToDo> toDos = toDoService.listAll();
    	assertEquals(toDos.get(0).getName(), "test1");
    }
    
    @Test
    void listAll_test_failure() throws Exception {
    	when(toDoDao.listAll()).thenReturn(List.of());
    	
    	List<ToDo> toDos = toDoService.listAll();
    	assertTrue(toDos.isEmpty());
    }
    
    @Test
    void create_test_success() throws Exception {
    	when(toDoDao.create(any(ToDo.class))).thenReturn(toDo);
    	
    	ToDo toDo2 = toDoService.create(toDo);
    	assertEquals(toDo2.getName(), "test1");
    }
    
    @Test
    void create_test_failure() throws Exception {
    	when(toDoDao.create(any(ToDo.class))).thenReturn(null);
    	ToDo toDo = toDoService.create(new ToDo());
    	assertNull(toDo);
    }
    
    @Test
    void update_test_success() throws Exception {
    	when(toDoDao.update(any(ToDo.class))).thenReturn(toDo);
    	
    	ToDo toDo2 = toDoService.update(toDo);
    	assertEquals(toDo2.getName(), "test1");
    }
    
    @Test
    void update_test_failure() throws Exception {
    	when(toDoDao.update(any(ToDo.class))).thenReturn(null);
    	
    	ToDo toDo = toDoService.update(new ToDo());
    	assertNull(toDo);
    }
    
    @Test
    void delete_test_success() throws Exception {
    	doNothing().when(toDoDao).deleteByIds(anySet());
    	assertDoesNotThrow(()-> toDoService.deleteByIds(Set.of(1,2)));
    }
    
    @Test
    void delete_test_failure() throws Exception {
    	doThrow(IllegalArgumentException.class).when(toDoDao).deleteByIds(anySet());
    	IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, ()->{ 
    		toDoService.deleteByIds(Sets.set(1,2));
    	});
    	assertEquals(null, ex.getMessage());
    }
    
}
