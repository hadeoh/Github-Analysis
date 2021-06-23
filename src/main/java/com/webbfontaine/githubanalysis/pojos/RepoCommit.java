package com.webbfontaine.githubanalysis.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RepoCommit {
    private String sha;
    private String url;
    private RepoOwner author;
    private RepoOwner committer;
}
