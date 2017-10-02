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
	<link rel="stylesheet" href="skin/mesh/base2.css" type="text/css" media="all" />
	<link rel="stylesheet" href="skin/css/jquery-ui.css" type="text/css" media="all" />
	<script src="skin/js/jquery-ui.min.js" type="text/javascript"></script>
	<script src="skin/allinone.js" type="text/javascript"></script>
	<script src="skin/mesh/display.js" type="text/javascript"></script>
	<script src="skin/mesh/main.js" type="text/javascript"></script>
	<link rel="stylesheet" href="skin/mesh/main.css" type="text/css" charset="utf-8" />
  	<script type="text/javascript">
	/* Tiles */
	tiles = function(){/* Add your prefered tiles here */
		tile_live(1,0,5,"#bfbfbf","mmist/", "&nbsp;",4000,"BPCSC method is based on a bind profile-bind profile contacts derived from PPIs fromn the Biomolecular Interaction Network Database(BIND)."," If a bind profile pair is found in the observed PPIs, other protein pair matches with the bind profile group pair, then these two proteins can be thought to have interaction.","","");
		tile_image_slider(0,0,1,1,"#558ed5","mmist/","skin/mesh/mmist.png","130","130","<span style='font-size:18px'>bind profiles from structural topology</span>",1.05);
		tile_live(1,1,5,"#bfbfbf","pssm/", "&nbsp;",4000,"D-MIST is based on a two-step approach. First, potential domain-binding motifs are extracted from structural data."," These motifs are then converted to sequence profiles in the form of position-specific scoring matrices (PSSMs).","If one protein has a domain and another has corresponding motif information, the two proteins are considered to interact with each other.","");
		tile_image_slider(0,1,1,1,"#558ed5","pssm/","skin/mesh/dmist.png","130","130","<span style='font-size:18px'>domain motif interactions from structural topology</span>",1.05);
		tile_live(1,2,5,"#bfbfbf","interolog/", "&nbsp;",4000,"An interolog is a conserved interaction between a pair of proteins which have interacting homologs in another organism.","Suppose that A and B are two different interacting human proteins, and A' and B' are two different interacting dog proteins. Then the interaction between A and B is an interolog of the interaction between A' and B' if the following conditions all hold:","1)A is a homolog of A'. (Protein homologs have similar amino acid sequences and derive from a common ancestral sequence); 2)B is a homolog of B'; 3)A and B interact; 4)A' and B' interact. Thus, interologs are homologous pairs of protein interactions across different organisms.","");
		tile_image_slider(0,2,1,1,"#558ed5","interolog/","skin/mesh/interolog.png","130","130","<span style='font-size:10px'>An interolog is a conserved interaction between a pair of proteins which have interacting homologs in another organism.</span>",1.05);
		tile_live(1,3,5,"#ebebeb","dfsp/", "&nbsp;",4000,"Protein–protein interaction network-based detection of functionally similar proteins within species. The input data is protein-protein interaction network,","After denoting protein–protein interaction networks using graphs, we split the graphs into subgraphs using the 1-hop method. Proteins with functional similarities in a species were detected using a method of modified shortest path to compare these subgraphs and to find the eligible optimal results.","Some functionally similar proteins with low sequence similarity that cannot detected by sequence alignment would be  identified..","");
		tile_image_slider(0,3,1,1,"#9e9ef5","dfsp/","skin/mesh/dfsp.png","130","130","<span style='font-size:19px'>Detection of Dunctionally Similar Proteins within species</span>",1.05);
	}
	</script>
</head>
<body>
  <jsp:include page="/template/head_navigation.txt" />
  	<div id="content-wrapper">
<!-- download-builder -->
<div id="download-builder" >
<div id="content" style="height:600px; font-size:15px;"></div>
</div><!-- /download-builder -->
<jsp:include page="/template/footer.txt" />
</body>
</html>
