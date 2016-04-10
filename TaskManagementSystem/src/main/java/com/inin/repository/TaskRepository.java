package com.inin.repository;

import com.inin.dao.TaskDao;
import com.inin.domain.Comment;
import com.inin.domain.Status;
import com.inin.domain.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Deepak on 5/4/16.
 * Represents Task repository performs storing Task in system.
 */

@Repository
public class TaskRepository {

    @Autowired
    private TaskDao taskDao;

    private Map<Integer, Task> taskHashMap = new HashMap<>();

    public int add(Task task){
        //taskHashMap.put(task.getId(), task);
        return taskDao.insert(task);
    }

    public List<Task> getAllTask(){
        //return taskHashMap.values().stream().collect(Collectors.toList());
        return taskDao.getAll();
    }


    /**
     * This is responsible to get the task from repository on the basis of id provided.
     * */
    public Task getTask(int taskId){
        /*Optional<Task> taskOptional = taskHashMap.values().stream()
                .filter(task -> task.getId() == taskId)
                .findAny();

        if(taskOptional.isPresent()){
           return taskOptional.get();
        }*/

        return taskDao.get(taskId);

    }


    /**
     * This is resoponsible to add the comment on task in repository.
     * */
    public int saveComments(Comment comment, int tid){

/*        Task task = taskHashMap.get(tid);
        task.getComments().add(comment);
        return task;*/

        return taskDao.addComments(comment, tid);

    }


    /**
     * This is responsible to update the status of task
     * */
/*    public void updateStatus(int tid, Status status){

        taskHashMap.get(tid).setCurrentStatus(status);
    }*/


    /**
     * This is responsible to update task
     * */
    public int update(int tid, Task updatedTask){
        //return taskHashMap.put(tid, updatedTask);     // updating the task
        return taskDao.update(tid, updatedTask);
    }
}
