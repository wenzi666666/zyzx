package net.tfedu.zhl.cloud.teaching.discuss.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import net.tfedu.zhl.cloud.teaching.discuss.dao.TDiscussRecommendMapper;
import net.tfedu.zhl.cloud.teaching.discuss.entity.TDiscussRecommend;
import net.tfedu.zhl.cloud.teaching.discuss.service.DiscussRecommendService;
import net.tfedu.zhl.cloud.utils.datatype.StringUtils;
import net.tfedu.zhl.core.exception.ParamsException;
import net.tfedu.zhl.core.service.impl.BaseServiceImpl;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.sso.grade.dao.GradeMapper;


@Service("disCussService")
public class DiscussRecommendServiceImpl extends BaseServiceImpl<TDiscussRecommend>  implements DiscussRecommendService  {

	
	
	@Resource
	TDiscussRecommendMapper mapper;
	@Resource
	GradeMapper  gradeMapper;
	
	/**
	 * 批量删除推荐班级
	 * @return
	 * @throws Exception
	 */
	public ResultJSON removeRecommendRecords(String ids) throws Exception{
		if(StringUtils.isEmpty(ids)){
			throw new ParamsException();
		}
		mapper.removeRecommendRecords(ids.split(",")) ;
		return ResultJSON.getSuccess("");
	}

	@Override
	public ResultJSON queryRecommendRecordsPageForBack(int page, int perPage, String orderBy) throws Exception {
		// 用PageInfo对结果进行包装
        // Page插件必须放在查询语句之前紧挨的第一个位置
        PageHelper.startPage(page, perPage);
		
		if("createtime_desc".equalsIgnoreCase(orderBy)){
	        PageHelper.orderBy("id desc");
		}else{
	        PageHelper.orderBy("id asc");
		}
        // 这里不能放其它语句
        List<TDiscussRecommend> list = mapper.selectAllRecords();
        PageInfo<TDiscussRecommend> temp_page = new PageInfo<TDiscussRecommend>(list);
		return defaultSuccess(temp_page);
	}

	@Override
	public PageInfo<TDiscussRecommend> queryRecommendRecordsPage(int page, int perPage) throws Exception {
		// TODO Auto-generated method stub

		 PageHelper.startPage(page, perPage);
         PageHelper.orderBy("id desc");
	        // 这里不能放其它语句
	        List<TDiscussRecommend> list = mapper.selectAllRecords();
	        PageInfo<TDiscussRecommend> temp_page = new PageInfo<TDiscussRecommend>(list);
			return temp_page;
	
	
	}
	
	

}
