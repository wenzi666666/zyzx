package net.tfedu.zhl.sso.back.func.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.tfedu.zhl.core.exception.ParamsException;
import net.tfedu.zhl.core.service.impl.BaseServiceImpl;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.sso.back.func.dao.SProductBackFunclistDetailMapper;
import net.tfedu.zhl.sso.back.func.dao.SProductBackFunclistMapper;
import net.tfedu.zhl.sso.back.func.entity.SProductBackFunclist;
import net.tfedu.zhl.sso.back.func.entity.SProductBackFunclistDetail;
import net.tfedu.zhl.sso.back.func.service.SProductBackFuncListService;
import net.tfedu.zhl.sso.back.role.dao.SProductBackRoleConfigDetailMapper;
import net.tfedu.zhl.sso.back.role.dao.SProductBackRoleConfigMapper;
import net.tfedu.zhl.sso.back.role.entity.SProductBackRoleConfig;
import net.tfedu.zhl.sso.back.role.entity.SProductBackRoleConfigDetail;
import tk.mybatis.mapper.entity.Example;

/**
 
 
 	各个产品的后台功能管理业务类
  
    @author wangwr
    @date 2017年1月16日
    @desc 
  
    copyRight@ 同方知好乐教育科技(北京)有限公司 
*/
@Service("funcService")
public class SProductBackFuncListServiceImpl extends BaseServiceImpl<SProductBackFunclist> implements SProductBackFuncListService {

	
	@Resource
	SProductBackFunclistMapper mapper;
	
	
	@Resource
	SProductBackFunclistDetailMapper detailMapper;
	
	@Resource
	SProductBackRoleConfigMapper  roleConfigMapper;
	
	@Resource
	SProductBackRoleConfigDetailMapper roleConfigDetailMapper;
	
	
	
	@Override
	public ResultJSON getFuncAllDetail(Long funcId) throws Exception {

		Example example = new Example(SProductBackFunclistDetail.class);

		example.createCriteria().andCondition("funcid=", funcId).andCondition("flag=", false);
		
		return ResultJSON.getSuccess(detailMapper.selectByExample(example));
	}

	@Override
	public ResultJSON updateFuncDetail(SProductBackFunclistDetail detail) throws Exception {
		
		detailMapper.updateByPrimaryKeySelective(detail);
		
		return ResultJSON.getSuccess("");
	}

	@Override
	public ResultJSON delFuncDetail(Long detailId) throws Exception {
		
		SProductBackFunclistDetail record = new SProductBackFunclistDetail();
		
		record.setId(detailId);
		record.setFlag(true);
		
		detailMapper.updateByPrimaryKeySelective(record);
		
		return ResultJSON.getSuccess("");
	}

	@Override
	public ResultJSON addFuncDetail(SProductBackFunclistDetail detail) throws Exception {
		
		detail.setFlag(false);
		
		detailMapper.insertSelective(detail);
		
		return ResultJSON.getSuccess(detail);
	}

	@Override
	public ResultJSON addDetailRoleConfig(Long funcId, Long[] detailIds, Long roleId) throws Exception {
		
		SProductBackRoleConfig config = new SProductBackRoleConfig();
		config.setRoleid(roleId);
		config.setFuncid(funcId);
		config.setFlag(false);
		List<SProductBackRoleConfig> list = roleConfigMapper.select(config);
		if(list==null || list.size()==0){
			roleConfigMapper.insertUseGeneratedKeys(config);
		}
		
		if(detailIds!=null && detailIds.length!=0){
			SProductBackRoleConfigDetail detail = null;
			List<SProductBackRoleConfigDetail> recordList = new ArrayList<SProductBackRoleConfigDetail>();
			for (int i = 0; i < detailIds.length; i++) {
				detail = new SProductBackRoleConfigDetail();
				detail.setFlag(false);
				detail.setFuncdetailid(detailIds[i]);
				detail.setRoleid(roleId);
				recordList.add(detail);
			}
			roleConfigDetailMapper.insertList(recordList);
			return ResultJSON.getSuccess("");
		}
		return ResultJSON.defaultError(new ParamsException());
	}
	
	
	
	
}
