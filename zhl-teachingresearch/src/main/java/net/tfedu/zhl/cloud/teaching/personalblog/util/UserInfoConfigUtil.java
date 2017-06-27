package net.tfedu.zhl.cloud.teaching.personalblog.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import net.tfedu.zhl.cloud.teaching.personalblog.model.CloudPlatformUsrInfo;
import net.tfedu.zhl.cloud.teaching.personalblog.model.Constant4AppIntegral;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.helper.httpclient.HttpClientUtils;
import net.tfedu.zhl.helper.sign.SignUtil;

/**
 * 用户信息工具类
 * 
 * @author wangwr
 *
 */
public class UserInfoConfigUtil {

	/**
	 * //重置list中的用戶學段\學科\學校\頭像信息
	 * 
	 * @param cloudPlatFormLocal
	 * @param cloudPlatForm
	 * @param list
	 * @throws Exception
	 */
	public static void resetUserImage(String cloudPlatFormLocal, String cloudPlatForm, List<Map<String, Object>> list)
			throws Exception {

		if (list != null) {

			Map<Long, Object> map = new HashMap<Long, Object>();// 用于记录查询结果

			if (map != null) {

				StringBuffer userIds = new StringBuffer();
				for (Iterator<Map<String, Object>> iterator = list.iterator(); iterator.hasNext();) {
					Map<String, Object> _map = iterator.next();

					if(_map!=null){
						
						
						
						String temp = (_map.containsKey("userid")? 
								_map.get("userid").toString():
								_map.get("userId").toString()
								)
								+ ",";
						if (!userIds.toString().contains(temp)) {
							userIds.append(temp);
						}
					}

				}
				if (StringUtils.isNotEmpty(userIds.toString())) {
					String ids = userIds.substring(0, userIds.length() - 1);
					String url = cloudPlatFormLocal + "/base_getUserInfo.action";

					String result = createURLAndGetResult(url, "userIds", ids);

					ResultJSON json = JSONObject.parseObject(result, ResultJSON.class);

					if ("OK".equalsIgnoreCase(json.getCode()) && json.getData() != null) {

						JSONArray array = (JSONArray) json.getData();

						CloudPlatformUsrInfo userinfo = null;
						for (int i = 0; i < array.size(); i++) {

							userinfo = JSONObject.parseObject(JSONObject.toJSONString(array.get(i)),
									CloudPlatformUsrInfo.class);

							map.put(userinfo.getId(), userinfo);
						}

						if (list != null) {

							for (Iterator<Map<String, Object>> iterator = list.iterator(); iterator.hasNext();) {
								Map<String, Object> ls = iterator.next();
								CloudPlatformUsrInfo info = (CloudPlatformUsrInfo) map.get(ls.get("userid"));

								if (info != null) {

									ls.put("schoolName", info.getSchoolName());
									ls.put("termName", getTermName(info.getTermId()));
									ls.put("subjectName", info.getSubjects() == null || info.getSubjects().size() == 0
											? "" : info.getSubjects().get(0).getSubjectName());
									ls.put("trueName", info.getTrueName());
									ls.put("userImage", info.getUserImage());
								}

							}
						}
					} else {
						System.out.println(json.getMessage() + ":" + url);
					}

				}
			}
		}
	}

	private static String getTermName(Long termId) {
		return 1 == termId ? "小学" : 2 == termId ? "初中" : 3 == termId ? "高中" : "其他 ";
	}

	private static String createURLAndGetResult(String url, String param_key, String param_value) throws Exception {

		return createURLAndGetResult(url, new String[] { param_key }, new String[] { param_value });

	}

	private static String createURLAndGetResult(String url, String[] param_key, String[] param_value) throws Exception {
		String sign = SignUtil.createSignMap(param_key, param_value, Constant4AppIntegral.APPKEY_4_TEACHING_USERINFO);

		url += "?sign=" + sign;

		for (int i = 0; i < param_value.length; i++) {
			url += "&" + param_key[i] + "=" + param_value[i];
		}

		System.out.println("url---:" + url);
		return HttpClientUtils.doGET(url);

	}

}
