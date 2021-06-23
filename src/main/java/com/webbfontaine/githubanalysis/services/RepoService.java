package com.webbfontaine.githubanalysis.services;

import com.webbfontaine.githubanalysis.pojos.GithubRepo;
import com.webbfontaine.githubanalysis.pojos.RepoCommit;
import com.webbfontaine.githubanalysis.pojos.RepoOwner;
import com.webbfontaine.githubanalysis.pojos.RepoSearch;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RepoService {
    ResponseEntity<List<GithubRepo>> getRepos();
    ResponseEntity<GithubRepo> getRepo(Long id);
    ResponseEntity<RepoSearch> searchRepos(String query, String sort, String order);
    ResponseEntity<List<RepoOwner>> getRepoUsers(String owner, String repo);
    ResponseEntity<List<RepoCommit>> getRepoCommits(String owner, String repo);
}


