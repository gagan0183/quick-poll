package com.spring.controller;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.model.Poll;
import com.spring.rep.PollRepository;

@RestController
public class PollController {

	@Inject
	private PollRepository pollRepository;

	@RequestMapping(value = "/polls", method = RequestMethod.GET)
	private ResponseEntity<Iterable<Poll>> getAllPolls() {
		return new ResponseEntity<Iterable<Poll>>(pollRepository.findAll(), HttpStatus.OK);
	}
}
