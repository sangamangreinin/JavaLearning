package com.inin.taskmanager.resources;

import com.inin.taskmanager.TaskManagerApplication;
import com.inin.taskmanager.constants.TaskStatus;
import com.inin.taskmanager.domain.Comment;
import com.inin.taskmanager.domain.Task;
import com.inin.taskmanager.domain.User;
import com.inin.taskmanager.service.TaskService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by virendra on 5/4/16.
 * TaskControllerTest class.
 * This class is used to unit test the controllers
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TaskManagerApplication.class)
public class TaskControllerTest {

    private static final String BASE_URI = "/taskmanager/api/tasks";

    private MockMvc mockMvc;

    @Mock
    TaskService taskService;

    @InjectMocks
    TaskController controller;


    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);

        standaloneSetup(controller).build();

    }



    @Test
    public void create_draft_task() throws Exception {
        User user = new User.Builder("user1").setUserId("uid-1011").create();
        Task task =
                new Task.Builder("test-task", "task for testing")
                        .setTaskId("T-1011")
                        .setCreatedBy(user)
                        .setStatus(TaskStatus.DRAFT)
                        .setComments(new ArrayList<Comment>())
                        .create();

        when(taskService.createTask(task)).thenReturn(1);

        mockMvc.perform(post(BASE_URI))
                .andExpect(status().isCreated());
    }


    @Test
    public void getTasks() throws Exception {

    }

    @Test
    public void getTask() throws Exception {

    }


    @Test
    public void updateTask() throws Exception {

    }

    @Test
    public void comments() throws Exception {

    }

}