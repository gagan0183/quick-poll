package com.spring.rep;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.model.Option;

@Repository
public interface OptionRepository extends CrudRepository<Option, Long> {

}
