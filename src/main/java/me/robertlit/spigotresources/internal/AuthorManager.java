package me.robertlit.spigotresources.internal;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import me.robertlit.spigotresources.api.Author;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class AuthorManager {

    private static final String GET_AUTHOR_URL = "https://api.spigotmc.org/simple/0.1/index.php?action=getAuthor&id=";

    private final Map<Integer, Author> idToAuthorMap = Collections.synchronizedMap(new HashMap<>());

    private final Gson gson = new Gson();

    public CompletableFuture<Author> getAuthor(int authorId, boolean fetch) {
        if (fetch || idToAuthorMap.get(authorId) == null) {
            return CompletableFuture.supplyAsync(() -> {
                JsonElement jsonElement = HttpRequester.request(GET_AUTHOR_URL+authorId);
                if (jsonElement == null || !jsonElement.isJsonObject()) {
                    return null;
                }
                Author author = gson.fromJson(jsonElement, Author.class);
                idToAuthorMap.put(authorId, author);
                return author;
            });
        }
        CompletableFuture<Author> future = new CompletableFuture<>();
        future.complete(idToAuthorMap.get(authorId));
        return future;
    }
}
