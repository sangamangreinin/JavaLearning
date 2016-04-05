package com.inin.taskmanager.repository;

import com.inin.taskmanager.dao.TaskQueryRequest;
import com.inin.taskmanager.domain.Comment;
import com.inin.taskmanager.domain.Task;
import org.springframework.stereotype.Repository;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by virendra on 1/4/16.
 * TaskRepository class.
 * This class performs the operation of serialization and deserialization of task
 * objects in the file
 */

@Repository
public class TaskRepositoryOld {

    public static final String TASK_FILE = "src/main/resources/tasks.ser";
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
    public TaskRepositoryOld() {
        tasks = new HashMap<>();
        File file = new File(TASK_FILE);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * connects the input stream and output stream to
     *
     * @throws IOException if application fails to connect file to streams
     */
    private void connectReader() throws IOException {
        oIn = new ObjectInputStream(new FileInputStream(TASK_FILE));
    }
    /**
     * connects the input stream and output stream to
     *
     * @throws IOException if application fails to connect file to streams
     */
    private void connectWriter() throws IOException {
        oOut = new ObjectOutputStream(new FileOutputStream(TASK_FILE));
    }

    /**
     * closes the connection of the streams from the file
     *
     * @throws IOException if there is problem in closing the stream
     */
    private void disconnectReader() throws IOException {
        if (oIn!= null)     oIn.close();
    }
    /**
     * closes the connection of the streams from the file
     *
     * @throws IOException if there is problem in closing the stream
     */
    private void disconnectWriter() throws IOException {
        if (oOut!= null)    oOut.close();
    }

    /**
     * writes the objects in the file in serialized format
     *
     * @param objects Map of task objects
     * @throws IOException if stream encounters problem in connecting to file
     */
    private void writeObject(Map<String, Task> objects) throws IOException {
        if (objects.size() > 0) {
            connectWriter();
            oOut.writeObject(objects);
            disconnectWriter();
        }
    }

    /**
     * reads the serialized object from the file
     *
     * @throws IOException            if application encounters the problem in connecting to file
     * @throws ClassNotFoundException when the objects are not found the file
     */
    private void readObject() throws IOException, ClassNotFoundException {
        try{
            connectReader();
            this.tasks = (Map<String, Task>) oIn.readObject();
        }catch (EOFException e){
            this.tasks = new HashMap();
        }finally {
            disconnectReader();
        }
    }

    /**
     * saves the task object and calls the method to store it in serialized format in file
     *
     * @param task Task object to be saved
     * @return Task object which is saved
     * @throws IOException if application encounters the problem in connecting to file
     */
    public Task save(Task task) throws IOException {
        task.save();
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
        task.update();
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
        return Collections.unmodifiableList(tasks.values().stream().collect(Collectors.toList()));
    }

    /**
     * searches the specific tasks in the pool
     *
     * @return List of task objects
     * @throws IOException            if application encounters the problem in connecting to file
     * @throws ClassNotFoundException when the objects are not found the file
     * @param request
     */
    public List<Task> search(TaskQueryRequest request) throws IOException, ClassNotFoundException {
        readObject();
        List<Task> obj = tasks.values().stream().filter(i->i.getStatus().equals(request.getStatus())).collect(Collectors.toList());
        return obj;
    }

    public List<Comment> getComments(String id) throws IOException, ClassNotFoundException {
        Task task = find(id);
        return Collections.unmodifiableList(task.getComments());
    }
}
