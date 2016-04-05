package net.tfedu.zhl.cloud.resource.bookself.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.tfedu.zhl.cloud.resource.bookself.bean.BookSelfView;
import net.tfedu.zhl.cloud.resource.bookself.dao.JSyscourseUserMapper;
import net.tfedu.zhl.cloud.resource.bookself.service.BookSelfService;


@Service("bookSelfService")
public class BookSelfServiceImpl implements BookSelfService {
	
	@Autowired
	JSyscourseUserMapper mapper;
	

	@Override
	public List<BookSelfView> getAllMyBook(Long userId) {
		// TODO Auto-generated method stub
		return mapper.getAllMyBook(userId);
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
