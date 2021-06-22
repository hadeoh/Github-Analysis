package com.webbfontaine.githubanalysis.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RepoOwner {
    private String login;
    private Long id;
    private String type;
}
