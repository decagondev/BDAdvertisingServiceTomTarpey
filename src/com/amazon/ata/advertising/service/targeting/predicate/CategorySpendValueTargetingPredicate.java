package com.amazon.ata.advertising.service.targeting.predicate;

import com.amazon.ata.advertising.service.dao.ReadableDao;
import com.amazon.ata.advertising.service.model.RequestContext;
import com.amazon.ata.advertising.service.targeting.Comparison;
import com.amazon.ata.customerservice.Spend;

import com.google.common.annotations.VisibleForTesting;
import org.apache.commons.lang3.Validate;

import java.util.Map;
import javax.inject.Inject;

/**
 * Compare against the amount a customer has spent in USD in a single category on Amazon.
 */
public class CategorySpendValueTargetingPredicate extends TargetingPredicate {
    private static final Spend ZERO_SPEND = new Spend.Builder().withNumberOfPurchases(0).withUsdSpent(0).build();

    @Inject
    ReadableDao<RequestContext, Map<String, Spend>> spendDao;

    private String targetedCategory;
    private Comparison comparison;
    private int targetedValue;

    /**
     * Create a predicate to compare against spend in a category on Amazon.
     * @param targetedCategory The category you want to compare spend for - i.e. KINDLE
     * @param comparison How to compare the customer's number of purchases against the targeted value.  For example,
     *                  if you specify the comparision as Comparison.LT and the targeted value as $50, this predicate
     *                  will evaluate to TRUE for all customers who have spent less than $50 in the specified category.
     * @param targetedValue The value in rounded USD to compare the customer against.
     * @param inverse If you would like to negate the value of this predicate.
     */
    public CategorySpendValueTargetingPredicate(String targetedCategory,
                                                Comparison comparison,
                                                int targetedValue,
                                                boolean inverse) {
        super(inverse);

        Validate.notNull(targetedCategory, "The targeted category cannot be null.");
        Validate.notNull(comparison, "How to compare against the targeted value cannot be null.");

        this.targetedCategory = targetedCategory;
        this.comparison = comparison;
        this.targetedValue = targetedValue;
    }

    /**
     * Create a predicate that evaluates to true based on how much a customer has spent in a given category.
     * @param targetedCategory Category customer to have spent money in
     * @param comparison How to compare against the value
     * @param targetedValue The value of money in the local currency to have spent or not spent
     */
    public CategorySpendValueTargetingPredicate(String targetedCategory, Comparison comparison, int targetedValue) {
        this(targetedCategory, comparison, targetedValue, false);
    }

    /**
     *
     */
    public CategorySpendValueTargetingPredicate() {}

    @Override
    TargetingPredicateResult evaluateRecognizedCustomer(RequestContext context) {
        Validate.notNull(targetedCategory, "The targeted category cannot be null.");
        Validate.notNull(comparison, "How to compare against the targeted value cannot be null.");

        final Map<String, Spend> customerSpend = spendDao.get(context);
        final Spend categorySpend = customerSpend.getOrDefault(targetedCategory, ZERO_SPEND);
        return comparison.compare(categorySpend.getUsdSpent(), targetedValue) ?
                TargetingPredicateResult.TRUE : TargetingPredicateResult.FALSE;
    }

    public String getTargetedCategory() {
        return targetedCategory;
    }

    public void setTargetedCategory(String targetedCategory) {
        this.targetedCategory = targetedCategory;
    }

    public Comparison getComparison() {
        return comparison;
    }

    public void setComparison(Comparison comparison) {
        this.comparison = comparison;
    }

    public int getTargetedValue() {
        return targetedValue;
    }

    public void setTargetedValue(int targetedValue) {
        this.targetedValue = targetedValue;
    }

    @VisibleForTesting
    void setSpendDao(ReadableDao<RequestContext, Map<String, Spend>> spendDao) {
        this.spendDao = spendDao;
    }
}
