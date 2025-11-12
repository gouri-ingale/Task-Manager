//package com.project.taskManager.controller;
//
//import com.project.taskManager.entity.TaskEntry;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.*;
//import java.util.HashMap;
//
//@RestController
//@RequestMapping("/_tasks")
//public class TaskManagerEntryController {
//    private Map<Long, TaskEntry> taskEntries = new HashMap<>();
//
//    @GetMapping
//    public List<TaskEntry> getAll() {
//        return new ArrayList<>(taskEntries.values());
//    }
//
//
//    @PostMapping
//    public boolean createEntry(@RequestBody TaskEntry myEntry){
//            taskEntries.put(myEntry.getId(), myEntry);
//            return true;
//    }
//
//    @GetMapping("/id/{myId}")
//    public TaskEntry getTaskEntries(@PathVariable Long myId){
//        return  taskEntries.get(myId);
//    }
//
//    @DeleteMapping("/id/{myId}")
//     public TaskEntry deleteTaskEntryById(@PathVariable Long myId){
//        return taskEntries.remove(myId);
//    }
//
//    @PutMapping("id/{myId}")
//    public TaskEntry updateTaskById(@PathVariable Long myId, @RequestBody TaskEntry myEntry){
//        return  taskEntries.put(myId, myEntry);
//    }
//}
