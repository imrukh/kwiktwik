package com.kwiktwik.feedbackservice.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kwiktwik.feedbackservice.entity.Feedback;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class Interview {
    @JsonProperty(value = "id")
    private String id;

    @JsonProperty(value = "cmpId")
    private String cmpId;

    @JsonProperty(value = "startTime")
    private String startTime;

    @JsonProperty(value = "meetingLink")
    private String meetingLink;

    @JsonProperty(value = "resumeLink")
    private String resumeLink;

    @JsonProperty(value = "feedbackStatus")
    private String feedbackStatus;

    @JsonProperty(value = "status")
    private String status;

    @JsonProperty(value = "comments")
    private String[] comments;

    @JsonProperty(value = "createdAt")
    private String createdAt;

    @JsonProperty(value = "createdBy")
    private String createdBy;

    @JsonProperty(value = "modifiedAt")
    private String modifiedAt;

    @JsonProperty(value = "modifiedBy")
    private String modifiedBy;
}

