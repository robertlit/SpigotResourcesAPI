package me.robertlit.spigotresources.api;

import com.google.gson.annotations.JsonAdapter;
import me.robertlit.spigotresources.internal.ResourceJsonAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Represents a resource
 */
@JsonAdapter(ResourceJsonAdapter.class)
public class Resource {
    protected final int id;
    protected final String title;
    protected final String tag;
    protected final String version;
    protected final AuthorData authorData;
    protected final Stats stats;

    /**
     * Constructs a Resource with the given parameters
     * This should only be used internally
     * @param id resource id
     * @param title resource title
     * @param tag resource tag
     * @param version resource version
     * @param authorData data about the author of the resource
     * @param stats resource stats
     */
    public Resource(int id, @NotNull String title, @NotNull String tag, @NotNull String version, @NotNull AuthorData authorData, @NotNull Stats stats) {
        this.id = id;
        this.title = title;
        this.tag = tag;
        this.version = version;
        this.authorData = authorData;
        this.stats = stats;
    }

    /**
     * @return resource id
     */
    public int getId() {
        return id;
    }

    /**
     * @return resource title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return resource tag
     */
    public String getTag() {
        return tag;
    }

    /**
     * @return resource version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @return data about the author of the resource
     */
    public AuthorData getAuthorData() {
        return authorData;
    }

    /**
     * @return resource stats
     */
    public Stats getStats() {
        return stats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resource resource = (Resource) o;
        return id == resource.id &&
                title.equals(resource.title) &&
                tag.equals(resource.tag) &&
                version.equals(resource.version) &&
                authorData.equals(resource.authorData) &&
                stats.equals(resource.stats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, tag, version, authorData, stats);
    }

    @Override
    public String toString() {
        return "Resource{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", tag='" + tag + '\'' +
                ", version='" + version + '\'' +
                ", authorData=" + authorData +
                ", stats=" + stats +
                '}';
    }

    /**
     * Represents stats of a Resource
     */
    public static class Stats {
        private final int downloads, updates, reviews;
        private final double rating;

        /**
         * Constructs a Stats object with the given parameters
         * This should only be used internally
         * @param downloads amount of downloads
         * @param updates amount of updates
         * @param reviews amount of reviews
         * @param rating rating (1-5)
         */
        public Stats(int downloads, int updates, int reviews, double rating) {
            this.downloads = downloads;
            this.updates = updates;
            this.reviews = reviews;
            this.rating = rating;
        }

        /**
         * @return amount of downloads
         */
        public int getDownloads() {
            return downloads;
        }

        /**
         * @return amount of updates
         */
        public int getUpdates() {
            return updates;
        }

        /**
         * @return amount of reviews
         */
        public int getReviews() {
            return reviews;
        }

        /**
         * @return rating (1-5)
         */
        public double getRating() {
            return rating;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Stats stats = (Stats) o;
            return downloads == stats.downloads &&
                    updates == stats.updates &&
                    reviews == stats.reviews &&
                    Double.compare(stats.rating, rating) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(downloads, updates, reviews, rating);
        }

        @Override
        public String toString() {
            return "Stats{" +
                    "downloads=" + downloads +
                    ", updates=" + updates +
                    ", reviews=" + reviews +
                    ", rating=" + rating +
                    '}';
        }
    }

    /**
     * A class holding data about an Author of a Resource
     */
    public static class AuthorData {
        private final int id;
        private final String username;

        /**
         * Constructs an AuthorData object with the given parameters
         * This should only be used internally
         * @param id author id
         * @param username author username
         */
        public AuthorData(int id, @NotNull String username) {
            this.id = id;
            this.username = username;
        }

        /**
         * @return author id
         */
        public int getId() {
            return id;
        }

        /**
         * @return author username
         */
        @NotNull
        public String getUsername() {
            return username;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            AuthorData that = (AuthorData) o;
            return id == that.id &&
                    username.equals(that.username);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, username);
        }

        @Override
        public String toString() {
            return "AuthorData{" +
                    "id=" + id +
                    ", username='" + username + '\'' +
                    '}';
        }
    }
}
