package br.com.ff.models;

import br.com.ff.abstracts.AbstractModel;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class UserModel extends AbstractModel {

	@Column(unique = true, nullable = false)
	private String username;

	@Column(unique = true, nullable = false)
	private String password;

	@OneToMany(mappedBy = "requestedBy")
	private List<Expense> requestedExpenses = new ArrayList<Expense>();

	@OneToMany(mappedBy = "approvedBy")
	private List<Expense> approvedExpenses = new ArrayList<Expense>();

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Expense> getApprovedExpenses() {
		return approvedExpenses;
	}

	public List<Expense> getRequestedExpenses() {
		return requestedExpenses;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof UserModel userModel)) return false;
		return Objects.equals(getUsername(), userModel.getUsername()) && Objects.equals(getPassword(), userModel.getPassword());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getUsername(), getPassword());
	}
}
