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

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Class used to perform translations to and from the service's internal TargetingPredicate representation.
 */
public class TargetingPredicateTranslator {
    private static final String AGE_KEY = "AgeRange";
    private static final String PURCHASE_NUMBER_KEY = "NumberOfPurchases";
    private static final String VALUE_KEY = "Value";
    private static final String BENEFIT_KEY = "Benefit";
    private static final String CATEGORY_KEY = "Category";
    private static final String COMPARISON_KEY = "Comparison";
    private static final String EXCEPTION_MESSAGE = "An %s must be specified with a valid %s in it's attribute Map. " +
            "Valid values include: %s. Value specified was %s.";

    private TargetingPredicateTranslator() {}

    /**
     * Converts from a coral shape to the internal representation of a TargetingPredicate.
     * @param predicate The coral shape
     * @return The internal representation
     */
    public static TargetingPredicate fromCoral(com.amazon.ata.advertising.service.model.TargetingPredicate predicate) {
        TargetingPredicate targetingPredicate;
        switch (predicate.getTargetingPredicateType()) {
            case AGE:
                targetingPredicate = toAgeTargetingPredicate(predicate);
                break;
            case CATEGORY_SPEND_FREQUENCY:
                targetingPredicate = toCategorySpendFrequencyTargetingPredicate(predicate);
                break;
            case CATEGORY_SPEND_VALUE:
                targetingPredicate = toCategorySpendValueTargetingPredicate(predicate);
                break;
            case PARENT:
                targetingPredicate = toParentPredicate(predicate);
                break;
            case PRIME_BENEFIT:
                targetingPredicate = toPrimeBenefitTargetingPredicate(predicate);
                break;
            case RECOGNIZED:
                targetingPredicate = toRecognizedTargetingPredicate(predicate);
                break;
            default:
                throw new AdvertisementClientException(String.format("An unknown predicate type was requested, %s. " +
                                "Valid predicate types are: %s",
                        predicate.getTargetingPredicateType(), Arrays.toString(TargetingPredicateType.values())));
        }
        return targetingPredicate;
    }

    private static AgeTargetingPredicate toAgeTargetingPredicate(
            com.amazon.ata.advertising.service.model.TargetingPredicate predicate
    ) {
        String targetedAgeRange = predicate.getAttributes().get(AGE_KEY);
        if (!Arrays.asList(AgeRange.values()).contains(targetedAgeRange)) {
            throw new AdvertisementClientException(String.format(EXCEPTION_MESSAGE,
                    "AgeTargetingPredicate", AGE_KEY, Arrays.toString(AgeRange.values()), targetedAgeRange));
        }
        return new AgeTargetingPredicate(targetedAgeRange, predicate.isNegate());
    }

    private static CategorySpendFrequencyTargetingPredicate toCategorySpendFrequencyTargetingPredicate(
            com.amazon.ata.advertising.service.model.TargetingPredicate predicate
    ) {
        String predicateName = "CategorySpendFrequencyTargetingPredicate";

        String targetedCategory = toCategory(predicate.getAttributes(), predicateName);
        Comparison comparison = toComparison(predicate.getAttributes(), predicateName);
        Integer numberOfPurchases = toInt(predicate.getAttributes(), PURCHASE_NUMBER_KEY, predicateName);

        return new CategorySpendFrequencyTargetingPredicate(targetedCategory, comparison, numberOfPurchases,
                predicate.isNegate());
    }

    private static CategorySpendValueTargetingPredicate toCategorySpendValueTargetingPredicate(
            com.amazon.ata.advertising.service.model.TargetingPredicate predicate
    ) {
        String predicateName = "CategorySpendValueTargetingPredicate";

        String targetedCategory = toCategory(predicate.getAttributes(), predicateName);
        Comparison comparison = toComparison(predicate.getAttributes(), predicateName);
        Integer value = toInt(predicate.getAttributes(), VALUE_KEY, predicateName);

        return new CategorySpendValueTargetingPredicate(targetedCategory, comparison, value, predicate.isNegate());
    }

    private static ParentPredicate toParentPredicate(com.amazon.ata.advertising.service.model.TargetingPredicate predicate) {
        return new ParentPredicate(predicate.isNegate());
    }

    private static PrimeBenefitTargetingPredicate toPrimeBenefitTargetingPredicate(
            com.amazon.ata.advertising.service.model.TargetingPredicate predicate
    ) {
        String benefit = predicate.getAttributes().get(BENEFIT_KEY);
        if (!Arrays.asList(Benefit.values()).contains(benefit)) {
            throw new AdvertisementClientException(String.format(EXCEPTION_MESSAGE,
                    "PrimeBenefitTargetingPredicate", BENEFIT_KEY, Arrays.toString(Benefit.values()), benefit));
        }

        return new PrimeBenefitTargetingPredicate(benefit, predicate.isNegate());
    }

    private static RecognizedTargetingPredicate toRecognizedTargetingPredicate(
            com.amazon.ata.advertising.service.model.TargetingPredicate predicate
    ) {
        return new RecognizedTargetingPredicate(predicate.isNegate());
    }

    private static String toCategory(Map<String, String> attributes, String predicateName) {
        String targetedCategory = attributes.get(CATEGORY_KEY);
        if (!Arrays.asList(Category.values()).contains(targetedCategory)) {
            throw new AdvertisementClientException(String.format(EXCEPTION_MESSAGE,
                    predicateName, CATEGORY_KEY, Arrays.toString(Category.values()), targetedCategory));
        }
        return targetedCategory;
    }

    private static Comparison toComparison(Map<String, String> attributes, String predicateName) {
        String comparison = attributes.get(COMPARISON_KEY);
        Arrays.stream(Comparison.values())
                .map(Object::toString)
                .filter(value -> value.equals(comparison))
                .findAny()
                .orElseThrow(() -> new AdvertisementClientException(String.format(EXCEPTION_MESSAGE,
                        predicateName, COMPARISON_KEY, Arrays.toString(Comparison.values()), comparison)));

        return Comparison.valueOf(comparison);
    }

    private static Integer toInt(Map<String, String> attribute, String keyName, String predicateName) {
        String value = attribute.get(keyName);
        Integer targetedValue = null;
        try {
            targetedValue = Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new AdvertisementClientException(String.format(EXCEPTION_MESSAGE,
                    predicateName, keyName, "any integer", value));
        }
        return targetedValue;
    }

    /**
     * Convert from an internal representation of a TargetingPredicate to the coral shape.
     * @param targetingPredicate The internal representation
     * @return The coral shape
     */
    public static com.amazon.ata.advertising.service.model.TargetingPredicate toCoral(TargetingPredicate targetingPredicate) {
        return com.amazon.ata.advertising.service.model.TargetingPredicate.builder()
                .withNegate(targetingPredicate.isInverse())
                .withTargetingPredicateType(toType(targetingPredicate))
                .withAttributes(toAttributes(targetingPredicate))
                .build();
    }

    private static TargetingPredicateType toType(TargetingPredicate predicate) {
        TargetingPredicateType type = null;
        if (predicate instanceof AgeTargetingPredicate) {
            type = TargetingPredicateType.AGE;
        } else if (predicate instanceof CategorySpendFrequencyTargetingPredicate) {
            type = TargetingPredicateType.CATEGORY_SPEND_FREQUENCY;
        } else if (predicate instanceof CategorySpendValueTargetingPredicate) {
            type = TargetingPredicateType.CATEGORY_SPEND_VALUE;
        } else if (predicate instanceof ParentPredicate) {
            type = TargetingPredicateType.PARENT;
        } else if (predicate instanceof PrimeBenefitTargetingPredicate) {
            type = TargetingPredicateType.PRIME_BENEFIT;
        } else if (predicate instanceof RecognizedTargetingPredicate) {
            type = TargetingPredicateType.RECOGNIZED;
        }
        return type;
    }

    private static Map<String, String> toAttributes(TargetingPredicate predicate) {
        Map<String, String> attributes = new HashMap<>();
        if (predicate instanceof AgeTargetingPredicate) {
            AgeTargetingPredicate ageTargetingPredicate = (AgeTargetingPredicate) predicate;
            attributes.put(AGE_KEY, ageTargetingPredicate.getTargetedAgeRange());
        } else if (predicate instanceof CategorySpendFrequencyTargetingPredicate) {
            CategorySpendFrequencyTargetingPredicate frequency = (CategorySpendFrequencyTargetingPredicate) predicate;
            attributes.put(CATEGORY_KEY, frequency.getTargetedCategory());
            attributes.put(COMPARISON_KEY, frequency.getComparison().toString());
            attributes.put(PURCHASE_NUMBER_KEY, Integer.toString(frequency.getTargetedNumberOfPurchases()));
        } else if (predicate instanceof CategorySpendValueTargetingPredicate) {
            CategorySpendValueTargetingPredicate spendPredicate = (CategorySpendValueTargetingPredicate) predicate;
            attributes.put(CATEGORY_KEY, spendPredicate.getTargetedCategory());
            attributes.put(COMPARISON_KEY, spendPredicate.getComparison().toString());
            attributes.put(VALUE_KEY, Integer.toString(spendPredicate.getTargetedValue()));
        }  else if (predicate instanceof PrimeBenefitTargetingPredicate) {
            PrimeBenefitTargetingPredicate primePredicate = (PrimeBenefitTargetingPredicate) predicate;
            attributes.put(BENEFIT_KEY, primePredicate.getBenefitToHave());
        }

        return attributes;
    }
}
