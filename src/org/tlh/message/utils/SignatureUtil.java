package org.tlh.message.utils;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

/**
 * 通过java提供的api实现数字签名和验证
 * @author ping
 *
 */
public class SignatureUtil {

	public static byte[] sign(byte[] content,PrivateKey privateKey,SignatureType type) throws Exception{
		Signature signature=Signature.getInstance(type.name);
		signature.initSign(privateKey);
		signature.update(content);
		return signature.sign();
	}
	
	public static boolean verify(byte[] content,byte[] sign,PublicKey publicKey,SignatureType type) throws Exception{
		Signature signature=Signature.getInstance(type.name);
		signature.initVerify(publicKey);
		signature.update(content);
		return signature.verify(sign);
	}
	
	public enum SignatureType{
		MD5WITHRSA("MD5withRSA"),SHA1WITHRSA("SHA1withRSA");
		
		private String name;
		
		private SignatureType(String name) {
			this.name=name;
		}
		
		public String getName() {
			return name;
		}
	}
	
}
