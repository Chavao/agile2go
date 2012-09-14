//package br.com.scrum.test.user;
//
//import static org.junit.Assert.assertNotNull;
//
//import java.util.Random;
//
//import org.jboss.arquillian.container.test.api.Deployment;
//import org.jboss.arquillian.core.api.annotation.Inject;
//import org.jboss.arquillian.junit.Arquillian;
//import org.jboss.shrinkwrap.api.spec.WebArchive;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import br.com.scrum.domain.entity.User;
//import br.com.scrum.entity.enums.UserRole;
//import br.com.scrum.service.UserService;
//
//@RunWith(Arquillian.class)
//public class UserTest 
//{
//	@Deployment
//	public static WebArchive deployment()
//	{
//		return UserDeployment.deployment();
//	}
//	
//	@Inject private UserService userService;
//	private static Long random = new Random().nextLong(); 
//	private static Integer ID; 
//
//	@Test
//	public void save() throws Exception 
//	{
//		User user = new User("Anonymous - " + random, String.valueOf(random), "123", UserRole.MASTER);		
//		userService.create(user);
//		assertNotNull(user.getId());
//		ID = user.getId();
//	}
//
////	private void update(long random) throws Exception {
////		User u = service.withId(ID);
////		u.setName("Rafael" + random);
////		service.update(u);
////		assertSame(u.getId(), ID);
////	}
////
////	private void newUser(long random) throws Exception {
////		final User u = new User();
////		u.setName("Juliana" + random);
////		u.setLogin(String.valueOf(random + random));
////		u.setPassword("123");
////		u.setRole(UserRole.TEAM);
////		service.save(u);
////		assertNotNull(u);			
////	}
////	
////	private void userWithLogin() throws Exception {
////		User u = service.withId(ID);
////		User u2 = service.withLogin(u.getLogin(), u.getPassword());
////		assertNotNull(u2.getLogin(), u2.getPassword());
////	}
////	
////	private void findAll() throws Exception {
////		List<User> users = service.findAll();
////		assertTrue(!users.isEmpty());
////	}
//	
////	@BeforeClass					
////	public static void before() throws Exception {
////		em = new EntityManagerFactoryProducer().getEntityManager();
////		service = new AuthenticationMB().setEm(em);		
////	}
////
////	@AfterClass
////	public static void after() throws Exception {				
////		em.close();
////	}
//}
