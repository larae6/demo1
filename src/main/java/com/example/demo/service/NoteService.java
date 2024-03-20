package com.example.demo.service;

import com.example.demo.entities.NoteEntity;
import com.example.demo.entities.TaskEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class NoteService {
    private TaskService taskService;
    private HashMap<Integer, TaskNotesHolder> taskNotesHolderHashMap = new HashMap<>();
    public NoteService(TaskService taskService){
        this.taskService = taskService;
    }
    class TaskNotesHolder{
        private int noteId = 1;
        private ArrayList<NoteEntity> notes = new ArrayList<>();
    }

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

    }
}
