package org.formation.projet_somkhit_willy.repository;

import org.formation.projet_somkhit_willy.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}