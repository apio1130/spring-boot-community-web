package com.web;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.web.domain.Board;
import com.web.domain.User;
import com.web.domain.enums.BoardType;
import com.web.repository.BoardRepository;
import com.web.repository.UserRepository;
import com.web.resolver.UserArgumentResolver;

@SpringBootApplication
public class SpringBootCommunityWebApplication extends WebMvcConfigurerAdapter {
    
    @Autowired
    private UserArgumentResolver userArgumentResolver;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootCommunityWebApplication.class, args);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(userArgumentResolver);
    }

    @Bean
    public CommandLineRunner runner(UserRepository userRepository, BoardRepository boardRepository) throws Exception {
        return (args) -> {
            User user = userRepository.save(User.builder().name("Tester").password("test").email("test@test.com")
                    .createdDate(LocalDateTime.now()).updatedDate(LocalDateTime.now()).build());

            IntStream.range(1, 215)
                    .forEach(idx -> boardRepository.save(Board.builder().title("게시글" + idx).content("컨텐츠")
                            .boardType(BoardType.FREE).createdDate(LocalDateTime.now().minusYears(1L).plusDays(idx))
                            .updatedDate(LocalDateTime.now().minusYears(1L).plusDays(idx)).user(user).build()));
        };

    }

}
