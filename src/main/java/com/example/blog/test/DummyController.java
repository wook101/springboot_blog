package com.example.blog.test;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.model.RoleType;
import com.example.blog.model.User;
import com.example.blog.repository.UserRepository;


@RestController
public class DummyController {
	
	@Autowired
	UserRepository userRepository;
	
	@PostMapping("/insert")
	public String postInsert(User user) {
		System.out.println(user);
		user.setRole(RoleType.ADMIN);
		userRepository.save(user);
		return "데이터 삽입";
	}
	
	@GetMapping("/select/{id}")
	public User getSelect(@PathVariable("id") int id) {
		//타입이 Optional인 이유는 user객체가 null인 경우를 대비해서 null 유무를 직접 판단하라는 의미
		//후에 IllegalArgumentException이 발생하면 에러페이지를 보여주게 구현
		User user  = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 사용자가 없습니다. id: " + id);
			}
		});
		return user; //user객체를 웹브라우저가 이해할수있는 Json형태로 변환 후 응답
	}
	
	@GetMapping("/selects")
	public List<User> getSelectAll(){
		List<User> userList = userRepository.findAll();
		return userList;
	}
	
	//페이징 기능
	@GetMapping("/select")
	public List<User> getSelectPaging(@PageableDefault(size=2, sort="id", direction=Direction.DESC) Pageable pageable){
		List<User> userList = userRepository.findAll(pageable).getContent();
		return userList;
	}
	
	//password, email 수정
	//요청 json데이터로 보냄
	//@RequestBody로 User객체 받음
	@Transactional
	@PutMapping("/update/{id}")
	public String update(@PathVariable(name="id") int id, @RequestBody User requestUser) { // MassageConverter가 jackson라이브러리를 호출해서 json데이터를 user객체로 변환해줌
		
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정을 실패하였습니다.");
		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		//더티 체킹
		//userRepository.save(user);
		
		return "수정 완료";
	}
	
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable(name="id") int id) {
		try {
			userRepository.deleteById(id);		
		}catch(EmptyResultDataAccessException e) {
			return "해당 id: "+id+"는 db에 존재하지 않습니다. 삭제할 수 없습니다.";
		}
		return "삭제 완료";
	}
	
}
