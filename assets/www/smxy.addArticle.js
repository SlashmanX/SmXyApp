var AddArticle = function() {
};
AddArticle.prototype.add_post = function(title, tags, article, url, win, fail) {
  PhoneGap.exec(win, fail, "SmXyAddArticlePlugin", "add_post", [title, tags, article, url]);
};


PhoneGap.addConstructor(function() { 
  // Register the javascript plugin with PhoneGap
  PhoneGap.addPlugin('SmXyAddArticlePlugin', new AddArticle());

  // Register the native class of plugin with PhoneGap
  //navigator.app.addService("SmXyAddArticlePlugin", "com.phonegap.plugins.SmXy.SmXyAddArticlePlugin"); 
  
});