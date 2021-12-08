package com.demo.relationalGeode;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SomeObjectRepository extends CrudRepository<SomeObject, String> {
}
