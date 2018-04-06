package com.lemon.doctorpointcollector.entity.converter;

import com.lemon.androidlibs.utility.recycler.Item;
import com.lemon.androidlibs.concurrent.AbstractConverter;
import com.lemon.doctorpointcollector.entity.Diseases;


/**
 * Created by lemon on 3/24/2018.
 */
@SuppressWarnings({"DefaultFileTemplate", "unused", "FieldCanBeLocal"})
public class DiseaseConverter extends AbstractConverter<Diseases,Item> {
    @Override
    public Item convert(Diseases item) {
        return new Item(item.getId(), item.getDisease(), item.getDiseaseType());
    }
}
