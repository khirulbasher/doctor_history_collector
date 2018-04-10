package com.lemon.doctorpointcollector.entity.converter;

import com.lemon.androidlibs.concurrent.AbstractConverter;
import com.lemon.androidlibs.utility.item.Item;
import com.lemon.doctorpointcollector.entity.Hospital;

/**
 * Created by lemon on 4/9/2018.
 */

@SuppressWarnings("DefaultFileTemplate")
public class HospitalConverter extends AbstractConverter<Hospital,Item> {
    @Override
    public Item convert(Hospital item) {
        return new Item(item.getId(),item.getName(),item.getHospitalType());
    }
}
