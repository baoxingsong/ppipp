<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<% String uuid=UUID.randomUUID().toString(); %>
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



<style type="text/css">
#fileQueue {
	width: 400px;
	height: 300px;
	overflow: auto;
	border: 1px solid #E5E5E5;
	margin-bottom: 10px;
}
</style>

<link href="skin/css/uploadify/uploadify.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="skin/js/uploadify/swfobject.js"></script>
<script type="text/javascript"	src="skin/js/uploadify/jquery.uploadify.v2.1.4.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#sequencefile').uploadify( {
			'uploader' : 'skin/js/uploadify/uploadify.swf',
			'script' : '../uploadFile?savePath=<%=uuid%>',  
			'folder' : 'upldaoad',
			'cancelImg' : 'skin/img/uploadify/cancel.png',
			'fileDataName' : 'fileInput', //和input的name属性值保持一致就好，Struts2就能处理了   
			'auto' : true,//是否选取文件后自动上传   
			'multi' : true,//是否支持多文件上传 
			'simUploadLimit' :1,//一次同步上传的文件数目   
			'queueSizeLimit':1,
			'sizeLimit' : 1024000000, //单位为字节
				'onError' : function(event, ID, fileObj, errorObj) {
					if (errorObj.type === "File Size") {
						alert('you are trying to upload a too large file, it will be cancelled!');
						$('#uploadsequencefile').uploadifyClearQueue(ID);
					}
				},
				
				'onQueueFull': function(event,data) {  
					alert("you can upload at most 1 files at one time");
				},
    		 
				'onComplete': function(event, queueID, fileObj, response, data) {
					$('#uploadsequencefile').val(fileObj.name);
				},
				
				'onCancel': function(event, queueID, fileObj, data) {
					$('#uploadsequencefile').val("");
				}
		});
	});
</script>
  </head>
  
  <body>
  <jsp:include page="/template/head_navigation.txt" />
  	<div id="content-wrapper">

<!-- download-builder -->
<div id="download-builder" >
<h1>M-MIST</h1>
<p>M-MIST method is based on a bind profile-bind profile contacts derived from PPIs from the Biomolecular Interaction Network Database(BIND). If a bind profile pair is found in the observed PPIs, other protein pair matches with the bind profile group pair, then these two proteins can be thought to have interaction.</p>
<p>&nbsp;</p>
<p>All it need is just the sequence information. So you should upload the file in FASTA format. <a href="sampledata/uniprot-taxonomy3A4565.fasta" target="_blank">The sample data could be find here.</a></p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<s:form action="startMmist" method="post" target="_self" validate="true">
<tr><td></td><td><input type="file" name="sequencefile" id="sequencefile" /></td></tr>
<tr><td></td><td>Upload Sequence File in FASTA Format</td></tr>
<s:textfield name="uploadsequencefile" id="uploadsequencefile" class="inputbox" readonly="true"></s:textfield>

<input type="hidden" name="uuid" value="<%=uuid%>"/>
<tr>
<td><button type="submit" id="download_zip" >Submit</button>&nbsp;&nbsp;&nbsp;</td>
<td><button type="reset" id="download_zip">Reset</button></td>
</tr>
</s:form>
</div><!-- /download-builder -->
<jsp:include page="/template/footer.txt" />
  </body>
</html>
