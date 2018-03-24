package com.lemon.doctorpointcollector.concurrent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lemon on 3/24/2018.
 */
@SuppressWarnings({"DefaultFileTemplate", "unused", "FieldCanBeLocal"})
public abstract class AbstractConverter<S,R> implements Converter<S,R> {
    @Override
    public List<R> convert(List<S> items) {
        List<R> rs=new ArrayList<>();
        for(S s:items)
            rs.add(convert(s));
        return rs;
    }
}
