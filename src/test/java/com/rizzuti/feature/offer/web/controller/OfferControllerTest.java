package com.rizzuti.feature.offer.web.controller;

import com.rizzuti.feature.offer.builder.TestCreateOfferRequestBuilder;
import com.rizzuti.feature.offer.builder.TestOfferEntityBuilder;
import com.rizzuti.feature.offer.common.OfferStatus;
import com.rizzuti.feature.offer.factory.CreateOfferResponseFactory;
import com.rizzuti.feature.offer.model.OfferEntity;
import com.rizzuti.feature.offer.service.OfferService;
import com.rizzuti.feature.offer.web.request.CreateOfferRequest;
import com.rizzuti.feature.offer.web.response.OffersResponse;
import com.rizzuti.feature.offer.web.response.OffersResponseFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OfferControllerTest {

    @Mock
    private OfferService offerService;

    @Mock
    private CreateOfferResponseFactory createOfferResponseFactory;

    @Mock
    private OffersResponseFactory offersResponseFactory;

    @InjectMocks
    private OfferController offerController;

    @Mock
    private OfferEntity offerEntity;

    @Test
    public void shouldReturnCreatedOffer(){

        final CreateOfferRequest createOfferRequest = TestCreateOfferRequestBuilder.newBuilder().build();

        when(offerService.create(createOfferRequest)).thenReturn(offerEntity);

        final ResponseEntity responseEntity = offerController.create(createOfferRequest);

        verify(createOfferResponseFactory).getInstance(offerEntity);
        assertEquals(200,responseEntity.getStatusCode().value());

    }

    @Test
    public void shouldReturnAllOffers() {
        final OfferEntity offerEntity = TestOfferEntityBuilder.newBuilder().build();
        final List<OfferEntity> offerEntityList = Arrays.asList(offerEntity);
        when(offerService.getOfferByStatus(OfferStatus.ACTIVE)).thenReturn(offerEntityList);

        final  ResponseEntity <OffersResponse> response = offerController.getOfferByStatus("active");

        verify(offersResponseFactory).getInstance(offerEntityList);
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void shouldReturnOkWhenCancelActiveOffer(){
        final int id = 9;

        when(offerService.cancel(id)).thenReturn(true);
        ResponseEntity responseEntity = offerController.cancel(id);
        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    public void shouldConflictWhenFailToCancelActiveOffer(){
        final int id = 9;

        when(offerService.cancel(id)).thenReturn(false);
        ResponseEntity responseEntity = offerController.cancel(id);
        assertEquals(409, responseEntity.getStatusCodeValue());
    }
}