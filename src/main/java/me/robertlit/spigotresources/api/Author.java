package me.robertlit.spigotresources.api;

import com.google.gson.annotations.JsonAdapter;
import me.robertlit.spigotresources.internal.AuthorJsonAdapter;
import me.robertlit.spigotresources.internal.HttpRequester;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.image.BufferedImage;
import java.util.Objects;

/**
 * Represents a resource author
 */
@JsonAdapter(AuthorJsonAdapter.class)
public class Author {
    private final int id;
    private final String username;
    private final int resourceCount;
    private final Identities identities;
    private final BufferedImage avatar;

    private static final String AVATAR_URL = "https://www.spigotmc.org/data/avatars/l/%d/%d.jpg";
    private static final String DEFAULT_AVATAR_URL = "https://static.spigotmc.org/styles/spigot/xenforo/avatars/avatar_male_l.png";
    private static final BufferedImage DEFAULT_AVATAR = HttpRequester.requestImage(DEFAULT_AVATAR_URL);

    /**
     * Constructs an Author with the given parameters
     * <p>
     * This should only be used internally
     * </p>
     * @param id user id
     * @param username username
     * @param resourceCount amount of resources
     * @param identities social media identities
     */
    public Author(int id, @NotNull String username, int resourceCount, @NotNull Identities identities) {
        this.id = id;
        this.username = username;
        this.resourceCount = resourceCount;
        this.identities = identities;
        BufferedImage image = HttpRequester.requestImage(String.format(AVATAR_URL, id / 1000, id));
        if (image == null) {
            image = DEFAULT_AVATAR;
        }
        this.avatar = image;
    }

    /**
     * Gets this author's id
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Gets this author's username
     * @return username
     */
    @NotNull
    public String getUsername() {
        return username;
    }

    /**
     * Gets the amount of resources this author has published
     * @return amount of resources this author has published
     */
    public int getResourceCount() {
        return resourceCount;
    }

    /**
     * Gets this author's social media identities
     * @return social media identities
     */
    @NotNull
    public Identities getIdentities() {
        return identities;
    }

    /**
     * Gets this author's avatar
     * @return author's avatar
     */
    @NotNull
    public BufferedImage getAvatar() {
        return avatar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return id == author.id &&
                resourceCount == author.resourceCount &&
                username.equals(author.username) &&
                identities.equals(author.identities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, resourceCount, identities);
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", resourceCount=" + resourceCount +
                ", identities=" + identities +
                '}';
    }

    /**
     * Represents an Author's social media identities
     */
    public static class Identities {
        private final String discord, youtube, aim, icq, msn, yahoo, skype, gtalk, facebook, twitter;

        /**
         * Constructs an Identities object with the given parameters
         * <p>
         * This should only be used internally
         * </p>
         * @param discord discord identity
         * @param youtube youtube identity
         * @param aim aim identity
         * @param icq icq identity
         * @param msn msn identity
         * @param yahoo yahoo identity
         * @param skype skype identity
         * @param gtalk google talk identity
         * @param facebook facebook identity
         * @param twitter twitter identity
         */
        public Identities(@Nullable String discord, @Nullable String youtube, @Nullable String aim, @Nullable String icq, @Nullable String msn, @Nullable String yahoo, @Nullable String skype, @Nullable String gtalk, @Nullable String facebook, @Nullable String twitter) {
            this.discord = discord;
            this.youtube = youtube;
            this.aim = aim;
            this.icq = icq;
            this.msn = msn;
            this.yahoo = yahoo;
            this.skype = skype;
            this.gtalk = gtalk;
            this.facebook = facebook;
            this.twitter = twitter;
        }

        /**
         * @return discord identity
         */
        @Nullable
        public String getDiscord() {
            return discord;
        }

        /**
         * @return youtube identity
         */
        @Nullable
        public String getYoutube() {
            return youtube;
        }

        /**
         * @return AIM identity
         */
        @Nullable
        public String getAim() {
            return aim;
        }

        /**
         * @return ICQ identity
         */
        @Nullable
        public String getIcq() {
            return icq;
        }

        /**
         * @return MSN identity
         */
        @Nullable
        public String getMsn() {
            return msn;
        }

        /**
         * @return yahoo identity
         */
        @Nullable
        public String getYahoo() {
            return yahoo;
        }

        /**
         * @return skype identity
         */
        @Nullable
        public String getSkype() {
            return skype;
        }

        /**
         * @return google talk identity
         */
        @Nullable
        public String getGoogleTalk() {
            return gtalk;
        }

        /**
         * @return facebook identity
         */
        @Nullable
        public String getFacebook() {
            return facebook;
        }

        /**
         * @return twitter identity
         */
        @Nullable
        public String getTwitter() {
            return twitter;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Identities that = (Identities) o;
            return Objects.equals(discord, that.discord) &&
                    Objects.equals(youtube, that.youtube) &&
                    Objects.equals(aim, that.aim) &&
                    Objects.equals(icq, that.icq) &&
                    Objects.equals(msn, that.msn) &&
                    Objects.equals(yahoo, that.yahoo) &&
                    Objects.equals(skype, that.skype) &&
                    Objects.equals(gtalk, that.gtalk) &&
                    Objects.equals(facebook, that.facebook) &&
                    Objects.equals(twitter, that.twitter);
        }

        @Override
        public int hashCode() {
            return Objects.hash(discord, youtube, aim, icq, msn, yahoo, skype, gtalk, facebook, twitter);
        }

        @Override
        public String toString() {
            return "Identities{" +
                    "discord='" + discord + '\'' +
                    ", youtube='" + youtube + '\'' +
                    ", aim='" + aim + '\'' +
                    ", icq='" + icq + '\'' +
                    ", msn='" + msn + '\'' +
                    ", yahoo='" + yahoo + '\'' +
                    ", skype='" + skype + '\'' +
                    ", gtalk='" + gtalk + '\'' +
                    ", facebook='" + facebook + '\'' +
                    ", twitter='" + twitter + '\'' +
                    '}';
        }
    }
}
