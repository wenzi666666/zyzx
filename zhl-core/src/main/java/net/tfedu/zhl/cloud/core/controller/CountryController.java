package net.tfedu.zhl.cloud.core.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import net.tfedu.zhl.cloud.core.entity.Country;
import net.tfedu.zhl.cloud.core.service.CountryService;
import net.tfedu.zhl.cloud.utils.datatype.ArrayUtil;

@Controller 
@RequestMapping("/country*")
public class CountryController {
	
	@Resource CountryService countryService;

	/**
	handler method 参数绑定常用的注解,我们根据他们处理的Request的不同内容部分分为四类：（主要讲解常用类型）
	A、处理requet uri 部分（这里指uri template中variable，不含queryString部分）的注解：   @PathVariable;
	B、处理request header部分的注解：   @RequestHeader, @CookieValue;
	C、处理request body部分的注解：@RequestParam,  @RequestBody;
	D、处理attribute类型是注解： @SessionAttributes, @ModelAttribute;
	*/

	/**
	 * 获取单个实体
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@ResponseBody
	public  Country getById(@PathVariable int id) throws Exception {
		return countryService.get(id);
	}

	/**
	 * 新建
	 * 
	 * @param request
	 * @param response
	 * @param entity
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> create(HttpServletRequest request,
			HttpServletResponse response, @RequestBody Country entity)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		countryService.insert(entity);
		map.put("code", 200);
		return map;
	}
	
	/**
	 * 批量新增
	 * 
	 * @param request
	 * @param response
	 * @param entity
	 * @throws Exception
	 */
	@RequestMapping(value = "/collection/", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addArray(HttpServletRequest request,
			HttpServletResponse response, @RequestBody Country[] entity)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		countryService.insert(ArrayUtil.arrayToList(entity));
		map.put("code", 200);
		return map;
	}

	/**
	 * 更新
	 * 
	 * @param request
	 * @param response
	 * @param entity
	 * @throws Exception
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> update(HttpServletRequest request,
			HttpServletResponse response, @RequestBody Country entity)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		countryService.update(entity);
		map.put("code", 200);
		return map;
	}

	/**
	 * 删除
	 * 
	 * @param request
	 * @param id
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request, @RequestParam int id) {
		Map<String, Object> map = new HashMap<String, Object>();
		countryService.delete(id);
		return map;
	}
	
	/**
	 * 分页查询
	 * 
	 * @param request
	 * @param response
	 * @param entity
	 * @throws Exception
	 */
	@RequestMapping(value = "/page/", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getPage(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		PageInfo<Country> list = countryService.getPage(pageNum, pageSize);
		map.put("data", list);
		map.put("code", "添加成功。");
		return map;
	}
	
	/**
	 * 自由查询
	 * 
	 * @param request
	 * @param response
	 * @param entity
	 * @throws Exception
	 */
	@RequestMapping(value = "/collection/", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> queryMaps(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<?,?>> list = countryService.queryMaps();
		map.put("data", list);
		map.put("code", "添加成功。");
		return map;
	}
	
	/**
	 * 自由查询
	 * 
	 * @param request
	 * @param response
	 * @param entity
	 * @throws Exception
	 */
	@RequestMapping(value = "/collection/property", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> queryIds(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Long> list = countryService.queryIds();
		map.put("data", list);
		map.put("code", "添加成功。");
		return map;
	}
}
