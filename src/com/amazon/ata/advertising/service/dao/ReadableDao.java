package com.amazon.ata.advertising.service.dao;

/**
 * Get data from a datasource.
 * @param <I> The input type needed to retrieve an object.  This may often by a query object.
 * @param <O> The type to be retrieved from the datasource.
 */
public interface ReadableDao<I, O> {

    /**
     * Get an object from the datasource.
     * @param inputQuery The information necessary to retrieve an object.
     * @return The object queried for.
     */
    O get(I inputQuery);
}
