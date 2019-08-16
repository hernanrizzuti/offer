package com.rizzuti.feature.offer.service;

import com.rizzuti.feature.offer.common.OfferStatus;
import com.rizzuti.feature.offer.model.OfferEntity;
import com.rizzuti.feature.offer.repository.OfferRepository;
import com.rizzuti.feature.offer.web.request.CreateOfferRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.rizzuti.feature.offer.common.OfferStatus.*;
import static java.time.LocalDate.now;
import static java.time.LocalDate.parse;
import static java.time.format.DateTimeFormatter.ofPattern;

@Service
public class OfferServiceImpl implements OfferService {

    @Autowired
    private OfferRepository offerRepository;

    @Override
    public OfferEntity create(final CreateOfferRequest createOfferRequest) {
        final OfferEntity offerEntity = buildOfferEntity(createOfferRequest);

        return offerRepository.save(offerEntity);
    }

    @Override
    public List<OfferEntity> getOfferByStatus(final OfferStatus status) {
        return offerRepository.findByStatus(status);
    }

    @Override
    public boolean cancel(final int id) {
        final Optional<OfferEntity> optional = offerRepository.findById(id);

        if(optional.isPresent()) {
            final OfferEntity offerEntity = optional.get();
            final OfferStatus status = optional.get().getStatus();

            if(ACTIVE.equals(status) || CREATED.equals(status) &&
                    parse(offerEntity.getExpiryDate(), ofPattern("d/MM/yyyy")).isAfter(now())) {

                final OfferEntity cancelledOfferEntity = OfferEntity.copyBuilder(offerEntity)
                        .withStatus(CANCELLED)
                        .build();
                offerRepository.save(cancelledOfferEntity);
                return true;
            }
        }

        return false;
    }

    private OfferEntity buildOfferEntity(final CreateOfferRequest createOfferRequest) {
        final OfferStatus status = createOfferRequest.getStatus();
        return OfferEntity.newBuilder()
                .withPrice(createOfferRequest.getPrice())
                .withCurrency(createOfferRequest.getCurrency())
                .withDescription(createOfferRequest.getDescription())
                .withCreatedDate()
                .withCreatedBy(createOfferRequest.getCreatedBy())
                .withStatus(Objects.isNull(status) ? CREATED : status)
                .withExpiryDate(createOfferRequest.getExpiryDate())
                .build();
    }
}
