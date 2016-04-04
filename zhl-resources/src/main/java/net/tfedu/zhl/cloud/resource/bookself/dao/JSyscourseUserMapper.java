package net.tfedu.zhl.cloud.resource.bookself.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.tfedu.zhl.cloud.resource.bookself.bean.BookSelfView;
import net.tfedu.zhl.cloud.resource.bookself.entity.JSyscourseUser;
import net.tfedu.zhl.helper.CoreMapper;

public interface JSyscourseUserMapper extends CoreMapper<JSyscourseUser> {
	
	

	/**
	 * 获取我的全部书架
	 * @param userId
	 * @return
	 */
	public List<BookSelfView> getAllMyBook(Long userId);
	
	
	/**根据条件删除
	 * 
	 * @param userId
	 * @param syscourseId
	 */
	public void  delMyBook(@Param("userId")Long userId,@Param("tfcode")String tfcode);
	
	
	
	
	
	/**
	 * 增加我的书架书籍
	 * @param userId
	 * @param syscourseId
	 */
	public void addMyBook(@Param("userId")Long userId,@Param("tfcode")String tfcode);
	
	
	
	/**
	 * 获取版本下的书籍
	 * @param userId
	 * @param tfcode
	 * @return
	 */
	public List<BookSelfView> queryBook(@Param("userId")Long userId,@Param("tfcode")String tfcode);
}