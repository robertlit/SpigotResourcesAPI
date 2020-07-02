package me.robertlit.spigotresources.internal;

import com.google.gson.Gson;
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
                Author author = gson.fromJson(HttpRequester.requestString(GET_AUTHOR_URL+authorId), Author.class);
                idToAuthorMap.put(authorId, author);
                return author;
            });
        }
        return CompletableFuture.completedFuture(idToAuthorMap.get(authorId));
    }
}
