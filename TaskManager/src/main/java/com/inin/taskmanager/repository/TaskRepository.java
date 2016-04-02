package com.inin.taskmanager.repository;

import com.inin.taskmanager.domain.Task;
import org.springframework.stereotype.Repository;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

/**
 * Created by virendra on 1/4/16.
 * TaskRepository class.
 * This class performs the operation of serialization and deserialization of task
 * objects in the file
 */

@Repository
public class TaskRepository {

    public static final String TASK_FILE = "resources/tasks.ser";
    /**
     * ObjectInputStream is used to read objects from serialized file
     */
    private ObjectInputStream oIn;
    /**
     * ObjectOutputStream is used to write the object to the file
     */
    private ObjectOutputStream oOut;
    /**
     * list of task object
     */
    private Map<String, Task> tasks;

    /**
     * default constructor
     */
    public TaskRepository() {
        tasks = new TreeMap<>();
    }


    /**
     * connects the input stream and output stream to
     *
     * @throws IOException if application fails to connect file to streams
     */
    private void connectStream() throws IOException {

        oIn = new ObjectInputStream(new FileInputStream(TASK_FILE));
        oOut = new ObjectOutputStream(new FileOutputStream(TASK_FILE));

    }

    /**
     * closes the connection of the streams from the file
     *
     * @throws IOException if there is problem in closing the stream
     */
    private void disconnectStream() throws IOException {
        oIn.close();
        oOut.close();
    }

    /**
     * writes the objects in the file in serialized format
     *
     * @param objects Map of task objects
     * @throws IOException if stream encounters problem in connecting to file
     */
    private void writeObject(Map<String, Task> objects) throws IOException {
        if (objects.size() > 0) {
            connectStream();
            oOut.writeObject(objects);
            disconnectStream();
        }
    }

    /**
     * reads the serialized object from the file
     *
     * @throws IOException            if application encounters the problem in connecting to file
     * @throws ClassNotFoundException when the objects are not found the file
     */
    private void readObject() throws IOException, ClassNotFoundException {
        connectStream();
        this.tasks = (Map<String, Task>) oIn.readObject();
        disconnectStream();
    }

    /**
     * saves the task object and calls the method to store it in serialized format in file
     *
     * @param task Task object to be saved
     * @return Task object which is saved
     * @throws IOException if application encounters the problem in connecting to file
     */
    public Task save(Task task) throws IOException {
        tasks.put(task.getTaskId(), task);
        writeObject(tasks);
        return task;
    }

    /**
     * updates the task object in the pool
     *
     * @param task Task object
     * @return Task object
     * @throws IOException if application encounters the problem in connecting to file
     */
    public Task update(Task task) throws IOException {
        tasks.put(task.getTaskId(), task);
        writeObject(tasks);
        return task;
    }

    /**
     * finds the single task object from the pool of tasks
     *
     * @param id unique id of the task
     * @return List of task objects
     * @throws IOException            if application encounters the problem in connecting to file
     * @throws ClassNotFoundException when the objects are not found the file
     */
    public Task find(String id) throws IOException, ClassNotFoundException {
        readObject();
        Optional<Task> taskEntry =
                tasks.values()
                        .stream()
                        .filter(i -> i.getTaskId().equals(id))
                        .findAny();
        if (taskEntry.isPresent())
            return taskEntry.get();
        else
            return null;
    }

    /**
     * fetches all tasks entries present in the task pool
     *
     * @return List of task objects
     * @throws IOException            if application encounters the problem in connecting to file
     * @throws ClassNotFoundException when the objects are not found the file
     */
    public List<Task> findAll() throws IOException, ClassNotFoundException {
        readObject();
        return Collections.unmodifiableList((List<Task>) tasks.values());
    }

    /**
     * searches the specific tasks in the pool
     *
     * @return List of task objects
     * @throws IOException            if application encounters the problem in connecting to file
     * @throws ClassNotFoundException when the objects are not found the file
     */
    public List<Task> search() throws IOException, ClassNotFoundException {
        return findAll();
    }

}
