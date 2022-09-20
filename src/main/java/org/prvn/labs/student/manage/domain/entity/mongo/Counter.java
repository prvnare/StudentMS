package org.prvn.labs.student.manage.domain.entity.mongo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Counter {

  @MongoId
  private String counterName;
  private Integer currentSequence;
  private Integer nextSequence;

}
