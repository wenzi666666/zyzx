package net.tfedu.zhl.cloud.resource.integration.util;


import java.security.MessageDigest;

/**
 * 
 * copyRight@知好乐教育技术北京有限公司
 * @author wangwr
 * @date  2015-6-23
 * @version v1.0
 */
public class CloudClientMD5 {
	// upflag大小写标志，1大写
	public static String Md5(String plainText, int num, int upflag) {
		StringBuffer buf = new StringBuffer();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();
			int i;
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (num == 16) {
			if (upflag == 1) {
				return buf.toString().substring(8, 24).toUpperCase();
			} else {
				return buf.toString().substring(8, 24);
			}
		} else {
			if (upflag == 1) {
				return buf.toString().toUpperCase();
			} else {
				return buf.toString();
			}
		}

	}
}