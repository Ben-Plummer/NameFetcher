import java.io.*;
import java.net.*;
import java.util.Scanner;

public class PersonFetcher {

    public void run() {
        // Get the user to enter a username
        System.out.print("Enter username: ");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();
        scanner.close();

        // Construct URL from the username
        String url = URL_START + username;

        // Look for the title tag and use for first index
        String titleTag = "<title>";
        String html = fetchHTML(url);
        int firstIndex = html.indexOf(titleTag) + titleTag.length();
        // Last index is the ' |' - the titles of the pages are like this
        int lastIndex = html.indexOf(" |", firstIndex);

        // Get the name and print it out
        String name = html.substring(firstIndex, lastIndex);
        System.out.println(name);
    }

    private String fetchHTML(String url) {
        String html = "";

        try {
            URL urlHandle = new URL(url);
            BufferedReader in = new BufferedReader(new InputStreamReader(urlHandle.openStream()));
            String line;
            while ((line = in.readLine()) != null)
                html += line + "\n";
            in.close();
        }
        catch (Exception e) {
            System.out.println("HTML fetching failed with: " + e.toString());
        }

        return html;
    }

    private static final String URL_START = "https://www.ecs.soton.ac.uk/people/";

}
