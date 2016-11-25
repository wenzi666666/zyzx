package net.tfedu.zhl.cloud.casProxy.data.service.impl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import net.tfedu.zhl.cloud.casProxy.config.ThirdPartyCASConfig;
import net.tfedu.zhl.cloud.casProxy.data.BaseData;
import net.tfedu.zhl.cloud.casProxy.data.service.BaseDataCommonService;
import net.tfedu.zhl.cloud.casProxy.model.RegisterAddFormTp;
import net.tfedu.zhl.cloud.casProxy.model.ResultDataTp;
import net.tfedu.zhl.cloud.casProxy.util.SignVerify;
import net.tfedu.zhl.cloud.utils.datatype.StringUtils;
import net.tfedu.zhl.core.exception.CustomException;
import net.tfedu.zhl.fileservice.MD5;
import net.tfedu.zhl.helper.httpclient.APIForm;
import net.tfedu.zhl.helper.httpclient.HttpClientUtils;
import net.tfedu.zhl.sso.app.entity.SApp;
import net.tfedu.zhl.sso.area.dao.AreaMapper;
import net.tfedu.zhl.sso.users.entity.RegisterAddForm;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
/**
 * 获取第三方基础数据
 * 
 * copyRight@知好乐教育技术北京有限公司
 * 
 * @author jiys
 * @date 2016-11-23
 * @version v1.0.0
 */
@Service("baseDataCommonService")
public class BaseDataCommonServiceImpl extends BaseData implements BaseDataCommonService {

	/**
	 * 处理服务接口,通过修改配置文件中的实现类，切换不同的场景
	 */
	@Autowired
	AreaMapper areaMapper;


	@Override
	public RegisterAddForm parseAPI(String userid,
			ThirdPartyCASConfig config, SApp app) throws CustomException {

		// 获取第三方基础数据
		RegisterAddFormTp userinfo = getBaseDataTp(app, userid);

		// 用户对象转换器
		RegisterAddForm form = RegisterAddFormConversion(userinfo,areaMapper);

		return form;
	}

}
