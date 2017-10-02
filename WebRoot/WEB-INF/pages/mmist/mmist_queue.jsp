<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<% String randomID=UUID.randomUUID().toString(); %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8">
  <head>
    <base href="<%=basePath%>">
    <title><s:property value="webTitle"/></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="author" content="Baoxing Song">
	<meta http-equiv="contact" content="sognbaoxing@hotmail.com">
	<link rel="stylesheet" href="skin/css/base2.css" type="text/css" media="all" />
	<link rel="stylesheet" href="skin/css/jquery-ui.css" type="text/css" media="all" />
	<script src="skin/js/jquery-ui.min.js" type="text/javascript"></script>
	<script src="skin/allinone.js" type="text/javascript"></script>
  	<META HTTP-EQUIV="Refresh" CONTENT="5">
  </head>
  <body>
	<jsp:include page="/template/head_navigation.txt" />
  	<div id="content-wrapper">
		<!-- download-builder -->
		<div id="download-builder" >
			<h1>There are really too much tasks. You submitting is still in line. Be patient please! </h1>
			<p>&nbsp;</p>
			<p>&nbsp;</p>
			<p>&nbsp;</p>
			<img src="skin/images/state/queue.png" alt="in line" witdh="100" >
		</div><!-- /download-builder -->
<jsp:include page="/template/footer.txt" />
</body>
</html>

