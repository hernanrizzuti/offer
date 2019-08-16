package com.rizzuti.feature.offer.web.response;

import com.rizzuti.feature.offer.builder.TestOfferEntityBuilder;
import com.rizzuti.feature.offer.model.OfferEntity;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class OffersResponseFactoryTest {


    @Test
    public void shouldReturnOfferResponse(){
        final OfferEntity offerEntity = TestOfferEntityBuilder.newBuilder().build();

        final OffersResponseFactory offersResponseFactory = new OffersResponseFactory();
        final OffersResponse offersResponse = offersResponseFactory.getInstance(Arrays.asList(offerEntity));
        final OfferEntity actualOfferEntity = offersResponse.getOffers().get(0);
        assertEquals(offerEntity, actualOfferEntity);
    }

}