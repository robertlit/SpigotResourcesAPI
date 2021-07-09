package me.robertlit.spigotresources.api;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.Objects;

/**
 * Represents a resource
 */
public class Resource {

    private final int id;
    private final String title;
    private final String tag;
    @SerializedName("current_version")
    private final String version;
    @SerializedName("native_minecraft_version")
    private final String nativeMinecraftVersion;
    @SerializedName("supported_minecraft_versions")
    private final String[] supportedMinecraftVersions;
    @SerializedName("author")
    private final AuthorData authorData;
    private final Stats stats;
    @SerializedName("premium")
    private final PremiumData premiumData;
    @SerializedName("icon_link")
    private final String iconLink;
    private final String description;

    private Resource(int id,
                     String title,
                     String tag,
                     String version,
                     String nativeMinecraftVersion,
                     String[] supportedMinecraftVersions,
                     AuthorData authorData,
                     Stats stats,
                     PremiumData premiumData,
                     String iconLink,
                     String description) {
        this.id = id;
        this.title = title;
        this.tag = tag;
        this.version = version;
        this.nativeMinecraftVersion = nativeMinecraftVersion;
        this.supportedMinecraftVersions = supportedMinecraftVersions;
        this.authorData = authorData;
        this.stats = stats;
        this.premiumData = premiumData;
        this.iconLink = iconLink;
        this.description = description;
    }

    public int getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }


    public String getTag() {
        return tag;
    }


    public String getVersion() {
        return version;
    }

    public String getNativeMinecraftVersion() {
        return nativeMinecraftVersion;
    }

    public String[] getSupportedMinecraftVersions() {
        return supportedMinecraftVersions;
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

    public PremiumData getPremiumData() {
        return premiumData;
    }

    public String getIconLink() {
        return iconLink;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resource resource = (Resource) o;
        return id == resource.id
                && title.equals(resource.title)
                && tag.equals(resource.tag)
                && version.equals(resource.version)
                && Objects.equals(nativeMinecraftVersion, resource.nativeMinecraftVersion)
                && Arrays.equals(supportedMinecraftVersions, resource.supportedMinecraftVersions)
                && authorData.equals(resource.authorData)
                && stats.equals(resource.stats)
                && premiumData.equals(resource.premiumData)
                && iconLink.equals(resource.iconLink)
                && description.equals(resource.description);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, title, tag, version, nativeMinecraftVersion, authorData, stats, premiumData, iconLink, description);
        result = 31 * result + Arrays.hashCode(supportedMinecraftVersions);
        return result;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", tag='" + tag + '\'' +
                ", version='" + version + '\'' +
                ", nativeMinecraftVersion='" + nativeMinecraftVersion + '\'' +
                ", supportedMinecraftVersions=" + Arrays.toString(supportedMinecraftVersions) +
                ", authorData=" + authorData +
                ", stats=" + stats +
                ", premiumData=" + premiumData +
                ", iconLink='" + iconLink + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    /**
     * Represents stats of a Resource
     */
    public static class Stats {
        private final int downloads, updates;
        private final Reviews reviews;
        private final double rating;

        /**
         * Constructs a Stats object with the given parameters
         * <p>
         * This should only be used internally
         * </p>
         *
         * @param downloads amount of downloads
         * @param updates   amount of updates
         * @param reviews   reviews
         * @param rating    rating (1-5)
         */
        public Stats(int downloads, int updates, Reviews reviews, double rating) {
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

        public int getUpdates() {
            return updates;
        }

        public Reviews getReviews() {
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
            return downloads == stats.downloads && updates == stats.updates && Double.compare(stats.rating, rating) == 0 && reviews.equals(stats.reviews);
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

        public static class Reviews {

            private final int unique;
            private final int total;

            public Reviews(int unique, int total) {
                this.unique = unique;
                this.total = total;
            }

            public int getUnique() {
                return unique;
            }

            public int getTotal() {
                return total;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Reviews reviews = (Reviews) o;
                return unique == reviews.unique && total == reviews.total;
            }

            @Override
            public int hashCode() {
                return Objects.hash(unique, total);
            }

            @Override
            public String toString() {
                return "Reviews{" +
                        "unique=" + unique +
                        ", total=" + total +
                        '}';
            }
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
         * <p>
         * This should only be used internally
         * </p>
         *
         * @param id       author id
         * @param username author username
         */
        public AuthorData(int id, String username) {
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

    /**
     * A class holding data about a PremiumResource
     */
    public static class PremiumData {
        private final double price;
        private final String currency;

        /**
         * Constructs a PremiumResource with the given parameters
         * <p>
         * This should only be used internally
         * </p>
         *
         * @param price    resource price
         * @param currency currency of the price
         */
        public PremiumData(double price, String currency) {
            this.price = price;
            this.currency = currency;
        }

        /**
         * @return the price of a PremiumResource
         */
        public double getPrice() {
            return price;
        }

        /**
         * @return the currency of the price
         */

        public String getCurrency() {
            return currency;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PremiumData that = (PremiumData) o;
            return Double.compare(that.price, price) == 0 &&
                    currency.equals(that.currency);
        }

        @Override
        public int hashCode() {
            return Objects.hash(price, currency);
        }

        @Override
        public String toString() {
            return "PremiumData{" +
                    "price=" + price +
                    ", currency='" + currency + '\'' +
                    '}';
        }
    }
}
