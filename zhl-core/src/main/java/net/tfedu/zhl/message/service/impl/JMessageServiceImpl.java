package net.tfedu.zhl.message.service.impl;



import java.util.List;

import net.tfedu.zhl.message.dao.JMessageMapper;
import net.tfedu.zhl.message.entity.JMessage;
import net.tfedu.zhl.message.service.JMessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service("jMessageService")
public class JMessageServiceImpl implements JMessageService {
	
	@Autowired
	JMessageMapper  mapper;

	
	@Override
	public int getUserNewMessageNumber(Long userId) {
		return mapper.getUserNewMessageNumber(userId);
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
