package com.project.taskManager.controller;

import com.project.taskManager.entity.TaskEntry;
import com.project.taskManager.entity.User;
import com.project.taskManager.service.TaskEntryService;
import com.project.taskManager.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
public class TaskManagerEntryControllerV2 {

    @Autowired
    private TaskEntryService taskEntryService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllTaskEntriesOfUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserName(userName);
        if (user == null) {
            return new ResponseEntity<>("User not found: " + userName, HttpStatus.NOT_FOUND);
        }
       List<TaskEntry> all = user.getTaskEntries();
       if(all != null && !all.equals("")){
           return new ResponseEntity<>(all, HttpStatus.OK);
       }
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping
    public ResponseEntity<TaskEntry> createEntry(@RequestBody TaskEntry myEntry){
        try{
            //user.setTaskEntries(myEntry.getId());
           // myEntry.setDate(LocalDateTime.now()); //Java-8
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            taskEntryService.saveEntry(myEntry,userName);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        } catch (Exception e) {
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/id/{myId}")
    public ResponseEntity<TaskEntry> getTaskEntryById(@PathVariable ObjectId myId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserName(userName);
        List<TaskEntry> collect = user.getTaskEntries().stream().filter(x -> x.getId().equals(myId)).collect(Collectors.toList());
        if(!collect.isEmpty()){
            Optional<TaskEntry> taskEntry = taskEntryService.findById(myId);
            if(taskEntry.isPresent()){
                return new ResponseEntity<>(taskEntry.get(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    @DeleteMapping("/id/{myId}")
    public ResponseEntity<?> deleteTaskEntryById(@PathVariable ObjectId myId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        boolean removed = taskEntryService.deleteById(myId, userName);
        if (removed){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("id/{myId}")
    public ResponseEntity<?> updateTaskById(
            @PathVariable ObjectId myId,
            @RequestBody TaskEntry newEntry)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserName(userName);
        List<TaskEntry> collect = user.getTaskEntries().stream().filter(x -> x.getId().equals(myId)).collect(Collectors.toList());
        if(!collect.isEmpty()){
            Optional<TaskEntry> taskEntry = taskEntryService.findById(myId);
            if(taskEntry.isPresent()){

                TaskEntry old = taskEntry.get();
                    old.setTitle(newEntry.getTitle() != null && !newEntry.equals("") ? newEntry.getTitle() : old.getTitle());
                    old.setContent(newEntry.getContent() != null && !newEntry.equals("") ? newEntry.getContent() : old.getContent());
                    taskEntryService.saveEntry(old );
                    return new ResponseEntity<>(old, HttpStatus.OK);
            }
        }
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
