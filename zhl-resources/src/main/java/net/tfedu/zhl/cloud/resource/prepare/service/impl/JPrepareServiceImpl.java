package net.tfedu.zhl.cloud.resource.prepare.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.tfedu.zhl.cloud.resource.prepare.dao.JPrepareContentMapper;
import net.tfedu.zhl.cloud.resource.prepare.dao.JPrepareMapper;
import net.tfedu.zhl.cloud.resource.prepare.entity.FirstNavigationInfo;
import net.tfedu.zhl.cloud.resource.prepare.entity.JPrepare;
import net.tfedu.zhl.cloud.resource.prepare.entity.JPrepareContent;
import net.tfedu.zhl.cloud.resource.prepare.entity.JPrepareContentView;
import net.tfedu.zhl.cloud.resource.prepare.entity.JPrepareView;
import net.tfedu.zhl.cloud.resource.prepare.service.JPrepareService;
import net.tfedu.zhl.cloud.resource.prepare.util.JPrepareConstant;



@Service("jPrepareService")
public class JPrepareServiceImpl implements JPrepareService {
	
	
	
	@Autowired
	JPrepareMapper mapper;
	
	@Autowired
	JPrepareContentMapper contMapper;
	
	
	

	@Override
	public JPrepare addPrepare(JPrepare obj) {
		// TODO Auto-generated method stub
		mapper.insert(obj);
		//更新排序字段
		mapper.update_default_prepare_order();
		return obj;
	}

	@Override
	public void editPrepare(JPrepare obj) {
		mapper.updateByPrimaryKeySelective(obj);
	}

	@Override
	public void deletePrepareById(Long id) {
		mapper.deleteByPrimaryKey(id);
	}

	@Override
	public JPrepareContent addPrepareContent(JPrepareContent content) {
		// TODO Auto-generated method stub
		contMapper.insert(content);
		mapper.update_default_prepare_content_order();
		return content;
	}

	@Override
	public void editPrepareContent(JPrepareContent content) {
		contMapper.updateByPrimaryKeySelective(content);
	}

	@Override
	public void deletePrepareContentById(Long id) {
		contMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void clearPrepareContentByPrepareId(Long prepareId) {
		mapper.clearPrepareContentByPrepareId(prepareId);
	}

	@Override
	public List<JPrepareView> queryPrepareList(String tfcode,Long userId) {
		// TODO Auto-generated method stub
		return mapper.queryPrepareList(tfcode+"%", userId);
	}

	@Override
	public List<JPrepareView> queryPrepareAndTimeScopeList(String tfcode,Long userId) {
		// TODO Auto-generated method stub
		return mapper.queryPrepareAndTimeScopeList(tfcode+"%", userId);
	}

	@Override
	public List<JPrepareContentView> queryPrepareContentList(Long prepareId) {
		// TODO Auto-generated method stub
		return mapper.queryPrepareContentList(prepareId);
	}

	@Override
	public void removeResourceFromPrepare(Long prepareId, Integer contType,
			Long contId) {
		// TODO Auto-generated method stub
		mapper.removeResourceFromPrepare(prepareId, contType, contId);
	}

	@Override
	public void addToPrepareWithOnlySysResource(String resCode,Long userId) {
		// TODO Auto-generated method stub
		//获取资源的第一个导航节点
		FirstNavigationInfo nav = mapper.getFirstNavigationForSysResource(resCode);
		if(nav!=null){
			String tfcode = nav.getTfcode();
			//获取节点下的备课夹 
			List<JPrepareView> list = mapper.queryPrepareList(tfcode, userId);
			//如果有则 加入到备课夹
			//没有则 新建备课夹 加入
			Date currentDate = Calendar.getInstance().getTime();
			if(list!=null && list.size()>0){
				JPrepareView prepare = list.get(0);
				//将资源加入备课夹
				JPrepareContent cont = new JPrepareContent();
				cont.setPreid(prepare.getId());
				cont.setContid(nav.getResId());
				cont.setConttype(JPrepareConstant.CONTTYPE_SYSRES);
				cont.setCreatetime(currentDate);
				contMapper.insert(cont);
				mapper.update_default_prepare_content_order();
			}else{
				String title = nav.getTitle();
				//新建备课夹 
				JPrepare prepare = new JPrepare();
				prepare.setUserid(userId);
				prepare.setCreatetime(currentDate);
				prepare.setTfcode(tfcode);
				prepare.setTitle(title);
				mapper.insert(prepare);
				
				//将资源加入备课夹
				JPrepareContent cont = new JPrepareContent();
				cont.setPreid(prepare.getId());
				cont.setContid(nav.getResId());
				cont.setConttype(JPrepareConstant.CONTTYPE_SYSRES);
				cont.setCreatetime(currentDate);
				contMapper.insert(cont);
				mapper.update_default_prepare_content_order();
			}
		}
	}

}
