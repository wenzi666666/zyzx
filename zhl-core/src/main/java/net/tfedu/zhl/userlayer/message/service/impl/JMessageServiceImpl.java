package net.tfedu.zhl.userlayer.message.service.impl;




import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import net.tfedu.zhl.userlayer.message.dao.JMessageMapper;
import net.tfedu.zhl.userlayer.message.entity.JMessage;
import net.tfedu.zhl.userlayer.message.service.JMessageService;


@Service("jMessageService")
public class JMessageServiceImpl implements JMessageService {
	
	@Resource 
	JMessageMapper  mapper;

	
	@Override
	public int getUserNewMessageNumber(Long userId) {
		return mapper.getUserNewMessageNumber(userId);
	}

	@Override
	public PageInfo<?> queryMessage(Long userId, Integer page, Integer perPage) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, perPage);
		List<JMessage> list = mapper.queryMessage(userId);
		return new PageInfo<JMessage>(list);
	}

	@Override
	public void updateMessageReaded(Long id) {
		JMessage record =  new JMessage();
		record.setId(id);
		record.setReadflag(true);
		mapper.updateByPrimaryKeySelective(record);
	}

}

