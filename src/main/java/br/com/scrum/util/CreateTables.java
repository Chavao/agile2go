package br.com.scrum.util;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import br.com.scrum.entity.enums.Const;

public class CreateTables {	

	private static EntityManager em;
	
	public static void main(String[] args) {
		em = Persistence.createEntityManagerFactory(Const.SCHEMA).createEntityManager();
		em.close();				
	}
			
}
