package com.kwiktwik.feedbackservice.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class CandidateInfo {
    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "contactNumber")
    private String contactNumber;

    @JsonProperty(value = "resumeLink")
    private String resumeLink;

    @JsonProperty(value = "status")
    private String status;

    @JsonProperty(value = "currentRole")
    private String currentRole;

    @JsonProperty(value = "comments")
    private String[] comments;
}

