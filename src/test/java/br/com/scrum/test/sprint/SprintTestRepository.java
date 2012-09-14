package br.com.scrum.test.sprint;
//package br.com.scrum.test.sprint;
//
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertTrue;
//
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import javax.persistence.EntityManager;
//import javax.persistence.Persistence;
//
//import org.hibernate.exception.ConstraintViolationException;
//import org.junit.AfterClass;
//import org.junit.BeforeClass;
//import org.junit.Test;
//
//import br.com.scrum.domain.entity.Project;
//import br.com.scrum.domain.entity.Sprint;
//import br.com.scrum.entity.enums.Const;
//import br.com.scrum.test.ProjectRepository;
//import br.com.scrum.test.SprintRepository;
//import br.com.scrum.service.ProjectService;
//import br.com.scrum.service.SprintService;
//import br.com.scrum.util.exception.NoResultExceptionHandler;
//
//public class SprintTestRepository {
//
//	private static SprintRepository sprintRepository;
//	private static ProjectRepository projectRepository;	
//	private static Integer projetoID;
//	private static Integer sprintID;
//
//	@BeforeClass
//	public static void before() throws Exception {
//		after();
//		sprintRepository = new SprintService();
//		projectRepository = new ProjectService();
//	}
//
//	@AfterClass
//	public static void after() throws Exception {
//		EntityManager em = Persistence.createEntityManagerFactory(Const.SCHEMA).createEntityManager();
//		em.close();
//	}
//
//	@Test
//	public void salvaSprintCompleto() throws Exception {
////		criaSprint();
////		remove();		
//	}
//
//	private void criaSprint() throws ConstraintViolationException, Exception {		
//		Project p = projectRepository.withId(2);	
//		Sprint s = new Sprint();		
//		s.setName("Sprint_03");
//		s.setStartDate(new Date());
//		s.setEndDate(new Date());
//		s.setProject(p);	
//		
//		sprintRepository.save(s);
//		
//		projetoID = s.getProject().getId();
//		sprintID = s.getId();
//
//		assertNotNull(projetoID);
//		assertNotNull(sprintID);		
//	}
//
////	private void remove() {		
////		Sprint s = sprintRepository.comId(sprintID);
////		sprintRepository.remove(s);
////		
////		Project p = projectRepository.comId(projetoID);
////		projectRepository.remove(p);
////
////	}
//				
//
//}
