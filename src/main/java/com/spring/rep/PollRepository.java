package com.spring.rep;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.model.Poll;

@Repository
public interface PollRepository extends JpaRepository<Poll, Long> {

}
