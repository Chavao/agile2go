//package br.com.scrum.test;
//
//import org.jboss.shrinkwrap.api.ShrinkWrap;
//import org.jboss.shrinkwrap.api.asset.EmptyAsset;
//import org.jboss.shrinkwrap.api.spec.WebArchive;
//
//import br.com.scrum.factory.Resources;
//
//public class Agile2GoDeployment
//{
//	public static WebArchive deployment() 
//	{
//        return ShrinkWrap
//                .create(WebArchive.class, "test.war")
//                .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
//                .addAsResource("sql/scripts.sql")
//                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
//                .addPackage(Resources.class.getPackage())
//                // Deploy our test datasource
//                .addAsWebInfResource("test-ds.xml");
//    }
//}
