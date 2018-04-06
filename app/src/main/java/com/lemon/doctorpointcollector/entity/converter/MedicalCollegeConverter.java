package com.lemon.doctorpointcollector.entity.converter;

import com.lemon.androidlibs.concurrent.AbstractConverter;
import com.lemon.androidlibs.utility.recycler.Item;
import com.lemon.doctorpointcollector.entity.MedicalCollege;

/**
 * Created by lemon on 4/6/2018.
 */

public class MedicalCollegeConverter extends AbstractConverter<MedicalCollege,Item> {
    @Override
    public Item convert(MedicalCollege item) {
        return new Item(item.getId(),item.getName(),item.getMedicalCollegeType());
    }
}
