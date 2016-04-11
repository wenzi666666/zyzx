package net.tfedu.zhl.cloud.resource.sysRes.navigation;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.resource.navigation.controller.NavigationController;
import net.tfedu.zhl.cloud.resource.navigation.entity.JSyscourse;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.helper.tests.BaseControllerTestCase;
import net.tfedu.zhl.sso.subject.entity.JSubject;
import net.tfedu.zhl.sso.term.entity.JTerm;

import org.junit.Test;
import org.springframework.util.Assert;

public class NavigationControllerTest extends BaseControllerTestCase{

	@Resource NavigationController navigationController;
	
	/**
	 * 学段controller单元测试
	 * @throws IOException
	 */
	@Test
	public void navigationTest()throws IOException{
		request = newGet("/resRestAPI/v1.0/terms");
        ResultJSON resultJSON = navigationController.selectAllTerms(request, response);

        Assert.isTrue(resultJSON != null);
        List<JTerm> terms = (List<JTerm>) resultJSON.getData();
        Assert.isTrue(terms.size() > 0);
        for (int i = 0; i < terms.size(); i++) {
            System.out.println(terms.get(i).getId() + ":" + terms.get(i).getName());
        }
	}
	
	/**
	 * 学科controller单元测试
	 * @throws IOException
	 */
	@Test
    public void testGetAllSubjectsByTerm() throws IOException {
        request = newGet("/resRestAPI/v1.0/subjects");
        request.setParameter("termId", "1");
        ResultJSON resultJSON = navigationController.getAllSubjectsByTerm(request, response);
        Assert.isTrue(resultJSON != null);
        List<JSubject> subjects = (List<JSubject>) resultJSON.getData();
        Assert.isTrue(subjects.size() > 0);
        for (int i = 0; i < subjects.size(); i++) {
            System.out.println(subjects.get(i).getId() + ":" + subjects.get(i).getName());
        }
    }
	
	/**
	 * 根据学段、学科查询版本controller单元测试
	 * @throws IOException
	 */
	@Test
    public void testEditionController() throws IOException {
        request = newGet("/resRestAPI/v1.0/editions");
        request.setParameter("termId", "1");
        request.setParameter("subjectId", "2");

        ResultJSON resultJSON = navigationController.getAllEditions(request, response);
        Assert.isTrue(resultJSON != null);
        List<JSyscourse> editons = (List<JSyscourse>) resultJSON.getData();
        Assert.isTrue(editons.size() > 0);
        for (int i = 0; i < editons.size(); i++) {
            System.out.println(
                    editons.get(i).getId() + ":" + editons.get(i).getName() + ":" + editons.get(i).getTfcode());
        }
    }
	
	/**
	 * 根据版本id，查询教材controller单元测试
	 * @throws IOException
	 */
	@Test
    public void testBooksController() throws IOException {
        request = newGet("/resRestAPI/v1.0/books");
        request.setParameter("pnodeId", "101140105");

        ResultJSON resultJSON = navigationController.getAllBooksByEdition(request, response);
        Assert.isTrue(resultJSON != null);
        List<JSyscourse> books = (List<JSyscourse>) resultJSON.getData();
        Assert.isTrue(books.size() > 0);
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i).getId() + ":" + books.get(i).getName() + ":" + books.get(i).getTfcode());
        }

    }
}
