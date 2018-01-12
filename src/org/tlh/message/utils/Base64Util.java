package org.tlh.message.utils;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

/**
 * Base64µÄ±àÂëºÍ½âÂë
 * @author ping
 *
 */
public class Base64Util {

	public static String byte2base64(byte[] datas){
		Encoder encoder=Base64.getEncoder();
		return encoder.encodeToString(datas);
	}
	
	public static byte[] base642byte(String base64){
		Decoder decoder = Base64.getDecoder();
		return decoder.decode(base64);
	}
	
}
