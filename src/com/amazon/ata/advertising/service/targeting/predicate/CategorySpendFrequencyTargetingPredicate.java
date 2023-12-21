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
 * Compare against the number of purchases a customer has made in a single category on Amazon.
 */
public class CategorySpendFrequencyTargetingPredicate extends TargetingPredicate {
    private static final Spend ZERO_SPEND = new Spend.Builder().withNumberOfPurchases(0).withUsdSpent(0).build();

    @Inject
    ReadableDao<RequestContext, Map<String, Spend>> spendDao;

    private String targetedCategory;
    private Comparison comparison;
    private int targetedNumberOfPurchases;

    /**
     * Create a predicate to compare against the number of purchases in a category on Amazon.
     * @param targetedCategory The category you want to compare spend for - i.e. KINDLE
     * @param comparison How to compare the customer's number of purchases against the targeted value.  For example,
     *                  if you specify the comparision as Comparison.LT and the targeted number of purchases as 2,
     *                  this predicate will evaluate to TRUE for all customers who have made less than 2 purchases
     *                  in the specified category.
     * @param targetedNumberOfPurchases The number of purchases to compare against.
     * @param inverse If you would like to negate the value of this predicate.
     */
    public CategorySpendFrequencyTargetingPredicate(String targetedCategory,
                                                    Comparison comparison,
                                                    int targetedNumberOfPurchases,
                                                    boolean inverse) {
        super(inverse);

        Validate.notNull(targetedCategory, "The targeted category cannot be null.");
        Validate.notNull(comparison, "How to compare against the targeted value cannot be null.");

        this.targetedCategory = targetedCategory;
        this.comparison = comparison;
        this.targetedNumberOfPurchases = targetedNumberOfPurchases;
    }

    /**
     * Predicate for evaluating how often a customer spends in a given category.
     * @param targetedCategory The goal category to compare spending against
     * @param comparison Whether it should be greater than or less than the value
     * @param targetedNumberOfPurchases The value to compare against how many times the customer has spent money
     */
    public CategorySpendFrequencyTargetingPredicate(String targetedCategory,
                                                    Comparison comparison,
                                                    int targetedNumberOfPurchases) {
        this(targetedCategory, comparison, targetedNumberOfPurchases, false);
    }

    /**
     * Predicate for evaluating how often a customer spends in a given category.
     */
    public CategorySpendFrequencyTargetingPredicate() {}

    @Override
    TargetingPredicateResult evaluateRecognizedCustomer(RequestContext context) {
        Validate.notNull(targetedCategory, "The targeted category cannot be null.");
        Validate.notNull(comparison, "How to compare against the targeted value cannot be null.");

        final Map<String, Spend> customerSpend = spendDao.get(context);
        final Spend categorySpend = customerSpend.getOrDefault(targetedCategory, ZERO_SPEND);
        return comparison.compare(categorySpend.getNumberOfPurchases(), targetedNumberOfPurchases) ?
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

    public int getTargetedNumberOfPurchases() {
        return targetedNumberOfPurchases;
    }

    public void setTargetedNumberOfPurchases(int targetedNumberOfPurchases) {
        this.targetedNumberOfPurchases = targetedNumberOfPurchases;
    }

    @VisibleForTesting
    public void setSpendDao(ReadableDao<RequestContext, Map<String, Spend>> spendDao) {
        this.spendDao = spendDao;
    }
}
