package com.project.manager.user.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "OrganizationContact")
public class OrganizationContact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
    private String city;
    private String country;
    private String address;
    private String contactType;
    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;
}
