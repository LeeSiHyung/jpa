package com.jpa.app.chap01_04.study;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class JpaMain {
	
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
		finally{
			em.close();
		}
		emf.close();
		
	}
	
	public static void logic(EntityManager em) {
		
		String id = "id1";
		Member member = new Member();
		member.setId(id);
		member.setUsername("지한");
		member.setAge(2);
		member.setFirstName("강");
		member.setLastName("치한");
		
		em.persist(member);
		
		System.out.println("fullname=" + member.getFullName());
		
		member.setAge(20);
		
		Member findMember = em.find(Member.class, id);
		System.out.println(findMember.toString());
		
		// List<Member> members = 
		//		em.createQuery("select m from Member m", Member.class).getResultList();
		
		TypedQuery<Member> query = em.createQuery("select m from Member m", Member.class);
		List<Member> members = query.getResultList();
		
		System.out.println("members.size=" + members.size());
		
		System.out.println();
		
		em.remove(member);
	}

}
