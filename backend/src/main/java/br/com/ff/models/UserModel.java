package br.com.ff.models;

import br.com.ff.abstracts.AbstractModel;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Table(name = "users")
public class UserModel extends AbstractModel implements UserDetails {

	@Column(unique = true, nullable = false)
	private String username;

	@Column(unique = true, nullable = false)
	private String password;

	@OneToMany(mappedBy = "requestedBy")
	private List<ExpenseModel> requestedExpenses = new ArrayList<ExpenseModel>();

	@OneToMany(mappedBy = "approvedBy")
	private List<ExpenseModel> approvedExpenses = new ArrayList<ExpenseModel>();

	@ManyToMany
	@JoinTable(name = "user_role",
		joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id")
	)
	private Set<RoleModel> roles = new HashSet<>();

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles;
	}

	public void addRole(RoleModel role) {
		roles.add(role);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<ExpenseModel> getApprovedExpenses() {
		return approvedExpenses;
	}

	public List<ExpenseModel> getRequestedExpenses() {
		return requestedExpenses;
	}


	// Equals and hashcode

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
