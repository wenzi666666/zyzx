package net.tfedu.zhl.cloud.resource.user.teach.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import net.tfedu.zhl.cloud.resource.navigation.dao.JSyscourseMapper;
import net.tfedu.zhl.cloud.resource.navigation.entity.JSyscourse;
import net.tfedu.zhl.cloud.resource.user.teach.dao.JTeachSyscourseMapper;
import net.tfedu.zhl.cloud.resource.user.teach.entity.JTeachSyscourse;
import net.tfedu.zhl.cloud.resource.user.teach.service.JTeachSyscourseService;
import net.tfedu.zhl.core.service.impl.BaseServiceImpl;

/**
 * 
 * 
 * 
 * @author wangwr
 * @date 2017年8月10日
 * @desc
 * 
 * 		copyRight@ 同方知好乐教育科技(北京)有限公司
 */
@Service("jTeachSyscourseService")
public class JTeachSyscourseServiceImpl extends BaseServiceImpl<JTeachSyscourse> implements JTeachSyscourseService {

	@Autowired
	JTeachSyscourseMapper mapper;
	@Autowired
	JSyscourseMapper sysMapper;

	@Cacheable(value = "bussinesscache", key = "'TEACHINGSELECT_'+#userId")
	@Override
	public String queryTeachingSelectTfcode(Long userId) {

		JTeachSyscourse record = getTeachingSelect(userId);
		if (null != record) {

			JSyscourse sys = sysMapper.selectByPrimaryKey(record.getSyscourseid());

			if (null != sys) {
				return sys.getTfcode();
			}

		}
		return null;
	}

	@CacheEvict(value = "bussinesscache", key = "'TEACHINGSELECT_'+#userId")
	@Override
	public void replaceUserTeachingSelect(Long userId, Long termId, Long subjectId, Long syscourseId) {
		
		JTeachSyscourse record = new JTeachSyscourse();
		record.setUserid(userId);
		
		List<JTeachSyscourse> records = getTeachingSelectALLWithDeleted(userId);
		
		if (null != records && records.size() > 0) {
			mapper.delete(record);
		} 
		
		record.setFlag(false);
		record.setSubjectid(subjectId.intValue());
		record.setTermid(termId.intValue());
		record.setSyscourseid(syscourseId);
			
		mapper.insertUseGeneratedKeys(record);
	}

	/**
	 * 获取用户的历史选择
	 * 
	 * @param userId
	 * @return
	 */
	private JTeachSyscourse getTeachingSelect(Long userId) {

		JTeachSyscourse record = new JTeachSyscourse();

		record.setFlag(false);
		record.setUserid(userId);

		return mapper.selectOne(record);
	}

	/**
	 * 获取用户的（全部）历史选择，包括历史上逻辑删除的
	 * 
	 * @param userId
	 * @return
	 */
	private List<JTeachSyscourse> getTeachingSelectALLWithDeleted(Long userId) {

		JTeachSyscourse record = new JTeachSyscourse();

		record.setUserid(userId);

		return mapper.select(record);
	}

}
