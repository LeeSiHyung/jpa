package com.jpa.app.chap05;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
	
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			
			// testSave(em);
			// queryLogicJoin(em);
			
			// updateRelation(em);
			// Member member1 = em.find(Member.class, "member1");
			// System.out.println("member1.team.id = " + member1.getTeam().getId());
			// 
			// deleteRelation(em);
			
			// testSaveNonOwner(em);
			// test순수한객체_양방향();
			
			// testORM_양방향(em);
			// biDirection(em);
			test연관관계_편의메소드_버그(em);
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
	
	public static void testSave(EntityManager em) {
		
		// 팀 1 저장
		Team team1 = new Team("team1", "팀1");
		em.persist(team1);
		
		// 회원 1 저장
		Member member1 = new Member("member1", "회원1");
		member1.setTeam(team1); // 연간관계 설정 member1 -> team1
		//team1.getMembers().add(member1); // 추가하지 않는다면 team에서 members를 조회할 경우 값이 나오지 않는다.
		em.persist(member1);
		
		// 회원 2 저장
		Member member2 = new Member("member2", "회원2");
		member2.setTeam(team1);
		//team1.getMembers().add(member2); // 추가하지 않는다면 team에서 members를 조회할 경우 값이 나오지 않는다.
		em.persist(member2);
		
		
		Member member = em.find(Member.class, "member1");
		Team team = member.getTeam();
		System.out.println("팀 이름 = " + team.getName());
		
	}
	
	private static void queryLogicJoin(EntityManager em) {
		String jpql = "select m from Member m join m.team t where t.name = :teamName";
		List<Member> resultList = em.createQuery(jpql, Member.class)
				.setParameter("teamName", "팀1")
				.getResultList();
		
		for(Member member : resultList) {
			System.out.println("[query] member.username=" + member.getUsername());
		}
		
	}
	
	private static void updateRelation(EntityManager em) {
		// 새로운 팀2
		Team team2 = new Team("team2", "팀2");
		em.persist(team2);
		
		// 회원1에 새로운 팀2 설정
		Member member = em.find(Member.class, "member1");
		member.setTeam(team2);
	}
	
	private static void deleteRelation(EntityManager em) {
		Member member1 = em.find(Member.class, "member1");
		member1.setTeam(null);
		
		// team2의 연관관계 member1을 제거 했기 때문에 team2를 제거할 수 있음
		Team team2 = em.find(Team.class, "team2");
		em.remove(team2);
	}
	
	public static void biDirection(EntityManager em) {
		Team team = em.find(Team.class, "team1");
		// 객체 그래프 탐색 (팀 -> 회원)
		List<Member> members = team.getMembers();
		
		for(Member member : members) {
			System.out.println("member.username = " + member.getUsername());
		}
	}
	
	public static void testSaveNonOwner(EntityManager em) {
		
		// 회원1 저장
		Member member1 = new Member("member1", "회원1");
		em.persist(member1);
		
		// 회원2 저장
		Member member2 = new Member("member2", "회원2");
		em.persist(member2);
		
		Team team1 = new Team("team1", "팀1");
		// 주인이 아닌곳에만 연관관계 설정
		team1.getMembers().add(member1);
		team1.getMembers().add(member2);
		
		em.persist(team1);
	}
	
	public static void test순수한객체_양방향() {
		// 팀1
		Team team1 = new Team("team1", "팀1");
		Member member1 = new Member("member1", "회원1");
		Member member2 = new Member("member2", "회원2");
		
		member1.setTeam(team1);
		team1.getMembers().add(member1);
		member2.setTeam(team1);
		team1.getMembers().add(member2);
		
		List<Member> members = team1.getMembers();
		System.out.println("members.size = " + members.size());
	}
	
	public static void testORM_양방향(EntityManager em) {
		// 팀1 저장
		Team team1 = new Team("team1", "팀1");
		em.persist(team1);
		
		// 양방향 연관관계 설정
		Member member1 = new Member("member1", "회원1");
		member1.setTeam(team1);
		//team1.getMembers().add(member1);
		em.persist(member1);
		
		Member member2 = new Member("member2", "회원2");
		member2.setTeam(team1);
		//team1.getMembers().add(member2);
		em.persist(member2);
		
	}
	
	public static void test연관관계_편의메소드_버그(EntityManager em) {
		// 팀1 저장
		Team teamA = new Team("team1", "팀1");
		em.persist(teamA);
		
		Team teamB = new Team("team2", "팀2");
		em.persist(teamB);
		
		// 양방향 연관관계 설정
		Member member1 = new Member("member1", "회원1");
		member1.setTeam(teamA);
		member1.setTeam(teamB);
		member1.setTeam(teamA);
		em.persist(member1);
		
		List<Member> findMember = member1.getTeam().getMembers();
		System.out.println("findMember.size() = " + findMember.size());
		
	}
	

}
