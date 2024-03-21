package com.example.demo.service;

import com.example.demo.entities.TaskEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

//this service class manages tasks, it provides methods for adding, getting, and updating tasks (handles CRUD operations for task)
@Service
public class TaskService {
    private ArrayList<TaskEntity> tasks= new ArrayList<>(); //stores TaskEntity objects
    private int taskId = 1; //an integer to keep track of the ID for new tasks
    private final SimpleDateFormat deadlineFormatter = new SimpleDateFormat("yyyy-MM-dd"); //a SimpleDateFormat
    // object to parse deadline strings into dates

    //adds a new task to the list of tasks, it takes the task's title, description, and deadline as parameters,
    // creates a new TaskEntity object, assigns an ID, and adds it to the list.
    public TaskEntity addTask(String title, String description, String deadline) throws ParseException {

        TaskEntity task = new TaskEntity();
        task.setId(taskId); //assigns an ID
        task.setTitle(title);
        task.setDescription(description);
        task.setDeadline(deadlineFormatter.parse(deadline));
        task.setCompleted(false);
        tasks.add(task);
        taskId++;

        return task;
    }

    public ArrayList<TaskEntity> getTasks() {
        return tasks;
    }

    public TaskEntity getTaskById(int id) {

        //tasks.stream().findAny().filter(task -> task.getId() == id).orElse(null);

        for (TaskEntity task: tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }

    //updates the specified task with new description, deadline, and completion status
    public TaskEntity updateTask(int id, String description, String deadline, Boolean completed) throws ParseException {
        TaskEntity task = getTaskById(id);
        if (task == null) {
            return null;
        }
        if (description != null) {
            task.setDescription(description);
        }
        if (deadline != null) {
            task.setDeadline(deadlineFormatter.parse(deadline));
        }
        if (completed != null) {
            task.setCompleted(completed);
        }

        return task;
    }
}
