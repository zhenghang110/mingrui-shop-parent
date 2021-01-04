package com.baidu.shop.utils;

import org.springframework.beans.BeanUtils;
public class BaiduBeanUtil {

    public static <T> T copyProperties(Object source,Class<T> clazz){

        try {
            T t = clazz.newInstance();
            BeanUtils.copyProperties(source,t);
            return t;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }
}
