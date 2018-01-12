package org.tlh.message.utils;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

/**
 * 在非对称加密中使用的主要算法有：RSA、Elgamal、背包算法、Rabin、D-H、ECC（椭圆曲线加密算法）等。
 * @author ping
 *
 */
public class RsaUtil {

	/**
	 * 生成密钥对
	 * @return
	 * @throws Exception
	 */
	public static KeyPair getKeyPair() throws Exception{
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		keyPairGenerator.initialize(512);
		return keyPairGenerator.generateKeyPair();
	}
	
	/**
	 * 获取公钥
	 * @param keyPair
	 * @return
	 * @throws Exception
	 */
	public static String getPublicKey(KeyPair keyPair) throws Exception{
		PublicKey publicKey = keyPair.getPublic();
		byte[] encoded = publicKey.getEncoded();
		return Base64Util.byte2base64(encoded);
	}
	
	/**
	 * 获取公钥
	 * @param publicKey
	 * @return
	 * @throws Exception
	 */
	public static PublicKey getPublicKey(String publicKey) throws Exception{
		byte[] datas = Base64Util.base642byte(publicKey);
		X509EncodedKeySpec keySpec=new X509EncodedKeySpec(datas);
		KeyFactory keyFactory=KeyFactory.getInstance("RSA");
		return keyFactory.generatePublic(keySpec);
	}
	
	/**
	 * 获取私钥
	 * @param keyPair
	 * @return
	 * @throws Exception
	 */
	public static String getPrivateKey(KeyPair keyPair) throws Exception{
		PrivateKey privateKey = keyPair.getPrivate();
		byte[] encoded = privateKey.getEncoded();
		return Base64Util.byte2base64(encoded);
	}
	
	/**
	 * 获取私钥
	 * @param privateKey
	 * @return
	 * @throws Exception
	 */
	public static PrivateKey getPrivateKey(String privateKey) throws Exception{
		byte[] datas = Base64Util.base642byte(privateKey);
		PKCS8EncodedKeySpec keySpec=new PKCS8EncodedKeySpec(datas);
		KeyFactory keyFactory=KeyFactory.getInstance("RSA");
		return keyFactory.generatePrivate(keySpec);
	}
	
	/**
	 * 公钥加密
	 * @param content
	 * @param publicKey
	 * @return
	 */
	public static byte[] publicEncrypt(byte[] content,PublicKey publicKey)throws Exception{
		Cipher cipher=Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		return cipher.doFinal(content);
	}
	
	/**
	 * 私钥解密
	 * @param content
	 * @param privateKey
	 * @return
	 * @throws Exception
	 */
	public static byte[] privateDecrypt(byte[] content,PrivateKey privateKey) throws Exception{
		Cipher cipher=Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		return cipher.doFinal(content);
	}
	
}
