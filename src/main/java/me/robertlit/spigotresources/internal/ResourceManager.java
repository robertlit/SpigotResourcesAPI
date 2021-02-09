package me.robertlit.spigotresources.internal;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import me.robertlit.spigotresources.api.Resource;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class ResourceManager {

    private static final String GET_RESOURCE_URL = "https://api.spigotmc.org/simple/0.1/index.php?action=getResource&id=%d";
    private static final String GET_RESOURCES_BY_AUTHOR_URL = "https://api.spigotmc.org/simple/0.1/index.php?action=getResourcesByAuthor&id=%d";

    private final Gson gson = new Gson();
    private final LoadingCache<Integer, CompletableFuture<Resource>> resourceCache;
    private final LoadingCache<Integer, CompletableFuture<Collection<Resource>>> authorResourcesCache;

    public ResourceManager(long duration, TimeUnit unit) {
        this.resourceCache = CacheBuilder.newBuilder()
                .expireAfterWrite(duration, unit)
                .build(new CacheLoader<Integer, CompletableFuture<Resource>>() {
                    @Override
                    public CompletableFuture<Resource> load(@NotNull Integer resourceId) {
                        return CompletableFuture.supplyAsync(() -> gson.fromJson(HttpRequester.requestString(String.format(GET_RESOURCE_URL, resourceId)), Resource.class));
                    }
                });
        this.authorResourcesCache = CacheBuilder.newBuilder()
                .expireAfterWrite(duration, unit)
                .build(new CacheLoader<Integer, CompletableFuture<Collection<Resource>>>() {
                    @Override
                    public CompletableFuture<Collection<Resource>> load(@NotNull Integer authorId) {
                        return CompletableFuture.supplyAsync(() -> {
                            Type type = new TypeToken<Collection<Resource>>(){}.getType();
                            Collection<Resource> resources = gson.fromJson(HttpRequester.requestString(String.format(GET_RESOURCES_BY_AUTHOR_URL, authorId)), type);
                            if (resources == null) {
                                return Collections.emptyList();
                            }
                            return Collections.unmodifiableCollection(resources);
                        });
                    }
                });
    }

    public CompletableFuture<Resource> getResource(int resourceId) {
        try {
            return resourceCache.get(resourceId);
        } catch (ExecutionException e) {
            e.printStackTrace();
            return CompletableFuture.completedFuture(null);
        }
    }

    public CompletableFuture<Collection<Resource>> getResourcesByAuthor(int authorId) {
        try {
            return authorResourcesCache.get(authorId);
        } catch (ExecutionException e) {
            e.printStackTrace();
            return CompletableFuture.completedFuture(Collections.emptyList());
        }
    }
}
