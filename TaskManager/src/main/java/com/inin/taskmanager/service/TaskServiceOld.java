package com.inin.taskmanager.service;

import com.inin.taskmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by virendra on 1/4/16.
 * TaskService class.
 * Service class which provides the service to the caller for the tasks.
 */
@Service
@Scope("singleton")
public class TaskServiceOld {

    /**
     * taskRepository property stores TaskRepository object instance .
     */
    @Autowired
    TaskRepository taskRepository;

    /**
     * default constructor for ticket service
     */
    public TaskServiceOld() {

    }
/*
    *//**
     * gets the task list present in the application
     * @return List of task objects
     * @throws IOException
     * @throws ClassNotFoundException
     *//*
    public List<Task> getTasks() throws IOException, ClassNotFoundException {
        try {
            List<Task> entries = taskRepository.findAll();
            return entries;
        } catch (IOException | ClassNotFoundException e) {
            throw e;
        }
    }

    *//**
     * gets the task object
     * @param taskId String task id
     * @return Task object
     * @throws IOException
     * @throws ClassNotFoundException
     *//*
    public Task getTask(String taskId) throws IOException, ClassNotFoundException {
        return taskRepository.find(taskId);
    }

    *//**
     * creates the new task in the application
     * @param task Task object
     * @return Task object
     * @throws IOException if server encounters problem
     * @throws IllegalAccessException if user supplies invalid input
     *//*
    public Task createTask(Task task) throws IOException, IllegalAccessException {
        if (validateInput(task)){
            setValue(task);
            return taskRepository.save(task);
        }
        else
            throw new IllegalAccessException("Invalid input supplied");
    }

    *//**
     * formats data in proper way to be stored
     * @param task Task object
     * @throws IllegalAccessException
     *//*
    private void setValue(Task task) throws IllegalAccessException {

        Class clazz = task.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields){

            field.setAccessible(true);
            switch (field.getName().toLowerCase()){
                case "createdby":
                case "assignedto":
                    User user = (User) field.get(task);
                    if(user.getUserId() == null) user.save();
                    else user.update();
                    field.set(task, user);
                    break;
                case "comments":
                    List<Comment> temp = (List<Comment>)field.get(task);
                    for (Comment c: temp){
                        if (c.getCommentId() == null) {
                            c.setCommentBy(task.getCreatedBy());
                            c.save();
                        }
                        else c.update();
                    }
                    field.set(task, temp);
                    break;
                case "endDate":
                    break;
            }
        }

    }

    *//**
     * validates the input
     * @param task Task object
     * @return boolean flag
     * @throws IllegalAccessException if the
     *//*
    private boolean validateInput(Task task) throws IllegalAccessException {

        List<String> required = Arrays.asList("title", "description", "createdBy", "status");
        boolean flag = true;

        Class clazz = task.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields){
            field.setAccessible(true);
            if (required.contains(field.getName()) && field.get(task) == null){
                flag = false;
                break;
            }
        }

        return flag;
    }

    *//**
     * retrieves the comment form the task
     * @param id task id for which comments need to be retrieved
     * @return List of comments
     * @throws IOException if
     * @throws ClassNotFoundException
     *//*
    public List<Comment> getComments(String id ) throws IOException, ClassNotFoundException {
        Task task = taskRepository.find(id);
        if (task == null)
            return null;
        return taskRepository.getComments(id);
    }

    public List<Task> query(TaskQueryRequest request) throws IOException, ClassNotFoundException {
        return taskRepository.searchComments(request);
    }*/
}
