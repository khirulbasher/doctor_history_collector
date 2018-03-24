package com.lemon.doctorpointcollector.concurrent;

import java.util.List;

/**
 * <p>
 *     <code>S</code> as Supplier Type
 *     <code>R</code> as Return Type
 * </p>
 * Created by lemon on 3/17/2018.
 */

public interface Converter<S,R> {
    List<R> convert(List<S> items);
    R convert(S item);
}
