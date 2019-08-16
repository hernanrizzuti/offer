package com.rizzuti.feature.offer.web.response;

import com.rizzuti.feature.offer.model.OfferEntity;

public class CreateOfferResponse {

    private OfferEntity offer;

    public CreateOfferResponse(final Builder builder) {
        this.offer = builder.offer;
    }
    public static Builder newBuilder(){return new Builder(); }

    public OfferEntity getOffer() {
        return offer;
    }

    public static final class Builder{

        private OfferEntity offer;

        public Builder withOffer(OfferEntity offer) {
            this.offer = offer;
            return this;
        }

        public CreateOfferResponse build(){
            return new CreateOfferResponse(this);
        }

    }
}
