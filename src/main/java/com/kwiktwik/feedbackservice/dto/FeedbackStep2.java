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
public class FeedbackStep2 implements Serializable {
    @JsonProperty(value = "timeSpent")
    private String timeSpent;

    @JsonProperty(value = "question")
    private String question;

    @JsonProperty(value = "approach")
    private String[] approach;

    @JsonProperty(value = "spaceComplexity")
    private String spaceComplexity;

    @JsonProperty(value = "timeComplexity")
    private String timeComplexity;

    @JsonProperty(value = "positive")
    private String[] positive;

    @JsonProperty(value = "negative")
    private String[] negative;

    @JsonProperty(value = "neutral")
    private String[] neutral;
}