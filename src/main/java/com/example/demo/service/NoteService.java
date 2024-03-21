package com.example.demo.service;

import com.example.demo.entities.NoteEntity;
import com.example.demo.entities.TaskEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//this service class manages the notes related to tasks, it provides methods for getting and adding notes for tasks
//(handles operations related to notes, utilizing TaskService for task-related operations)
@Service
public class NoteService {
    private TaskService taskService; //an instance of TaskService, injected via constructor
    private HashMap<Integer, TaskNotesHolder> taskNotesHolderHashMap = new HashMap<>(); // a HashMap to store notes for each task ID

    //constructor takes a TaskService object as a parameter, allowing NoteService to communicate with TaskService,
    // facilitating dependency injection
    public NoteService(TaskService taskService){
        this.taskService = taskService;
    }

    //An inner class to hold notes for a specific task
    class TaskNotesHolder{
        private int noteId = 1;
        private ArrayList<NoteEntity> notes = new ArrayList<>();
    }

    //returns notes for a specific taskID,
    // it fetches the corresponding TaskEntity using TaskService and returns the list of notes
    public List<NoteEntity> getNotesForTask(int taskId){
        TaskEntity task = taskService.getTaskById(taskId);
        if (task == null) {
            return null;
        }

        if (taskNotesHolderHashMap.get(taskId) == null) {
            taskNotesHolderHashMap.put(taskId, new TaskNotesHolder()); //?
        }

        return taskNotesHolderHashMap.get(taskId).notes;
    }

    //adds a new note for a specific taskID, it fetched the corresponding TaskEntity using TaskService,
    // creates a new NoteEntity object, adds it to the TaskNotesHolder associated with the taskID, and returns the new note
    public NoteEntity addNotesForTask(int taskId, String title, String body){
        TaskEntity task = taskService.getTaskById(taskId);
        if (task == null) {
            return null;
        }
        if (taskNotesHolderHashMap.get(taskId) == null) {
            taskNotesHolderHashMap.put(taskId, new TaskNotesHolder());
        }

        TaskNotesHolder taskNotesHolder = taskNotesHolderHashMap.get(taskId);

        NoteEntity note = new NoteEntity();
        note.setId(taskNotesHolder.noteId);
        note.setTitle(title);
        note.setBody(body);

        taskNotesHolder.notes.add(note);
        taskNotesHolder.noteId++;

        return note;
    }
}
