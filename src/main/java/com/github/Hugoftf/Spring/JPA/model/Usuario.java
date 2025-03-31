package com.github.Hugoftf.Spring.JPA.model;

import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import jakarta.persistence.*;
import org.hibernate.annotations.Type;

import java.util.List;
import java.util.UUID;

@Entity
@Table
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "login", unique = true, nullable = false, length = 50)
    private String login;

    @Column(name = "senha", nullable = false, length = 300)
    private String senha;

    @Type(ListArrayType.class)
    @Column(name = "roles", columnDefinition = "roles[]")
    private List<String> roles;
}
