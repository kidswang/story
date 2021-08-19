package com.waiwaiwai.story.util;

import com.waiwaiwai.story.exception.BeanUtilsException;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.CollectionUtils;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

/**
 * @author mhwangzl
 * @date 2021/8/18 12:05
 * @description
 */
public class BeanUtils extends org.springframework.beans.BeanUtils{

    /**
     * 通过json数据格式转换实现复制list
     *
     * @param list
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> copyList(List<?> list, Class<T> clazz) {
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList<>();
        }
        return JsonUtils.toList(JsonUtils.toJson(list), clazz);
    }

    /**
     * 集合数据的拷贝
     *
     * @param sources: 数据源类
     * @param target:  目标类::new(eg: UserVO::new)
     * @return
     */
    public static <S, T> List<T> copyListProperties(List<S> sources, Supplier<T> target) {
        return copyListProperties(sources, target, null);
    }

    /**
     * 带回调函数的集合数据的拷贝（可自定义字段拷贝规则）
     * @param sources: 数据源类
     * @param target: 目标类::new(eg: UserVO::new)
     * @param callBack: 回调函数
     * @return
     */
    public static <S, T> List<T> copyListProperties(List<S> sources, Supplier<T> target, ColaBeanUtilsCallBack<S, T> callBack) {
        List<T> list = new ArrayList<>(sources.size());
        for (S source : sources) {
            T t = target.get();
            copyProperties(source, t);
            if (callBack != null) {
                // 回调
                callBack.callBack(source, t);
            }
            list.add(t);
        }
        return list;
    }

    /**
     * 封装{@link org.springframework.beans.BeanUtils}，惯用与直接将转换结果返回
     *
     * <pre>
     *      UserBean userBean = new UserBean("username");
     *      return BeanUtil.transform(UserDTO.class, userBean);
     * </pre>
     */
    public static <T> T transform(Object src, Class<T> clazz) {
        return transform(src, clazz, (String[])null);
    }

    public static <T> T transform(Object src, Class<T> clazz, String... ignoreProperties) {
        if (src == null) {
            return null;
        }
        T instance;
        try {
            instance = clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new BeanUtilsException(e);
        }
        copyProperties(src, instance, ignoreProperties);
        return instance;
    }

    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

}
