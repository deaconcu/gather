<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
	</head>
	
	<body>
	    <h1>feedbacks</h1>
	    
	    <s:if test="page.current > 1"><a href="feedback_list.do?page.current=<s:property value="page.current - 1" />">上一页</a></s:if>
	    <a href="feedback_list.do?page.current=<s:property value="page.current + 1" />">下一页</a><br /><br />
	    <s:iterator value="#request.list">
            title: <s:property value="title" /> <br />
            content: <s:property value="content" /> <br />	
            email: <s:property value="email" /><br />
            postTime: 
            <s:set name="date" value="posttime"/>
            <%
            Object obj = pageContext.getAttribute("date");
            out.print((new Date(Long.valueOf(obj.toString()))).toString());
            %>
            <hr>
	    </s:iterator>
	    <br />
	    <s:if test="page.current > 1"><a href="feedback_list.do?page.current=<s:property value="page.current - 1" />">上一页</a></s:if>
        <a href="feedback_list.do?page.current=<s:property value="page.current + 1" />">下一页</a><br /><br />
	    <br />
	    <br />
	
	</body>

</html>