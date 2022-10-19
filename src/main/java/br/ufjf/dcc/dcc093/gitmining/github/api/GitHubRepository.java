package br.ufjf.dcc.dcc093.gitmining.github.api;

import br.ufjf.dcc.dcc093.gitmining.http.GitHubHTTPClient;

/**
 *
 * @author gleiph
 */
public class GitHubRepository {

    //GitHub Http: https://github.com/voldemort/voldemort
    //GitHub API: https://api.github.com/repos/collectiveidea/css_naked_day
    private final String url;
    private final String urlAPI;
    private final String organization;
    private final String name;
    private final String token;

    public GitHubRepository(String url) {

        String[] split = url.split("/");

        if (split.length != 5) {
            throw new RuntimeException("It is not a GitHub url");
        }

        GitHubHTTPClient.executeRequest("https://api.github.com/repos/" + split[3] + "/" + split[4], null);

        this.url = url;
        this.organization = split[3];
        this.name = split[4];
        this.urlAPI = "https://api.github.com/repos/" + organization + "/" + name;
        this.token = null;
    }

    public GitHubRepository(String url, String token) {

        String[] split = url.split("/");

        if (split.length != 5) {
            throw new RuntimeException("It is not a GitHub url");
        }

        GitHubHTTPClient.executeRequest("https://api.github.com/repos/" + split[3] + "/" + split[4], token);

        this.url = url;
        this.organization = split[3];
        this.name = split[4];
        this.urlAPI = "https://api.github.com/repos/" + organization + "/" + name;
        this.token = token;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlAPI() {
        return urlAPI;
    }

    public String getOrganization() {
        return organization;
    }

    public String getName() {
        return name;
    }

    public String getToken() {
        return token;
    }

    
    
    @Override
    public String toString() {
        return "GitHubRepository{" + "url=" + url + ", urlAPI=" + urlAPI + ", organizacao=" + organization + ", name=" + name + '}';
    }

}
