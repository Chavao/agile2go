package br.com.scrum.test.unit;

import org.junit.Assert;
import org.junit.Test;

import br.com.scrum.entity.Task;
import br.com.scrum.entity.enums.Status;

/**
 * @author rafael 
 * 
 */
public class TaskUnitTest
{
	
	@Test
	public void shouldSetATask()
	{
		Task task = new Task("task story", 5, "05:00", Status.INPROGRESS);
		assertTask(task);
	}
	
	private void assertTask(Task task)
	{
		Assert.assertTrue(task != null);
		Assert.assertTrue(!task.getStorie().isEmpty() &&
						  task.getPriority() != null &&
						  !task.getHours().isEmpty() &&
						  task.getStatus() != null);
		Assert.assertSame(task.getStorie(), "task story");
		Assert.assertSame(task.getPriority(), 5);
		Assert.assertEquals(task.getHours(), "05:00");
		Assert.assertSame(task.getStatus(), Status.INPROGRESS);
	}

}
