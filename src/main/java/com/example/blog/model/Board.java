package com.example.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
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
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable=false, length=100)
	private String title;
	
	@Lob //대용량 데이터 
	private String content; 
	
	@ColumnDefault("0")
	private int count;	//조회수
	
	@ManyToOne(fetch = FetchType.EAGER) //Many = Board , One= User, User테이블 정보 필요 (EAGER)
	@JoinColumn(name = "userId")
	private User user; //DB는 Object를 저장할수 없음. FK 생성 , 연관관계 맺기
	
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER) //FK가 아니다. , Reply객체들 정보 필요 (EAGER)
	private List<Reply> reply;
	
	@CreationTimestamp
	private Timestamp createDate;
	
	
	
	
}
