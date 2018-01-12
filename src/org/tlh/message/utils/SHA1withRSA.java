package org.tlh.message.utils;

import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

/**
 * ����ǩ������ͨ�ŵ�����ͨ������ժҪ֮����ͨ��˽Կ���м���
 * @author ping
 */
public class SHA1withRSA {

	/**
	 * ǩ��
	 * @param content
	 * @param privateKey
	 * @return
	 * @throws Exception
	 */
	public static byte[] sign(byte[] content,PrivateKey privateKey) throws Exception{
		MessageDigest digest=MessageDigest.getInstance("SHA-1");
		byte[] datas = digest.digest(content);
		Cipher cipher=Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);
		return cipher.doFinal(datas);
	}
	
	//���õ�����Ϣ����ժҪ��ͨ����Կ��ǩ�����н��ܵõ�ժҪ��Ϣ���ȶ�ժҪ��Ϣ
	public static boolean verify(byte[] content,byte[] sign,PublicKey publicKey) throws Exception{
		MessageDigest digest=MessageDigest.getInstance("SHA-1");
		byte[] datas = digest.digest(content);
		//������ǩ�����н���õ�ժҪ��Ϣ
		Cipher cipher=Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, publicKey);
		byte[] signData = cipher.doFinal(sign);
		if(Base64Util.byte2base64(datas).equals(Base64Util.byte2base64(signData))){
			return true;
		}else{
			return false;
		}
	}
	
}
