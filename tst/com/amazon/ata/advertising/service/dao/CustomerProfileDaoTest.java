package com.amazon.ata.advertising.service.dao;

import com.amazon.ata.customerservice.AgeRange;
import com.amazon.ata.customerservice.CustomerProfile;
import com.amazon.ata.customerservice.GetCustomerProfileRequest;
import com.amazon.ata.customerservice.GetCustomerProfileResponse;
import com.amazon.ata.customerservice.State;
import com.amazon.atacustomerservicelambda.service.ATACustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CustomerProfileDaoTest {
    private static final String CUSTOMER_ID = "1";
    private static final CustomerProfile PROFILE = CustomerProfile.builder()
            .withAgeRange(AgeRange.AGE_26_TO_30)
            .withHomeState(State.WA)
            .withParent(false)
            .build();
    private static final GetCustomerProfileResponse RESULT = GetCustomerProfileResponse.builder().
            withCustomerProfile(PROFILE)
            .build();

    @Mock
    private ATACustomerService customerClient;

    @InjectMocks
    private CustomerProfileDao customerProfileDao;

    @BeforeEach
    public void setup() {
        initMocks(this);
    }

    @Test
    public void get_customerId_receivesCustomerProfile() {
        // GIVEN
        ArgumentCaptor<GetCustomerProfileRequest> requestCaptor = ArgumentCaptor.forClass(GetCustomerProfileRequest.class);
        when(customerClient.getCustomerProfile(any(GetCustomerProfileRequest.class))).thenReturn(RESULT);

        // WHEN
        CustomerProfile actual = customerProfileDao.get(CUSTOMER_ID);

        // THEN
        assertEquals(actual, PROFILE);
        verify(customerClient).getCustomerProfile(requestCaptor.capture());
        GetCustomerProfileRequest capturedRequest = requestCaptor.getValue();
        assertEquals(capturedRequest.getCustomerId(), CUSTOMER_ID);
    }
}
