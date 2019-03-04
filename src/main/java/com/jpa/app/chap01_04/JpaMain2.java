package com.jpa.app.chap01_04;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain2 {
	
	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			
			logic(em);
			
			tx.commit();
		}
		catch(Exception e) {
			tx.rollback();
		}
		finally {
			em.close();
		}
		
		emf.close();
	}
	
	
	public static void logic(EntityManager em) {
		Board board = new Board();
		em.persist(board);
		System.out.println("board.id = " + board.getId());
	}

}
