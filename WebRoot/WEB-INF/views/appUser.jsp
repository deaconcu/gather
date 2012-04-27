<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
</head>

<body>
	用户：
	<s:property value="appUser.userId" />
	<br />
	<br /> 来自：
	<s:property value="appUser.app.appName" />
	<br />
	<br /> 朋友：[ 
	<s:iterator value="appUser.friends">
		<s:property value="userId" />,&nbsp;&nbsp;
    </s:iterator>]
	<br />
	<br /> 历史登录组：
	<br />
	<s:iterator value="appUser.groups">(
		<s:iterator value="contains">
			<s:property value="app.appName" />: <s:property value="userId" />,&nbsp;&nbsp;
		</s:iterator>)
		<br />
	</s:iterator>
</body>
</html>
