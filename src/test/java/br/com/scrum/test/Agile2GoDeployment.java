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
//                .addPackage(Resources.class.getPackage())
//                .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
//                .addAsWebInfResource("test-ds.xml")
//                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
//    }
//}
//
//
//
//
//
//
