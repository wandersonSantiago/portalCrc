app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		.state('comunicacao', {
			abstract : true,
			url : '/comunicacao',
			templateUrl : 'views/comunicacao/comunicacao.index.html',
			ncyBreadcrumb: {			
			 	parent: 'home.menu',
				    label: 'comunicacao'
				  }
		})
		.state('comunicacao.menu', {
			url : "/menu",
			templateUrl : "views/comunicacao/home.html",
			ncyBreadcrumb: {				
				 	parent: 'comunicacao',
				    label: 'menu'
				  }
		})	
		
		.state('comunicacao.telefone', {
			url : "/telefone",
			templateUrl : "views/comunicacao/telefone/telefone.form.html",
			ncyBreadcrumb: {
				 	parent: 'comunicacao.menu',
				    label: 'telefone '
				  }
		})	
		.state('comunicacao.ramal', {
			url : "/ramal",
			templateUrl : "views/comunicacao/ramal/ramal.form.html",
			ncyBreadcrumb: {
				 	parent: 'comunicacao.menu',
				    label: 'ramal '
				  }
		})	
});