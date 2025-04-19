package br.com.ff.models;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serial;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "roles")
public class RoleModel implements GrantedAuthority {

	@Id
	@GeneratedValue(generator = "UUID")
	@UuidGenerator
	@Column(name = "id", updatable = false, nullable = false)
	private UUID id;

	private String authority;

	@ManyToMany(mappedBy = "roles")
	private Set<UserModel> users = new HashSet<>();

	public RoleModel() {}

	public RoleModel(UUID id, String authority) {
		this.id = id;
		this.authority = authority;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	@Override
	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public Set<UserModel> getUsers() {
		return users;
	}

	// TODO implementar o metodo addUser pois é assim que deve ser feito
	public void setUsers(Set<UserModel> users) {
		this.users = users;
	}

	// Equals and hashCode

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof RoleModel roleModel)) return false;
		return Objects.equals(getAuthority(), roleModel.getAuthority());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getAuthority());
	}
}
