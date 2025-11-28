package org.formation.projet_somkhit_willy.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "ACCOUNTS")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "account_type")
public abstract class Account {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "BINARY(16)")
    private UUID accountNumber;

    private BigDecimal balance;

    private LocalDate openingDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    @JsonIgnoreProperties({"savingAccount", "currentAccount"})
    private Client client;
}
