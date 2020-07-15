package com.example.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
//ORM : (JAVA)의 Object를 테이블로 매핑해주는 기술
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder //빌더 패턴
@Entity //User 클래스가 Mysql에 테이블이 생성됨
//@DynamicInsert //널 필드 제외하고 삽입
public class User {
	 
	 //프로젝트에 연결된 db의 넘버링 전략을 따라감
	 //오라클이면 시퀀스 사용, mysql이면 auto_increment 사용함을 의미
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // IDENTITY 
	private int id; //시퀀스 또는 auto_increment
	
	@Column(nullable=false, length=30, unique=true)
	private String username;
	
	@Column(nullable=false, length=100) // 비밀번호를 해쉬로 암호화 해서 넣기위해 넣넣히 100으로 잡음
	private String password;
	
	@Column(nullable=false, length=50)
	private String email;
	
	//@ColumnDefault("'user'") //디폴트값 user지정
	@Enumerated(EnumType.STRING)
	private RoleType role; //Enum을 쓰는게 더 좋음 (0,1,2) //admin, user, manager
	
	@CreationTimestamp	// 비워놔두어도 자동으로 시간이 자동으로 입력됨 
	private Timestamp createDate;
}
