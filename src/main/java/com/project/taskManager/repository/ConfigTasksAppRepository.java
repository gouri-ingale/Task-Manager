package com.project.taskManager.repository;

import com.project.taskManager.entity.ConfigTasksAppEntity;
import com.project.taskManager.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigTasksAppRepository extends MongoRepository<ConfigTasksAppEntity, ObjectId> {
}
