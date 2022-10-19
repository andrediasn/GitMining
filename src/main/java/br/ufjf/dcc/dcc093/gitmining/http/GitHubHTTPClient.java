package br.ufjf.dcc.dcc093.gitmining.http;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

/**
 *
 * @author gleiph
 */
public class GitHubHTTPClient {

    public static String executeRequest(String url, String token) {
        // Send GET request to the server API
        HttpResponse<String> httpResponse;

        if (token != null) {
            httpResponse
                    = Unirest
                            .get(url)
                            .header("Authorization", "Bearer " + token)
                            .asString(); // Return response as String
        } else {
            httpResponse
                    = Unirest
                            .get(url)
                            .asString(); // Return response as String
        }

        // Print server response to terminal
        String bodyResponse = httpResponse.getBody();

        if (bodyResponse.contains("\"message\":\"Not Found\"")) {
            throw new RuntimeException("It is not a GitHub url");
        }

        return bodyResponse;
    }

}
