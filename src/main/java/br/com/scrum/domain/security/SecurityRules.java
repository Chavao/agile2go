package br.com.scrum.domain.security;

import org.jboss.seam.security.Identity;
import org.jboss.seam.security.annotations.LoggedIn;
import org.jboss.seam.security.annotations.Secures;

import br.com.scrum.domain.entity.User;
import br.com.scrum.domain.security.authorization.SameUserLoggedIn;

public class SecurityRules {

	@Secures
	@LoggedIn
	public boolean isSomething(Identity identity) {
		return identity.isLoggedIn();
	}

	// public boolean isProjectAdmin(@ProjectBinding Project project, Identity identity)
	// {
	// return identity.hasRole("ADMIN", project.getId().toString(), "PROJECT_MEMBERSHIP");
	// }

	@Secures
	@SameUserLoggedIn
	public boolean isSameUserLoggedIn(User user, Identity identity) {
		return identity.isLoggedIn() && user != null && !user.getName().isEmpty()
			 && user.getName().equals(identity.getUser().getKey());
	}

}
