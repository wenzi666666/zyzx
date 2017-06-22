package net.tfedu.zhl.userlayer.funclist.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import net.tfedu.zhl.helper.CoreMapper;
import net.tfedu.zhl.userlayer.funclist.entity.FuncList;
import net.tfedu.zhl.userlayer.funclist.entity.FuncListSimple;

/**
 * 权限资源类
 * @author bruce
 *
 */
public interface FuncListMapper extends CoreMapper<FuncList> {
    /**
     * 根据角色编号查询用户权限
     * @param roleIds
     * @param model
     * @return
     */
    List<FuncListSimple> getRoleFuncByRoleIds(@Param("roleIds") Set<Long> roleIds, @Param("model")String model);
}