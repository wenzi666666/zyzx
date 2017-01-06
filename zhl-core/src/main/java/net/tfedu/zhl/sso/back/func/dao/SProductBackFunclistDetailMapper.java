package net.tfedu.zhl.sso.back.func.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.tfedu.zhl.helper.CoreMapper;
import net.tfedu.zhl.sso.back.func.entity.SProductBackFunclistDetail;
import net.tfedu.zhl.sso.back.user.bean.FuncDetail;

public interface SProductBackFunclistDetailMapper extends CoreMapper<SProductBackFunclistDetail> {
	
	
	/**
	 * 获取指定功能的权限
	 * @param funcId
	 * @param roleId
	 * @return
	 */
	public List<FuncDetail> getFuncDetailList(@Param("funcId")long funcId,@Param("roleId")long roleId);
	
	
}