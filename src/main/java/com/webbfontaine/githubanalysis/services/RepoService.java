package com.webbfontaine.githubanalysis.services;

import com.webbfontaine.githubanalysis.pojos.GithubRepo;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RepoService {
    ResponseEntity<List<GithubRepo>> getRepos();
    ResponseEntity<GithubRepo> getRepo(Long id);
}
