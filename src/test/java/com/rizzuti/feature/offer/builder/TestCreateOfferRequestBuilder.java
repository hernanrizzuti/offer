package com.rizzuti.feature.offer.builder;

import com.rizzuti.feature.offer.web.request.CreateOfferRequest;

import java.math.BigDecimal;

import static com.rizzuti.feature.offer.common.OfferStatus.CREATED;

public class TestCreateOfferRequestBuilder {

    public static CreateOfferRequest.Builder newBuilder(){
        return CreateOfferRequest.newBuilder()
                .withPrice(new BigDecimal(12))
                .withCurrency("GBP")
                .withDescription("Journal Offer")
                .withStatus(CREATED)
                .withCreatedBy("John Tailor")
                .withExpiryDate("10/11/2019");
    }

}
