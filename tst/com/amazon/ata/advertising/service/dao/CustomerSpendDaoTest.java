package com.amazon.ata.advertising.service.dao;

import com.amazon.ata.advertising.service.model.RequestContext;
import com.amazon.ata.customerservice.Category;
import com.amazon.ata.customerservice.CustomerSpendCategories;
import com.amazon.ata.customerservice.GetCustomerSpendCategoriesRequest;
import com.amazon.ata.customerservice.GetCustomerSpendCategoriesResponse;
import com.amazon.ata.customerservice.Spend;
import com.amazon.atacustomerservicelambda.service.ATACustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CustomerSpendDaoTest {
    private static final String MARKETPLACE_ID = "1";
    private static final String CUSTOMER_ID = "2";
    private static final RequestContext REQUEST_CONTEXT = new RequestContext(CUSTOMER_ID, MARKETPLACE_ID);
    private static final String CATEGORY = Category.COMPUTERS;
    private static final Spend SPEND = Spend.builder().withNumberOfPurchases(1).withUsdSpent(1).build();

    private static final GetCustomerSpendCategoriesResponse SERVICE_RESULT = GetCustomerSpendCategoriesResponse.builder()
            .withCustomerSpendCategories(CustomerSpendCategories.builder()
                    .withSpendCategories(Collections.singletonMap(CATEGORY, SPEND))
                    .build())
            .build();

    @Mock
    private ATACustomerService customerClient;

    @InjectMocks
    private CustomerSpendDao customerSpendDao;

    @BeforeEach
    public void setup() {
        initMocks(this);
    }

    @Test
    public void get_requestContext_receivesCustomerSpendCategories() {
        // GIVEN
        ArgumentCaptor<GetCustomerSpendCategoriesRequest> requestCaptor = ArgumentCaptor.forClass(GetCustomerSpendCategoriesRequest.class);
        when(customerClient.getCustomerSpendCategories(any(GetCustomerSpendCategoriesRequest.class))).thenReturn(SERVICE_RESULT);

        // WHEN
        Map<String, Spend> result = customerSpendDao.get(REQUEST_CONTEXT);


        // THEN
        assertEquals(result.size(), 1);
        assertEquals(result.get(Category.COMPUTERS), SPEND);

        verify(customerClient).getCustomerSpendCategories(requestCaptor.capture());
        GetCustomerSpendCategoriesRequest request = requestCaptor.getValue();
        assertEquals(request.getCustomerId(), CUSTOMER_ID);
        assertEquals(request.getMarketplaceId(), MARKETPLACE_ID);
    }
}
