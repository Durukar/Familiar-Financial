package br.com.ff.repositories;

import br.com.ff.models.UserModel;
import br.com.ff.projections.UserDetailProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {
	Object findByUsername(String username);

	@Query(nativeQuery = true, value = """
				SELECT ff.users.username AS username, ff.users.password, ff.roles.id AS role_id, ff.roles.authority
				FROM ff.users
				INNER JOIN ff.user_role ON ff.users.id = ff.user_role.user_id
				INNER JOIN ff.roles ON ff.roles.id = ff.user_role.role_id
				WHERE ff.users.username = :username
""")
	List<UserDetailProjection> searchUserAndRolesByUsername(String username);
}
