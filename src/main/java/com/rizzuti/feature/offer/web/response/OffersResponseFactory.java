package com.rizzuti.feature.offer.web.response;

import com.rizzuti.feature.offer.model.OfferEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OffersResponseFactory {


    public OffersResponse getInstance(final List<OfferEntity> offerEntities) {
        return OffersResponse.newBuilder().withOffer(offerEntities).build();
    }
}
