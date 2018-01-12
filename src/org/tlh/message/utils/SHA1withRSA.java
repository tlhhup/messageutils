package org.tlh.message.utils;

import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

/**
 * 数字签名：将通信的正文通过数字摘要之后在通过私钥进行加密
 * @author ping
 */
public class SHA1withRSA {

	/**
	 * 签名
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
	
	//将得到的信息进行摘要，通过公钥将签名进行解密得到摘要信息，比对摘要信息
	public static boolean verify(byte[] content,byte[] sign,PublicKey publicKey) throws Exception{
		MessageDigest digest=MessageDigest.getInstance("SHA-1");
		byte[] datas = digest.digest(content);
		//将数字签名进行界面得到摘要信息
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
