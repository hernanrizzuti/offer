package com.rizzuti.feature.offer.repository;

import com.rizzuti.feature.offer.common.OfferStatus;
import com.rizzuti.feature.offer.model.OfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfferRepository extends JpaRepository<OfferEntity, Integer> {

    List<OfferEntity> findByStatus(OfferStatus offerStatus);
}
