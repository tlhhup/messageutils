package org.tlh.message.utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * �ԳƼ��ܣ����ܺͽ���ʹ�õ�����ͬ����Կ
 * @author ping
 *
 */
public class DesUtil {
	
	/**
	 * ������Կ 
	 * <br>DES ��Կ����Ϊ64λ ʵ��ֻ��56λ����
	 * <br>3DES��DES��AES���ɵļ����㷨����ʹ��3��56����Կ�����ݽ���3�μ���
	 * <br>AED ��Կ���ȿ���Ϊ128��192��256λ
	 * @param type ���ܵķ�ʽ
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
	 * ������Կ
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
	 * ����
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
	 * ����
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
