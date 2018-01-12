package org.tlh.message.utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 对称加密：加密和解密使用的是相同的密钥
 * @author ping
 *
 */
public class DesUtil {
	
	/**
	 * 生成密钥 
	 * <br>DES 密钥长度为64位 实际只有56位参与
	 * <br>3DES是DES向AES过渡的加密算法，它使用3条56的密钥对数据进行3次加密
	 * <br>AED 密钥长度可以为128、192和256位
	 * @param type 加密的方式
	 * @return
	 * @throws Exception 
	 */
	public static String getKeyDES(DesType type) throws Exception{
		KeyGenerator keyGenerator=KeyGenerator.getInstance(type.name());
		switch (type) {
		case DES:
				keyGenerator.init(56);
			break;
		case AES:
				keyGenerator.init(128);
			break;
		}
		SecretKey secretKey = keyGenerator.generateKey();
		return Base64Util.byte2base64(secretKey.getEncoded());
	}
	
	/**
	 * 加载密钥
	 * @param base64
	 * @param type
	 * @return
	 */
	public static SecretKey loadKeyDES(String base64,DesType type)throws Exception{
		byte[] datas = Base64Util.base642byte(base64);
		SecretKey secretKey=new SecretKeySpec(datas, type.name());
		return secretKey;
	}

	/**
	 * 加密
	 * @param source
	 * @param secretKey
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptDES(byte[] source,SecretKey secretKey,DesType type) throws Exception{
		Cipher cipher=Cipher.getInstance(type.name());
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		return cipher.doFinal(source);
	}
	
	/**
	 * 解密
	 * @param source
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptDES(byte[] source,SecretKey key,DesType type) throws Exception{
		Cipher cipher=Cipher.getInstance(type.name());
		cipher.init(Cipher.DECRYPT_MODE, key);
		return cipher.doFinal(source);
	}
	
	public enum DesType{
		DES,AES;
	}
	
}
