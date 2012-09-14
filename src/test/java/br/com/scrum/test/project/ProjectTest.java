package br.com.scrum.test.project;
//package br.com.scrum.test.project;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertNull;
//import static org.junit.Assert.assertSame;
//import static org.junit.Assert.assertTrue;
//
//import java.util.Date;
//import java.util.List;
//
//import javax.persistence.EntityManager;
//
//import org.junit.AfterClass;
//import org.junit.BeforeClass;
//import org.junit.Test;
//
//import br.com.scrum.domain.entity.Project;
//import br.com.scrum.domain.entity.Sprint;
//import br.com.scrum.domain.entity.Task;
//import br.com.scrum.domain.entity.User;
//import br.com.scrum.entity.enums.Status;
//import br.com.scrum.test.ProjectRepository;
//import br.com.scrum.test.SprintRepository;
//import br.com.scrum.test.TaskRepository;
//import br.com.scrum.test.UserRepository;
//import br.com.scrum.service.ProjectService;
//import br.com.scrum.service.SprintService;
//import br.com.scrum.service.TaskService;
//import br.com.scrum.service.AuthenticationMB;
//import br.com.scrum.util.exception.NoResultExceptionHandler;
//import br.com.scrum.factory.EntityManagerFactoryProducer;
//
//public class ProjectTestRepository {
//	
//    private static EntityManager em;
//	private static UserRepository userRepository;
//	private static ProjectRepository projectRepository;
//	private static SprintRepository sprintRepository;	
//	private static TaskRepository taskRepository;
//
//	@BeforeClass
//	public static void before() throws Exception {
//        em = new EntityManagerFactoryProducer().getEntityManager();
//		userRepository = new AuthenticationMB().setEm(em);
//		projectRepository = new ProjectService().setEm(em);
//		sprintRepository = new SprintService().setEm(em);		
//		taskRepository = new TaskService().setEm(em);
//	}
//
//	@AfterClass
//	public static void after() throws Exception {		
//		em.close();
//	}
//
////	@Test
//	public void saveACompletProject() throws Exception {
//		criaProjetoCompleto();
//	}
//
//	@Test
//	public void BDD() throws Exception {
////		assertNotNull(salva());		
////		update();
////		withId();
////		findAll();
////		remove();
//		searchBy();
//	}
//	public void searchBy () throws NoResultExceptionHandler {		
//		List<Project> projects = projectRepository.searchBy("agile2go".toUpperCase());			
//		assertTrue(projects != null && !projects.isEmpty());		
//	}
//	
//
//	private Project salva() throws Exception  {
//		Project p = new Project();
//		p.setName("Scrum2Go");
//		p.setDescription("Um project com o objetivo de agilizar o processo de desenvolvimento de software");
//		p.setLastDate(new Date());
//		p.setCompany("Vitae");		
//		p = projectRepository.save(p);
//		return p;
//	}	
//
//	private void update() throws Exception {
//		Project p = projectRepository.withId(2);		
//		p.setName("Agile2Go");		
//		p.setDescription("A project for agile teams");
//		p = projectRepository.save(p);		
//		assertSame(2, p.getId());
//	}
//
//	private void withId() {
//		Project p = projectRepository.withId(2);		
//		assertNotNull(p);
//		assertSame(2, p.getId());		
//	}
//
//	private void findAll() throws Exception {		
//		List<Project> projects = projectRepository.findAll();
//		assertTrue(!projects.isEmpty() && projects != null);
//		assertEquals(1, projects.size());		
//	}
//
//	private void remove() throws Exception {
//		Project p = projectRepository.withId(2);		
//		projectRepository.remove(p);
//		assertNull(projectRepository.withId(2));
//	}
//
//	private void criaProjetoCompleto() throws Exception {
//		User u = userRepository.withId(1);
//		
//		Project p = new Project();		
//		p.setName("Agile2Go");
//		p.setDescription("A software for agile teams");
//		p.setCompany("São José dos Campos");
//		p.setLastDate(new Date());		
//		projectRepository.save(p);
//
//		Sprint s = new Sprint();		
//		s.setName("Sprint_01 ");
//		s.setStartDate(new Date());
//		s.setEndDate(new Date());
//		s.setProject(p);
//		sprintRepository.save(s);		
//
//		Task t = new Task();		
//		t.setStory("Task ");		
//		t.setStatus(Status.TODO);
//		taskRepository.save(t);
//
//		assertNotNull(p);
//		assertNotNull(s);		
//		assertNotNull(t);
//
//		Project p2 = projectRepository.withId(p.getId());
//		assertSame(1, p2.getId());
//
//		Sprint s2 = sprintRepository.withId(s.getId());
//		assertSame(1, s2.getId());
//
//		Task t2 = taskRepository.withId(t.getId());
//		assertSame(1, t2.getId());
//	}
//
//}
