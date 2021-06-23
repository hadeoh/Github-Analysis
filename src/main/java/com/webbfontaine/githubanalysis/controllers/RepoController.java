package com.webbfontaine.githubanalysis.controllers;

import com.webbfontaine.githubanalysis.pojos.GithubRepo;
import com.webbfontaine.githubanalysis.pojos.RepoCommit;
import com.webbfontaine.githubanalysis.pojos.RepoOwner;
import com.webbfontaine.githubanalysis.pojos.RepoSearch;
import com.webbfontaine.githubanalysis.services.RepoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/repos")
public class RepoController {

    private final RepoService repoService;

    @GetMapping
    public ResponseEntity<List<GithubRepo>> getRepos() {
        return repoService.getRepos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GithubRepo> getRepo(@PathVariable Long id) {
        return repoService.getRepo(id);
    }

    @GetMapping("/search")
    public ResponseEntity<RepoSearch> searchRepos(@RequestParam String query, @RequestParam(required = false) String sort, @RequestParam(required = false) String order) {
        return repoService.searchRepos(query, sort, order);
    }

    @GetMapping("/users/{owner}/{repo}")
    public ResponseEntity<List<RepoOwner>> getRepoUsers(@PathVariable("owner") String owner, @PathVariable("repo") String repo) {
        return repoService.getRepoUsers(owner, repo);
    }

    @GetMapping("/commits/{owner}/{repo}")
    public ResponseEntity<List<RepoCommit>> getRepoCommits(@PathVariable("owner") String owner, @PathVariable("repo") String repo) {
        return repoService.getRepoCommits(owner, repo);
    }

}
