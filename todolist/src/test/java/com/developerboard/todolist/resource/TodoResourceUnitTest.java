package com.developerboard.todolist.resource;

import com.developerboard.todolist.model.Todo;
import com.developerboard.todolist.service.TodoService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Class Description
 *
 * @author Nana on 8/15/2019
 * @version 1.0
 * @see
 * @since 1.0
 */
@WebMvcTest(TodoResource.class)
@ExtendWith(MockitoExtension.class)
public class TodoResourceUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TodoService todoService;

    private List<Todo> todoList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        // create Todo
        todoList.add(new Todo("Go to the Gym", new Date(), true));
        todoList.add(new Todo("Learn React Props", new Date(), false));
    }

    @Test
    void getAllTodoHappyPath() throws Exception {

        // setup Mock condition
        Mockito.when(todoService.getAllTodoItems())
                .thenReturn(todoList);

        // Perform endPoint request and Verify
        mockMvc.perform(MockMvcRequestBuilders.get("/todos")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()", Matchers.is(2)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

    }

    @Test
    @DisplayName("Test should retrieve a single todo item")
    void getAllTodoByIdTest() throws Exception {

        // setup Mock condition
        Mockito.when(todoService.getTodoById(1L))
                .thenReturn(todoList.get(1));

        // Perform endPoint request and Verify
        mockMvc.perform(MockMvcRequestBuilders.get("/todos/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Learn React Props")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.completed", Matchers.is(false)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

    }

    @Test
    @Disabled("CreateTodoTest is disable")
    void createTodoTest() throws Exception {
        Todo todo = new Todo("Learn how to learn faster", new Date(), true);
        Mockito.when(todoService.saveOrUpdate(todo))
                .thenReturn(todo);

        // Perform endPoint request and Verify
        mockMvc.perform(MockMvcRequestBuilders.get("/todos")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Learn how to learn faster")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.completed", Matchers.is(true)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

    }

}
