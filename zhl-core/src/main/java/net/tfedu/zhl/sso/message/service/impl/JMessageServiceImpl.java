package net.tfedu.zhl.sso.message.service.impl;



import javax.annotation.Resource;

import net.tfedu.zhl.sso.message.dao.JMessageMapper;
import net.tfedu.zhl.sso.message.entity.JMessage;
import net.tfedu.zhl.sso.message.service.JMessageService;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


@Service("jMessageService")
public class JMessageServiceImpl implements JMessageService {
	
	@Resource 
	JMessageMapper  mapper;

	
	@Override
	public int getUserNewMessageNumber(Long userId) {
	//	return mapper.getUserNewMessageNumber(userId);
		return 0;
	}

	@Override
	public PageInfo queryMessage(Long userId, Integer page, Integer perPage) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, perPage);
//		List<JMessage> list = mapper.queryMessage(userId);
//		return new PageInfo(list);
		return  null ;
	}

	@Override
	public void updateMessageReaded(Long id) {
		// TODO Auto-generated method stub
		JMessage record =  new JMessage();
		record.setId(id);
		record.setReadflag(true);
		mapper.updateByPrimaryKeySelective(record);
	}

}
