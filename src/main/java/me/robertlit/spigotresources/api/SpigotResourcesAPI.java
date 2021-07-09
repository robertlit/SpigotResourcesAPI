package me.robertlit.spigotresources.api;

import me.robertlit.spigotresources.internal.AuthorManager;
import me.robertlit.spigotresources.internal.ResourceManager;

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
     *
     * @param cacheDuration duration for which data is cached
     * @param cacheUnit     time unit for the duration
     */
    public SpigotResourcesAPI(long cacheDuration, TimeUnit cacheUnit) {
        this.resourceManager = new ResourceManager(cacheDuration, cacheUnit);
        this.authorManager = new AuthorManager(cacheDuration, cacheUnit);
    }

    /**
     * Get information about all resources in the system
     *
     * @param category the resource category to restrict results to. {@see #listResourceCategories}
     * @param page     the page number to retrieve. Items are paginated at 10 results per page.
     * @return the requested resources
     */
    public CompletableFuture<Collection<Resource>> listResources(int category, int page) {
        return resourceManager.listResources(category, page);
    }

    /**
     * Get information about a resource by its id
     *
     * @param resourceId the id of the resource to retrieve
     * @return the requested resource
     */
    public CompletableFuture<Resource> getResource(int resourceId) {
        return resourceManager.getResource(resourceId);
    }

    /**
     * Get information about all resources created by a specific author by the user id
     *
     * @param authorId the id of the author to restrict results to {@see #findAuthor}
     * @return resources of the given author
     */
    public CompletableFuture<Collection<Resource>> getResourcesByAuthor(int authorId) {
        return resourceManager.getResourcesByAuthor(authorId);
    }

    /**
     * Get information about available resource categories
     *
     * @return available resource categories
     */
    public CompletableFuture<Collection<Category>> listResourceCategories() {
        return resourceManager.listResourceCategories();
    }

    /**
     * Get information about a specific resource update by its id
     *
     * @param updateId the id of the resource <strong>update</strong> to retrieve {@see #getResourceUpdates}
     * @return the requested resource update
     */
    public CompletableFuture<ResourceUpdate> getResourceUpdate(int updateId) {
        return resourceManager.getResourceUpdate(updateId);
    }

    /**
     * Get information about all of the updates for a specific resource by the resource id
     *
     * @param resourceId the id of the resource for which to retrieve updates
     * @param page       the page number to retrieve. Items are paginated at 10 results per page
     * @return requested updates of the given resource
     */
    public CompletableFuture<Collection<ResourceUpdate>> getResourceUpdates(int resourceId, int page) {
        return resourceManager.getResourceUpdates(resourceId, page);
    }

    /**
     * Get information about an author by the user id
     *
     * @param authorId The id of the author to retrieve
     * @return the requested author
     */
    public CompletableFuture<Author> getAuthor(int authorId) {
        return authorManager.getAuthor(authorId);
    }

    /**
     * Get information about an author by the username (exact username match only)
     *
     * @param username the exactly matching username of the desired user, with escape sequences if necessary
     * @return the requested author
     */
    public CompletableFuture<Author> findAuthor(String username) {
        return authorManager.findAuthor(username);
    }
}
