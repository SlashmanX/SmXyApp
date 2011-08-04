$(document).bind("mobileinit", function() {
	      $.mobile.page.prototype.options.addBackBtn = true;
	      $.mobile.defaultPageTransition = 'none';
	      $.mobile.useFastClick  = false;
});


document.addEventListener("deviceready", onDeviceReady, false);

function onDeviceReady() {
	window.plugins.statusBarNotification.notify("Put your title here", "Put your message here");
    // JSON url
    var url="http://slashmanx.com/admin/android/getPosts.php";
	    var postsArray = [];
	    
	    
	    // get the json file
	 	$.getJSON(url,function(json){
		    // this is where we can loop through the results in the json object
		    $.each(json.blogposts, function(i,posts){
		     // this is where we do what we want with the tweet
		     	postsArray.push = posts.blogpost;
		     	var newList = "<li data-theme='c' class='ui-btn ui-btn-icon-right ui-li ui-btn-up-c' id='post-"+ posts.blogpost.id+"'>";
		     	newList+="<div class='ui-btn-inner ui-li'>";
				newList+="<div class='ui-btn-text'>";
				newList+="<a href='index.html' class='ui-link-inherit'>";
				//newList+="<p class='ui-li-aside ui-li-desc'><strong>"+ posts.blogpost.date_posted+"</strong></p>";
				newList+="<h3 class='ui-li-heading'>"+ posts.blogpost.title +"</h3>";
				newList+="<p class='ui-li-desc'>"+ posts.blogpost.date_posted+"</p>";
				newList+="</a>";
				newList+="</div>";
				newList+="<span class='ui-icon ui-icon-arrow-r'/>";
				newList+="</div>";
				newList+="</li>";
		     	$("#listBlogPosts").append(newList);
		     	$("#listBlogPosts").listview("refresh");
			});
			$(".spinner").hide();
		});
}