package com.kwiktwik.feedbackservice.response;

import lombok.Data;

@Data
public class BaseMessageResponse {

  private Boolean status;
  private String message;

  public BaseMessageResponse(){

  }

  public BaseMessageResponse(Boolean status, String message) {
    this.status = status;
    this.message = message;
  }
}
