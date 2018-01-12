package org.tlh.message.utils;

import java.security.MessageDigest;

/**
 * ����ժҪ������
 * @author ping
 */
public class MessageDigestUtil {

	/**
	 * md5����ժҪ
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public static byte[] md5Hex(String content) throws Exception{
		MessageDigest digest=MessageDigest.getInstance("MD5");
		byte[] datas = digest.digest(content.getBytes("UTF-8"));
		return datas;
	}
	
	/**
	 * ͨ��sha-1���ɵ�ժҪ��Ϣ����Ϊ160λ
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public static byte[] sha1Hex(String content) throws Exception{
		MessageDigest digest=MessageDigest.getInstance("SHA-1");
		byte[] datas = digest.digest(content.getBytes("UTF-8"));
		return datas;
	}
	
}
