package com.ring.tools.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ring
 * on 15/12/17.
 */
public class ValidUtils {

    /**
     * 判断email是否合法
     */
    public static boolean checkEmailValid(String loginStr) {
        String regEx = "^([a-z0-9_A-Z]+[-|\\.]?)+[a-z0-9_A-Z]@([a-z0-9_A-Z]+(-[a-z0-9_A-Z]+)?\\.)+[a-zA-Z_]{2,}$";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(loginStr);
        return m.matches();// boolean
    }

    /**
     * 判断密码是否合法 ,6-20位字母数字组合 flw
     */
    public static boolean checkPassWordValid(String loginStr) {
        // String regEx = "(\\S){6,20}";
        String regEx = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=\\S+$).{6,20}$";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(loginStr);
        return m.matches();// boolean
    }

    /**
     * 判断手机号是否合法 11位数字，不做号段检测，避免出现新增号段无法校验的情况
     */
    public static boolean checkPhoneValid(String phoneNumber) {
        boolean isValid = false;
        phoneNumber = phoneNumber.trim().replaceAll(" ", "").replaceAll("\\+86", "");
        // 只验证11位数字
        String expression = "^\\d{11}$";
        CharSequence inputStr = phoneNumber;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {// || matcher2.matches()
            isValid = true;
        }
        return isValid;
    }
}
