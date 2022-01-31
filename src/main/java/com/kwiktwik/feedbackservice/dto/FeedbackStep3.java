package com.kwiktwik.feedbackservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackStep3 implements Serializable {
    @JsonProperty(value = "id")
    private String id;

    @JsonProperty(value = "timeSpent")
    private String timeSpent;

    @JsonProperty(value = "question")
    private String question;

    @JsonProperty(value = "answer")
    private String[] answer;
}
//{
//        "timeSpent": "34",
//        "question": "324234",
//        "answer": ["32423423", "234234234"]
//        },