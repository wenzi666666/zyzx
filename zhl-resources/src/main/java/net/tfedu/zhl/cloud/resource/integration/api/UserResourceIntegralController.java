package net.tfedu.zhl.cloud.resource.integration.api;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.tfedu.zhl.cloud.resource.intergral.constant.ResourceIntegralConstants;
import net.tfedu.zhl.cloud.resource.intergral.entity.UserResourceIntergral;
import net.tfedu.zhl.cloud.resource.intergral.service.UserResourceIntergralService;
import net.tfedu.zhl.cloud.resource.resourceList.entity.Pagination;
import net.tfedu.zhl.helper.ResultJSON;

@Controller
public class UserResourceIntegralController {
	@Resource
	UserResourceIntergralService userResourceIntergralService;

	/**
	 * 获取当前用户的积分
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "integral_getUserIntegral.action", method = RequestMethod.GET)
	@ResponseBody
	public ResultJSON getUserIntegral(Long userId) throws Exception {
		UserResourceIntergral userResourceIntergral = userResourceIntergralService.getUserIntegral(userId);
		return ResultJSON.getSuccess(userResourceIntergral);
	}

	/**
	 * 当前用户的积分是否充足
	 * 
	 * @param userId
	 * @param sharedUserId
	 * @param sharedAssetId
	 * @param sharedAssetIntegral
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "integral_isUserIntegralEnough.action", method = RequestMethod.GET)
	@ResponseBody
	public ResultJSON getIsUserIntegralEnough(Long userId, Long sharedUserId, Long sharedAssetId,
			Integer sharedAssetIntegral) throws Exception {
		return userResourceIntergralService.isUserIntegralEnough(userId, sharedAssetId, sharedAssetIntegral,
				sharedUserId);
	}

	/**
	 * 用户积分消费： 增减双方积分 增加双方积分增减的记录 返回成功失敗信息
	 * 
	 * userId 当前用户 sharedAssetId 共享资源id sharedAssetIntegral 共享资源积分 sharedUserId
	 * 共享资源创建人
	 */
	@RequestMapping(value = "integral_userIntegralExpend.action", method = RequestMethod.GET)
	@ResponseBody
	public ResultJSON getUserIntegralExpend(Long userId, Long sharedAssetId, Long sharedUserId,
			Integer sharedAssetIntegral) throws Exception {
		Boolean flag = userResourceIntergralService.userIntegralExpend(userId, sharedUserId, sharedAssetId,
				sharedAssetIntegral);
		return ResultJSON.getSuccess(flag);
	}

	/**
	 * 打开用户资源积分中心的首页 查询用户的总积分和可用积分 并返回首页视图
	 * 
	 * @param userId
	 */
	@RequestMapping(value = "integral_load.action", method = RequestMethod.GET)
	@ResponseBody
	public ResultJSON getLoad(Long userId) throws Exception {
		Map<String, Object> map = userResourceIntergralService.staticUserIntegral(userId);
		return ResultJSON.getSuccess(map);
	}

	/**
	 * 查询用户的(消费积分的)历史记录
	 * @param userId
	 * @param curPage
	 * @param numPerPage
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "integral_list.action", method = RequestMethod.GET)
	@ResponseBody
	public ResultJSON getList(Long userId, Integer curPage, Integer numPerPage) throws Exception {
		Pagination page = userResourceIntergralService.list(userId, curPage, numPerPage);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("curPage", curPage);
		map.put("numPerPage", numPerPage);
		map.put("totalPage", page.getTotal());
		map.put("totalRows", page.getTotalLines());
		map.put("list", dealWithIntegralHistory(page.getList()));
		return ResultJSON.getSuccess(map);
	}

	/**
	 * 将获取用户积分历史记录的 CachedRowSet 处理 为List<Map<String,String>>
	 * 
	 * @param resultRowSet
	 * @return
	 * @throws SQLException
	 */
	private List<Map<String, String>> dealWithIntegralHistory(List<Map<String, String>> listUserIntergral)
			throws SQLException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String operate_desc = "";// 操作描述
		String integral_desc = "";// 积分描述
		if (listUserIntergral != null && listUserIntergral.size() > 0) {
			for (int i = 0; i < listUserIntergral.size(); i++) {
				Map<String, String> map = listUserIntergral.get(i);

				int score = Integer.parseInt(map.get("score"));
				String operatetype = map.get("operatetype");
				String resName = map.get("resName");
				String createtime = map.get("createtime");
				Integer scope = Integer.parseInt(map.get("scope"));

				resName = "<span class='resource'>" + resName + "</span>";

				if (ResourceIntegralConstants.PASS_RECOMMENDEDASSET_CODE.equalsIgnoreCase(operatetype)) {
					operate_desc = "我的资源“" + resName + "”被" + (scope == 5 ? "校" : "区") + "本资源录用";
				} else if (ResourceIntegralConstants.DOWN_SHAREDRES_CODE.equalsIgnoreCase(operatetype)) {

					if (score >= 0) {
						operate_desc = "我的资源“" + resName + "”被下载";
					} else {
						operate_desc = "我引用资源“" + resName + "”";
					}

				} else if (ResourceIntegralConstants.IMP_SHAREDRES_CODE.equalsIgnoreCase(operatetype)) {

					operate_desc = "我引用资源“" + resName + "”";
				}

				if (score >= 0) {
					integral_desc = "获取" + score + "积分";
				} else {
					integral_desc = "消耗" + Math.abs(score) + "积分";
				}

				map.put("createtime", format.format(createtime));
				map.put("operate_desc", operate_desc);
				map.put("integral_desc", integral_desc);
			}

		}
		return listUserIntergral;

	}

}
