package com.project.manager.user.entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    private long count;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String city;
    private String avatar;
    private String company;
    private String jobPosition;
    private String mobile;
    private String username;
    private String email;
    private String password;
    private String role;

    public User(Integer count, String firstName, String lastName, Date birthDate, String city, String avatar, String company, String jobPosition, String mobile, String username, String email, String password, String role) {
        this.count = count;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.city = city;
        this.avatar = avatar;
        this.company = company;
        this.jobPosition = jobPosition;
        this.mobile = mobile;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}