package com.rizzuti.feature.offer.factory;

import com.rizzuti.feature.offer.model.OfferEntity;
import com.rizzuti.feature.offer.web.response.CreateOfferResponse;
import org.springframework.stereotype.Component;

@Component
public class CreateOfferResponseFactory {

    public CreateOfferResponse getInstance(final OfferEntity offerEntity) {
        return CreateOfferResponse.newBuilder().withOffer(offerEntity).build();
    }
}
