package com.webbfontaine.githubanalysis.services;

import com.webbfontaine.githubanalysis.components.RestCaller;
import com.webbfontaine.githubanalysis.pojos.GithubRepo;
import com.webbfontaine.githubanalysis.utilities.JsonConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RepoServiceImpl implements RepoService{

    private final RestCaller restCaller;

    @Value("${github.url}")
    private String githubUrl;

    @Override
    public ResponseEntity<List<GithubRepo> > getRepos() {
        List<GithubRepo> repos = null;
        githubUrl += "/repositories";
        try {
            String repositories = restCaller.makeRequest(githubUrl, null, HttpMethod.GET);
            repos = Arrays.asList(JsonConverter.toObj(repositories, GithubRepo[].class));
        } catch (Exception e) {
            log.error("An error occurred while getting the public repositories dur to {}", e.getMessage());
        }
        return new ResponseEntity<>(repos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<GithubRepo> getRepo(Long id) {
        GithubRepo repo = new GithubRepo();
        githubUrl += "/repositories/" + id;
        try {
            String repository = restCaller.makeRequest(githubUrl, null, HttpMethod.GET);
            repo = JsonConverter.toObj(repository, GithubRepo.class);
        } catch (Exception e) {
            log.error("An error occurred while getting the public repository with the id " + id + " due to {}", e.getMessage());
        }
        return new ResponseEntity<>(repo, HttpStatus.OK);
    }
}
