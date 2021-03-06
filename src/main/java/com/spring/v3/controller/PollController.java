package com.spring.v3.controller;

import java.net.URI;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.spring.exception.ResourceNotFoundException;
import com.spring.model.Poll;
import com.spring.rep.PollRepository;

import io.swagger.annotations.Api;

@RestController("pollControllerV3")
@RequestMapping({ "/v3", "/oauth2/v3" })
@Api(value = "Polls", description = "Polls")
public class PollController {

	@Inject
	private PollRepository pollRepository;

	@RequestMapping(value = "/polls", method = RequestMethod.GET)
	public ResponseEntity<Iterable<Poll>> getAllPolls(Pageable pageable) {
		return new ResponseEntity<Iterable<Poll>>(
				pollRepository.findAll(new PageRequest(0, 5, new Sort(Direction.DESC, "question"))), HttpStatus.OK);
	}

	@RequestMapping(value = "/poll/{pollId}", method = RequestMethod.GET)
	public ResponseEntity<Poll> getPoll(@PathVariable("pollId") long pollId) {
		verifyPoll(pollId);
		Poll poll = pollRepository.findOne(pollId);
		return new ResponseEntity<Poll>(poll, HttpStatus.OK);
	}

	@RequestMapping(value = "/polls", method = RequestMethod.POST)
	public ResponseEntity<?> createPoll(@Valid @RequestBody Poll poll) {
		pollRepository.save(poll);
		HttpHeaders httpHeaders = new HttpHeaders();
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(poll.getId()).toUri();
		httpHeaders.setLocation(uri);
		return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/polls/{pollId}", method = RequestMethod.PUT)
	public ResponseEntity<?> updatePoll(@RequestBody Poll poll, @PathVariable("pollId") Long pollId) {
		verifyPoll(pollId);
		Poll p = pollRepository.save(poll);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PreAuthorize(value = "hasAuthority('ROLE_ADMIN')")
	@RequestMapping(value = "/polls/{pollId}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletePoll(@PathVariable("pollId") Long pollId) {
		verifyPoll(pollId);
		pollRepository.delete(pollId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	protected void verifyPoll(Long pollId) {
		Poll poll = pollRepository.findOne(pollId);
		if (poll == null) {
			throw new ResourceNotFoundException("Poll with id " + pollId + " not found");
		}
	}
}
