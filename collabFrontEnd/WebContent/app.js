

var app = angular.module('myApp', [ 'ngRoute', 'ngCookies' ]);

console.log('----Starting app.js')
app.config(function($routeProvider) {
	$routeProvider.when('/', {
		templateUrl : 'c_common/navbar.html',
	}).when('/register', {
		templateUrl : 'c_user/register.html'
	}).when('/login', {
		templateUrl : 'c_user/login.html'
	}).when('/listOfUsers', {
		templateUrl : 'c_user/listOfUsers.html',
		controller : 'userController'
	}).when('/listofBlog', {
		templateUrl : 'c_blog/listofBlog.html',
		controller : 'blogController'
	}).when('/addNew', {
		templateUrl : 'c_blog/addNew.html',
		controller : 'blogController'
	}).when('/editBlog/:blogId', {
		templateUrl : 'c_blog/editBlog.html',
		controller : 'editBlogController'
	}).when('/listOfJobs', {
		templateUrl : 'c_job/jobList.html',
		controller : 'jobController'
	}).when('/addJob', {
		templateUrl : 'c_job/addJob.html',
		controller : 'jobController'
	}).when('/editJob/:jobId', {
		templateUrl : 'c_job/editJob.html',
		controller : 'editJobController'
	}).when('/friendList', {
		templateUrl : 'c_friend/friendList.html',
		controller : 'friendController'
	}).when('/userList', {
		templateUrl : 'c_friend/userList.html',
		controller : 'friendController'
	}).when('/chatPage', {
		templateUrl : 'c_chat/chatPage.html',
		controller : 'chatController'

	}).when('/uploadPicture', {
		templateUrl : 'c_user/uploadPicture.html'
	})

	.otherwise({
		redirectTo : '/'
	})
});

/*
app.run(function ($rootScope,$location,$cookieStore,$http) {
	
$rootScope.$on('$locationChangeStart', function(event,next,current){
		console.log("$locationChangeStart")
		
		var userPages=['/myProfile','create_blog','/addfriend','/search_friend','/view_friend','/viewfriend request','/viewchat','/view_applied_job']
		var adminPages=["/post_job","/manage_user"]
		
		var CurrentPage=$location.path() currentPage=""
		
		var isUserPage = $.inArray(currentPage,userPages)
		var isAdminPage = $.inArray(currentPage,adminPages)
		
		var isLoggedIn=$root.currentUser.id;
		
		console.log("isLoggedIn:" +isLoggedIn)
		console.log("isLoggedIn:" +isUserPage)
		console.log("isLoggedIn:" +isAdminOage)
		
		if(!isLoggedIn)
			{
			if(isUserPage===0 || isAdminPage===0){
				console.log("navigating to login  page")
				alert("you need to login")
				$location.path('/');
			}
			}
		else{
			var role=$rootScope.currentUser.role;
			console.log("role is:" +role)
			
			if(isAdminPage>=0 && role!='admin'){
				alert("you cannot do this operation as you are not loggedin as admin")
				$location.path('/');
			}
		}
})
})*/