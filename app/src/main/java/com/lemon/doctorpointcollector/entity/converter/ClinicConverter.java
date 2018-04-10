package com.lemon.doctorpointcollector.entity.converter;

import com.lemon.androidlibs.concurrent.AbstractConverter;
import com.lemon.androidlibs.utility.item.Item;
import com.lemon.doctorpointcollector.entity.Clinic;

/**
 * Created by lemon on 4/9/2018.
 */

public class ClinicConverter extends AbstractConverter<Clinic,Item>{
    @Override
    public Item convert(Clinic item) {
        return new Item(item.getId(),item.getName(),item.getClinicType());
    }
}
