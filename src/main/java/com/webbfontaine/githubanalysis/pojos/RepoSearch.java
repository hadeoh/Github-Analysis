package com.webbfontaine.githubanalysis.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class RepoSearch {
    @JsonProperty("total_count")
    private Long totalCount;

    @JsonProperty("incomplete_results")
    private boolean incompleteResults;

    private List<GithubRepo> items;
}
