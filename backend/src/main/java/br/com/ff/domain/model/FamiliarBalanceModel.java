package br.com.ff.domain.model;


import br.com.ff.abstracts.AbstractModel;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "familiar_balance")
public class FamiliarBalanceModel extends AbstractModel {

	private BigDecimal balance;

	@OneToMany(mappedBy = "balanceFamiliar")
	private Set<UserModel> users;

	public FamiliarBalanceModel() {}

	public FamiliarBalanceModel(BigDecimal balance) {
		this.balance = balance;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public Set<UserModel> getUsers() {
		return users;
	}

	public void addUser(UserModel user) {
		this.users.add(user);
	}
}
