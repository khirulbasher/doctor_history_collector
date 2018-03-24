package com.lemon.doctorpointcollector.entity.converter;

import com.lemon.doctorpointcollector.concurrent.AbstractConverter;
import com.lemon.doctorpointcollector.entity.Diseases;
import com.lemon.doctorpointcollector.utility.util.Item;

/**
 * Created by lemon on 3/24/2018.
 */
@SuppressWarnings({"DefaultFileTemplate", "unused", "FieldCanBeLocal"})
public class DiseaseConverter extends AbstractConverter<Diseases,Item> {
    @Override
    public Item convert(Diseases item) {
        return new Item(item.getId(), item.getDisease(),"Primary Key:"+item.getId());
    }
}
