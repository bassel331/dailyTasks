package com.sumergeTask.sumergeTask.models;

import jakarta.persistence.*;
import org.mapstruct.Builder;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_seq_gen")
    @SequenceGenerator(name = "course_seq_gen", sequenceName = "course_seq", allocationSize = 1)

    private Long id;
    private String name;
    private String description;
    private int credit;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setID(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

//    @Override
//    public String toString() {
//        return "Course{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", description='" + description + '\'' +
//                ", credit=" + credit +
//                '}';
//    }
}
