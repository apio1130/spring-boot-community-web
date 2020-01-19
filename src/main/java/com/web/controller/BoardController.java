package com.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.domain.Board;
import com.web.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {

	private static final String VIEW_BOARD_FORM = "board/form";
	private static final String VIEW_BOARD_LIST = "board/list";

	@Autowired
	private BoardService boardService;

	@GetMapping({ "", "/", "/list" })
	public String list(@PageableDefault Pageable pageable, Model model) {
		log.debug("pageable Info : {}", pageable.toString());
		model.addAttribute("boardList", boardService.findBoardList(pageable));
		return VIEW_BOARD_LIST;
	}

	@GetMapping("/{idx}")
	public String boardDetail(@PathVariable("idx") Long idx, Model model) {
		Board board = boardService.findBoardByIdx(idx);
		log.debug("board Info : {}", board.toString());
		model.addAttribute("board", board);
		return VIEW_BOARD_FORM;
	}

	@GetMapping("/regist")
	public String registBoard(Model model) {
		return VIEW_BOARD_FORM;
	}

}
