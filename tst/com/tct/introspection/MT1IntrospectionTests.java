package com.tct.introspection;

import com.amazon.ata.test.reflect.ClassQuery;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import java.lang.reflect.Field;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("MT01-Loop")
public class MT1IntrospectionTests {
    private static final String IMPLEMENTED_STREAMS_FIELD_NAME = "IMPLEMENTED_STREAMS";

    @Test
    public void addTargetingGroupActivity_convertedLoopToStream_setImplementedStreamsFlag()
        throws IllegalAccessException {
        Class<?> addTargetingGroupActivity = ClassQuery.inExactPackage("com.amazon.ata.advertising.service.activity")
            .withExactSimpleName("AddTargetingGroupActivity")
            .findClassOrFail();

        Field implementedStreamsFlag = Stream.of(addTargetingGroupActivity.getDeclaredFields())
            .filter(f -> f.getName().equals(IMPLEMENTED_STREAMS_FIELD_NAME))
            .findFirst()
            .orElseThrow(() -> new AssertionFailedError(
                String.format("Expected a field named %s in class %s, was it deleted?",
                    IMPLEMENTED_STREAMS_FIELD_NAME,
                    addTargetingGroupActivity)));

        // since it's a static field we can access it without an instance of the class
        // by passing in null
        assertTrue(implementedStreamsFlag.getBoolean(null),
            String.format("Expected the %s field to be true. " +
                "Please set it to true after you've updated the %s class to use streams.",
                IMPLEMENTED_STREAMS_FIELD_NAME,
                addTargetingGroupActivity));
    }

    @Test
    public void targetingEvaluator_convertedLoopToStream_setImplementedStreamsFlag()
        throws IllegalAccessException {
        Class<?> targetingEvaluator = ClassQuery.inExactPackage("com.amazon.ata.advertising.service.targeting")
            .withExactSimpleName("TargetingEvaluator")
            .findClassOrFail();

        Field implementedStreamsFlag = Stream.of(targetingEvaluator.getDeclaredFields())
            .filter(f -> f.getName().equals(IMPLEMENTED_STREAMS_FIELD_NAME))
            .findFirst()
            .orElseThrow(() -> new AssertionFailedError(
                String.format("Expected a field named %s in class %s, has it been deleted?",
                    IMPLEMENTED_STREAMS_FIELD_NAME,
                    targetingEvaluator)));

        // since it's a static field we can access it without an instance of the class
        // by passing in null
        assertTrue(implementedStreamsFlag.getBoolean(null),
            String.format("Expected the %s field to be true. " +
                    "Please set it to true after you've updated the %s class to use streams.",
                IMPLEMENTED_STREAMS_FIELD_NAME,
                targetingEvaluator));
    }
}
