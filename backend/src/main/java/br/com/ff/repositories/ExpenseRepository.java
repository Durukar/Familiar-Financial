package br.com.ff.repositories;

import br.com.ff.models.ExpenseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ExpenseRepository extends JpaRepository<ExpenseModel, UUID> {
}
