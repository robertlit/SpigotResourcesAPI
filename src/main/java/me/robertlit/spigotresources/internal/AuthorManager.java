package me.robertlit.spigotresources.internal;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.gson.Gson;
import me.robertlit.spigotresources.api.Author;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class AuthorManager {

    private static final String GET_AUTHOR_URL = "https://api.spigotmc.org/simple/0.2/index.php?action=getAuthor&id=%d";
    private static final String FIND_AUTHOR_URL = "https://api.spigotmc.org/simple/0.2/index.php?action=findAuthor&name=%s";

    private final Gson gson = new Gson();
    private final LoadingCache<Integer, Author> authorIdCache;
    private final LoadingCache<String, Author> authorNameCache;

    public AuthorManager(long duration, TimeUnit unit) {
        this.authorIdCache = CacheBuilder.newBuilder()
                .expireAfterWrite(duration, unit)
                .build(new CacheLoader<Integer, Author>() {
                    @Override
                    public Author load(Integer authorId) throws Exception {
                        Author author = gson.fromJson(HttpRequester.requestString(String.format(GET_AUTHOR_URL, authorId)), Author.class);
                        if (author == null) {
                            throw new Exception();
                        }
                        authorNameCache.put(author.getUsername(), author);
                        return author;
                    }
                });
        this.authorNameCache = CacheBuilder.newBuilder()
                .expireAfterWrite(duration, unit)
                .build(new CacheLoader<String, Author>() {
                    @Override
                    public Author load(String username) throws Exception {
                        Author author = gson.fromJson(HttpRequester.requestString(String.format(FIND_AUTHOR_URL, username)), Author.class);
                        if (author == null) {
                            throw new Exception();
                        }
                        authorIdCache.put(author.getId(), author);
                        return author;
                    }
                });
    }

    public CompletableFuture<Author> getAuthor(int authorId) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return authorIdCache.get(authorId);
            } catch (ExecutionException e) {
                return null;
            }
        });
    }

    public CompletableFuture<Author> findAuthor(String username) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return authorNameCache.get(username);
            } catch (ExecutionException e) {
                return null;
            }
        });
    }
}
