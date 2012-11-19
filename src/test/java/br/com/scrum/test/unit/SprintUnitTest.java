package br.com.scrum.test.unit;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import br.com.scrum.entity.Sprint;
import br.com.scrum.entity.Task;
import br.com.scrum.entity.enums.Status;
import br.com.scrum.util.exception.BusinessException;


/**
 * @author rafael 
 * 
 */
public class SprintUnitTest
{
	
	private static Sprint SPRINT = new Sprint();
	
	@Test
	public void shouldSetASprint()
	{
		Date endDate = new Date(2012, 12, 01); 
		Sprint sprint = new Sprint("sprint test", new Date(), endDate, "10:00", "sprint goal");
		assertSprint(sprint);
		SPRINT = sprint;
	}
	
	@Test
	public void shouldAssociateTaskToSprint() throws BusinessException
	{
		Sprint sprint = SPRINT;
		Task task = new Task("sprint story", 5, "05:00", Status.INPROGRESS);
		sprint.addTask(task);
		Assert.assertTrue(!sprint.getTasks().isEmpty());
		
		try {
			sprint.addTask(task);
		} catch (BusinessException be) {
			// ok
		}
		sprint.removeTask(task);
		Assert.assertTrue(sprint.getTasks().isEmpty());
	}
	
	private void assertSprint(Sprint sprint)
	{
		String startDate = sprint.getStartDate().toString();
		Assert.assertTrue(sprint != null);
		Assert.assertTrue(sprint.getStartDate() != null &&
						  sprint.getEndDate() != null &&
						  !sprint.getDailyScrum().isEmpty() &&
						  !sprint.getGoal().isEmpty());
		Assert.assertSame(sprint.getName(), "sprint test");
		Assert.assertEquals(sprint.getStartDate().toString(), startDate);
		Assert.assertEquals(sprint.getEndDate(), new Date(2012, 12, 01));
		Assert.assertSame(sprint.getDailyScrum(), "10:00");
		Assert.assertSame(sprint.getGoal(), "sprint goal");
	}
}
