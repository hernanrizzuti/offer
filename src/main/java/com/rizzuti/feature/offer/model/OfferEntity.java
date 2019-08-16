package com.rizzuti.feature.offer.model;

import com.rizzuti.feature.offer.common.OfferStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class OfferEntity {

    @Id
    @GeneratedValue
    private Integer id;

    private BigDecimal price;

    private String currency;

    private String description;

    @Enumerated(EnumType.STRING)
    private OfferStatus status;

    private String createdBy;

    private String createdDate;

    private String expiryDate;

    public OfferEntity() {
    }

    public OfferEntity(final Builder builder) {
        this.price = builder.price;
        this.currency = builder.currency;
        this.description = builder.description;
        this.status = builder.status;
        this.createdBy = builder.createdBy;
        this.createdDate = builder.createdDate;
        this.expiryDate = builder.expiryDate;
    }

    public Integer getId() { return id; }

    public BigDecimal getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }

    public String getDescription() {
        return description;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public OfferStatus getStatus() {
        return status;
    }

    public static Builder newBuilder() { return new Builder();}

    public static Builder copyBuilder(OfferEntity copy) { return new Builder(copy); }

    public static final class Builder {
        private Integer id;

        private BigDecimal price;

        private String currency;

        private String description;

        private OfferStatus status;

        private String createdBy;

        private String createdDate;

        private String expiryDate;

        public Builder() {
        }

        public Builder(OfferEntity copy) {
            this.id = copy.id;
            this.price = copy.price;
            this.currency = copy.currency;
            this.description = copy.description;
            this.status = copy.status;
            this.createdBy = copy.createdBy;
            this.createdDate = copy.createdDate;
            this.expiryDate = copy.expiryDate;
        }

        public Builder withPrice(final BigDecimal price) {
            this.price = price;
            return this;
        }

        public Builder withCurrency(final String currency) {
            this.currency = currency;
            return this;
        }


        public Builder withDescription(final String description) {
            this.description = description;
            return this;
        }

        public Builder withStatus(final OfferStatus status) {
            this.status = status;
            return this;
        }


        public Builder withCreatedBy(final String createdBy) {
            this.createdBy = createdBy;
            return this;
        }

        public Builder withCreatedDate() {
            this.createdDate = LocalDate.now().toString();
            return this;
        }

        public Builder withExpiryDate(final String expiryDate) {
            this.expiryDate = expiryDate;
            return this;
        }

        public OfferEntity build() {
            return new OfferEntity(this);
        }

    }
}
