package com.amazon.ata.advertising.service.targeting.predicate;

import com.amazon.ata.advertising.service.exceptions.AdvertisementServiceException;
//import com.amazon.ata.advertising.service.dependency.DaggerGeneratedCoralComponent;
import com.amazon.ata.advertising.service.dependency.DaggerLambdaComponent;
import com.amazon.ata.advertising.service.dependency.LambdaComponent;
import com.amazon.ata.advertising.service.dependency.TargetingPredicateInjector;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class to convert a list of the complex type TargetingPredicate to a string and vice-versa.
 */
public class TargetingPredicateTypeConverter implements DynamoDBTypeConverter<String, List<TargetingPredicate>> {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * Serializes the passed predicate list into a String. Each member is serialized separately so that the Jackson
     * annotation (@JsonTypeInfo) can live at the abstract TargetingPredicate class, rather than annotating each
     * subclass.
     * information
     * @param predicateList - a list of TargetingPredicates that will be converted to a String value
     * @return The serialized string. "[]" in the case of an empty list.
     */
    @Override
    public String convert(List<TargetingPredicate> predicateList) {
        return new StringBuffer()
                .append("[")
                .append(predicateList.stream()
                        .map(this::getSerializePredicateFunction).collect(Collectors.joining(",")))
                .append("]").toString();
    }

    private String getSerializePredicateFunction(TargetingPredicate predicate) {
        try {
            return MAPPER.writeValueAsString(predicate);
        } catch (IOException e) {
            throw new AdvertisementServiceException("Unable to convert the predicate to a String. " +
                    "Object: " + predicate, e);
        }
    }

    @Override
    public List<TargetingPredicate> unconvert(String value) {
        LambdaComponent component = DaggerLambdaComponent.create();
        TargetingPredicateInjector injector = component.getTargetingPredicateInjector();
        try {
            final List<TargetingPredicate> predicates = MAPPER.readValue(value,
                    new TypeReference<List<TargetingPredicate>>() { });
            for (TargetingPredicate predicate : predicates) {
                injector.inject(predicate);
            }
            return predicates;
        } catch (IOException e) {
            throw new AdvertisementServiceException("Unable to convert the String value to a list of targeting " +
                    "predicates. String: " + value, e);
        }
    }
}
