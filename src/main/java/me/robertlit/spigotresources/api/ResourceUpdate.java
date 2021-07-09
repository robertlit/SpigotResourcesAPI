package me.robertlit.spigotresources.api;

import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * Represents a resource update
 */
public class ResourceUpdate {

    private final int id;
    @SerializedName("resource_id")
    private final int resourceId;
    private final String title;
    private final String message;

    private ResourceUpdate(int id, int resourceId, String title, String message) {
        this.id = id;
        this.resourceId = resourceId;
        this.title = title;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public int getResourceId() {
        return resourceId;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals( Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResourceUpdate that = (ResourceUpdate) o;
        return id == that.id && resourceId == that.resourceId && title.equals(that.title) && message.equals(that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, resourceId, title, message);
    }

    @Override
    public  String toString() {
        return "ResourceUpdate{" +
                "id=" + id +
                ", resourceId=" + resourceId +
                ", title='" + title + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
