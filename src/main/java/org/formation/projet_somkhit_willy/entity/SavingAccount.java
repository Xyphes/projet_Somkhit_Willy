package org.formation.projet_somkhit_willy.entity;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Data
public class SavingAccount extends Account {
    private double interestRate = 0.03;
}