package com.spring.rep;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.model.Poll;

@Repository
public interface PollRepository extends CrudRepository<Poll, Long> {

}
