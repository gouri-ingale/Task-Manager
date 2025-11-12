package com.project.taskManager.repository;

import com.project.taskManager.entity.TaskEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskEntryRepository extends MongoRepository<TaskEntry, ObjectId> {
}
