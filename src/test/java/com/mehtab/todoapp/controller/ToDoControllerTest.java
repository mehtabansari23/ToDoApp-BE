package com.mehtab.todoapp.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Set;

import org.assertj.core.util.Sets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.mehtab.todoapp.model.ToDo;
import com.mehtab.todoapp.service.ToDoService;

@ExtendWith({SpringExtension.class})
public class ToDoControllerTest {
	
	@InjectMocks
    ToDoController toDoController;
    
    @Mock
    ToDoService toDoService;
    
    private ToDo toDo;
    
    @BeforeEach
    void setup() {
    	toDo = new ToDo(1, "test1", false);
    }
    
    @Test
    void index_test() throws Exception {
    	assertEquals(toDoController.index(), "ToDo App");
    }

    @Test
    void listAll_test_success() throws Exception {
    	when(toDoService.listAll()).thenReturn(List.of(toDo));
    	
    	ResponseEntity<List<ToDo>> toDoResp = toDoController.listAll();
    	assertEquals(toDoResp.getBody().get(0).getName(), "test1");
    	assertEquals(toDoResp.getStatusCode(), HttpStatus.OK);
    }
    
    @Test
    void listAll_test_failure() throws Exception {

    	when(toDoService.listAll()).thenReturn(List.of());
    	
    	ResponseEntity<List<ToDo>> toDoResp = toDoController.listAll();
    	assertNull(toDoResp.getBody());
    	assertEquals(toDoResp.getStatusCode(), HttpStatus.NO_CONTENT);
    }
    
    @Test
    void create_test_success() throws Exception {
    	
    	when(toDoService.create(any(ToDo.class))).thenReturn(toDo);
    	
    	ResponseEntity<ToDo> toDoResp = toDoController.create(toDo);
    	assertEquals(toDoResp.getBody().getName(), "test1");
    	assertEquals(toDoResp.getStatusCode(), HttpStatus.CREATED);
    }
    
    @Test
    void delete_test_success() throws Exception {
    	ResponseEntity<Boolean> resp = toDoController.deleteByIds(Sets.set(1,2,3));
    	assertTrue(resp.getBody());
    	assertEquals(resp.getStatusCode(), HttpStatus.OK);
    }
    
    @Test
    void create_update_success() throws Exception {
    	when(toDoService.update(anyList())).thenReturn(List.of(toDo));
    	
    	ResponseEntity<List<ToDo>> toDoResp = toDoController.update(Set.of(toDo));
    	assertEquals(toDoResp.getBody().get(0).getName(), "test1");
    	assertEquals(toDoResp.getStatusCode(), HttpStatus.OK);
    }
    
}