package me.robertlit.spigotresources.internal;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import me.robertlit.spigotresources.api.Resource;

import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.CompletableFuture;

public class ResourceManager {

    private static final String GET_RESOURCE_URL = "https://api.spigotmc.org/simple/0.1/index.php?action=getResource&id=";
    public static final String GET_RESOURCES_BY_AUTHOR_URL = "https://api.spigotmc.org/simple/0.1/index.php?action=getResourcesByAuthor&id=";

    private final Map<Integer, Resource> idToResourceMap = Collections.synchronizedMap(new HashMap<>());
    private final Map<Integer, Collection<Resource>> authorToResourcesMap = Collections.synchronizedMap(new HashMap<>());

    private final Gson gson = new Gson();

    public CompletableFuture<Resource> getResource(int resourceId, boolean fetch) {
        if (fetch || idToResourceMap.get(resourceId) == null) {
            return CompletableFuture.supplyAsync(() -> {
                JsonElement jsonElement = HttpRequester.request(GET_RESOURCE_URL+resourceId);
                if (jsonElement == null || !jsonElement.isJsonObject()) {
                    return null;
                }
                Resource resource = gson.fromJson(jsonElement, Resource.class);
                idToResourceMap.put(resourceId, resource);
                return resource;
            });
        }
        CompletableFuture<Resource> future = new CompletableFuture<>();
        future.complete(idToResourceMap.get(resourceId));
        return future;
    }

    public CompletableFuture<Collection<Resource>> getResourcesByAuthor(int authorId, boolean fetch) {
        if (fetch || authorToResourcesMap.get(authorId) == null) {
            return CompletableFuture.supplyAsync(() -> {
                JsonElement jsonElement = HttpRequester.request(GET_RESOURCES_BY_AUTHOR_URL+authorId);
                if (jsonElement == null || !jsonElement.isJsonArray() || jsonElement.getAsJsonArray().size() == 0) {
                    return Collections.emptyList();
                }
                Type type = new TypeToken<Collection<Resource>>(){}.getType();
                Collection<Resource> resources = Collections.unmodifiableCollection(gson.fromJson(jsonElement, type));
                authorToResourcesMap.put(authorId, resources);
                return resources;
            });
        }
        CompletableFuture<Collection<Resource>> future = new CompletableFuture<>();
        future.complete(new ArrayList<>(authorToResourcesMap.get(authorId)));
        return future;
    }
}
