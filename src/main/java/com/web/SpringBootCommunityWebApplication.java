package com.web;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.web.domain.Board;
import com.web.domain.User;
import com.web.domain.enums.BoardType;
import com.web.repository.BoardRepository;
import com.web.repository.UserRepository;

@SpringBootApplication
public class SpringBootCommunityWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCommunityWebApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(UserRepository userRepository, BoardRepository boardRepository) throws Exception {
		return (args) -> {
			User user = userRepository.save(User.builder()
					.name("Tester")
					.password("test")
					.email("test@test.com")
					.createdDate(LocalDateTime.now())
					.updatedDate(LocalDateTime.now())
					.build());
			
			IntStream.range(1, 200).forEach(idx -> boardRepository.save(Board.builder()
					.title("게시글" + idx)
					.content("컨텐츠")
					.boardType(BoardType.free)
					.createdDate(LocalDateTime.now().minusYears(1L).plusDays(idx))
					.updatedDate(LocalDateTime.now().minusYears(1L).plusDays(idx))
					.user(user)
					.build()));
		};
		
	}
	
}
