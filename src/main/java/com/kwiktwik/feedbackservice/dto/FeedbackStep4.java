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
public class FeedbackStep4 implements Serializable {
    @JsonProperty(value = "highlights")
    private String[] highlights;

    @JsonProperty(value = "lowlights")
    private String[] lowlights;

    @JsonProperty(value = "scopeOfImprovement")
    private String[] scopeOfImprovement;

    @JsonProperty(value = "comments")
    private String[] comments;

    @JsonProperty(value = "requirementsGathering")
    private String requirementsGathering;

    @JsonProperty(value = "codingSkills")
    private String codingSkills;

    @JsonProperty(value = "htmlcss")
    private String htmlcss;

    @JsonProperty(value = "depthKnowledge")
    private String depthKnowledge;

    @JsonProperty(value = "experience")
    private String experience;

    @JsonProperty(value = "debugging")
    private String debugging;

    @JsonProperty(value = "problemSolving")
    private String problemSolving;

    @JsonProperty(value = "communication")
    private String communication;
}
