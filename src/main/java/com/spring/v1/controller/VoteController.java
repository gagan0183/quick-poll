package com.spring.v1.controller;

import java.net.URI;

import javax.inject.Inject;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.spring.model.Vote;
import com.spring.rep.VoteRepository;

import io.swagger.annotations.Api;

@RestController("voteControllerV1")
@RequestMapping("/v1")
@Api(value = "Votes", description = "Votes")
public class VoteController {

	@Inject
	private VoteRepository voteRepository;

	@RequestMapping(value = "/polls/{pollId}/votes", method = RequestMethod.POST)
	public ResponseEntity<?> createVote(@PathVariable("pollId") Long pollId, @RequestBody Vote vote) {
		vote = voteRepository.save(vote);

		HttpHeaders httpHeaders = new HttpHeaders();
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{pollId}").buildAndExpand(vote.getId())
				.toUri();
		httpHeaders.setLocation(uri);
		return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
	}

	@RequestMapping(value = "/polls/{pollId}/votes", method = RequestMethod.GET)
	public Iterable<Vote> getAllVotes(@PathVariable Long pollId) {
		return voteRepository.findByPoll(pollId);
	}
}
