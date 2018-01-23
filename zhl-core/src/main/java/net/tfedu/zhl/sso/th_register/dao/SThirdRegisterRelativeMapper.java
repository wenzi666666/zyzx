package net.tfedu.zhl.sso.th_register.dao;

import org.apache.ibatis.annotations.Param;

import net.tfedu.zhl.helper.CoreMapper;
import net.tfedu.zhl.sso.th_register.entity.SThirdRegisterRelative;


public interface SThirdRegisterRelativeMapper extends CoreMapper<SThirdRegisterRelative> {
	
	/**
	 * 获取某个第三方系统的userName对接的结果（映射）
	 * @param userName
	 * @param thirdCode 第三方系统的编码
	 * @return
	 */
	public SThirdRegisterRelative getThirdRelativeResult(@Param("userName")String userName,@Param("thirdCode")String thirdCode);
	
}