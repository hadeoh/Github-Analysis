package com.webbfontaine.githubanalysis.controllers;

import com.webbfontaine.githubanalysis.pojos.GithubRepo;
import com.webbfontaine.githubanalysis.services.RepoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
