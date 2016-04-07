package com.inin.tms.repositary;

import com.inin.tms.domain.Comment;
import com.inin.tms.domain.Task;
import com.inin.tms.serialization.TaskSerialization;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by root on 2/4/16.
 *
 */
@Repository
public class TaskRepository {
    // need to give serlizarion id :

    private Map<Integer, Task> taskMaster;

    public TaskRepository() {
        taskMaster = new HashMap<>();
    }

    /**
     * Saving task into the system.
     * @param task Task object to be saved in the system.
     * @return Task object which is saved in the system.
     */
    public Task save(Task task){
        taskMaster.put(task.getId(),task);
        TaskSerialization.serialize(taskMaster);
        return task;
    }

    /**
     * Finding a particular task using id
     * @param id Task id used to find a task
     * @return Task object
     */
    public Task get(String id) {
       /* taskMaster = TaskSerialization.deserialize();
        Optional<Task> taskOptional = taskMaster.values()
                                                        .stream()
                                                        .filter(task -> task.getId().equals(id))
                                                        .findAny();
        if(taskOptional.isPresent()){
            return taskOptional.get();
        }*/
        return null;
    }

    /**
     * find all available tasks in the system.
     * @return All tasks in the system.
     */
    public List<Task> getAll() {
        taskMaster = TaskSerialization.deserialize();
        return Collections.unmodifiableList(taskMaster.values()
                                                            .stream()
                                                            .collect(Collectors.toList()));
    }

    /**
     * Updating the task in the system
     * @param task Task object to uodate
     * @return Updated Task object
     */
    public Task update(Task task) {
        task.update();
        taskMaster.put(task.getId(), task);
        TaskSerialization.serialize(taskMaster);
        return task;
    }

    /**
     * Get all comments on the task
     * @param id Task id to get commenst on the task
     * @return List of Comment objects
     */
    public List<Comment> getComments(String id) {
        Task task = get(id);
       // return Collections.unmodifiableList(task.getComments());
        return null;
    }
}
