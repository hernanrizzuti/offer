package com.rizzuti.feature.offer.web.request;

import com.rizzuti.feature.offer.common.OfferStatus;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

//@JsonDeserialize(builder = CreateOfferRequest.Builder.class)
public class CreateOfferRequest {

    @NotNull
    private BigDecimal price;

    @NotNull
    private String currency;

    @NotNull
    private String description;

    private OfferStatus status;

    @NotNull
    private String createdBy;

    @NotNull
    private String expiryDate;

    private CreateOfferRequest(final Builder builder) {
        this.price = builder.price;
        this.currency = builder.currency;
        this.description = builder.description;
        this.status = builder.status;
        this.createdBy = builder.createdBy;
        this.expiryDate = builder.expiryDate;
    }

    public CreateOfferRequest() {
    }

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

    public String getExpiryDate() {
        return expiryDate;
    }

    public OfferStatus getStatus() {
        return status;
    }

    public static Builder newBuilder() { return new Builder();
    }

    public static final class Builder{

        private BigDecimal price;

        private String currency;

        private String description;

        private OfferStatus status;

        private String createdBy;

        private String expiryDate;

        public Builder withPrice(final BigDecimal price){
            this.price =price ;
            return this;
        }

        public Builder withCurrency(final String currency){
            this.currency = currency ;
            return this;
        }

        public Builder withDescription(final String description){
            this.description = description;
            return this;
        }

        public Builder withStatus(final OfferStatus status){
            this.status = status;
            return this;
        }

        public Builder withCreatedBy(final String createdBy){
            this.createdBy = createdBy;
            return this;
        }

        public Builder withExpiryDate(final String expiryDate){
            this.expiryDate = expiryDate;
            return this;
        }

        public CreateOfferRequest build(){ return new CreateOfferRequest(this);}

    }

}
