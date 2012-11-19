package br.com.scrum.test.unit;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import br.com.scrum.entity.Project;
import br.com.scrum.entity.User;
import br.com.scrum.entity.enums.UserRole;
import br.com.scrum.util.exception.BusinessException;

/***
 * @author rafael
 * 
 */
public class ProjectUnitTest
{
	
	private static Project PROJECT = new Project();
	
	@Test
	public void shouldSetAProject()
	{
		Project project = new Project("project test", "project_test", new Date(), "project company test");
		assertProject(project);
		PROJECT = project;
	}
	
	@Test
	public void shouldAssociateUserToProject() throws BusinessException
	{
		Project project = PROJECT;
		User membership = new User("user test", "user_test", "test123", UserRole.MASTER);
		project.addUser(membership);
		Assert.assertTrue(!project.getUsers().isEmpty());
		
		try {
			project.addUser(membership);
		} catch (BusinessException be) {
			// ok
		}
		
		project.removeUser(membership);
		Assert.assertTrue(project.getUsers().isEmpty());
	}
	
	private void assertProject(Project project)
	{
		String deadline = project.getLastDate().toString();
		Assert.assertTrue(project != null);
		Assert.assertTrue(!project.getName().isEmpty() &&
						  !project.getDescription().isEmpty() &&
						  !project.getCompany().isEmpty() &&
						  project.getLastDate() != null);
		Assert.assertSame(project.getName(), "project test");
		Assert.assertSame(project.getDescription(), "project_test");
		Assert.assertEquals(project.getLastDate().toString(), deadline);
		Assert.assertSame(project.getCompany(), "project company test");
	}
}
