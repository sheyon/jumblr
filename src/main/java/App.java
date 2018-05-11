import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tumblr.jumblr.JumblrClient;
import com.tumblr.jumblr.types.Post;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Example usage of Jumblr
 * @author jc
 */
public class App {

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {

        // Read in the JSON data for the credentials
        FileReader fr = new FileReader("credentials.json");
        BufferedReader br = new BufferedReader(fr);
        StringBuilder json = new StringBuilder();
        try {
        	while (br.ready()) { json.append(br.readLine()); }
        } finally {
        	br.close();
        }

        // Parse the credentials
        JsonParser parser = new JsonParser();
        JsonObject obj = (JsonObject) parser.parse(json.toString());

        // Create a client
        JumblrClient client = new JumblrClient(
            obj.getAsJsonPrimitive("consumer_key").getAsString(),
            obj.getAsJsonPrimitive("consumer_secret").getAsString()
        );

        // Give it a token
        client.setToken(
            obj.getAsJsonPrimitive("oauth_token").getAsString(),
            obj.getAsJsonPrimitive("oauth_token_secret").getAsString()
        );

        // Usage
        List<Post> posts = client.blogPosts("seejohnrun");
        for (Post post : posts) {
            System.out.println(post.getShortUrl());
        }

    }

}
