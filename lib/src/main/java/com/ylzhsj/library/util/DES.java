package com.ylzhsj.library.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import android.util.Base64;

public class DES {
	private static final String CHAR_CODE = "UTF-8";

	// 算法DESede==3DES
	private static final String ALGORITHM = "DESede";

	// 算法名称/加密模式/填充方式
	private static final String TRANSFORMATION = "DESede/CBC/PKCS5Padding";

	// 向量iv,ECB不需要向量iv，CBC需要向量iv
	private static final String IV = "):::::::";

	/**
	 * 加密
	 * 
	 * @param key
	 * @param src
	 * @return
	 * @throws Exception
	 */
//	public static String encryptMode(String key, String src) throws Exception {
//		return encryptModeImpl(key, src.getBytes(CHAR_CODE));
//	}
	public static String encryptMode(String key, String src)  {
		try
		{
			return encryptModeImpl(key, src.getBytes(CHAR_CODE));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}
	
	public static void main(String[] args)
	{
		String data = "WKuqw6hDyrt6f1Zs9w4AbAsPA-i4d57fXwEG9-Kf8QBjR8pZO0jvxhnjFj_uWIyK";
		String key = "a123456789012345678901234567890b";
		decryptMode(key, data);
	}

	/**
	 * 解密
	 * 
	 * @param key
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String decryptMode(String key, String data) //throws Exception 
	{
		data = data.replaceAll("-", "\\+").replaceAll("_", "/");
		int j = data.length() % 2;
		for (int i = 0; i < j; i++) {
			data = data + "=";
		}
		j = data.length() % 4;
		if (j != 0)
			data = data + "==";
		try
		{
			return decryptModeImpl(key, Base64.decode(data.getBytes(CHAR_CODE), Base64.DEFAULT));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return "";
	}

	private static byte[] getKeyByte(String key) throws Exception {
		byte[] data = key.getBytes(CHAR_CODE);
		int len = data.length;
		byte[] newdata = new byte[24];
		System.arraycopy(data, 0, newdata, 0, len > 24 ? 24 : len);
		return newdata;
	}

	private static String encryptModeImpl(String key, byte[] src) {
		try {
			SecretKey deskey = new SecretKeySpec(getKeyByte(key), ALGORITHM);
			IvParameterSpec iv = new IvParameterSpec(IV.getBytes(CHAR_CODE));
			Cipher c1 = Cipher.getInstance(TRANSFORMATION);
			c1.init(Cipher.ENCRYPT_MODE, deskey, iv);
			String en = Base64.encodeToString(c1.doFinal(src), Base64.DEFAULT);
			// 替换特殊字符处理
			en = en.toString().replaceAll("\\+", "-").replaceAll("/", "_").replace("\r", "").replace("\n", "");
			en = en.substring(0, en.length() - 2) + en.substring(en.length() - 2, en.length()).replaceAll("=", "");
			en = en.trim();
			return en;
		} catch (Exception e) {
			//throw new ProcessException("加密数据失败");
		}
		
		return "";
	}

	private static String decryptModeImpl(String key, byte[] src) {
		try {
			SecretKey deskey = new SecretKeySpec(getKeyByte(key), ALGORITHM);
			IvParameterSpec iv = new IvParameterSpec(IV.getBytes(CHAR_CODE));
			Cipher c1 = Cipher.getInstance(TRANSFORMATION);
			c1.init(Cipher.DECRYPT_MODE, deskey, iv);
			byte[] data = c1.doFinal(src);
			return new String(data);
		} catch (Exception e) {
			//throw new ProcessException("解密数据失败");
		}
		return "";
	}

}