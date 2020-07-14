/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sitekick.utils;

import java.util.Objects;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Gia Bảo Hoàng
 */
public class TextUtils {

    public static boolean isNullOrEmptyOrBlank(String text) {
        return isNull(text) || isEmpty(text) || isBlank(text);
    }

    public static boolean isNull(Object obj) {
        return Objects.isNull(obj);
    }

    public static boolean isEmpty(String text) {
        return StringUtils.isEmpty(text);
    }

    public static boolean isBlank(String text) {
        return StringUtils.isBlank(text);
    }
}
