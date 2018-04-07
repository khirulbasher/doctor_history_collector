package com.lemon.androidlibs.utility;

import com.lemon.androidlibs.utility.item.Item;
import com.lemon.androidlibs.utility.item.ItemDescription;
import com.lemon.androidlibs.utility.item.ItemTitle;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import io.realm.RealmList;

/**
 * Created by lemon on 4/7/2018.
 */

@SuppressWarnings({"unchecked", "DefaultFileTemplate", "WeakerAccess", "ConstantConditions"})
public class Utility {
    public static Object createObjectFromProxy(Class clazz, Object proxy) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> objClass=Class.forName(clazz.getName());
        Constructor<?> constructor=objClass.getConstructor();
        Object obj=constructor.newInstance();
        Field[] fields=clazz.getDeclaredFields();
        for(Field field:fields) {
            try {
                String name=field.getName();
                Class<?> type=field.getType();
                String setter=makeSetterOrGetter(name,type,true);
                String getter=makeSetterOrGetter(name,type,false);
                Method setterMethod=clazz.getMethod(setter,type);
                Method getterMethod=clazz.getMethod(getter);
                setterMethod.invoke(obj,getterMethod.invoke(proxy));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return obj;
    }

    public static String makeSetterOrGetter(String name, Class<?> type, boolean isSetter) {
        name=""+((char)(name.charAt(0)-'a'+'A'))+name.substring(1);
        return isSetter?"set"+name:(type.getName().equals(Boolean.class.getName())?"is"+name:"get"+name);
    }

    public static List<Item> makeItemList(Object obj) throws Exception {
        List<Item>items =new ArrayList<>();
        Class clazz=obj.getClass();
        Field[] fields=clazz.getDeclaredFields();
        for(Field field:fields) {
            try {
                String name=field.getName();
                Class<?> type=field.getType();
                String getter=makeSetterOrGetter(name,type,false);
                Method getterMethod=clazz.getMethod(getter);
                if(type.getName().equals(RealmList.class.getName())) {
                    items.addAll(convertToItemList(new ArrayList<Object>((Collection<? extends Item>) getterMethod.invoke(obj))));
                }
                items.add(new Item(name, type.getName().equals(Boolean.class.getName())?(getterMethod.invoke(obj)==null||!((Boolean)getterMethod.invoke(obj))?"Not Yet Persisted":"Persited On Cloud"): (String) getterMethod.invoke(obj)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return items;
    }

    private static Collection<? extends Item> convertToItemList(ArrayList<Object> items) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        if(items.size()<=0) return new ArrayList<>();
        Class clazz=items.get(0).getClass();
        Field[] fields=clazz.getDeclaredFields();
        String title=null;
        String description=null;
        for(Field field:fields) {
            if(field.getAnnotation(ItemTitle.class)!=null)
                title=field.getName();
            else if(field.getAnnotation(ItemDescription.class)!=null)
                description=field.getName();
        }

        if(title==null || description==null)
            return new ArrayList<>();
        List<Item> itemList=new ArrayList<>();
        itemList.add(new Item("",""));
        itemList.add(new Item(""+clazz.getSimpleName()+" List",""));
        String titleGet=makeSetterOrGetter(title,String.class,false);
        String descriptionGet=makeSetterOrGetter(description,String.class,false);
        Method titleMethod=clazz.getMethod(titleGet);
        Method descriptionMethod=clazz.getMethod(descriptionGet);
        for(Object obj:items) {
            itemList.add(new Item((String) titleMethod.invoke(obj),(String) descriptionMethod.invoke(obj)));
        }
        return itemList;
    }
}
