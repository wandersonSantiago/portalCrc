app.config(function($stateProvider, $urlRouterProvider, $httpProvider) {
 
 // $urlRouterProvider.otherwise("/404");

  $stateProvider
/*     .state('home', {
      url: "",
      templateUrl: "views/home.html",
      ncyBreadcrumb: {
    	    label: 'Home'
    	  }
    })
     .state('/', {
      url: "",
      templateUrl: "views/home.html",
      ncyBreadcrumb: {
    	    label: 'Home'
    	  }
    })*/
    .state('login', {
		templateUrl : "views/login.html",
		 ncyBreadcrumb: {
	    	    skip: true // Never display this state in breadcrumb.
	    	  }
	})
    .state('/404', {
      url: "/404",
      templateUrl: "404.html",
      ncyBreadcrumb: {
    	    skip: true // Never display this state in breadcrumb.
    	  }
    })
     .state('sem-permissao', {
      url: "/404",
      templateUrl: "views/erros/permissao.html",
      ncyBreadcrumb: {
    	    skip: true // Never display this state in breadcrumb.
    	  }
    })
    $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
    
});