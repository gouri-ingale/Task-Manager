package com.project.taskManager.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Document(collection = "config_tasks_app")
@Data
@NoArgsConstructor
public class ConfigTasksAppEntity {
    private String key;
    private String value;
}
