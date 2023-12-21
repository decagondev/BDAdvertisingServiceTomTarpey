package com.amazon.ata.advertising.service.dao;

import com.amazon.ata.advertising.service.model.RequestContext;
import com.amazon.ata.primeclubservice.Benefit;
import com.amazon.ata.primeclubservice.GetPrimeBenefitsRequest;
import com.amazon.ata.primeclubservice.GetPrimeBenefitsResponse;
import com.amazon.ata.primeclubservice.PrimeBenefit;
import com.amazon.ataprimeclubservicelambda.service.ATAPrimeClubService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class PrimeDaoTest {
    private static final String CUSTOMER_ID = "12345";
    private static final String MARKETPLACE_ID = "1";
    private static final RequestContext REQUEST_CONTEXT = new RequestContext(CUSTOMER_ID, MARKETPLACE_ID);

    private static final GetPrimeBenefitsRequest REQUEST = GetPrimeBenefitsRequest.builder()
            .withMarketplaceId(MARKETPLACE_ID)
            .withCustomerId(CUSTOMER_ID)
            .build();

    private static final GetPrimeBenefitsResponse RESULT = GetPrimeBenefitsResponse.builder().withPrimeBenefits(
            Arrays.asList(PrimeBenefit.builder()
                    .withBenefitType(Benefit.MOM_DISCOUNT)
                    .build()))
            .build();

    @InjectMocks
    private PrimeDao primeDao;

    @Mock
    private ATAPrimeClubService primeClubService;

    @BeforeEach
    public void setup() {
        initMocks(this);
    }

    @Test
    public void get_requestContext_listOfPrimeBenefitsForCustomer() {
        // GIVEN
        when(primeClubService.getPrimeBenefits(REQUEST)).thenReturn(RESULT);

        // WHEN
        List<String> primeBenefits = primeDao.get(REQUEST_CONTEXT);

        // THEN
        assertEquals(Collections.singletonList(Benefit.MOM_DISCOUNT), primeBenefits);
    }
}
