package br.com.ff.models;

import br.com.ff.abstracts.AbstractModel;
import br.com.ff.enums.UserRoles;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Table(name = "users")
public class UserModel extends AbstractModel implements UserDetails {

	@Column(unique = true, nullable = false)
	private String username;

	@Column(unique = true, nullable = false)
	private String password;

	@Enumerated(EnumType.STRING)
	private UserRoles role;

	@OneToMany(mappedBy = "requestedBy")
	private List<ExpenseModel> requestedExpenses = new ArrayList<ExpenseModel>();

	@OneToMany(mappedBy = "approvedBy")
	private List<ExpenseModel> approvedExpenses = new ArrayList<ExpenseModel>();

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
		// Versão melhorada usando o próprio enum
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(role.name()));

		// Se for ADMIN, adiciona também o ROLE_USER
		if (role == UserRoles.ROLE_ADMIN) {
			authorities.add(new SimpleGrantedAuthority(UserRoles.ROLE_USER.name()));
		}

		return authorities;
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

	public UserRoles getRole() {
		return role;
	}

	public void setRole(UserRoles role) {
		this.role = role;
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
