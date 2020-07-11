package com.example.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.example.blog.model.User.UserBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder //빌더 패턴
@Entity
public class Reply {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable=false, length=200)
	private String content;
	
	//FK 2개 필요 User, Board의 id 참조
	@ManyToOne
	@JoinColumn(name="userid")
	private User user;
	
	@ManyToOne // Many = reply, One = board
	@JoinColumn(name="boardId")
	private Board board;
	
	@CreationTimestamp
	private Timestamp createDate;
	
}
