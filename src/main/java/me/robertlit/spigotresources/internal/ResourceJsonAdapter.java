package me.robertlit.spigotresources.internal;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import me.robertlit.spigotresources.api.PremiumResource;
import me.robertlit.spigotresources.api.Resource;

import java.io.IOException;

public class ResourceJsonAdapter extends TypeAdapter<Resource> {

    @Override
    public void write(JsonWriter out, Resource resource) throws IOException {
        writeResource(out, resource);
    }

    private void writeResource(JsonWriter out, Resource resource) throws IOException {
        out.beginObject();
        out.name("id").value(resource.getId());
        out.name("title").value(resource.getTitle());
        out.name("tag").value(resource.getTag());
        out.name("current_version").value(resource.getVersion());
        out.name("author");
        writeResourceAuthorData(out, resource.getAuthorData());
        out.name("premium");
        writeResourcePremiumData(out, resource);
        out.name("stats");
        writeResourceStats(out, resource.getStats());
        out.endObject();
    }

    private void writeResourceAuthorData(JsonWriter out, Resource.AuthorData authorData) throws IOException {
        out.beginObject();
        out.name("id").value(authorData.getId());
        out.name("username").value(authorData.getUsername());
        out.endObject();
    }

    private void writeResourcePremiumData(JsonWriter out, Resource resource) throws IOException {
        out.beginObject();
        if (resource instanceof PremiumResource) {
            PremiumResource premium = (PremiumResource) resource;
            out.name("price").value(premium.getPremiumData().getPrice());
            out.name("currency").value(premium.getPremiumData().getCurrency());
        } else {
            out.name("price").value(0.00);
            out.name("currency").value("");
        }
        out.endObject();
    }

    private void writeResourceStats(JsonWriter out, Resource.Stats stats) throws IOException {
        out.beginObject();
        out.name("downloads").value(stats.getDownloads());
        out.name("updates").value(stats.getUpdates());
        out.name("reviews").value(stats.getReviews());
        out.name("rating").value(stats.getRating());
        out.endObject();
    }

    @Override
    public Resource read(JsonReader in) throws IOException {
        return readResource(in);
    }

    private Resource readResource(JsonReader in) throws IOException {
        int id = 0;
        String title = null, tag = null, version = null;
        Resource.Stats stats = null;
        Resource.AuthorData authorData = null;
        PremiumResource.PremiumData premiumData = null;

        in.beginObject();
        while (in.hasNext()) {
            String name = in.nextName();
            switch (name) {
                case "id":
                    id = in.nextInt();
                    break;
                case "title":
                    title = in.nextString();
                    break;
                case "tag":
                    tag = in.nextString();
                    break;
                case "current_version":
                    version = in.nextString();
                    break;
                case "author":
                    authorData = readAuthorData(in);
                    break;
                case "premium":
                    premiumData = readPremiumData(in);
                    break;
                case "stats":
                    stats = readStats(in);
                    break;
                default:
                    in.skipValue();
                    break;
            }
        }
        in.endObject();

        if (premiumData == null) {
            return new Resource(id, title, tag, version, authorData, stats);
        }
        return new PremiumResource(id, title, tag, version, authorData, stats, premiumData);
    }

    private Resource.AuthorData readAuthorData(JsonReader in) throws IOException {
        int id = 0;
        String username = null;

        in.beginObject();
        while (in.hasNext()) {
            String name = in.nextName();
            if (name.equals("id")) {
                id = in.nextInt();
            } else if (name.equals("username")) {
                username = in.nextString();
            } else {
                in.skipValue();
            }
        }
        in.endObject();

        return new Resource.AuthorData(id, username);
    }

    private PremiumResource.PremiumData readPremiumData(JsonReader in) throws IOException {
        double price = 0;
        String currency = null;

        in.beginObject();
        while (in.hasNext()) {
            String name = in.nextName();
            if (name.equals("price")) {
                price = in.nextDouble();
            } else if (name.equals("currency")) {
                currency = in.nextString();
            } else {
                in.skipValue();
            }
        }
        in.endObject();

        if (price == 0) {
            return null;
        }
        return new PremiumResource.PremiumData(price, currency);
    }

    private Resource.Stats readStats(JsonReader in) throws IOException {
        int downloads = 0, updates = 0, reviews = 0;
        double rating = 0D;

        in.beginObject();
        while (in.hasNext()) {
            String name = in.nextName();
            switch (name) {
                case "downloads":
                    downloads = in.nextInt();
                    break;
                case "updates":
                    updates = in.nextInt();
                    break;
                case "reviews":
                    reviews = in.nextInt();
                    break;
                case "rating":
                    rating = in.nextDouble();
                    break;
                default:
                    in.skipValue();
                    break;
            }
        }
        in.endObject();

        return new Resource.Stats(downloads, updates, reviews, rating);
    }


}
