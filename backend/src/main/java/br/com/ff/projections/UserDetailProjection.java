package br.com.ff.projections;

import java.util.UUID;

public interface UserDetailProjection {

	String getUsername();
	String getPassword();
	UUID getRoleId();
	String getAuthority();
}
