package org.formation.projet_somkhit_willy.repository;

import org.formation.projet_somkhit_willy.entity.CurrentAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrentAccountRepository extends JpaRepository<CurrentAccount, Long> {
}