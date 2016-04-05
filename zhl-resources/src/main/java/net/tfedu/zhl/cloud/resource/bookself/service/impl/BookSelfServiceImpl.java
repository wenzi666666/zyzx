package net.tfedu.zhl.cloud.resource.bookself.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.tfedu.zhl.cloud.resource.bookself.bean.BookSelfView;
import net.tfedu.zhl.cloud.resource.bookself.dao.JSyscourseUserMapper;
import net.tfedu.zhl.cloud.resource.bookself.service.BookSelfService;
import net.tfedu.zhl.cloud.utils.datatype.StringUtils;
import net.tfedu.zhl.sso.user.dao.JUserMapper;
import net.tfedu.zhl.sso.user.entity.JUser;
import net.tfedu.zhl.sso.user.service.UserService;


@Service("bookSelfService")
public class BookSelfServiceImpl implements BookSelfService {
	
	@Autowired
	JSyscourseUserMapper mapper;
	
	
	@Autowired
	JUserMapper userMapper;
	

	@Override
	public List<BookSelfView> getAllMyBook(Long userId,String title) {
		// TODO Auto-generated method stub
		
		
		
		List <BookSelfView> list = mapper.getAllMyBook(userId,title==null?"":("%"+title.trim()+"%"));
		//如果没有书架内容 默认增加一个课标版的教材
		if(StringUtils.isEmpty(title)&&(list==null || list.size()==0)){
			
			JUser user = userMapper.getUserById(userId);
			long termId = user.getTermId();
			String subjectIds =  user.getSubjectIds();
			long subjectId = 0;
			if(StringUtils.isNotEmpty(subjectIds)){
				String _id = subjectIds.split(",")[0];
				subjectId = Long.parseLong(_id);
			}
			//增加默认知识点到书架
			mapper.addMyBookByDefaultKnowledge(userId, termId, subjectId);
			list = mapper.getAllMyBook(userId,title==null?"":("%"+title.trim()+"%"));
		}
		return list;
	}

	@Override
	public void delMyBook(Long userId, String tfcode) {
		// TODO Auto-generated method stub
		mapper.delMyBook(userId, tfcode);
	}

	@Override
	public void addMyBook(Long userId, String tfcode) {
		// TODO Auto-generated method stub
		mapper.addMyBook(userId, tfcode);
	}

	@Override
	public List<BookSelfView> queryBook(Long userId, String tfcode) {
		// TODO Auto-generated method stub
		return mapper.queryBook(userId, tfcode);
	}

}