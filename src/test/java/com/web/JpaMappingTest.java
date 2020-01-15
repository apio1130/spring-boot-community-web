package com.web;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.web.domain.Board;
import com.web.domain.User;
import com.web.domain.enums.BoardType;
import com.web.repository.BoardRepository;
import com.web.repository.UserRepository;

@DataJpaTest
class JpaMappingTest {
	private final String boardTestTitle = "테스트";
	private final String email = "test@test.com";
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BoardRepository boardRepository;
	
	@BeforeEach
	public void init() {
		System.out.println("init");
		User user = userRepository.save(User.builder()
				.name("Tester")
				.password("test")
				.email(email)
				.createdDate(LocalDateTime.now())
				.updatedDate(LocalDateTime.now())
				.build());
		boardRepository.save(Board.builder()
				.title(boardTestTitle)
				.content("컨텐츠")
				.boardType(BoardType.FREE)
				.createdDate(LocalDateTime.now())
				.updatedDate(LocalDateTime.now())
				.user(user)
				.build());
	}

	@Test
	public void Repository_조회_테스트() {
		User user = userRepository.findByEmail(email);
		assertEquals(user.getName(), "Tester");
		assertEquals(user.getPassword(), "test");
		assertEquals(user.getEmail(), email);
		
		
		Board board = boardRepository.findByUser(user);
		assertEquals(board.getTitle(), boardTestTitle);
		assertEquals(board.getContent(), "컨텐츠");
		assertEquals(board.getBoardType(), BoardType.FREE);
		
	}

}
