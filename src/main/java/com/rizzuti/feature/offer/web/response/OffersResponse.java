package com.rizzuti.feature.offer.web.response;

import com.rizzuti.feature.offer.model.OfferEntity;

import java.util.List;

public class OffersResponse {

    private List<OfferEntity> offers;

    public OffersResponse(final Builder builder) {
        this.offers = builder.offers;
    }

    public static Builder newBuilder(){return new Builder(); }

    public List<OfferEntity> getOffers() {
        return offers;
    }

    public static final class Builder{

        private List<OfferEntity> offers;

        public Builder withOffer(List<OfferEntity> offers) {
            this.offers = offers;
            return this;
        }

        public OffersResponse build(){
            return new OffersResponse(this);
        }

    }

}
