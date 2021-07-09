package me.robertlit.spigotresources.internal;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import me.robertlit.spigotresources.api.Category;
import me.robertlit.spigotresources.api.Resource;
import me.robertlit.spigotresources.api.ResourceUpdate;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class ResourceManager {

    private static final String GET_RESOURCE_URL = "https://api.spigotmc.org/simple/0.2/index.php?action=getResource&id=%d";
    private static final String GET_RESOURCES_BY_AUTHOR_URL = "https://api.spigotmc.org/simple/0.2/index.php?action=getResourcesByAuthor&id=%d";
    private static final String LIST_RESOURCES_URL = "https://api.spigotmc.org/simple/0.2/index.php?action=listResources&page=%d";
    private static final String LIST_RESOURCES_CAT_URL = LIST_RESOURCES_URL + "&cat=%d";
    private static final String LIST_CATEGORIES_URL = "https://api.spigotmc.org/simple/0.2/index.php?action=listResourceCategories";
    private static final String GET_RESOURCE_UPDATE_URL = "https://api.spigotmc.org/simple/0.2/index.php?action=getResourceUpdate&id=%d";
    private static final String GET_RESOURCE_UPDATES_URL = "https://api.spigotmc.org/simple/0.2/index.php?action=getResourceUpdates&id=%d&page=%d";

    private final Gson gson = new Gson();
    private final LoadingCache<Integer, Resource> resourceCache;
    private final LoadingCache<Integer, Collection<Resource>> authorResourcesCache;
    private Collection<Category> cachedCategories = null;
    private final LoadingCache<Integer, ResourceUpdate> resourceUpdateCache;

    public ResourceManager(long duration, TimeUnit unit) {
        this.resourceCache = CacheBuilder.newBuilder()
                .expireAfterWrite(duration, unit)
                .build(new CacheLoader<Integer, Resource>() {
                    @Override
                    public Resource load(Integer resourceId) throws Exception {
                        Resource resource = gson.fromJson(HttpRequester.requestString(String.format(GET_RESOURCE_URL, resourceId)), Resource.class);
                        if (resource == null) {
                            throw new Exception();
                        }
                        return resource;
                    }
                });
        this.authorResourcesCache = CacheBuilder.newBuilder()
                .expireAfterWrite(duration, unit)
                .build(new CacheLoader<Integer, Collection<Resource>>() {
                    @Override
                    public Collection<Resource> load(Integer authorId) {
                        Type type = new TypeToken<Collection<Resource>>() {
                        }.getType();
                        Collection<Resource> resources = gson.fromJson(HttpRequester.requestString(String.format(GET_RESOURCES_BY_AUTHOR_URL, authorId)), type);
                        if (resources == null) {
                            return Collections.emptyList();
                        }
                        resources.forEach(resource -> resourceCache.put(resource.getId(), resource));
                        return Collections.unmodifiableCollection(resources);
                    }
                });
        this.resourceUpdateCache = CacheBuilder.newBuilder()
                .expireAfterWrite(duration, unit)
                .build(new CacheLoader<Integer, ResourceUpdate>() {
                    @Override
                    public ResourceUpdate load(Integer updateId) throws Exception {
                        ResourceUpdate update = gson.fromJson(HttpRequester.requestString(String.format(GET_RESOURCE_UPDATE_URL, updateId)), ResourceUpdate.class);
                        if (update == null) {
                            throw new Exception();
                        }
                        return update;
                    }
                });
    }

    public CompletableFuture<Resource> getResource(int resourceId) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return resourceCache.get(resourceId);
            } catch (ExecutionException e) {
                return null;
            }
        });
    }

    public CompletableFuture<Collection<Resource>> getResourcesByAuthor(int authorId) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return authorResourcesCache.get(authorId);
            } catch (ExecutionException e) {
                return Collections.emptyList();
            }
        });
    }

    public CompletableFuture<Collection<Resource>> listResources(int category, int page) {
        return CompletableFuture.supplyAsync(() -> {
            Type type = new TypeToken<Collection<Resource>>() {
            }.getType();
            String url = category == -1
                    ? String.format(LIST_RESOURCES_URL, page)
                    : String.format(LIST_RESOURCES_CAT_URL, page, category);
            Collection<Resource> resources = gson.fromJson(HttpRequester.requestString(url), type);
            if (resources == null) {
                return Collections.emptyList();
            }
            resources.forEach(resource -> resourceCache.put(resource.getId(), resource));
            return Collections.unmodifiableCollection(resources);
        });
    }

    public CompletableFuture<Collection<Category>> listResourceCategories() {
        return CompletableFuture.supplyAsync(() -> {
            if (cachedCategories != null) {
                return cachedCategories;
            }
            Type type = new TypeToken<Collection<Category>>() {
            }.getType();
            Collection<Category> categories = gson.fromJson(HttpRequester.requestString(LIST_CATEGORIES_URL), type);
            if (categories == null) {
                return Collections.emptyList();
            }
            return cachedCategories = categories;
        });
    }

    public CompletableFuture<ResourceUpdate> getResourceUpdate(int id) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return resourceUpdateCache.get(id);
            } catch (ExecutionException e) {
                return null;
            }
        });
    }

    public CompletableFuture<Collection<ResourceUpdate>> getResourceUpdates(int id, int page) {
        return CompletableFuture.supplyAsync(() -> {
            Type type = new TypeToken<Collection<ResourceUpdate>>() {
            }.getType();
            Collection<ResourceUpdate> resources = gson.fromJson(HttpRequester.requestString(String.format(GET_RESOURCE_UPDATES_URL, id, page)), type);
            if (resources == null) {
                return Collections.emptyList();
            }
            resources.forEach(update -> resourceUpdateCache.put(update.getId(), update));
            return Collections.unmodifiableCollection(resources);
        });
    }
}
