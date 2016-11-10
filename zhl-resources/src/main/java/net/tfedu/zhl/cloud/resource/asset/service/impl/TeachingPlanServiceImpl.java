package net.tfedu.zhl.cloud.resource.asset.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;

import net.tfedu.zhl.cloud.resource.asset.dao.ZAssetMapper;
import net.tfedu.zhl.cloud.resource.asset.dao.ZAssetSyscourseMapper;
import net.tfedu.zhl.cloud.resource.asset.entity.TeachingPlan;
import net.tfedu.zhl.cloud.resource.asset.entity.ZAsset;
import net.tfedu.zhl.cloud.resource.asset.entity.ZAssetSyscourse;
import net.tfedu.zhl.cloud.resource.asset.entity.ZTeachingPlanContent;
import net.tfedu.zhl.cloud.resource.asset.service.TeachingPlanService;
import net.tfedu.zhl.core.service.impl.BaseServiceImpl;
import net.tfedu.zhl.helper.PaginationHelper;
import net.tfedu.zhl.helper.ResultJSON;
import tk.mybatis.mapper.entity.Example;

/**
 
  
  
     教案专用
  
  教案的信息 分別写到 z_asset  z_teaching_plan_content  中 ，需要开启事务
  
  @author wangwr
  @date 2016年11月10日
  @desc 
  
  copyRight@ 同方知好乐教育科技(北京)有限公司 
  
  

*/
@Service("teachingPlanService")
@Transactional
public class TeachingPlanServiceImpl extends BaseServiceImpl<ZTeachingPlanContent> implements TeachingPlanService {

	
	@Resource
	ZAssetMapper assetMapper;
	
	@Resource
	ZAssetSyscourseMapper assetSyscourseMapper;

	@Override
	public ResultJSON addRecord(TeachingPlan obj) throws Exception {
		Date createtime =  Calendar.getInstance().getTime();
		
		
		
		ZAsset record = new ZAsset();
		
		record.setUserid(obj.getUserId());
		record.setKeyword(obj.getKeyword());
		record.setAssetpath("");
		record.setAssetsize("");
		record.setCreatetime(createtime);
		record.setFiletype(TeachingPlan.FILE_TYPE);
		record.setTypeid(TeachingPlan.RESOURCE_TYPE);
		
		
		record.setAssetdesc("");
		record.setIsfinished(0);
		// 新版资源类型
		record.setUnifytypeid(TeachingPlan.RESOURCE_TYPE.intValue());
		record.setName(obj.getTitle());
		// 旧版资源类型字段也设置为新版的类型
		record.setIscourseware(false);
		record.setIslocal(0);
		record.setResourceid(TeachingPlan.FILE_TYPE+UUID.randomUUID().toString().replace("-", ""));
		record.setIsissue(false);
		record.setFilepath("");

		
	
		assetMapper.insertSelective(record);
		long id = record.getId();
		
		ZAssetSyscourse  as = new ZAssetSyscourse();
		as.setAssetid(id);
		as.setTfcode(obj.getTfcode());
		as.setFlag(false);
		as.setSharescope(0);

		assetSyscourseMapper.insertSelective(as);
		
		
		ZTeachingPlanContent content = new ZTeachingPlanContent();
		content.setAssetid(id);
		content.setContent(obj.getContent());
		content.setFlag(false);
		
		this.insert(content);
		
		return ResultJSON.getSuccess(id);
	}

	@Override
	public ResultJSON editRecord(TeachingPlan obj) throws Exception {
	
		Date updatetime =  Calendar.getInstance().getTime();

		ZAsset record = assetMapper.selectByPrimaryKey(obj.getId());
		
		record.setId(obj.getId());
		record.setName(obj.getTitle());
		record.setKeyword(obj.getKeyword());
		record.setUpdatetime(updatetime);
		
		//更新资源表记录
		assetMapper.updateByPrimaryKeySelective(record);
		
		
		ZTeachingPlanContent content = new ZTeachingPlanContent();
		content.setAssetid(obj.getId());
		content.setContent(obj.getContent());

		
		
		//更新导航
		assetSyscourseMapper.updateAssetSyscourse(obj.getId().toString(), obj.getTfcode(), "0");
		
		//更新教案内容表记录
		return	this.update(content);
		
	}

	@Override
	public ResultJSON delRecord(long id) throws Exception {

		ZAsset record = new ZAsset();
		record.setId(id);
		record.setFlag(true);
		//更新資源表
		assetMapper.updateByPrimaryKeySelective(record);
		
		
		Example example = new Example(ZAssetSyscourse.class);
		example.createCriteria().andCondition("assetid=", id);
		
		ZAssetSyscourse as = new ZAssetSyscourse();
		as.setFlag(true);
		
		//更新資源導航
		assetSyscourseMapper.updateByExampleSelective(as, example);
		
		
		ZTeachingPlanContent content = new ZTeachingPlanContent();
		content.setAssetid(id);
		content.setFlag(true);
		//逻辑删除
		return this.update(content);
	}

	@Override
	public ResultJSON getRecord(long id) throws Exception {
		
		//查询对应的资源记录
		ZAsset asset =  assetMapper.selectByPrimaryKey(id);
		
		ZAssetSyscourse as = new ZAssetSyscourse();
		as.setAssetid(id);
		
//		查询对应的资源导航记录
		as =assetSyscourseMapper.selectOne(as);
		
//		查询对应的教案内容
		ZTeachingPlanContent content = (ZTeachingPlanContent)this.get(id).getData();
		
		//组装包装类
		TeachingPlan record = new TeachingPlan();
		record.setId(id);
		record.setContent(content.getContent());
		record.setKeyword(asset.getKeyword());
		record.setTfcode(as.getTfcode());
		record.setTitle(asset.getName());
		record.setUserId(asset.getUserid());
		
		return ResultJSON.getSuccess(record);
	}

	@Override
	public ResultJSON queryRecord(int page, int perPage, long userId) throws Exception {
		
		
		//准备条件
		Example example = new Example(ZAsset.class);
		example.createCriteria().andCondition(" userid= "+userId)
		.andCondition(" flag = false ")
		;
		
		PageHelper.startPage(page, perPage);

		
		List<ZAsset> list =  assetMapper.selectByExample(example);
		
		PaginationHelper<Object> resultPage = PaginationHelper.transfer(list);
		
		List<Object> result = new ArrayList<Object>();
		for (Iterator<ZAsset> iterator = list.iterator(); iterator.hasNext();) {
			ZAsset asset = iterator.next();
			
			TeachingPlan plan = new TeachingPlan();
			plan.setId(asset.getId());
			plan.setTitle(asset.getName());
			plan.setCreateTime(asset.getCreatetime());
			
			result.add(plan);
		}
		resultPage.setList(result);
		return ResultJSON.getSuccess(resultPage);
	}
	
	
}
