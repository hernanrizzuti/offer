package com.rizzuti.feature.offer.web.controller;

import com.rizzuti.feature.offer.common.OfferStatus;
import com.rizzuti.feature.offer.factory.CreateOfferResponseFactory;
import com.rizzuti.feature.offer.model.OfferEntity;
import com.rizzuti.feature.offer.service.OfferService;
import com.rizzuti.feature.offer.web.request.CreateOfferRequest;
import com.rizzuti.feature.offer.web.response.CreateOfferResponse;
import com.rizzuti.feature.offer.web.response.OffersResponse;
import com.rizzuti.feature.offer.web.response.OffersResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/offer")
public class OfferController {

    @Autowired
    private CreateOfferResponseFactory createOfferResponseFactory;

    @Autowired
    private OffersResponseFactory offersResponseFactory;

    @Autowired
    private OfferService offerService;

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST)
    public ResponseEntity<CreateOfferResponse> create(@RequestBody @Valid CreateOfferRequest createOfferRequest) {

        final OfferEntity offerEntity = offerService.create(createOfferRequest);

        final CreateOfferResponse createOfferResponse = createOfferResponseFactory.getInstance(offerEntity);

    return ResponseEntity.ok(createOfferResponse);
    }
    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<OffersResponse> getOfferByStatus(@RequestParam("status") final String status) {
        final List<OfferEntity> offerEntities = offerService.getOfferByStatus(OfferStatus.valueOf(status.toUpperCase()));

        OffersResponse offersResponse = offersResponseFactory.getInstance(offerEntities);
        return ResponseEntity.ok(offersResponse);
    }

    @RequestMapping(path = "/{id}/cancel", method = RequestMethod.PATCH)
    public ResponseEntity cancel(@PathVariable int id) {
        if (offerService.cancel(id)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.CONFLICT);
    }
}
