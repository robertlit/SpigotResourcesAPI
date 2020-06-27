package me.robertlit.spigotresources.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SpigotResourcesAPITest {

    @Test
    void testAuthor() {
        SpigotResourcesAPI api = new SpigotResourcesAPI(false);
        CompletableFuture<Author> future = api.getAuthor(740512);
        while (!future.isDone()) {}
        Author author = future.getNow(null);
        System.out.println(author);
        Gson gson = new Gson();
        String json = gson.toJson(author);
        System.out.println(json);
        Author author1 = gson.fromJson(json, Author.class);
        System.out.println(author1);
        assertEquals(author, author1);
    }

    @Test
    void testResource() {
        SpigotResourcesAPI api = new SpigotResourcesAPI(false);
        CompletableFuture<Resource> future = api.getResource(72343);
        while (!future.isDone()) {}
        Resource resource = future.getNow(null);
        System.out.println(resource);
        Gson gson = new Gson();
        String json = gson.toJson(resource);
        System.out.println(json);
        Resource resource1 = gson.fromJson(json, Resource.class);
        System.out.println(resource1);
        assertEquals(resource, resource1);
    }

    @Test
    void testPremiumResource() {
        SpigotResourcesAPI api = new SpigotResourcesAPI(false);
        CompletableFuture<Resource> future = api.getResource(78200);
        while (!future.isDone()) {}
        Resource resource = future.getNow(null);
        System.out.println(resource);
        Gson gson = new Gson();
        String json = gson.toJson(resource);
        System.out.println(json);
        Resource resource1 = gson.fromJson(json, Resource.class);
        System.out.println(resource1);
        assertEquals(resource, resource1);
    }

    @Test
    void testResourcesFromAuthor() {
        SpigotResourcesAPI api = new SpigotResourcesAPI(false);
        CompletableFuture<Collection<Resource>> future = api.getResourcesByAuthor(740512);
        while (!future.isDone()) {}
        Collection<Resource> resources = future.getNow(null);
        System.out.println(resources);
        Gson gson = new Gson();
        String json = gson.toJson(resources);
        System.out.println(json);
        Collection<Resource> resources1 = gson.fromJson(json, new TypeToken<Collection<Resource>>(){}.getType());
        System.out.println(resources1);
        resources1.removeAll(resources);
        assertEquals(0, resources1.size());
    }

    @Test
    void testResourcesFromAuthorWithPremium() {
        SpigotResourcesAPI api = new SpigotResourcesAPI(false);
        CompletableFuture<Collection<Resource>> future = api.getResourcesByAuthor(467707);
        while (!future.isDone()) {}
        Collection<Resource> resources = future.getNow(null);
        System.out.println(resources);
        Gson gson = new Gson();
        String json = gson.toJson(resources);
        System.out.println(json);
        Collection<Resource> resources1 = gson.fromJson(json, new TypeToken<Collection<Resource>>(){}.getType());
        System.out.println(resources1);
        resources1.removeAll(resources);
        assertEquals(0, resources1.size());
    }

    @Test
    void testNullAuthor() {
        SpigotResourcesAPI api = new SpigotResourcesAPI(false);
        CompletableFuture<Author> future = api.getAuthor(123456789);
        while (!future.isDone()) {}
        Author author = future.getNow(null);
        System.out.println(author);
        Gson gson = new Gson();
        String json = gson.toJson(author);
        System.out.println(json);
        Author author1 = gson.fromJson(json, Author.class);
        System.out.println(author1);
        assertEquals(author, author1);
    }

    @Test
    void testNullResource() {
        SpigotResourcesAPI api = new SpigotResourcesAPI(false);
        CompletableFuture<Resource> future = api.getResource(123456789);
        while (!future.isDone()) {}
        Resource resource = future.getNow(null);
        System.out.println(resource);
        Gson gson = new Gson();
        String json = gson.toJson(resource);
        System.out.println(json);
        Resource resource1 = gson.fromJson(json, Resource.class);
        System.out.println(resource1);
        assertEquals(resource, resource1);
    }

    @Test
    void testNullResourcesArray() {
        SpigotResourcesAPI api = new SpigotResourcesAPI(false);
        CompletableFuture<Collection<Resource>> future = api.getResourcesByAuthor(123456789);
        while (!future.isDone()) {}
        Collection<Resource> resources = future.getNow(null);
        System.out.println(resources);
        Gson gson = new Gson();
        String json = gson.toJson(resources);
        System.out.println(json);
        Collection<Resource> resources1 = gson.fromJson(json, new TypeToken<Collection<Resource>>(){}.getType());
        System.out.println(resources1);
        assertEquals(resources, resources1);
    }
}