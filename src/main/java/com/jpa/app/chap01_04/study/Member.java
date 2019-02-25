package com.jpa.app.chap01_04.study;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;


@Entity
//@org.hibernate.annotations.DynamicInsert
//@org.hibernate.annotations.DynamicUpdate
@Table(name="MEMBER", uniqueConstraints = {
	@UniqueConstraint(name="NAME_AGE_UNIQUE", columnNames= {"NAME","AGE"})	
})
public class Member {
	
	@Id
	@Column(name="ID")
	private String id;
	@Column(name="NAME", nullable=false, length=10)
	private String username;
	
	private Integer age = new Integer(0);
	
	//== 추가 ==
	@Enumerated(EnumType.STRING)
	@Column(name="ROLE_TYPE")
	private RoleType roleType;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATED_DATE")
	private Date createdDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LAST_MODIFIED_DATE")
	private Date lastModifiedDate;
	
	@Lob
	private String description;
	
	@Transient
	private String firstName;
	
	@Transient
	private String lastName;
	
	@Access(AccessType.PROPERTY)
	public String getFullName() {
		return firstName + lastName;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public RoleType getRoleType() {
		return roleType;
	}
	public void setRoleType(RoleType roleType) {
		this.roleType = roleType;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", username=" + username + ", age=" + age + ", roleType=" + roleType
				+ ", createdDate=" + createdDate + ", lastModifiedDate=" + lastModifiedDate + ", description="
				+ description + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}

	

}
