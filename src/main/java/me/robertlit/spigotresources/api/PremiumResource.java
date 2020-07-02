package me.robertlit.spigotresources.api;

import com.google.gson.annotations.JsonAdapter;
import me.robertlit.spigotresources.internal.ResourceJsonAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Represents a premium (paid) resource
 */
@JsonAdapter(ResourceJsonAdapter.class)
public class PremiumResource extends Resource {
    private final PremiumData premiumData;

    /**
     * Constructs a PremiumResource with the given parameters
     * <p>
     * This should only be used internally
     * </p>
     * @param id resource id
     * @param title resource title
     * @param tag resource tag
     * @param version resource version
     * @param authorData data about the author of the resource
     * @param stats resource stats
     * @param premiumData data about premium resource
     */
    public PremiumResource(int id, @NotNull String title, @NotNull String tag, @NotNull String version, @NotNull AuthorData authorData, @NotNull Stats stats, @NotNull PremiumData premiumData) {
        super(id, title, tag, version, authorData, stats);
        this.premiumData = premiumData;
    }

    /**
     * Gets data about this premium resource
     * @return data about this premium resource
     */
    @NotNull
    public PremiumData getPremiumData() {
        return premiumData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PremiumResource that = (PremiumResource) o;
        return premiumData.equals(that.premiumData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), premiumData);
    }

    @Override
    public String toString() {
        return "PremiumResource{" +
                "premiumData=" + premiumData +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", tag='" + tag + '\'' +
                ", version='" + version + '\'' +
                ", authorData=" + authorData +
                ", stats=" + stats +
                '}';
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
         * @param price resource price
         * @param currency currency of the price
         */
        public PremiumData(double price, @NotNull String currency) {
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
        @NotNull
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
