package com.developerboard.todolist.resource;

import com.developerboard.todolist.model.Todo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;

import static org.hamcrest.Matchers.is;

/**
 * Class Description
 *
 * @author Nana on 8/10/2019
 * @version 1.0
 * @see
 * @since 1.0
 */

@SpringBootTest
@AutoConfigureMockMvc
class TodoResourceIntegrationTest {

    private static final String UPDATED = "updated";
    private static final String BASE_URL = "/todos";
    private static final String BASE_URL_WITH_ID = "/todos/{id}";
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getTodoItemsIncorrectUrlTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/todosList"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }


    @Test
    void getAllTodoItemsTest(TestInfo testInfo) throws Exception {

        Todo todo1 = new Todo("Learn React Props", new Date(), false);

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post(BASE_URL)
                        .content(objectMapper.writeValueAsString(todo1))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());



        this.mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

    }

    @Test
    void getTodoByIdHappyPathTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL_WITH_ID, 1))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();
    }

    @Test
    void getTodoByIdNotExistTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/todos/2"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").doesNotExist());
    }

    @Test
    void createTodoHappyPathTest(TestInfo info) throws Exception {

        Todo todo1 = new Todo(info.getDisplayName(), new Date(), false);

        GeoregMethod(todo1);

    }

    private void GeoregMethod(Todo todo1) throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post(BASE_URL)
                        .content(objectMapper.writeValueAsString(todo1))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void updateTodoHappyPathTest(TestInfo info) throws Exception {

        // create and save todo
        Todo todo1 = new Todo(info.getDisplayName(), new Date(), false);

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post(BASE_URL)
                        .content(objectMapper.writeValueAsString(todo1))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());

        // update todo
        Todo updatedTodo = new Todo(info.getDisplayName().concat(UPDATED), new Date(), true);
        updatedTodo.setId(1L);

        this.mockMvc.perform(MockMvcRequestBuilders
                .put(BASE_URL)
                .content(objectMapper.writeValueAsString(updatedTodo))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andDo(MockMvcResultHandlers.print());

        // get modified todo and verify updated name
        this.mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL_WITH_ID, 1))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name",is(info.getDisplayName().concat(UPDATED))))
                .andReturn();

    }

    @Test
    @DisplayName("Test delete Todo endpoint")
    void deleteTodoHappyPathTest(TestInfo info) throws Exception {
        // create and save todo
        Todo saveTodo = new Todo(info.getDisplayName(), new Date(), false);

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post(BASE_URL)
                        .content(objectMapper.writeValueAsString(saveTodo))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());

        // delete saveTodo
        this.mockMvc.perform(MockMvcRequestBuilders
                .delete(BASE_URL_WITH_ID, 1))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andDo(MockMvcResultHandlers.print());

        // get modified todo and verify updated name
        this.mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL_WITH_ID, 1))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();

    }

}