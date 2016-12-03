package com.spring.rep;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.model.Vote;

@Repository
public interface VoteRepository extends CrudRepository<Vote, Long> {

}
