package com.lemon.doctorpointcollector.entity.converter;


import com.lemon.androidlibs.concurrent.Converter;
import com.lemon.doctorpointcollector.entity.Designation;

import java.util.ArrayList;
import com.lemon.androidlibs.utility.Item;
import java.util.List;

/**
 * Created by lemon on 3/24/2018.
 */

@SuppressWarnings({"DefaultFileTemplate", "unused", "FieldCanBeLocal"})
public class DesignationConverter implements Converter<Designation,Item> {
    @Override
    public List<Item> convert(List<Designation> items) {
        List<Item> itemList=new ArrayList<>();
        for(Designation designation:items)
            itemList.add(convert(designation));
        return itemList;
    }

    @Override
    public Item convert(Designation item) {
        return new Item(item.getId(), item.getDesignation(),"PK:"+item.getId()+"  EmployeeType:"+item.getEmployeeType());
    }
}
