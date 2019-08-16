package com.rizzuti.feature.offer.builder;

import com.rizzuti.feature.offer.model.OfferEntity;

import java.math.BigDecimal;

import static com.rizzuti.feature.offer.common.OfferStatus.CREATED;

public class TestOfferEntityBuilder {

    public static OfferEntity.Builder newBuilder() {
        return OfferEntity.newBuilder()
                .withPrice(new BigDecimal(12))
                .withCurrency("GBP")
                .withDescription("Journal Offer")
                .withStatus(CREATED)
                .withCreatedBy("John Tailor")
                .withCreatedDate()
                .withExpiryDate("10/11/2019");
    }
}
