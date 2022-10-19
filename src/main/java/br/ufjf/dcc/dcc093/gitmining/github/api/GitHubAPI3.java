package br.ufjf.dcc.dcc093.gitmining.github.api;

import br.ufjf.dcc.dcc093.gitmining.http.GitHubHTTPClient;
import br.ufjf.dcc.dcc093.gitmining.model.Contribution;
import br.ufjf.dcc.dcc093.gitmining.model.Contributor;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gleiph
 * @author andrediasn
 */
public class GitHubAPI3 {

    //APIUrl Example: https://api.github.com/repos/collectiveidea/css_naked_day/contributors
    private static final int CONTRIBUTOR_PAGES = 5;

    
    public static List<Contributor> repositoryContributors(GitHubRepository repository) {

        List<Contributor> contributors = new ArrayList<>();

        for (int page = 1; page <= CONTRIBUTOR_PAGES; page++) {

            String githubURL = "https://api.github.com/repos/"
                    + repository.getOrganization() + "/" + repository.getName()
                    + "/contributors?per_page=100&&page=" + page;

            String body = GitHubHTTPClient.executeRequest(githubURL, repository.getToken());

            JsonElement jelement = JsonParser.parseString(body);
            JsonArray jsonArray = jelement.getAsJsonArray();

            if (jsonArray.isEmpty()) {
                break;
            }

            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
                String login = jsonObject.get("login").getAsString();
                contributors.add(new Contributor(login));
            }
        }

        return contributors;
    }

    public static List<Contribution> repositoryContributionsByContributors(GitHubRepository repository) {

        List<Contribution> contributions = new ArrayList<>();

        for (int page = 1; page <= CONTRIBUTOR_PAGES; page++) {

            String githubURL = "https://api.github.com/repos/"
                    + repository.getOrganization() + "/" + repository.getName()
                    + "/contributors?per_page=100&&page=" + page;

            String body = GitHubHTTPClient.executeRequest(githubURL, repository.getToken());

            JsonElement jelement = JsonParser.parseString(body);
            JsonArray jsonArray = jelement.getAsJsonArray();

            if (jsonArray.isEmpty()) {
                break;
            }

            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
                String login = jsonObject.get("login").getAsString();
                int contributionsAmount = jsonObject.get("contributions").getAsInt();
                contributions.add(new Contribution(login, contributionsAmount));
            }
        }

        return contributions;
    }

    public static List<Contribution> repositoryContributorsWithMoreThan10Contributions(GitHubRepository repository) {

        List<Contribution> contributions = new ArrayList<>();

        for (int page = 1; page <= CONTRIBUTOR_PAGES; page++) {

            String githubURL = "https://api.github.com/repos/"
                    + repository.getOrganization() + "/" + repository.getName()
                    + "/contributors?per_page=100&&page=" + page;

            String body = GitHubHTTPClient.executeRequest(githubURL, repository.getToken());

            JsonElement jelement = JsonParser.parseString(body);
            JsonArray jsonArray = jelement.getAsJsonArray();

            if (jsonArray.isEmpty()) {
                break;
            }

            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
                String login = jsonObject.get("login").getAsString();
                int contributionsAmount = jsonObject.get("contributions").getAsInt();
                if(contributionsAmount > 10){
                    contributions.add(new Contribution(login, contributionsAmount));
                }
            }
        }

        return contributions;
    }
}
