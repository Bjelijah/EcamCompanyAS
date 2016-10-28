package com.howell.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class EncryptPassword {
	public static String getEncryptPassword(String password) {
        byte[] key = { 0x48, 0x4F, 0x57, 0x45, 0x4C, 0x4C, 0x4B, 0x45 };
        byte[] iv = { 0x48, 0x4F, 0x57, 0x45, 0x4C, 0x4C, 0x56, 0x49 };
        byte[] rdKey = RandomBytes.getRandombyte();
        byte[] rdIv = RandomBytes.getRandombyte();
        String DES2Password = null;
        try {
            String MD5Password = MD5.getMD5(password);
            String hexKey = HEXTranslate.getHexString(rdKey);
            String hexIv = HEXTranslate.getHexString(rdIv);
            String DES1Password = DES.CBCEncrypt(MD5Password, rdKey, rdIv);
            DES2Password = DES.CBCEncrypt(hexKey + hexIv + DES1Password, key,
                    iv);
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return DES2Password;
    }
	
}

class MD5 {
    public static final String getMD5(String str)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        String newstr;
        try {
            newstr = HEXTranslate
                    .getHexString(md5.digest(str.getBytes("utf-8")));
            return newstr;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}

class RandomBytes {
    public static byte[] getRandombyte() {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 8; i++) {
            int is = random.nextInt(9);
            sb.append(is);
        }
        return sb.toString().getBytes();
    }
}

class DES {
    public static String CBCEncrypt(String data, byte[] key, byte[] iv) {
        try {
            DESKeySpec dks = new DESKeySpec(key);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance("DES/CBC/NoPadding");
            IvParameterSpec param = new IvParameterSpec(iv);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, param);
            byte encryptedData[] = cipher.doFinal(data.getBytes());
            return HEXTranslate.getHexString(encryptedData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

class HEXTranslate {
    public static String getHexString(byte[] buf) throws Exception {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    public static byte[] getByteArray(String hexString) {
        return new BigInteger(hexString, 16).toByteArray();
    }
}
