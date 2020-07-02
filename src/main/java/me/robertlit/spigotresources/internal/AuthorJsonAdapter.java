package me.robertlit.spigotresources.internal;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import me.robertlit.spigotresources.api.Author;

import java.io.IOException;

public class AuthorJsonAdapter extends TypeAdapter<Author> {

    @Override
    public void write(JsonWriter out, Author author) throws IOException {
        writeAuthor(out, author);
    }

    private void writeAuthor(JsonWriter out, Author author) throws IOException {
        out.beginObject();
        out.name("id").value(author.getId());
        out.name("username").value(author.getUsername());
        out.name("resource_count").value(author.getResourceCount());
        out.name("identities");
        writeIdentities(out, author.getIdentities());
        out.endObject();
    }

    private void writeIdentities(JsonWriter out, Author.Identities identities) throws IOException {
        out.beginObject();
        if (identities.getDiscord() != null) {
            out.name("discord").value(identities.getDiscord());
        }
        if (identities.getYoutube() != null) {
            out.name("youtube").value(identities.getYoutube());
        }
        if (identities.getAim() != null) {
            out.name("aim").value(identities.getAim());
        }
        if (identities.getIcq() != null) {
            out.name("icq").value(identities.getIcq());
        }
        if (identities.getMsn() != null) {
            out.name("msn").value(identities.getMsn());
        }
        if (identities.getYahoo() != null) {
            out.name("yahoo").value(identities.getYahoo());
        }
        if (identities.getGoogleTalk() != null) {
            out.name("gtalk").value(identities.getGoogleTalk());
        }
        if (identities.getSkype() != null) {
            out.name("skype").value(identities.getSkype());
        }
        if (identities.getFacebook() != null) {
            out.name("facebook").value(identities.getFacebook());
        }
        if (identities.getTwitter() != null) {
            out.name("twitter").value(identities.getTwitter());
        }
        out.endObject();
    }

    @Override
    public Author read(JsonReader in) throws IOException {
        return readAuthor(in);
    }

    private Author readAuthor(JsonReader in) throws IOException {
        int id = 0;
        String username = null;
        int resourceCount = 0;
        Author.Identities identities = null;

        in.beginObject();
        while (in.hasNext()) {
            String name = in.nextName();
            switch (name) {
                case "id":
                    id = in.nextInt();
                    break;
                case "username":
                    username = in.nextString();
                    break;
                case "resource_count":
                    resourceCount = in.nextInt();
                    break;
                case "identities":
                    identities = readIdentities(in);
                    break;
                default:
                    in.skipValue();
                    break;
            }
        }
        in.endObject();

        return new Author(id, username, resourceCount, identities);
    }

    private Author.Identities readIdentities(JsonReader in) throws IOException {
        String discord = null, youtube = null, aim = null, icq = null, msn = null, yahoo = null, skype = null, gtalk = null, facebook = null, twitter = null;

        try {
            in.beginObject();
            while (in.hasNext()) {
                String name = in.nextName();
                switch (name) {
                    case "discord":
                        discord = in.nextString();
                        break;
                    case "youtube":
                        youtube = in.nextString();
                        break;
                    case "aim":
                        aim = in.nextString();
                        break;
                    case "icq":
                        icq = in.nextString();
                        break;
                    case "msn":
                        msn = in.nextString();
                        break;
                    case "yahoo":
                        yahoo = in.nextString();
                        break;
                    case "skype":
                        skype = in.nextString();
                        break;
                    case "gtalk":
                        gtalk = in.nextString();
                        break;
                    case "facebook":
                        facebook = in.nextString();
                        break;
                    case "twitter":
                        twitter = in.nextString();
                        break;
                    default:
                        in.skipValue();
                        break;
                }
            }
            in.endObject();
        } catch (IllegalStateException ignored) {
            in.skipValue(); // try-catch would not be needed if REST returned empty object instead of array
        }

        return new Author.Identities(discord, youtube, aim, icq, msn, yahoo, skype, gtalk, facebook, twitter);
    }
}
