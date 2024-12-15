package com.example.Java_Server_Part.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "\"user\"")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
    @Id
    private Long id;
    private String username;
    private String password;
    private Integer rating;
    private String status;

}
