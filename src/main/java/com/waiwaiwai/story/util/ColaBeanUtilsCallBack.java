package com.waiwaiwai.story.util;

/**
 * @author mhwangzl
 * @date 2021/8/18 12:06
 * @description
 */
public interface ColaBeanUtilsCallBack<S, T> {

    /**
     * callBack
     * @param t t
     * @param s s
     */
    void callBack(S t, T s);
}
