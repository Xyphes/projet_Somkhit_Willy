package org.formation.projet_somkhit_willy.entity;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Data
public class CurrentAccount extends Account {
    private double overdraftLimit = 1000.0;
}

