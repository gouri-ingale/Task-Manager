package com.project.taskManager.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Document (collection = "task_entries")// to map to the collection in the mongodb we use @Document annotation

//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
//@EqualsAndHashCode
//@Builder
@Data //is equal to all of the above annotations
@NoArgsConstructor //It is required to convert the json to pojo
public class TaskEntry { //instance of TaskEntry will be equal to a document, i.e. to a row
    @Id
    private ObjectId id;
    private String title;
    private String content;

    private LocalDateTime date;

}
