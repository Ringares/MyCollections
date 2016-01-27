package com.ring.tools.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by ring
 * on 15/12/17.
 */
public class CommonUtils {

    public static String md5(String s) throws NoSuchAlgorithmException {
        if (s == null)
            return null;
        StringBuffer stringbuffer;
        byte abyte0[] = s.getBytes();
        MessageDigest messagedigest = MessageDigest.getInstance("MD5");
        messagedigest.reset();
        messagedigest.update(abyte0);
        byte abyte1[] = messagedigest.digest();
        stringbuffer = new StringBuffer();
        for (int i = 0; i < abyte1.length; i++)
            stringbuffer.append(String.format("%02X",
                    new Object[]{Byte.valueOf(abyte1[i])}));

        return stringbuffer.toString();

    }
}
