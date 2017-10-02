/*
 * Windows 8 METRO UI design
 * http://www.metro-webdesign.tk
 * 
 * Copyright (c) 2012 Thomas Verelst
 * Do NOT reupload, redistribuate or claim that this is your work. You may use this on any website, as long as you let this header here and the copyright comment on the index. Thanks
 */
String.prototype.stripSpaces = function(){ return this.replace(/\s/g,"-"); };
nLiveTiles = 0;
animatedTiles = new Array();
loadPageBusy = 0;
var Browser = {Version: function() { var version = 999; if (navigator.appVersion.indexOf("MSIE") != -1)version = parseFloat(navigator.appVersion.split("MSIE")[1]);return version;}}
if(Browser.Version() <9){ /* Hack for IE8 or lower */ 
	hidePageSpeed = 0;
}
$(document).ready(function(){
	makeMenuBar();
	loadPage = function(){
		$("#content").html(""); 
		if(window.location.href.indexOf("?_escaped_fragment_=") != -1){
			currentHash=window.location.href.split("?_escaped_fragment_=").pop()
		}else{
			currentHash = window.location.hash.split("&")[0].replace("!","");
		}
		if(currentHash == "#home" || currentHash  == "#" || !currentHash){
			document.title = siteTitle;
			tiles();// Make Tiles in HTML */
			$("#content").show();// Show the tiles with an animation
			if(showEffect == "fade"){
				$(".tile").first().fadeIn(showTileSpeed, function showNext() {
		    		$(this).next(".tile").fadeIn(showTileSpeed, showNext);
			 	});
			}else{
				$(".tile").first().show(showTileSpeed, function showNext() {	
		    		$(this).next(".tile").show(showTileSpeed, showNext);
			 	});
			}
                        $(".tile").mousedown(function(){
		            $(this).stop(false,true).animate({'margin-top':'+=20','margin-left':'+=20', height:'-=40', width:'-=40'},700)
			   .animate({'margin-top':'-=20','margin-left':'-=20', height:'+=40', width:'+=40'},1000,"swing")
	                });
		}else{
			$("#navstripe").fadeIn(showPageSpeed);
			$("#navbar").show(showPageSpeed);
			page = currentHash.replace(/-/g," ").replace("#","")
			if(typeof pageLink[page] == "undefined"){
				$("#content").html("<h2 style='margin-top:0px;'>We're sorry :(</h2>the page you're looking for is not found.").show(400); // show with nice animation :)
			}else{
				document.title = siteTitle+" - "+page;
				loadPageBusy = $.ajax({url:pageLink[page],success:function(data){
                                        $("#content").html(data)
					if(window.location.hash.indexOf("&show_all") == -1){
						$(".slide_content").css("display","none");
					}else{
						$("a#show_all").html("Hide All").attr("href",$("a#show_all").attr("href").replace("&show_all",""));
					}
					$("#content").show(showPageSpeed);
				}});
			}
		}		
	}
	loadPage();
	$("#nav_title").click(function(){
		showPage("home");
	});
	
	$(window).hashchange( function(){ // history button
		var hash = window.location.hash.split("&")[0];
		if(hash == "#home" || hash == "#"   || !hash){
			 $("#navbar").fadeOut(hidePageSpeed);
			 $("#navstripe").fadeOut(hidePageSpeed);
		}
		$("#nav_sub").fadeOut(hidePageSpeed); // fade out navigation title
		$("#content").fadeOut(hidePageSpeed,function(){loadPage()});
	})
	$("a").live("click", function(){
		if($(this).attr("href")==window.location.hash){$(window).hashchange();};
	});
});
makeMenuBar = function(){
	$("#navbar").html('');
	for(var i in menuLink){
		$("#navbar").append("<a "+makeLink(i)+" style='background-color:"+menuLink[i]+";' class='navbaritem'>"+i+"</a>");
	}
	$("#navbar").append('<a href="/forum/" style="background-color:#FE2E64;" class="navbaritem">Forum</a>');
}
showPage = function(page){
if(loadPageBusy != 0){
loadPageBusy.abort()
}
        
	if(window.location.hash.split("&")[0].replace("!","").replace("#","") == page){
		$(window).hashchange();
	}
	window.location.hash = page; // make our url so it likes linking, changing the hash will call hashchange ^
}

new_livetile = function(id,text1,text2,text3,text4,text5,speed,slide){
	if(typeof animatedTiles[id] == "undefined" || animatedTiles[id] == false){
		animatedTiles[id] = true;
		var n_sl = slide+1;
		switch(n_sl){
			case 1:var text = text1;break;
			case 2:var text = text2;break;
			case 3:var text = text3;break;
			case 4:var text = text4;break;
			case 5:var text = text5;break;
			default:var text=text1;
		}
		switch(""){
			case text1: if(n_sl>-1){n_sl=0};break;
			case text2: if(n_sl>0){n_sl=0};break;
			case text3: if(n_sl>1){n_sl=0};break;
			case text4: if(n_sl>2){n_sl=0};break;
			case text5: if(n_sl>3){n_sl=0};break;
			default: if(n_sl>4){n_sl=0};break;
		}
		$("#"+id).animate({opacity: 0,top: '+=15'},250,function(){$("#"+id).html(text).css("top",-15).animate({opacity: 1,top: '+=15'},250,function(){$("#"+id).css("top",0);animatedTiles[id]=false;})});
	}
	if(currentHash == "" || currentHash == "#home" || currentHash == "#"){//if we're home
		setTimeout(function(){new_livetile(id,text1,text2,text3,text4,text5,speed,n_sl)},speed);
	}
}
makeLink = function(linkPage){
	if(linkPage.substr(0,7) == "http://"){return "href='"+linkPage+"'";}
	if(linkPage.substr(0,1) == "/"){return "href='"+linkPage+"'";}
	return "href='"+linkPage.stripSpaces()+"'";	
}	