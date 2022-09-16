package org.prvn.labs.student.manage.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
@JsonIgnoreProperties(value = {"cause","stackTrace","message","suppressed","localizedMessage"})
@JsonPropertyOrder(value = {"serviceName","endpoint","operation","traceId","statusCode","errorCode","errorDescription","typeofError","debugMessage"})
public class BaseException extends RuntimeException{

   @JsonInclude
   String serviceName;

   @JsonInclude
   String endpoint;

   @JsonInclude
   String errorCode;

   @JsonInclude
   String errorDescription;

   @JsonInclude
   HttpStatus statusCode;

   @JsonInclude
   String typeofError;

   @JsonInclude
   @JsonPropertyOrder("9")
   String debugMessage;

   @JsonInclude
   String traceId;

   @JsonInclude
   String operation;

   public BaseException(String errorCode, String errorDescription, String debugMessage, HttpStatus statusCode){

      this.errorCode = errorCode;
      this.errorDescription = errorDescription;
      this.debugMessage = debugMessage;
      this.statusCode = statusCode;
   }
}
