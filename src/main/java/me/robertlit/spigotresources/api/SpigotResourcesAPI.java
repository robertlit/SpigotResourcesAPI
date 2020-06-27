package me.robertlit.spigotresources.api;

import me.robertlit.spigotresources.internal.AuthorManager;
import me.robertlit.spigotresources.internal.ResourceManager;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;

/**
 * The main class of SpigotResourcesAPI
 */
public class SpigotResourcesAPI {

    private final boolean fetchByDefault;

    /**
     * Constructs an API
     * @param fetchByDefault whether to fetch data or retrieve it from cache by default
     */
    public SpigotResourcesAPI(boolean fetchByDefault) {
        this.fetchByDefault = fetchByDefault;
    }

    private final ResourceManager resourceManager = new ResourceManager();
    private final AuthorManager authorManager = new AuthorManager();

    /**
     * Gets the Resource with the given id
     * If this API is set to fetch by default or if the data is not in cache, the data will be fetched,
     * otherwise the data will be gotten from cache
     * @param resourceId resource id
     * @return a future which is to be completed with the wanted Resource
     */
    public CompletableFuture<Resource> getResource(int resourceId) {
        return getResource(resourceId, fetchByDefault);
    }

    /**
     * Gets the Resource with the given id
     * Note: If fetch is true but the data is not in cache, it will be fetched
     * @param resourceId resource id
     * @param fetch whether to fetch data or get from cache
     * @return a future which is to be completed with the wanted Resource
     */
    public CompletableFuture<Resource> getResource(int resourceId, boolean fetch) {
        return resourceManager.getResource(resourceId, fetch);
    }

    /**
     * Gets the Resources of a given Author
     * If this API is set to fetch by default or if the data is not in cache, the data will be fetched,
     * otherwise the data will be gotten from cache
     * @param authorId id of the Author
     * @return a future which is to be completed with an unmodifiable collection representing the resources of the given Author
     */
    public CompletableFuture<Collection<Resource>> getResourcesByAuthor(int authorId) {
        return getResourcesByAuthor(authorId, fetchByDefault);
    }

    /**
     * Gets the Resources of a given Author
     * Note: If fetch is true but the data is not in cache, it will be fetched
     * @param authorId id of the Author
     * @param fetch whether to fetch data or get from cache
     * @return a future which is to be completed with an unmodifiable collection representing the resources of the given Author
     */
    public CompletableFuture<Collection<Resource>> getResourcesByAuthor(int authorId, boolean fetch) {
        return resourceManager.getResourcesByAuthor(authorId, fetch);
    }

    /**
     * Gets the Author with the given id
     * If this API is set to fetch by default or if the data is not in cache, the data will be fetched,
     * otherwise the data will be gotten from cache
     * @param authorId author id
     * @return a future which is to be completed with the wanted Author
     */
    public CompletableFuture<Author> getAuthor(int authorId) {
        return getAuthor(authorId, fetchByDefault);
    }

    /**
     * Gets the Author with the given id
     * Note: If fetch is true but the data is not in cache, it will be fetched
     * @param authorId author id
     * @param fetch whether to fetch data or get from cache
     * @return a future which is to be completed with the wanted Author
     */
    public CompletableFuture<Author> getAuthor(int authorId, boolean fetch) {
        return authorManager.getAuthor(authorId, fetch);
    }
}
