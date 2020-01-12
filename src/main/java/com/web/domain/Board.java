package com.web.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.web.domain.enums.BoardType;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table
public class Board implements Serializable {

	private static final long serialVersionUID = -8212484529001789121L;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idx;

	@Column
	private String title;

	@Column
	private String content;

	@Column
	private BoardType boardType;

	@Column
	private LocalDateTime createdDate;

	@Column
	private LocalDateTime updatedDate;

	@OneToOne(fetch = FetchType.LAZY)
	private User user;

	@Builder
	public Board(String title, String content, BoardType boardType, LocalDateTime createdDate,
			LocalDateTime updatedDate, User user) {
		super();
		this.title = title;
		this.content = content;
		this.boardType = boardType;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.user = user;
	}

}
