package net.tfedu.zhl.sso.back.role.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.tfedu.zhl.core.service.impl.BaseServiceImpl;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.sso.back.func.dao.SProductBackFunclistMapper;
import net.tfedu.zhl.sso.back.role.bean.SProductBackFuncInfoPermissionView;
import net.tfedu.zhl.sso.back.role.dao.SProductBackRoleConfigDetailMapper;
import net.tfedu.zhl.sso.back.role.dao.SProductBackRoleConfigMapper;
import net.tfedu.zhl.sso.back.role.entity.SProductBackRole;
import net.tfedu.zhl.sso.back.role.entity.SProductBackRoleConfig;
import net.tfedu.zhl.sso.back.role.entity.SProductBackRoleConfigDetail;
import net.tfedu.zhl.sso.back.role.service.SProductBackRoleService;
import tk.mybatis.mapper.entity.Example;

/**
 
  
  	后台角色管理功能业务实现类
    @author wangwr
    @date 2017年1月17日
    @desc 
  
    copyRight@ 同方知好乐教育科技(北京)有限公司 
*/
@Service("backRoleService")
public class SProductBackRoleServiceImpl extends BaseServiceImpl<SProductBackRole> implements SProductBackRoleService {

	
	@Resource
	SProductBackRoleConfigMapper configMapper;
	
	
	@Resource
	SProductBackRoleConfigDetailMapper detailConfigMapper;
	
	
	@Resource
	SProductBackFunclistMapper listMapper;
	
	
	

	@Override
	public ResultJSON queryRoleConfig(Long roleId, String productCode) throws Exception {
		
		
		List<SProductBackFuncInfoPermissionView> list
		 		= listMapper.getRolePremissionAboutProductTopFunc(roleId, productCode);
		
		if(list!=null && list.size()>0){
			SProductBackFuncInfoPermissionView s =null;
			List<SProductBackFuncInfoPermissionView> children = null;
			for (Iterator<SProductBackFuncInfoPermissionView> iterator = list.iterator(); iterator.hasNext();) {
				 s = iterator.next();
				 children = listMapper.getRolePremissionAboutProductChildrenFunc(s.getId(), roleId);
				 s.setChildren(children);
			}
			
		}
		return ResultJSON.getSuccess(list);
	}

	@Override
	public ResultJSON addRoleConfig(Long roleId, Long[] funcIds) throws Exception {
		
		SProductBackRoleConfig config = null;
		List<SProductBackRoleConfig> list = new ArrayList<SProductBackRoleConfig>();
		
		
		for (int i = 0; i < funcIds.length; i++) {
			config = new SProductBackRoleConfig();
			config.setFlag(false);
			config.setFuncid(funcIds[i]);
			config.setRoleid(roleId);
			list.add(config);
		}
		
		configMapper.insertList(list);
		
		return ResultJSON.getSuccess("");
	}

	@Override
	public ResultJSON delRoleConfig(Long roleId,Long[] funcIds) throws Exception {
		
		Example example = null;
		
		for (int i = 0; i < funcIds.length; i++) {
			example = new  Example(SProductBackRoleConfig.class);
			example.createCriteria().andCondition("roleId=",roleId).andCondition("funcid=", funcIds[i]);
			configMapper.deleteByExample(example);
		}
		
		return ResultJSON.getSuccess("");
	}

	@Override
	public ResultJSON queryRoleConfigDetail(Long funcId, Long roleId) throws Exception {
		return ResultJSON.getSuccess(listMapper.getRolePermissionAboutFuncDetail(funcId, roleId));
	}

	@Override
	public ResultJSON addRoleConfigDetail(Long roleId, Long[] detailIds) throws Exception {
	
		SProductBackRoleConfigDetail config = null;
		List<SProductBackRoleConfigDetail> list = new ArrayList<SProductBackRoleConfigDetail>();
		
		
		for (int i = 0; i < detailIds.length; i++) {
			config = new SProductBackRoleConfigDetail();
			config.setFlag(false);
			config.setFuncdetailid(detailIds[i]);
			config.setRoleid(roleId);
			list.add(config);
		}
		
		detailConfigMapper.insertList(list);
		
		return ResultJSON.getSuccess("");
	
	}

	@Override
	public ResultJSON delRoleConfigDetail(Long roleId, Long[] detailIds) throws Exception {
		
		Example example = null;
		
		for (int i = 0; i < detailIds.length; i++) {
			example = new  Example(SProductBackRoleConfigDetail.class);
			example.createCriteria().andCondition("roleId=",roleId).andCondition("funcdetailId=", detailIds[i]);
			detailConfigMapper.deleteByExample(example);
		}
		
		return ResultJSON.getSuccess("");
		
	}

}
