package me.robertlit.spigotresources.api;

import me.robertlit.spigotresources.internal.AuthorManager;
import me.robertlit.spigotresources.internal.ResourceManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * The main class of SpigotResourcesAPI
 */
public final class SpigotResourcesAPI {

    private final ResourceManager resourceManager;
    private final AuthorManager authorManager;
    /**
     * Constructs an API
     * @param cacheDuration the duration for which data is cached
     * @param cacheUnit the time unit for the duration
     */
    public SpigotResourcesAPI(long cacheDuration, TimeUnit cacheUnit) {
        this.resourceManager = new ResourceManager(cacheDuration, cacheUnit);
        this.authorManager = new AuthorManager(cacheDuration, cacheUnit);
    }

    /**
     * Gets the Resource with the given id
     * @param resourceId resource id
     * @return a future, which is to be completed with the wanted Resource or null
     */
    @NotNull
    public CompletableFuture<@Nullable Resource> getResource(int resourceId) {
        return resourceManager.getResource(resourceId);
    }

    /**
     * Gets the Resources of a given Author
     * @param authorId id of the Author
     * @return a future, which is to be completed with an unmodifiable collection representing the resources of the given Author
     */
    @NotNull
    public CompletableFuture<@NotNull Collection<Resource>> getResourcesByAuthor(int authorId) {
        return resourceManager.getResourcesByAuthor(authorId);
    }

    /**
     * Gets the Author with the given id
     * @param authorId author id
     * @return a future, which is to be completed with the wanted Author or null
     */
    @NotNull
    public CompletableFuture<@Nullable Author> getAuthor(int authorId) {
        return authorManager.getAuthor(authorId);
    }
}
