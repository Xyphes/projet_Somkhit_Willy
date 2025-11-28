package org.formation.projet_somkhit_willy.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "CLIENTS")
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

    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("client")
    private SavingAccount savingAccount;

    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("client")
    private CurrentAccount currentAccount;
}
