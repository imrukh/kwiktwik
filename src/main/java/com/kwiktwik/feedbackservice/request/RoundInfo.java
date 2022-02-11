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
public class RoundInfo {
    @JsonProperty(value = "cmpName")
    private String cmpName;

    @JsonProperty(value = "roundName")
    private String roundName;

    @JsonProperty(value = "role")
    private String role;

    @JsonProperty(value = "guideLink")
    private String guideLink;

    @JsonProperty(value = "comments")
    private String[] comments;
}

