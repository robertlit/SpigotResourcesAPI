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
    private final LoadingCache<Integer, CompletableFuture<Author>> authorCache;

    public AuthorManager(long duration, TimeUnit unit) {
        this.authorCache = CacheBuilder.newBuilder()
                .expireAfterWrite(duration, unit)
                .build(new CacheLoader<Integer, CompletableFuture<Author>>() {
                    @Override
                    public CompletableFuture<Author> load(@NotNull Integer authorId) {
                        return CompletableFuture.supplyAsync(() -> gson.fromJson(HttpRequester.requestString(String.format(GET_AUTHOR_URL, authorId)), Author.class));
                    }
                });
    }

    public CompletableFuture<Author> getAuthor(int authorId) {
        try {
            return authorCache.get(authorId);
        } catch (ExecutionException e) {
            e.printStackTrace();
            return CompletableFuture.completedFuture(null);
        }
    }
}
