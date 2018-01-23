package net.tfedu.zhl.sso.developer.service;

import net.tfedu.zhl.core.service.BaseService;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.sso.app.entity.SApp;
import net.tfedu.zhl.sso.developer.entity.SDeveloper;
import tk.mybatis.mapper.entity.Example;

/**
 
  
    @author wangwr
    @date 2017年1月19日
    @desc 
  
    copyRight@ 同方知好乐教育科技(北京)有限公司 
*/
public interface SDeveloperService extends BaseService<SDeveloper> {

	
	
	/**
	 * 重新实现分页查询，developerid 
	 * @param example
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public ResultJSON pageDeveloper(Example example, int pageNum, int pageSize);
	
	
	
	/**
	 * 增加app
	 * @param app
	 * @return
	 */
	public ResultJSON addApp(SApp app );
	
	/**
	 * 修改app
	 * @param app
	 * @return
	 */
	public ResultJSON updateApp(SApp app );
	
	/**
	 * 刪除app
	 * @param appId
	 * @return
	 */
	public ResultJSON delApp(Integer appId);
	
	/**
	 * 分页查询app
	 * @param example
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public ResultJSON pageApp(Example example, int pageNum, int pageSize);
	
	
	
	
}
