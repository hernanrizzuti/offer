package com.rizzuti.feature.offer.factory;

import com.rizzuti.feature.offer.builder.TestOfferEntityBuilder;
import com.rizzuti.feature.offer.model.OfferEntity;
import com.rizzuti.feature.offer.web.response.CreateOfferResponse;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CreateOfferResponseFactoryTest {

    @Test
    public void shouldReturnCreateOfferResponse(){
        CreateOfferResponseFactory createOfferResponseFactory = new CreateOfferResponseFactory();

        final OfferEntity offerEntity = TestOfferEntityBuilder.newBuilder().build();

        final CreateOfferResponse createOfferResponse = createOfferResponseFactory.getInstance(offerEntity);

        final OfferEntity expectedOfferEntity = createOfferResponse.getOffer();

        assertEquals(offerEntity.getPrice(), expectedOfferEntity.getPrice());
        assertEquals(offerEntity.getCurrency(), expectedOfferEntity.getCurrency());
        assertEquals(offerEntity.getDescription(), expectedOfferEntity.getDescription());
        assertEquals(offerEntity.getCreatedBy(), expectedOfferEntity.getCreatedBy());
        assertEquals(offerEntity.getCreatedDate(), expectedOfferEntity.getCreatedDate());
        assertEquals(offerEntity.getExpiryDate(), expectedOfferEntity.getExpiryDate());
        assertEquals(offerEntity.getStatus(), expectedOfferEntity.getStatus());

    }

}