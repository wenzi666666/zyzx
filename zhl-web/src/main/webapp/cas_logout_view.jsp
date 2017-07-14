<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Thread.sleep(1000);
session.invalidate();
response.sendRedirect(application.getInitParameter("casServerLogoutUrl") + "?service=" +application.getInitParameter("serverName"));
%>