package br.com.ff.infra.repository;

import br.com.ff.domain.model.FamiliarBalanceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FamiliarBalanceRepository extends JpaRepository<FamiliarBalanceModel, UUID> {
}
