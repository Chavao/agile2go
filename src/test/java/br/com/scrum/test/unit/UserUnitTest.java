package br.com.scrum.test.unit;

import org.junit.Assert;
import org.junit.Test;

import br.com.scrum.entity.User;
import br.com.scrum.entity.enums.UserRole;

/**
 * @author rafael
 * 
 */
public class UserUnitTest
{
	@Test
	public void shouldSetAUser()
	{
		User user = new User("user test", "user_test", "test123", UserRole.MASTER);
		Assert.assertTrue(user != null);
		Assert.assertTrue(!user.getName().isEmpty() &&
						  !user.getLogin().isEmpty() &&
						  !user.getPassword().isEmpty() &&
						  user.getRole() != null);
		Assert.assertSame(user.getName(), "user test");
		Assert.assertSame(user.getLogin(), "user_test");
		Assert.assertSame(user.getPassword(), "test123");
		Assert.assertSame(user.getRole(), UserRole.MASTER);
		
	} 
}
