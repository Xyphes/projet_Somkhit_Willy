package org.formation.projet_somkhit_willy.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("SAVING")
public class SavingAccount extends Account {

    private double interestRate = 0.03;

    @OneToOne
    @JoinColumn(name = "client_id", unique = true)
    @JsonIgnoreProperties({"savingAccount", "currentAccount"})
    private Client client;
}
