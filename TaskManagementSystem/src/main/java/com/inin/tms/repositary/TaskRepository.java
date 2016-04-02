package com.inin.tms.repositary;

import com.inin.tms.domain.Task;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by root on 2/4/16.
 *
 */
@Repository
public class TaskRepository {
    private Map<String, Task> taskMaster;

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
        return task;
    }

    /**
     * Finding a particular task using id
     * @param id Task id used to find a task
     * @return Task object
     */
    public Task find(String id) {
        Optional<Task> taskOptional = taskMaster.values()
                                                        .stream()
                                                        .filter(task -> task.getId() == id)
                                                        .findAny();
        if(taskOptional.isPresent()){
            return taskOptional.get();
        }
        return null;
    }

    /**
     * find all avialable tasks in the system.
     * @return All tasks in the system.
     */
    public List<Task> findAll() {
        return Collections.unmodifiableList(taskMaster.values()
                                                            .stream()
                                                            .collect(Collectors.toList()));
    }
}
