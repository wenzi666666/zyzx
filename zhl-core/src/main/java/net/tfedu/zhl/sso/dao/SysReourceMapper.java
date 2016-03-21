package net.tfedu.zhl.sso.dao;

import java.util.List;

import net.tfedu.zhl.helper.CoreMapper;
import net.tfedu.zhl.sso.entity.SysReource;

public interface SysReourceMapper extends CoreMapper<SysReource> {
	List<SysReource> selectAllPermission();
}