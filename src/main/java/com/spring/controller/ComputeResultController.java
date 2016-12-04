package com.spring.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dto.OptionCount;
import com.spring.dto.VoteResult;
import com.spring.model.Vote;
import com.spring.rep.VoteRepository;

import io.swagger.annotations.Api;

@RestController
@Api(value = "Compute Result", description = "Compute Result")
public class ComputeResultController {

	@Inject
	private VoteRepository voteRepository;

	@RequestMapping(value = "/computeResult", method = RequestMethod.GET)
	public ResponseEntity<?> computeResult(@RequestParam Long pollId) {
		VoteResult voteResult = new VoteResult();
		Iterable<Vote> votes = voteRepository.findByPoll(pollId);
		int totalVotes = 0;
		Map<Long, OptionCount> map = new HashMap<>();
		for (Vote vote : votes) {
			totalVotes++;
			OptionCount optionCount = map.get(vote.getOption().getId());
			if (optionCount == null) {
				optionCount = new OptionCount();
				optionCount.setOptionId(vote.getOption().getId());
				map.put(vote.getOption().getId(), optionCount);
			}
			optionCount.setCount(optionCount.getCount() + 1);
		}
		voteResult.setTotalVotes(totalVotes);
		voteResult.setResults(map.values());
		return new ResponseEntity<>(voteResult, HttpStatus.OK);
	}
}
