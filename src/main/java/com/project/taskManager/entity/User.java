package com.project.taskManager.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "users")
@Builder
//@NoArgsConstructor
public class User {
    @Id
    private ObjectId id;

    @Indexed(unique = true)
    @NonNull // It will automatically throw the exceptions if any occurs
    @NotBlank(message = "Username is required and cannot be empty.")
    private String userName;

    @NonNull
    @NotBlank(message = "Username is required and cannot be empty.")
    private String password;

    @DBRef
    private List<TaskEntry> taskEntries = new ArrayList<>();

    private List<String> roles;
}
