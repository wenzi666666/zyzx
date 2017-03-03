package net.tfedu.zhl.cloud.resource.poolconfig.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import net.tfedu.zhl.cloud.resource.poolconfig.dao.SAppUserPoolConfigMapper;
import net.tfedu.zhl.cloud.resource.poolconfig.entity.SAppUserPoolConfig;
import net.tfedu.zhl.cloud.resource.poolconfig.module.AppUserPoolConfigIndentInfo;
import net.tfedu.zhl.cloud.resource.poolconfig.module.AppUserPoolConfigRecord;
import net.tfedu.zhl.cloud.resource.poolconfig.service.SAppUserPoolConfigService;
import net.tfedu.zhl.core.exception.CustomException;
import net.tfedu.zhl.core.exception.ParamsException;
import net.tfedu.zhl.core.service.impl.BaseServiceImpl;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.sso.term.dao.JTermMapper;
import net.tfedu.zhl.sso.term.entity.JTerm;
import net.tfedu.zhl.sso.users.entity.FuncListSimple;
import tk.mybatis.mapper.entity.Example;

/**
 
 	第三方用户与资源库的物权管理服务
  
    @author wangwr
    @date 2017年2月21日
    @desc 
  
    copyRight@ 同方知好乐教育科技(北京)有限公司 
*/
@Service(value="appUserPoolConfigService")
public class SAppUserPoolConfigServiceImpl extends BaseServiceImpl<SAppUserPoolConfig> implements SAppUserPoolConfigService {

	@Autowired
	SAppUserPoolConfigMapper mapper;
	
	@Autowired
	JTermMapper termMapper;
	
	
	@Override
    @Cacheable(value="bussinesscache")
	public List<Map<String, Object>> getAppUserPoolConfig(Long termId, Long subjectId,String userName, String appId)
			throws CustomException {
		
		
		return mapper.getAppUserPoolConfig(termId, subjectId, userName, appId);
	}



	@Override
	public ResultJSON pageQueryAppUserPoolConfig(Integer page, Integer perPage, Integer year, Long termId,
			String userName, String appId) {
		
		
		PageHelper.startPage(page, perPage);
        PageHelper.orderBy("id desc");
        
        List<AppUserPoolConfigRecord> list = mapper.queryAppUserPoolConfig(year, termId, userName, appId);
		PageInfo<AppUserPoolConfigRecord> _page = new PageInfo<AppUserPoolConfigRecord>(list);
        result = defaultSuccess(_page);
        return result;
		
		
	}



	@Override
	public ResultJSON updateAppUserPoolConfig(Long termId,Long subjectId, Integer poolId, String appId, String userName,
			Integer months, Long recordId) throws CustomException {
		
		SAppUserPoolConfig config = mapper.selectByPrimaryKey(recordId);
		
		//获取起始时间
		Date startDate = config.getStartDate();
		
		Calendar c = Calendar.getInstance();
		c.setTime(startDate);
		c.add(Calendar.MONTH, months);
		
		Date expireDate = c.getTime();

		
		config.setId(recordId);
		config.setAppid(Long.parseLong(appId));
		config.setUserid(userName);
		config.setTermid(termId);
		config.setSubjectid(subjectId);
		config.setStartDate(startDate);
		config.setExpireDate(expireDate);
		
		mapper.updateByPrimaryKeySelective(config);
		
		return ResultJSON.getSuccess("");
	}



	@Override
	public ResultJSON addAppUserPoolConfigBatch(List<AppUserPoolConfigIndentInfo> list) throws CustomException {
		if(list!=null && list.size()>0){
			
			SAppUserPoolConfig record = null;
			
			AppUserPoolConfigIndentInfo info = null;
			Example example = null;
			List<Long> pools  = null;
			Long poolId = null;
			
			Calendar c = null;
			
			for (int i = 0; i < list.size(); i++) {
				info = list.get(i);
				
				
				pools = info.getPools();
				for (Iterator<Long> iterator = pools.iterator(); iterator.hasNext();) {
					poolId = (Long) iterator.next();
					example = new Example(SAppUserPoolConfig.class);
					
					
					example.createCriteria().andCondition(" appid = '"+info.getAppId()+"'")
					.andCondition(" userid= '"+info.getUserName()+"'")
					.andCondition("flag = false ")
					.andCondition("poolid = "+poolId)
					.andCondition("termid = "+info.getTermId())
					.andCondition("subjectid = "+info.getSubjectId())
					;
					
					
					List<SAppUserPoolConfig> ls = mapper.selectByExample(example);
					
					if(ls!=null && ls.size()>0){
					//更新已經存在的記錄
						for (Iterator<SAppUserPoolConfig> iterator2 = ls.iterator(); iterator2.hasNext();) {
							record = (SAppUserPoolConfig) iterator2.next();
							Date expireDate = record.getExpireDate();
							
							c = Calendar.getInstance();
							c.setTime(expireDate);
							c.add(Calendar.MONTH, info.getMonth());
							
							record.setExpireDate(c.getTime());
							
							mapper.updateByPrimaryKey(record);
						}
						
					}else{
					//查入新纪录
						
						c = Calendar.getInstance();
						
						record =  new SAppUserPoolConfig();
						
						record.setAppid(Long.parseLong(info.getAppId()));
						record.setAddTime(c.getTime());
						record.setStartDate(c.getTime());
						record.setEditTime(record.getAddTime());
						record.setFlag(false);
						record.setPoolid(poolId);
						record.setSubjectid(info.getSubjectId());
						record.setTermid(info.getTermId());
						record.setUserid(info.getUserName());
						
						c.add(Calendar.MONTH, info.getMonth());
						
						record.setExpireDate(c.getTime());
						
						mapper.insert(record);
						
					}
				}
				
			}
			
			return ResultJSON.getSuccess("");
		}
		return ResultJSON.defaultError(new ParamsException());
	}



	@Override
	public ResultJSON delAppUserPoolConfig(Long recordId) throws CustomException {
		
		SAppUserPoolConfig config = new SAppUserPoolConfig();
		config.setId(recordId);
		config.setFlag(true);
		
		return super.update(config);
	}



	@Override
	public List<FuncListSimple> getAppUserPool(String appId, String userName, String termName) throws CustomException {
		
		long termId = 0;
		long subjectId = 0 ;
		
		//先获取学科id
		Example example = new Example(JTerm.class);
		example.createCriteria().andCondition(" flag=false ").andCondition(" name="+termName);
		List<JTerm> ls =  termMapper.selectByExample(example);
		if(ls!=null &&ls.size()>0){
			termId = ls.get(0).getId();
		}
		
		//利用現有方法
		List<Map<String, Object>>  result = getAppUserPoolConfig(termId, subjectId, userName, appId);
		if(result!=null && result.size()>0){
			
			List<FuncListSimple> l = new ArrayList<FuncListSimple>();
			FuncListSimple f = null;
			for (Iterator<Map<String, Object>> iterator = result.iterator(); iterator.hasNext();) {
				Map<String, Object> map = (Map<String, Object>) iterator.next();
			
				f = new FuncListSimple();
				f.setCode(String.valueOf((Long)map.get("id")));
				l.add(f);
			}
			
			return l;
		}
		
		return null;
	}




	
	
	
	
	
	
}
