//package br.com.scrum.test.project;
//
//import org.jboss.shrinkwrap.api.spec.WebArchive;
//
//import br.com.scrum.domain.entity.Project;
//import br.com.scrum.service.ProjectService;
//import br.com.scrum.dao.PersistenceUtil;
//import br.com.scrum.test.Agile2GoDeployment;
//
//public class ProjectDeployment
//{
//	public static WebArchive deployment()
//	{
//		return Agile2GoDeployment.deployment().
//				addPackage(Project.class.getPackage()).
//				addPackage(PersistenceUtil.class.getPackage()).
//				addClass(ProjectService.class);
//	}
//}
