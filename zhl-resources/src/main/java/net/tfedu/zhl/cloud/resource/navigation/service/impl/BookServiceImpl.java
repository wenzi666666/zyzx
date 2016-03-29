package net.tfedu.zhl.cloud.resource.navigation.service.impl;

import java.util.List;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.resource.navigation.dao.JSyscourseMapper;
import net.tfedu.zhl.cloud.resource.navigation.entity.JSyscourse;
import net.tfedu.zhl.cloud.resource.navigation.service.BookService;

import org.springframework.stereotype.Service;

@Service("bookService")
public class BookServiceImpl implements BookService{
	
	@Resource JSyscourseMapper jSyscourseMapper;

	//获得所有教材信息
	@Override
	public List<JSyscourse> getAllBooks(long pnodeId){
		return jSyscourseMapper.getAllBooks(pnodeId);
	}
}
