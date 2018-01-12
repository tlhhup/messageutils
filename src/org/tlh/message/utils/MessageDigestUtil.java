package org.tlh.message.utils;

import java.security.MessageDigest;

/**
 * 数字摘要工具类
 * @author ping
 */
public class MessageDigestUtil {

	/**
	 * md5数字摘要
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
	 * 通过sha-1生成的摘要信息长度为160位
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
