scale = 130; // width and height for 1 tile in px 
tileSpace = 8 // space between tiles in px
showEffect = "move" // fade or move in the tiles
showTileSpeed = 80 // sets the speed of the appearing animation of the tiles in milliseconds
hidePageSpeed = 400 // sets the speed of how fast the content dissapears when switching pages, in milliseconds
showPageSpeed = 500 // sets the speed of the fadeIn of the titles on top of the page, in milliseconds
siteTitle = "Meta-Mesh"

jQuery.fx.off=false; // set to true to disable animations, can be handy for mobile phones or veryyy slow pc's.
jQuery.fx.interval=25; // smoothness of the animations, lower the number for smoother animations, but it will reduce performance. Optimal setting is 25.


/* Pages information */
pageLink= new Array(); // the index of pageLink MUST be the pagename. NEVER REMOVE THIS LINE

/* Menu Bar Information */

menuLink = new Array(); 


/* Tile Templates*/
tile_title_text = function(x,y,width,color,linkPage,title,text){ /* Tile with only a title and description */
	$("#content").append(
	"<a "+makeLink(linkPage)+" class='tile' id='tile' style='position: absolute; \
	margin-top:"+y*(scale+tileSpace)+"px; margin-left:"+x*(scale+tileSpace)+"px; \
	width: "+(width*(scale+tileSpace)-tileSpace)+"px; height:"+scale+"px; \
	background-color:"+color+";'>\
	<div id='title'>"+title+"</div>\
	<div class='desc' style='margin-left: 5px; margin-right: 5px' id='desc'>"+text+"</div>\
	</a>");
}
tile_image = function(x,y,color,linkPage, img,imgsize){ /* Tile with only an image */
	$("#content").append("<a "+makeLink(linkPage)+" id='tile_image' class='tile' style='position: absolute; \
	margin-top:"+y*(scale+tileSpace)+"px;margin-left:"+x*(scale+tileSpace)+"px; \
	width: "+scale+"px; height:"+scale+"px; \
	background-color:"+color+";'>\
	<img src='"+img+"' height="+imgsize+" width="+imgsize+" style='margin-left: "+((scale-imgsize)*0.5)+"px; margin-top: "+((scale-imgsize)*0.5)+"px'/></a>");
}
tile_title_text_image = function(x,y,color,linkPage,title,text,img,imgsize,imgtotop,imgtoleft){ // Tile with an image on the left side, a title, and a description, width is always 2
	$("#content").append("<a "+makeLink(linkPage)+" class='tile' id='tile_title_image' style='position: absolute; \
	margin-top:"+y*(scale+tileSpace)+"px;margin-left:"+x*(scale+tileSpace)+"px; \
	width: "+(2*(scale+tileSpace)-tileSpace)+"px; height:"+scale+"px; \
	background-color:"+color+";'>\
	<img style='float:left; margin-top:"+imgtotop+"px;margin-left:"+(imgtoleft)+"px;' src='"+img+"' height="+imgsize+" width="+imgsize+"/> \
	<div id='title' style='min-width: 50px; margin-left:"+(imgsize+5+imgtoleft)+"px;'>"+title+"</div>\
	<div class='desc' id='desc' style='margin-left:"+(imgsize+6+imgtoleft)+"px;'>"+text+"</div>\
	</a>");
}
tile_custom = function(x,y,width,height,color,linkPage,content){ // make your own tiles
	$("#content").append("<a "+makeLink(linkPage)+" class='tile' id='tile_title_image' style='position: absolute; \
	margin-top:"+y*(scale+tileSpace)+"px;margin-left:"+x*(scale+tileSpace)+"px; \
	width: "+(width*(scale+tileSpace)-tileSpace)+"px; height:"+(height*(scale+tileSpace)-tileSpace)+"px; \
	background-color:"+color+";'>\
	"+content+"\
	</a>");
}
tile_live = function(x,y,width,color,linkPage,title,speed,text1,text2,text3,text4,text5){
	nLiveTiles+=1;
	$("#content").append(
	"<a "+makeLink(linkPage)+" class='tile' style='position: absolute; \
	margin-top:"+y*(scale+tileSpace)+"px; margin-left:"+x*(scale+tileSpace)+"px; \
	width: "+(width*(scale+tileSpace)-tileSpace)+"px; height:"+scale+"px; \
	background-color:"+color+";'>\
	<div id='title' align='center'>"+title+"</div>\
	<div class='livetile' align='center' style='margin-left: 5px; margin-right: 5px;' id='livetile"+nLiveTiles+"' >"+text1+"</div>\
	</a>");
	new_livetile(("livetile"+nLiveTiles),text1,text2,text3,text4,text5,speed,0); // init this tile
}
tile_image_slider = function(x,y,width,height,color,linkPage,img,imgwidth,imgheight,text,slideDistance){ // make your own tiles
	$("#content").append("<a "+makeLink(linkPage)+" class='tile' id='tile_image_slider"+x+y+"' \
        onclick='showPage(linkPage)'\
        style=' \
	margin-top:"+y*(scale+tileSpace)+"px;margin-left:"+x*(scale+tileSpace)+"px; \
	width: "+(width*(scale+tileSpace)-tileSpace)+"px; height:"+(height*(scale+tileSpace)-tileSpace)+"px; \
	background-color:"+color+";position:absolute'>\
	<div class='tile_slider_wrapper' style='position:absolute;'>\
	<div style='width: "+(width*(scale+tileSpace)-tileSpace)+"px; height:"+(height*(scale+tileSpace)-tileSpace)+"px;'>\
	<img src='"+img+"' height="+imgheight+" width="+imgwidth+" style='; margin-top: "+((scale-imgheight)*0.5)+"px'/>\
	</div>\
	<div style='padding:5px;text-align:center;'>"+text+"</div>\
	</div>\
	</a>");
	$(".tile_slider_wrapper").css("top",0);
	$("#tile_image_slider"+x+y).mouseover(function(){
		$(this).find(".tile_slider_wrapper").stop().animate({top:-$("#tile_image_slider"+x+y).height()*slideDistance},300,"linear");
	});
	$("#tile_image_slider"+x+y).mouseleave(function(){
		$(this).find(".tile_slider_wrapper").animate({top:0},400,"linear");
	});
}	

tile_image_slider_unlive = function(x,y,width,height,color,linkPage,img,imgwidth,imgheight,text,slideDistance){ // make your own tiles
	$("#content").append("<a "+" class='tile' id='tile_image_slider"+x+y+"' \
        onclick='songbaoxingconfirm("+'"'+linkPage+'"'+")'\
        style=' \
	margin-top:"+y*(scale+tileSpace)+"px;margin-left:"+x*(scale+tileSpace)+"px; \
	width: "+(width*(scale+tileSpace)-tileSpace)+"px; height:"+(height*(scale+tileSpace)-tileSpace)+"px; \
	background-color:#bfbfbf"+";position:absolute'>\
	<div class='tile_slider_wrapper' style='position:absolute;'>\
	<div style='width: "+(width*(scale+tileSpace)-tileSpace)+"px; height:"+(height*(scale+tileSpace)-tileSpace)+"px;'>\
	<img src='"+img+"' height="+imgheight+" width="+imgwidth+" style='; margin-top: "+((scale-imgheight)*0.5)+"px'/>\
	</div>\
	<div style='padding:5px;'>"+text+"</div>\
	</div>\
	</a>");
	$(".tile_slider_wrapper").css("top",0);
	$("#tile_image_slider"+x+y).mouseover(function(){
		$(this).find(".tile_slider_wrapper").stop().animate({top:-$("#tile_image_slider"+x+y).height()*slideDistance},300,"linear");
	});
	
	$("#tile_image_slider"+x+y).mouseleave(function(){
		$(this).find(".tile_slider_wrapper").animate({top:0},400,"linear");
	});
	
}	
var songbaoxingconfirm=function(linkPage){
	confirm("You should log in to use this model!", function () {
		window.location.href = 'user/user/logininput?sourceurl='+linkPage;
	})
}