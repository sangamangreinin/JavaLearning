package com.inin.taskmanager.controller;

import com.inin.taskmanager.TaskManagerApplication;
import com.inin.taskmanager.constant.TaskStatus;
import com.inin.taskmanager.domain.Comment;
import com.inin.taskmanager.domain.Task;
import com.inin.taskmanager.domain.User;
import com.inin.taskmanager.service.TaskService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by virendra on 5/4/16.
 * TaskControllerTest class.
 * This class is used to unit test the controller
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

        mockMvc = standaloneSetup(controller).build();

    }


    @Test
    public void create_draft_task() throws Exception {
        Long id = 1011L;
        User user = new User.Builder("user1").setUserId(id).create();
        Task task =
                new Task.Builder("test-task", "task for testing")
                        .setTaskId(id)
                        .setCreatedBy(user)
                        .setStatus(TaskStatus.DRAFT)
                        .setComments(new ArrayList<Comment>())
                        .create();

        when(taskService.createTask(task)).thenReturn(id);

        mockMvc.perform(post(BASE_URI))
                .andExpect(status().isCreated());
//                .andExpect(id);
    }
/*


    @Test
    @Ignore
    public void getTasks() throws Exception {

    }

    @Test
    @Ignore
    public void getTask() throws Exception {

    }


    @Test
    @Ignore
    public void updateTask() throws Exception {

    }

    @Test
    @Ignore
    public void comments() throws Exception {

    }
*/

}