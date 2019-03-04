package com.jpa.app.chap05;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

// @Entity
public class Team {
	
	@Id
	@Column(name="TEAM_ID")
	private String id;
	
	private String name;
	
	//== 추가 ==//
	// 연관관계가 양방향이므로 한곳에서 외래키를 관리해야 하는데 이것을 연관관계이 주인이라고 한다.
	// Member에 외래키 team_id가 지정되어 있기 때문에 연관관계의 주인은 Member가 된다.
	// 그렇기 때문에 주인이 아닌 Team은 mappedBy를 설정해줘야 한다.
	@OneToMany(mappedBy = "team")
	private List<Member> members = new ArrayList<Member>();
	
	public Team() {
	}
	
	public Team(String id, String name) {
		this.id = id;
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}
	
}
