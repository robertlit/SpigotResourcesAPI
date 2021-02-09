package me.robertlit.spigotresources.internal;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.gson.Gson;
import me.robertlit.spigotresources.api.Author;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class AuthorManager {

    private static final String GET_AUTHOR_URL = "https://api.spigotmc.org/simple/0.1/index.php?action=getAuthor&id=%d";

    private final Gson gson = new Gson();
    private final LoadingCache<Integer, Author> authorCache;

    public AuthorManager(long duration, TimeUnit unit) {
        this.authorCache = CacheBuilder.newBuilder()
                .expireAfterWrite(duration, unit)
                .build(new CacheLoader<Integer, Author>() {
                    @Override
                    public Author load(@NotNull Integer authorId) throws Exception {
                        Author author = gson.fromJson(HttpRequester.requestString(String.format(GET_AUTHOR_URL, authorId)), Author.class);
                        if (author == null) {
                            throw new Exception();
                        }
                        return author;
                    }
                });
    }

    public CompletableFuture<Author> getAuthor(int authorId) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return authorCache.get(authorId);
            } catch (ExecutionException e) {
                return null;
            }
        });
    }
}
