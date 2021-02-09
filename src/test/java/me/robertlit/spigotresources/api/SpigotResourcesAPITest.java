package me.robertlit.spigotresources.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SpigotResourcesAPITest {

    SpigotResourcesAPI api = new SpigotResourcesAPI(1, TimeUnit.HOURS);

    @Test
    void testAuthor() {
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
        CompletableFuture<Collection<Resource>> future = api.getResourcesByAuthor(123456789);
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
    void testPrintResouceImage() throws IOException {
        CompletableFuture<Resource> future = api.getResource(71792);
        while (!future.isDone()) {}
        Resource resource = future.getNow(null);
        File file = new File("\\tests\\image.jpg");
        System.out.println(file.getAbsolutePath());
        file.getParentFile().mkdir();
        file.createNewFile();
        ImageIO.write(resource.getImage(), "jpg", file);
    }

    @Test
    void testPrintResouceMissingImage() throws IOException {
        CompletableFuture<Resource> future = api.getResource(72343);
        while (!future.isDone()) {}
        Resource resource = future.getNow(null);
        File file = new File("\\tests\\missing.png");
        System.out.println(file.getAbsolutePath());
        file.getParentFile().mkdir();
        file.createNewFile();
        ImageIO.write(resource.getImage(), "png", file);
    }

    @Test
    void testAuthorMissingAvatar() throws IOException {
        CompletableFuture<Author> future = api.getAuthor(740512);
        while (!future.isDone()) {}
        Author author = future.getNow(null);
        File file = new File("\\tests\\missing_avatar.png");
        System.out.println(file.getAbsolutePath());
        file.getParentFile().mkdir();
        file.createNewFile();
        ImageIO.write(author.getAvatar(), "png", file);
    }

    @Test
    void testAuthorAvatar() throws IOException {
        CompletableFuture<Author> future = api.getAuthor(41621);
        while (!future.isDone()) {}
        Author author = future.getNow(null);
        File file = new File("\\tests\\avatar.png");
        System.out.println(file.getAbsolutePath());
        file.getParentFile().mkdir();
        file.createNewFile();
        ImageIO.write(author.getAvatar(), "png", file);
    }

    @Test
    void testNoIdentities() {
        CompletableFuture<Author> future = api.getAuthor(154670);
        while (!future.isDone()) {}
        Author author = future.getNow(null);
        System.out.println(author);
    }
}