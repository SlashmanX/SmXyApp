$(document).bind("mobileinit", function() {
	      $.mobile.page.prototype.options.addBackBtn = true;
	      $.mobile.defaultPageTransition = 'none';
	      $.mobile.useFastClick  = false;
});
var ready = true;
    // JSON url
var url="http://slashmanx.com/admin/android/getPosts.php";
var posturl="http://slashmanx.com/admin/android/post.php";
postsArray = (JSON.parse(window.localStorage['postsStorage']));
if(postsArray==null)
{
   	var postsArray = [];
}

document.addEventListener("deviceready", onDeviceReady, false);

function onDeviceReady() {
	$(".spinner").show();
    if(window.postsArray.length==0){
    	window.ready = false;
    	alert ("Empty Array");
		getPosts();
	}
	
	if($("#listBlogPosts > li").size() == 0)
	{
		addToList();
	}
	$("#listBlogPosts").listview("refresh");
		
		$('div[id="blogposts"] ul[data-role="listview"] a').live("vclick", function() {  
    		window.plugins.statusBarNotification.notify("Put your title here", "Put your message here"); 
		}); 
		
        function onSuccess(data, status)
        {
            data = $.trim(data);
            $("#notification").text(data);
        }
 
        function onError(data, status)
        {
            data = $.trim(data);
            $("#notification").text("Error: "+status);
        } 
        
            $("#addSubmit").click(function(){
 
               var values = {};
				$.each($('#addBlogPostForm').serializeArray(), function(i, field) {
    				values[field.name] = field.value;
				});
				
				window.plugins.SmXyAddArticlePlugin.add_post(values['title_r'], values['tags_r'],values['article_r'], posturl);
				return false;
            });
            
            $("#addCancel").click(function(){
            
            	history.back(); return false;
            });
}

function getPosts() {
	$.getJSON(url,function(json){
		$.each(json.blogposts, function(i,posts){
		     	window.postsArray.push(posts.blogpost);
		     	//alert ("Adding one:");
			});
		//alert ("Length: "+ postsArray.length);
		window.ready = true;
		window.localStorage['postsStorage']=JSON.stringify(postsArray);
		//alert ("READY!"+ window.ready);
		});
	
}

function addToList(){
	//alert (window.ready);
	if(window.ready==false){
		//alert("Not ready");
		setTimeout('addToList()', 100);
	}	
	
	if(window.ready==true){
		for(post in window.postsArray){
			var blogpost = window.postsArray[post];
			var newList = "<li data-theme='c' class='ui-btn ui-btn-icon-right ui-li ui-btn-up-c' id='post-"+ blogpost.id+"'>";
            newList+="<div class='ui-btn-inner ui-li'>";
            newList+="<div class='ui-btn-text'>";
            newList+="<a class='ui-link-inherit'>";
            //newList+="<p class='ui-li-aside ui-li-desc'><strong>"+ posts.blogpost.date_posted+"</strong></p>";
            newList+="<h3 class='ui-li-heading'>"+ blogpost.title +"</h3>";
            newList+="<p class='ui-li-desc'>"+ blogpost.date_posted+"</p>";
            newList+="</a>";
            newList+="</div>";
            newList+="<span class='ui-icon ui-icon-arrow-r'/>";
            newList+="</div>";
            newList+="</li>";
            $("#listBlogPosts").append(newList);
		}
		$(".spinner").hide(); 
	}
}