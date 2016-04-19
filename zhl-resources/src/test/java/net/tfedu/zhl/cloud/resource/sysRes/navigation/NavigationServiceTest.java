package net.tfedu.zhl.cloud.resource.sysRes.navigation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.resource.navigation.entity.JSyscourse;
import net.tfedu.zhl.cloud.resource.navigation.entity.TreeNode;
import net.tfedu.zhl.cloud.resource.navigation.service.BookService;
import net.tfedu.zhl.cloud.resource.navigation.service.EditionService;
import net.tfedu.zhl.cloud.resource.navigation.service.TermService;
import net.tfedu.zhl.cloud.resource.navigation.service.TermSubjectService;
import net.tfedu.zhl.cloud.resource.navigation.service.TreeService;
import net.tfedu.zhl.helper.tests.BaseServiceTestCase;
import net.tfedu.zhl.sso.subject.entity.JSubject;
import net.tfedu.zhl.sso.term.entity.JTerm;

import org.junit.Test;
import org.springframework.util.Assert;

/**
 * 目录service 单元测试
 * @author WeiCuicui
 *
 */
public class NavigationServiceTest extends BaseServiceTestCase{

    @Resource TermService termService;
    
    @Resource TermSubjectService termSubjectService;
    
    @Resource EditionService editionService;
    
    @Resource BookService bookService;
    
    @Resource TreeService treeService;
    
    
   
    /**
     * 查询学段
     * @throws IOException
     */
	@Test
	public void testGetAllTerms() throws IOException{
		List<JTerm> terms = termService.selectAll();
		
		Assert.isTrue(terms.size() > 0);
		
	    for (int i = 0; i < terms.size(); i++) {
	         System.out.println(terms.get(i).getId() + ":" + terms.get(i).getName());
	    }
	} 
    
    /**
     * 根据学段，查询学科
     * @throws IOException
     */
	@Test
	public void testGetSubjectsByTerm() throws IOException{
		
		long termId = 1;
		
		List<JSubject> subjects = termSubjectService.getAllSubjectsByTerm(termId);
		
		Assert.isTrue(subjects.size() > 0);
		
	    for (int i = 0; i < subjects.size(); i++) {
	         System.out.println(subjects.get(i).getId() + ":" + subjects.get(i).getName());
	    }
	} 
	
	/**
     * 根据学段、学科，查询版本
     * @throws IOException
     */
	@Test
	public void testGetEditions() throws IOException{
		
		long termId = 1;
		long subjectId = 1;
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("termId", termId);
		map.put("subjectId", subjectId);
		
		List<JSyscourse> editions= editionService.getAllEditionsByTermAndSub(map);
		
		Assert.isTrue(editions.size() > 0);
		
	    for (int i = 0; i < editions.size(); i++) {
	    	 System.out.println(
	    			 editions.get(i).getId() + ":" + editions.get(i).getName() + ":" + editions.get(i).getTfcode());
	    }
	} 
    
	/**
     * 根据版本，查询教材
     * @throws IOException
     */
	@Test
	public void testGetBooks() throws IOException{
		
		long pnodeId = 101140105; //版本的id
		
		String proCode = "zy";
		
		List<JSyscourse> books= bookService.getAllBooks(pnodeId, proCode);
		
		Assert.isTrue(books.size() > 0);
		
	    for (int i = 0; i < books.size(); i++) {
	    	 System.out.println(
	    			 books.get(i).getId() + ":" + books.get(i).getName() + ":" + books.get(i).getTfcode());
	    }
	}
	
	/**
	 * 根据父结点id，查询课程目录树service单元测试
	 * @throws IOException
	 */
	@Test
    public void testTreeService() throws IOException {
        long pnodeId = 67527;
        List<TreeNode> resultNodes = new ArrayList<TreeNode>();
        resultNodes = treeService.geTreeNodes(pnodeId);

        Assert.isTrue(resultNodes.size() > 0);
        for (int i = 0; i < resultNodes.size(); i++)
            log.info(resultNodes.get(i).toString());
    }
}
