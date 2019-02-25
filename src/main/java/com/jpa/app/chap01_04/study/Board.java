package com.jpa.app.chap01_04.study;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
// accessType이 FIELD는 인스턴스 변수가 private이여도 직접 접근한다.
// accessType이 PROPERTY getter,setter 메소드에 접근한다.
// @Access(AccessType.FIELD)
// Access(AccessType.PROPERTY)
public class Board {
	
	@Id
	// H2에서는 작동되지 않는다. MYSQL 작동
	// @GeneratedValue(strategy=GenerationType.IDENTITY)
	
	// H2 작동, ORACLE 작동
	// @SequenceGenerator(name="BOARD_SEQ_GENERATOR", sequenceName="BOARD_SEQ", initialValue=1, allocationSize=1)
	// @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BOARD_SEQ_GENERATOR")
	
	// 모든 DB에서 사용가능
	// @TableGenerator(name="BOARD_SEQ_GENERATOR", table="MY_SEQUENCES", pkColumnValue="BOARD_SEQ", allocationSize=1)
	// @GeneratedValue(strategy=GenerationType.TABLE, generator="BOARD_SEQ_GENERATOR")
	
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="DATA", columnDefinition="varchar(100) default 'EMPTY'")
	private String data;
	
	@Column(precision=10, scale=2)
	private BigDecimal cal;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public BigDecimal getCal() {
		return cal;
	}

	public void setCal(BigDecimal cal) {
		this.cal = cal;
	}

	
}
