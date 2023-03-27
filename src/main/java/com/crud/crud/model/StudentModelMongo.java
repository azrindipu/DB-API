package com.crud.crud.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "studentModelMongo")
public class StudentModelMongo {
    @Id
    private String id;
    private String institute;
    private String studentName;
    private String studentRoll;
}
