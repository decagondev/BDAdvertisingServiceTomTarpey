package com.amazon.ata.advertising.service.model.translator;

import com.amazon.ata.advertising.service.exceptions.AdvertisementClientException;
import com.amazon.ata.advertising.service.model.TargetingPredicateType;
import com.amazon.ata.advertising.service.targeting.Comparison;
import com.amazon.ata.advertising.service.targeting.predicate.AgeTargetingPredicate;
import com.amazon.ata.advertising.service.targeting.predicate.CategorySpendFrequencyTargetingPredicate;
import com.amazon.ata.advertising.service.targeting.predicate.CategorySpendValueTargetingPredicate;
import com.amazon.ata.advertising.service.targeting.predicate.ParentPredicate;
import com.amazon.ata.advertising.service.targeting.predicate.PrimeBenefitTargetingPredicate;
import com.amazon.ata.advertising.service.targeting.predicate.RecognizedTargetingPredicate;
import com.amazon.ata.advertising.service.targeting.predicate.TargetingPredicate;
import com.amazon.ata.customerservice.AgeRange;
import com.amazon.ata.customerservice.Category;
import com.amazon.ata.primeclubservice.Benefit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TargetingPredicateTranslatorTest {
    private static final String AGE_KEY = "AgeRange";
    private static final String PURCHASE_NUMBER_KEY = "NumberOfPurchases";
    private static final String VALUE_KEY = "Value";
    private static final String BENEFIT_KEY = "Benefit";
    private static final String CATEGORY_KEY = "Category";
    private static final String COMPARISON_KEY = "Comparison";

    private com.amazon.ata.advertising.service.model.TargetingPredicate.Builder coralShapeBuilder;
    private Map<String, String> attributes;

    @BeforeEach
    public void setup() {
        attributes = new HashMap<>();
        coralShapeBuilder = com.amazon.ata.advertising.service.model.TargetingPredicate.builder()
                .withNegate(true)
                .withAttributes(attributes);
    }

    @Test
    public void fromCoral_ageTypeWithAttributes_returnAgePredicate() {
        // GIVEN
        String ageRange = AgeRange.AGE_26_TO_30;
        attributes.put(AGE_KEY, ageRange);
        com.amazon.ata.advertising.service.model.TargetingPredicate coralShape = coralShapeBuilder
                .withTargetingPredicateType(TargetingPredicateType.AGE)
                .build();

        // WHEN
        TargetingPredicate result = TargetingPredicateTranslator.fromCoral(coralShape);

        // THEN
        assertTrue(result.isInverse());
        assertTrue(result instanceof AgeTargetingPredicate);
        AgeTargetingPredicate predicate = (AgeTargetingPredicate) result;
        assertEquals(ageRange, predicate.getTargetedAgeRange());
    }

    @Test
    public void fromCoral_ageTypeMissingAttribute_throwsClientException() {
        // GIVEN
        com.amazon.ata.advertising.service.model.TargetingPredicate coralShape = coralShapeBuilder
                .withTargetingPredicateType(TargetingPredicateType.AGE)
                .build();

        // WHEN + THEN
        assertThrows(AdvertisementClientException.class, () -> TargetingPredicateTranslator.fromCoral(coralShape));
    }

    @Test
    public void fromCoral_ageTypeInvalidAge_throwsClientException() {
        // GIVEN
        String ageRange = "18 - 21";
        attributes.put(AGE_KEY, ageRange);
        com.amazon.ata.advertising.service.model.TargetingPredicate coralShape = coralShapeBuilder
                .withTargetingPredicateType(TargetingPredicateType.AGE)
                .build();

        // WHEN + THEN
        assertThrows(AdvertisementClientException.class, () -> TargetingPredicateTranslator.fromCoral(coralShape));
    }

    @Test
    public void fromCoral_spendValueTypeValidAttributes_returnsSpendValuePredicate() {
        // GIVEN
        String category = Category.COMPUTERS;
        String comparison = Comparison.GT.toString();
        int value = 10;
        attributes.put(CATEGORY_KEY, category);
        attributes.put(COMPARISON_KEY, comparison);
        attributes.put(VALUE_KEY, Integer.toString(value));
        com.amazon.ata.advertising.service.model.TargetingPredicate coralShape = coralShapeBuilder
                .withTargetingPredicateType(TargetingPredicateType.CATEGORY_SPEND_VALUE)
                .build();

        // WHEN
        TargetingPredicate result = TargetingPredicateTranslator.fromCoral(coralShape);

        // THEN
        assertTrue(result.isInverse());
        assertTrue(result instanceof CategorySpendValueTargetingPredicate);
        CategorySpendValueTargetingPredicate predicate = (CategorySpendValueTargetingPredicate) result;
        assertEquals(category, predicate.getTargetedCategory());
        assertEquals(comparison, predicate.getComparison().toString());
        assertEquals(value, predicate.getTargetedValue());
    }

    @Test
    public void fromCoral_spendValueTypeMissingCategoryKey_throwsClientException() {
        // GIVEN
        String comparison = Comparison.GT.toString();
        int value = 10;
        attributes.put(COMPARISON_KEY, comparison);
        attributes.put(VALUE_KEY, Integer.toString(value));
        com.amazon.ata.advertising.service.model.TargetingPredicate coralShape = coralShapeBuilder
                .withTargetingPredicateType(TargetingPredicateType.CATEGORY_SPEND_VALUE)
                .build();

        // WHEN + THEN
        assertThrows(AdvertisementClientException.class, () -> TargetingPredicateTranslator.fromCoral(coralShape));
    }

    @Test
    public void fromCoral_spendValueTypeMissingComparisonKey_throwsClientException() {
        // GIVEN
        String category = Category.COMPUTERS;
        int value = 10;
        attributes.put(CATEGORY_KEY, category);
        attributes.put(VALUE_KEY, Integer.toString(value));
        com.amazon.ata.advertising.service.model.TargetingPredicate coralShape = coralShapeBuilder
                .withTargetingPredicateType(TargetingPredicateType.CATEGORY_SPEND_VALUE)
                .build();

        // WHEN + THEN
        assertThrows(AdvertisementClientException.class, () -> TargetingPredicateTranslator.fromCoral(coralShape));
    }

    @Test
    public void fromCoral_spendValueTypeMissingPurchaseNumberKey_throwsClientException() {
        // GIVEN
        String category = Category.COMPUTERS;
        String comparison = Comparison.GT.toString();
        attributes.put(CATEGORY_KEY, category);
        attributes.put(COMPARISON_KEY, comparison);
        com.amazon.ata.advertising.service.model.TargetingPredicate coralShape = coralShapeBuilder
                .withTargetingPredicateType(TargetingPredicateType.CATEGORY_SPEND_VALUE)
                .build();

        // WHEN + THEN
        assertThrows(AdvertisementClientException.class, () -> TargetingPredicateTranslator.fromCoral(coralShape));
    }

    @Test
    public void fromCoral_spendValueTypeMissingInvalidCategoryValue_throwsClientException() {
        // GIVEN
        String category = "Toast";
        String comparison = Comparison.GT.toString();
        int value = 10;
        attributes.put(CATEGORY_KEY, category);
        attributes.put(COMPARISON_KEY, comparison);
        attributes.put(VALUE_KEY, Integer.toString(value));
        com.amazon.ata.advertising.service.model.TargetingPredicate coralShape = coralShapeBuilder
                .withTargetingPredicateType(TargetingPredicateType.CATEGORY_SPEND_VALUE)
                .build();

        // WHEN + THEN
        assertThrows(AdvertisementClientException.class, () -> TargetingPredicateTranslator.fromCoral(coralShape));
    }

    @Test
    public void fromCoral_spendValueTypeInvalidComparisonValue_throwsClientException() {
        // GIVEN
        String category = Category.COMPUTERS;
        String comparison = "LTE";
        int value = 10;
        attributes.put(CATEGORY_KEY, category);
        attributes.put(COMPARISON_KEY, comparison);
        attributes.put(VALUE_KEY, Integer.toString(value));
        com.amazon.ata.advertising.service.model.TargetingPredicate coralShape = coralShapeBuilder
                .withTargetingPredicateType(TargetingPredicateType.CATEGORY_SPEND_VALUE)
                .build();

        // WHEN + THEN
        assertThrows(AdvertisementClientException.class, () -> TargetingPredicateTranslator.fromCoral(coralShape));
    }

    @Test
    public void fromCoral_spendValueTypeInvalidValue_throwsClientException() {
        // GIVEN
        String category = Category.COMPUTERS;
        String comparison = Comparison.GT.toString();
        String value = "abc";
        attributes.put(CATEGORY_KEY, category);
        attributes.put(COMPARISON_KEY, comparison);
        attributes.put(VALUE_KEY, value);
        com.amazon.ata.advertising.service.model.TargetingPredicate coralShape = coralShapeBuilder
                .withTargetingPredicateType(TargetingPredicateType.CATEGORY_SPEND_VALUE)
                .build();

        // WHEN + THEN
        assertThrows(AdvertisementClientException.class, () -> TargetingPredicateTranslator.fromCoral(coralShape));
    }

    @Test
    public void fromCoral_spendFrequencyTypeValidAttributes_returnsSpendFrequencyPredicate() {
        // GIVEN
        String category = Category.COMPUTERS;
        String comparison = Comparison.GT.toString();
        int purchaseNumber = 10;
        attributes.put(CATEGORY_KEY, category);
        attributes.put(COMPARISON_KEY, comparison);
        attributes.put(PURCHASE_NUMBER_KEY, Integer.toString(purchaseNumber));
        com.amazon.ata.advertising.service.model.TargetingPredicate coralShape = coralShapeBuilder
                .withTargetingPredicateType(TargetingPredicateType.CATEGORY_SPEND_FREQUENCY)
                .build();

        // WHEN
        TargetingPredicate result = TargetingPredicateTranslator.fromCoral(coralShape);

        // THEN
        assertTrue(result.isInverse());
        assertTrue(result instanceof CategorySpendFrequencyTargetingPredicate);
        CategorySpendFrequencyTargetingPredicate predicate = (CategorySpendFrequencyTargetingPredicate) result;
        assertEquals(category, predicate.getTargetedCategory());
        assertEquals(comparison, predicate.getComparison().toString());
        assertEquals(purchaseNumber, predicate.getTargetedNumberOfPurchases());
    }

    @Test
    public void fromCoral_spendFrequencyTypeMissingCategoryKey_throwsClientException() {
        // GIVEN
        String comparison = Comparison.GT.toString();
        int purchaseNumber = 10;
        attributes.put(COMPARISON_KEY, comparison);
        attributes.put(PURCHASE_NUMBER_KEY, Integer.toString(purchaseNumber));
        com.amazon.ata.advertising.service.model.TargetingPredicate coralShape = coralShapeBuilder
                .withTargetingPredicateType(TargetingPredicateType.CATEGORY_SPEND_FREQUENCY)
                .build();

        // WHEN + THEN
        assertThrows(AdvertisementClientException.class, () -> TargetingPredicateTranslator.fromCoral(coralShape));
    }

    @Test
    public void fromCoral_spendFrequencyTypeMissingComparisonKey_throwsClientException() {
        // GIVEN
        String category = Category.COMPUTERS;
        int purchaseNumber = 10;
        attributes.put(CATEGORY_KEY, category);
        attributes.put(PURCHASE_NUMBER_KEY, Integer.toString(purchaseNumber));
        com.amazon.ata.advertising.service.model.TargetingPredicate coralShape = coralShapeBuilder
                .withTargetingPredicateType(TargetingPredicateType.CATEGORY_SPEND_FREQUENCY)
                .build();

        // WHEN + THEN
        assertThrows(AdvertisementClientException.class, () -> TargetingPredicateTranslator.fromCoral(coralShape));
    }

    @Test
    public void fromCoral_spendFrequencyTypeMissingPurchaseNumberKey_throwsClientException() {
        // GIVEN
        String category = Category.COMPUTERS;
        String comparison = Comparison.GT.toString();
        attributes.put(CATEGORY_KEY, category);
        attributes.put(COMPARISON_KEY, comparison);
        com.amazon.ata.advertising.service.model.TargetingPredicate coralShape = coralShapeBuilder
                .withTargetingPredicateType(TargetingPredicateType.CATEGORY_SPEND_FREQUENCY)
                .build();

        // WHEN + THEN
        assertThrows(AdvertisementClientException.class, () -> TargetingPredicateTranslator.fromCoral(coralShape));
    }

    @Test
    public void fromCoral_spendFrequencyTypeMissingInvalidCategoryValue_throwsClientException() {
        // GIVEN
        String category = "Toast";
        String comparison = Comparison.GT.toString();
        int purchaseNumber = 10;
        attributes.put(CATEGORY_KEY, category);
        attributes.put(COMPARISON_KEY, comparison);
        attributes.put(PURCHASE_NUMBER_KEY, Integer.toString(purchaseNumber));
        com.amazon.ata.advertising.service.model.TargetingPredicate coralShape = coralShapeBuilder
                .withTargetingPredicateType(TargetingPredicateType.CATEGORY_SPEND_FREQUENCY)
                .build();

        // WHEN + THEN
        assertThrows(AdvertisementClientException.class, () -> TargetingPredicateTranslator.fromCoral(coralShape));
    }

    @Test
    public void fromCoral_spendFrequencyTypeInvalidComparisonValue_throwsClientException() {
        // GIVEN
        String category = Category.COMPUTERS;
        String comparison = "LTE";
        int purchaseNumber = 10;
        attributes.put(CATEGORY_KEY, category);
        attributes.put(COMPARISON_KEY, comparison);
        attributes.put(PURCHASE_NUMBER_KEY, Integer.toString(purchaseNumber));
        com.amazon.ata.advertising.service.model.TargetingPredicate coralShape = coralShapeBuilder
                .withTargetingPredicateType(TargetingPredicateType.CATEGORY_SPEND_FREQUENCY)
                .build();

        // WHEN + THEN
        assertThrows(AdvertisementClientException.class, () -> TargetingPredicateTranslator.fromCoral(coralShape));
    }

    @Test
    public void fromCoral_spendFrequencyTypeInvalidPurchaseNumberValue_throwsClientException() {
        // GIVEN
        String category = Category.COMPUTERS;
        String comparison = Comparison.GT.toString();
        String purchaseNumber = "abc";
        attributes.put(CATEGORY_KEY, category);
        attributes.put(COMPARISON_KEY, comparison);
        attributes.put(PURCHASE_NUMBER_KEY, purchaseNumber);
        com.amazon.ata.advertising.service.model.TargetingPredicate coralShape = coralShapeBuilder
                .withTargetingPredicateType(TargetingPredicateType.CATEGORY_SPEND_FREQUENCY)
                .build();

        // WHEN + THEN
        assertThrows(AdvertisementClientException.class, () -> TargetingPredicateTranslator.fromCoral(coralShape));
    }

    @Test
    public void fromCoral_parentTypeWithAttributes_returnParentPredicate() {
        // GIVEN
        com.amazon.ata.advertising.service.model.TargetingPredicate coralShape = coralShapeBuilder
                .withTargetingPredicateType(TargetingPredicateType.PARENT)
                .build();

        // WHEN
        TargetingPredicate result = TargetingPredicateTranslator.fromCoral(coralShape);

        // THEN
        assertTrue(result.isInverse());
        assertTrue(result instanceof ParentPredicate);
    }

    @Test
    public void fromCoral_recognizedTypeWithAttributes_returnRecognizedPredicate() {
        // GIVEN
        com.amazon.ata.advertising.service.model.TargetingPredicate coralShape = coralShapeBuilder
                .withTargetingPredicateType(TargetingPredicateType.RECOGNIZED)
                .build();

        // WHEN
        TargetingPredicate result = TargetingPredicateTranslator.fromCoral(coralShape);

        // THEN
        assertTrue(result.isInverse());
        assertTrue(result instanceof RecognizedTargetingPredicate);
    }

    @Test
    public void fromCoral_primeTypeWithAttributes_returnPrimePredicate() {
        // GIVEN
        String primeBenefit = Benefit.FREE_EXPEDITED_SHIPPING;
        attributes.put(BENEFIT_KEY, primeBenefit);
        com.amazon.ata.advertising.service.model.TargetingPredicate coralShape = coralShapeBuilder
                .withTargetingPredicateType(TargetingPredicateType.PRIME_BENEFIT)
                .build();

        // WHEN
        TargetingPredicate result = TargetingPredicateTranslator.fromCoral(coralShape);

        // THEN
        assertTrue(result.isInverse());
        assertTrue(result instanceof PrimeBenefitTargetingPredicate);
        PrimeBenefitTargetingPredicate predicate = (PrimeBenefitTargetingPredicate) result;
        assertEquals(primeBenefit, predicate.getBenefitToHave());
    }

    @Test
    public void fromCoral_primeTypeMissingAttribute_throwsClientException() {
        // GIVEN
        com.amazon.ata.advertising.service.model.TargetingPredicate coralShape = coralShapeBuilder
                .withTargetingPredicateType(TargetingPredicateType.PRIME_BENEFIT)
                .build();

        // WHEN + THEN
        assertThrows(AdvertisementClientException.class, () -> TargetingPredicateTranslator.fromCoral(coralShape));
    }

    @Test
    public void fromCoral_ageTypeInvalidBenefit_throwsClientException() {
        // GIVEN
        String primeBenefit = "Free Shipping!";
        attributes.put(BENEFIT_KEY, primeBenefit);
        com.amazon.ata.advertising.service.model.TargetingPredicate coralShape = coralShapeBuilder
                .withTargetingPredicateType(TargetingPredicateType.PRIME_BENEFIT)
                .build();

        // WHEN + THEN
        assertThrows(AdvertisementClientException.class, () -> TargetingPredicateTranslator.fromCoral(coralShape));
    }

    @Test
    public void toCoral_agePredicate_returnsCoralShape() {
        // GIVEN
        String ageRange = AgeRange.AGE_26_TO_30;
        TargetingPredicate predicate = new AgeTargetingPredicate(ageRange, true);

        // WHEN
        com.amazon.ata.advertising.service.model.TargetingPredicate coralShape = TargetingPredicateTranslator.toCoral(predicate);

        // THEN
        assertTrue(coralShape.isNegate());
        assertEquals(TargetingPredicateType.AGE, coralShape.getTargetingPredicateType());
        assertEquals(ageRange, coralShape.getAttributes().get(AGE_KEY));
    }

    @Test
    public void toCoral_spendFrequencyPredicate_returnsCoralShape() {
        // GIVEN
        String category = Category.COMPUTERS;
        Comparison comparison = Comparison.LT;
        int purchaseNumber = 10;
        TargetingPredicate predicate = new CategorySpendFrequencyTargetingPredicate(category, comparison, purchaseNumber, false);

        // WHEN
        com.amazon.ata.advertising.service.model.TargetingPredicate coralShape = TargetingPredicateTranslator.toCoral(predicate);

        // THEN
        assertFalse(coralShape.isNegate());
        assertEquals(TargetingPredicateType.CATEGORY_SPEND_FREQUENCY, coralShape.getTargetingPredicateType());
        assertEquals(category, coralShape.getAttributes().get(CATEGORY_KEY));
        assertEquals(comparison.toString(), coralShape.getAttributes().get(COMPARISON_KEY));
        assertEquals(Integer.toString(purchaseNumber), coralShape.getAttributes().get(PURCHASE_NUMBER_KEY));
    }

    @Test
    public void toCoral_spendValuePredicate_returnsCoralShape() {
        // GIVEN
        String category = Category.COMPUTERS;
        Comparison comparison = Comparison.LT;
        int value = 100;
        TargetingPredicate predicate = new CategorySpendValueTargetingPredicate(category, comparison, value, true);

        // WHEN
        com.amazon.ata.advertising.service.model.TargetingPredicate coralShape = TargetingPredicateTranslator.toCoral(predicate);

        // THEN
        assertTrue(coralShape.isNegate());
        assertEquals(TargetingPredicateType.CATEGORY_SPEND_VALUE, coralShape.getTargetingPredicateType());
        assertEquals(category, coralShape.getAttributes().get(CATEGORY_KEY));
        assertEquals(comparison.toString(), coralShape.getAttributes().get(COMPARISON_KEY));
        assertEquals(Integer.toString(value), coralShape.getAttributes().get(VALUE_KEY));
    }

    @Test
    public void toCoral_primePredicate_returnsCoralShape() {
        // GIVEN
        String primeBenefit = Benefit.MOM_DISCOUNT;
        TargetingPredicate predicate = new PrimeBenefitTargetingPredicate(primeBenefit, false);

        // WHEN
        com.amazon.ata.advertising.service.model.TargetingPredicate coralShape = TargetingPredicateTranslator.toCoral(predicate);

        // THEN
        assertFalse(coralShape.isNegate());
        assertEquals(TargetingPredicateType.PRIME_BENEFIT, coralShape.getTargetingPredicateType());
        assertEquals(primeBenefit, coralShape.getAttributes().get(BENEFIT_KEY));
    }

    @Test
    public void toCoral_parentPredicate_returnsCoralShape() {
        // GIVEN
        TargetingPredicate predicate = new ParentPredicate(true);

        // WHEN
        com.amazon.ata.advertising.service.model.TargetingPredicate coralShape = TargetingPredicateTranslator.toCoral(predicate);

        // THEN
        assertTrue(coralShape.isNegate());
        assertEquals(TargetingPredicateType.PARENT, coralShape.getTargetingPredicateType());
    }

    @Test
    public void toCoral_recognizedPredicate_returnsCoralShape() {
        // GIVEN
        TargetingPredicate predicate = new RecognizedTargetingPredicate(false);

        // WHEN
        com.amazon.ata.advertising.service.model.TargetingPredicate coralShape = TargetingPredicateTranslator.toCoral(predicate);

        // THEN
        assertFalse(coralShape.isNegate());
        assertEquals(TargetingPredicateType.RECOGNIZED, coralShape.getTargetingPredicateType());
    }
}
