import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class URLShortener {
    private Map<String, String> shortToLongMap;
    private Map<String, String> longToShortMap;

    public URLShortener() {
        this.shortToLongMap = new HashMap<>();
        this.longToShortMap = new HashMap<>();
    }

    // Generate a random alphanumeric string of given length
    private String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomString = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            randomString.append(characters.charAt(random.nextInt(characters.length())));
        }
        return randomString.toString();
    }

    // Shorten a long URL
    public String shortenURL(String longURL) {
        if (longToShortMap.containsKey(longURL)) {
            return longToShortMap.get(longURL);
        }

        String shortURL;
        do {
            shortURL = generateRandomString(6); // Adjust the length as needed
        } while (shortToLongMap.containsKey(shortURL));

        shortToLongMap.put(shortURL, longURL);
        longToShortMap.put(longURL, shortURL);

        return shortURL;
    }

    // Expand a short URL
    public String expandURL(String shortURL) {
        return shortToLongMap.getOrDefault(shortURL, "Invalid short URL");
    }

    public static void main(String[] args) {
        URLShortener urlShortener = new URLShortener();

        // Example usage
        String longURL = "https://www.gani.com";
        String shortURL = urlShortener.shortenURL(longURL);
        System.out.println("Shortened URL: " + shortURL);

        String expandedURL = urlShortener.expandURL(shortURL);
        System.out.println("Expanded URL: " + expandedURL);
    }
}