package com.project.taskManager.service;

import com.project.taskManager.entity.TaskEntry;
import com.project.taskManager.entity.User;
import com.project.taskManager.repository.TaskEntryRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class TaskEntryService {
        @Autowired //Spring genreates a runtime implemetation and injects the dependency
        private TaskEntryRepository taskEntryRepository;

        @Autowired
        private  UserService userService;


        @Transactional
        public void saveEntry(TaskEntry taskEntry, String userName){
                try{
                        User user = userService.findByUserName(userName);
                        taskEntry.setDate(LocalDateTime.now());
                        TaskEntry saved = taskEntryRepository.save(taskEntry);
                        user.getTaskEntries().add(saved);
                        userService.saveUser(user);
                } catch (Exception e) {
                        log.error("Error : ",e);
                        throw new RuntimeException("An error occurred while saving the entry.",e);
                }
        }
        public void saveEntry(TaskEntry taskEntry){
                taskEntryRepository.save(taskEntry);
        }
        public List<TaskEntry> getAll(){
                return taskEntryRepository.findAll();
        }
        public Optional<TaskEntry> findById(ObjectId id){
                return taskEntryRepository.findById(id);
        }
        @Transactional
        public boolean deleteById(ObjectId id, String userName){
                boolean removed = false;
                try{
                        User user = userService.findByUserName(userName);
                         removed = user.getTaskEntries().removeIf(o -> o.getId().equals(id));
                        if(removed) {
                                userService.saveUser(user);
                                taskEntryRepository.deleteById(id);
                        }
                } catch (Exception e) {
                        System.out.println(e);
                        throw new RuntimeException("An error occured while deleting the entry",e);
                }
                return removed;
        }
//        public List<TaskEntry> findByUserName(String userName){
//                return userService.findByUserName(userName);
//        }
}
