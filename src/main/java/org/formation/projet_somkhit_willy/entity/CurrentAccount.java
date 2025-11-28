package org.formation.projet_somkhit_willy.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@DiscriminatorValue("CURRENT")
public class CurrentAccount extends Account {

    private BigDecimal overdraftLimit = new BigDecimal(1000.0);

//    @OneToOne
//    @JoinColumn(name = "client_id", unique = true)
//    @JsonIgnoreProperties({"savingAccount", "currentAccount"})
//    private Client client;
}
