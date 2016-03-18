package net.tfedu.zhl.cloud.core.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.tfedu.zhl.cloud.core.entity.Country;
import net.tfedu.zhl.cloud.core.service.CountryService;

@Controller 
@RequestMapping("/country*")
public class CountryController {
	
	@Resource CountryService countryService;

	@RequestMapping(value = "/all")  
	@ResponseBody
    public List<Country> getAll(HttpServletRequest request, HttpServletResponse response) throws IOException { 
		return countryService.selectAll();
	}
}
