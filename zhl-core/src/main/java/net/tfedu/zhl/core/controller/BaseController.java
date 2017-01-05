package net.tfedu.zhl.core.controller;

import org.springframework.beans.factory.annotation.Autowired;

import net.tfedu.zhl.core.service.BaseService;
import net.tfedu.zhl.helper.ResultJSON;
import tk.mybatis.mapper.entity.Example;

/**
 * 
 * （简单类） 基础控制类
 * 
 * @author wangwr
 * @date 2017年1月3日
 * @desc
 * 
 * 		copyRight@ 同方知好乐教育科技(北京)有限公司
 */
public class BaseController<T> {

	/**
	 * 按类型注入
	 */
	@Autowired
	BaseService<T> baseService;

	/**
	 * 增加
	 * 
	 * @param c
	 * @return
	 * @throws Exception
	 */
	public ResultJSON insert(T c) throws Exception {
		return baseService.insert(c);
	}

	/**
	 * 删除
	 * 
	 * @param id
	 *            主键id
	 * @return
	 */
	public ResultJSON delete(long id) throws Exception {

		return baseService.delete(id);
	}

	/**
	 * 修改
	 * 
	 * @param c
	 * @return
	 */
	public ResultJSON update(T c) throws Exception {
		return baseService.update(c);
	}

	/**
	 * 获取指定对象
	 * 
	 * @param key
	 *            主键key
	 * @return
	 */
	public ResultJSON get(Object key) throws Exception {
		return baseService.getByPrimaryKey(key);
	}

	/**
	 * 根据条件分页查询
	 * 
	 * @param example
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public ResultJSON getPage(Example example, int pageNum, int pageSize) throws Exception {
		if (null != example) {
			return baseService.getPageByExample(example, pageNum, pageSize);
		}

		return baseService.getPage(pageNum, pageSize);
	}

}
