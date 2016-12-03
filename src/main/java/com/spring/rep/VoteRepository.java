package com.spring.rep;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.model.Vote;

@Repository
public interface VoteRepository extends CrudRepository<Vote, Long> {

	@Query(value = "select v.* from Option o, Vote v where v.option_id = o.option_id and o.poll_id = ?1", nativeQuery = true)
	public Iterable<Vote> findByPoll(Long pollId);
}
