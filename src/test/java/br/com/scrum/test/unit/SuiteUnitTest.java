package br.com.scrum.test.unit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * @author rafael 
 * 
 */
@RunWith(Suite.class)
@SuiteClasses({
	UserUnitTest.class,
	ProjectUnitTest.class,
	SprintUnitTest.class,
	TaskUnitTest.class
})
public class SuiteUnitTest {}
