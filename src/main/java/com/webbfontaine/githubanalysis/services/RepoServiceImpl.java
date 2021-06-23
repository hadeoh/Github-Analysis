package com.webbfontaine.githubanalysis.services;

import com.webbfontaine.githubanalysis.components.RestCaller;
import com.webbfontaine.githubanalysis.pojos.GithubRepo;
import com.webbfontaine.githubanalysis.pojos.RepoCommit;
import com.webbfontaine.githubanalysis.pojos.RepoOwner;
import com.webbfontaine.githubanalysis.pojos.RepoSearch;
import com.webbfontaine.githubanalysis.utilities.JsonConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
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
        List<GithubRepo> repos = new ArrayList<>();
        String url = githubUrl + "/repositories";
        try {
            String repositories = restCaller.makeRequest(url, null, HttpMethod.GET);
            repos = Arrays.asList(JsonConverter.toObj(repositories, GithubRepo[].class));
        } catch (Exception e) {
            log.error("An error occurred while getting the public repositories dur to {}", e.getMessage());
        }
        return new ResponseEntity<>(repos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<GithubRepo> getRepo(Long id) {
        GithubRepo repo = new GithubRepo();
        String url = githubUrl + "/repositories/" + id;
        try {
            String repository = restCaller.makeRequest(url, null, HttpMethod.GET);
            repo = JsonConverter.toObj(repository, GithubRepo.class);
        } catch (Exception e) {
            log.error("An error occurred while getting the public repository with the id " + id + " due to {}", e.getMessage());
        }
        return new ResponseEntity<>(repo, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RepoSearch> searchRepos(String query, String sort, String order) {
        RepoSearch repos = new RepoSearch();
        String url = githubUrl + "/search/repositories";
        try {
            UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("q", query)
                    .queryParam("sort", sort)
                    .queryParam("order", order)
                    .build();
            String repositories = restCaller.makeRequest(builder.toUriString(), null, HttpMethod.GET);
            repos = JsonConverter.toObj(repositories, RepoSearch.class);
        } catch (Exception e) {
            log.error("An error occurred when searching for the repository due to {}", e.getMessage());
        }
        return new ResponseEntity<>(repos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<RepoOwner>> getRepoUsers(String owner, String repo) {
        List<RepoOwner> users = new ArrayList<>();
        String url = githubUrl + "/repos/" + owner + "/" + repo + "/contributors";
        try {
            String repoUsers = restCaller.makeRequest(url, null, HttpMethod.GET);
            users = Arrays.asList(JsonConverter.toObj(repoUsers, RepoOwner[].class));
        } catch (Exception e) {
            log.error("An error occurred while fetching the users of " + repo + " due to {}", e.getMessage());
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<RepoCommit>> getRepoCommits(String owner, String repo) {
        List<RepoCommit> commits = new ArrayList<>();
        String url = githubUrl + "/repos/" + owner + "/" + repo + "/commits?per_page=100";
        try {
            String repoCommits = restCaller.makeRequest(url, null, HttpMethod.GET);
            commits = Arrays.asList(JsonConverter.toObj(repoCommits, RepoCommit[].class));
        } catch (Exception e) {
            log.error("An error occurred while trying to fetch the commits due to {}", e.getMessage());
        }
        return new ResponseEntity<>(commits, HttpStatus.OK);
    }
}
