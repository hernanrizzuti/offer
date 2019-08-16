package com.rizzuti.feature.offer.service;

import com.rizzuti.feature.offer.builder.TestCreateOfferRequestBuilder;
import com.rizzuti.feature.offer.builder.TestOfferEntityBuilder;
import com.rizzuti.feature.offer.common.OfferStatus;
import com.rizzuti.feature.offer.model.OfferEntity;
import com.rizzuti.feature.offer.repository.OfferRepository;
import com.rizzuti.feature.offer.web.request.CreateOfferRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static com.rizzuti.feature.offer.common.OfferStatus.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OfferServiceImplTest {

    @Mock
    private OfferRepository offerRepository;

    @InjectMocks
    private OfferServiceImpl offerService;

    @Test
    public void shouldReturnCreatedOfferEntity(){
        final CreateOfferRequest createOfferRequest = TestCreateOfferRequestBuilder.newBuilder().build();

        offerService.create(createOfferRequest);

        verify(offerRepository).save(any(OfferEntity.class));
    }

    @Test
    public void shouldReturnOfferEntities(){
        final OfferStatus offerStatus = CREATED;

        offerService.getOfferByStatus(offerStatus);

        verify(offerRepository).findByStatus(offerStatus);
    }

    @Test
    public void shouldCancelCreatedOfferWhenExpiryDateIsNotExpired(){
        final int id = 2;
        OfferEntity offerEntity = TestOfferEntityBuilder.newBuilder().build();
        when(offerRepository.findById(id)).thenReturn(Optional.of(offerEntity));

        boolean result = offerService.cancel(id);

        verify(offerRepository).save(any(OfferEntity.class));
        assertTrue(result);
    }

    @Test
    public void shouldCancelActiveOfferWhenExpiryDateIsNotExpired(){
        final int id = 2;
        OfferEntity offerEntity = TestOfferEntityBuilder.newBuilder().withStatus(ACTIVE).build();
        when(offerRepository.findById(id)).thenReturn(Optional.of(offerEntity));

        boolean result = offerService.cancel(id);

        verify(offerRepository).save(any(OfferEntity.class));
        assertTrue(result);
    }

    @Test
    public void shouldNotCancelOfferWhenExpiryDateExpired(){
        final int id = 2;
        OfferEntity offerEntity = TestOfferEntityBuilder.newBuilder().withExpiryDate("07/08/2019").build();
        when(offerRepository.findById(id)).thenReturn(Optional.of(offerEntity));

        boolean result = offerService.cancel(id);

        verify(offerRepository, never()).save(any(OfferEntity.class));
        assertFalse(result);
    }

    @Test
    public void shouldNotCancelOfferWhenCancelledAlready(){
        final int id = 2;
        OfferEntity offerEntity = TestOfferEntityBuilder.newBuilder().withStatus(CANCELLED).build();
        when(offerRepository.findById(id)).thenReturn(Optional.of(offerEntity));

        boolean result = offerService.cancel(id);

        verify(offerRepository, never()).save(any(OfferEntity.class));
        assertFalse(result);
    }
}