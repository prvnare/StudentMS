package org.prvn.labs.student.manage.repository.mongo;

import org.prvn.labs.student.manage.domain.entity.mongo.Counter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CounterRepository extends MongoRepository<Counter,String> {


}
