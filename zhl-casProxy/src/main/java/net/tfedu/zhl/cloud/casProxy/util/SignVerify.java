package net.tfedu.zhl.cloud.casProxy.util;

import net.tfedu.zhl.fileservice.MD5;

/**
 * 校验对象 copyRight@知好乐教育技术北京有限公司
 * 
 * @author jiys
 * @date 2016-11-18
 * @version v2.7
 */
public class SignVerify {

	public static String separator = "〖zhl〗";

	/**
	 * 生成校验字符串
	 * 
	 * @param uid
	 *            用户的uuid
	 * @param appId
	 *            知好乐提供的appId
	 * @param appKey
	 *            知好乐提供的appKey
	 * @return
	 */
	public static String createSignVerify(final String uid,
			final Integer appId, String appKey) {
		return MD5.getMD5Str(uid + separator + appId + separator + appKey);
	}

	/**
	 * 生成校验字符串
	 * 
	 * @param uid
	 *            用户的uuid
	 * @param timestamp
	 *            时间戳
	 * @param appId
	 *            知好乐提供的appId
	 * @param appKey
	 *            知好乐提供的appKey
	 * @return
	 */
	public static String createSignVerify(final String uid,
			final Integer appId, String timestamp, String appKey) {
		return MD5.getMD5Str(uid + separator + timestamp + separator + appId
				+ separator + appKey);
	}
}
