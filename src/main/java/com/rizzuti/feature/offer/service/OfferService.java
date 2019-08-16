package com.rizzuti.feature.offer.service;

import com.rizzuti.feature.offer.common.OfferStatus;
import com.rizzuti.feature.offer.model.OfferEntity;
import com.rizzuti.feature.offer.web.request.CreateOfferRequest;

import java.util.List;

public interface OfferService {

    OfferEntity create(CreateOfferRequest createOfferRequest);

    List<OfferEntity> getOfferByStatus(OfferStatus status);

    boolean cancel(int id);
}
