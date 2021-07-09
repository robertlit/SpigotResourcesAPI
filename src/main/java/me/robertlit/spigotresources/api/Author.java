package me.robertlit.spigotresources.api;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

/**
 * Represents a resource author
 */
public class Author {

    private final int id;
    private final String username;
    @SerializedName("resource_count")
    private final int resourceCount;
    private final Identities identities;
    @SerializedName("avatar")
    private final String avatarLink;

    /**
     * Constructs an Author with the given parameters
     * <p>
     * This should only be used internally
     * </p>
     *
     * @param id            user id
     * @param username      username
     * @param resourceCount amount of resources
     * @param identities    social media identities
     * @param avatarLink    link to avatar
     */
    private Author(int id, String username, int resourceCount, Identities identities, String avatarLink) {
        this.id = id;
        this.username = username;
        this.resourceCount = resourceCount;
        this.identities = identities;
        this.avatarLink = avatarLink;
    }

    /**
     * Gets this author's id
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Gets this author's username
     *
     * @return username
     */

    public String getUsername() {
        return username;
    }

    /**
     * Gets the amount of resources this author has published
     *
     * @return amount of resources this author has published
     */
    public int getResourceCount() {
        return resourceCount;
    }

    /**
     * Gets this author's social media identities
     *
     * @return social media identities
     */

    public Identities getIdentities() {
        return identities;
    }

    public String getAvatarLink() {
        return avatarLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return id == author.id && resourceCount == author.resourceCount && username.equals(author.username) && identities.equals(author.identities) && avatarLink.equals(author.avatarLink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, resourceCount, identities, avatarLink);
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", resourceCount=" + resourceCount +
                ", identities=" + identities +
                ", avatarLink='" + avatarLink + '\'' +
                '}';
    }

    /**
     * Represents an Author's social media identities
     */
    public static class Identities {

        private final String discord, youtube, aim, icq, msn, yahoo, skype, gtalk, facebook, twitter, github;

        /**
         * Constructs an Identities object with the given parameters
         * <p>
         * This should only be used internally
         * </p>
         *
         * @param discord  discord identity
         * @param youtube  youtube identity
         * @param aim      aim identity
         * @param icq      icq identity
         * @param msn      msn identity
         * @param yahoo    yahoo identity
         * @param skype    skype identity
         * @param gtalk    google talk identity
         * @param facebook facebook identity
         * @param twitter  twitter identity
         * @param github   github identity
         */
        public Identities(String discord, String youtube, String aim, String icq, String msn, String yahoo, String skype, String gtalk, String facebook, String twitter, String github) {
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
            this.github = github;
        }

        /**
         * @return discord identity
         */

        public String getDiscord() {
            return discord;
        }

        /**
         * @return youtube identity
         */

        public String getYoutube() {
            return youtube;
        }

        /**
         * @return AIM identity
         */

        public String getAim() {
            return aim;
        }

        /**
         * @return ICQ identity
         */

        public String getIcq() {
            return icq;
        }

        /**
         * @return MSN identity
         */

        public String getMsn() {
            return msn;
        }

        /**
         * @return yahoo identity
         */

        public String getYahoo() {
            return yahoo;
        }

        /**
         * @return skype identity
         */

        public String getSkype() {
            return skype;
        }

        /**
         * @return google talk identity
         */

        public String getGoogleTalk() {
            return gtalk;
        }

        /**
         * @return facebook identity
         */

        public String getFacebook() {
            return facebook;
        }

        /**
         * @return twitter identity
         */

        public String getTwitter() {
            return twitter;
        }

        /**
         * @return github identity
         */

        public String getGithub() {
            return github;
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
