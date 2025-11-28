package org.formation.projet_somkhit_willy.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.HashSet;

@Entity
@Getter
@Setter
@Table(name = "CLIENTS")
@JsonIgnoreProperties("client")
public class Client {
    @Id
    @GeneratedValue
    private Long id;

    private String lastName;
    private String firstName;
    private String address;
    private String postalCode;
    private String city;
    private String phoneNumber;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Account> accounts = new HashSet<Account>();
}
