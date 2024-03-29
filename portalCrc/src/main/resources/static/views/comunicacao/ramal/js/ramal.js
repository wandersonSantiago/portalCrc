app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		.state('ramal', {
			abstract : true,
			url : '/ramal',
			templateUrl : 'views/comunicacao/ramal/ramal.index.html',
			redirectTo : 'ramal.listar',
			ncyBreadcrumb: {
					parent: 'comunicacao.menu',
				    label: 'Ramal'
				  }
		})
		.state('ramal.listar', {
			url : "/listar",
			templateUrl : "views/comunicacao/ramal/ramal.list.html",
			controller : "RamalListarController as ramalCtrl",
			ncyBreadcrumb: {
				 	parent: 'ramal.cadastrar',
				    label: 'Listar'
				  }
		})
		.state('ramal.cadastrar', {
		  	requiresAuthentication: true,
			permissions: ["CADASTRAR_RAMAL", "ADMIN"],
			url : "/cadastrar",
			templateUrl : "views/comunicacao/ramal/ramal.form.html",
			controller : "RamalCadastarController as ramalCtrl",
			ncyBreadcrumb: {
			 	parent: 'ramal',
			    label: 'Cadastrar '
			  }
		})
		.state('ramal.editar', {
			requiresAuthentication: true,
			permissions: ["CADASTRAR_RAMAL", "ADMIN"],
			url : "/:idRamal/editar",
			templateUrl : "views/comunicacao/ramal/ramal.form.html",
			controller : "RamalEditarController as ramalCtrl",
			ncyBreadcrumb: {
				 	parent: 'ramal.listar',
				    label: 'Editar'
				  }
		  	
		})
	
		.state('ramal.coordenadoria', {
			url : "/coordenadoria",
			templateUrl : "views/comunicacao/ramal/ramal.coordenadoria.html",
			controller : "RamalCoordenadoriaController as ramalCtrl",
			ncyBreadcrumb: {
				 	parent: 'ramal.listar',
				    label: 'Visualizar'
				  }
		})		
		.state('ramal.unidades', {
			url : "/:idUnidade/unidades",
			templateUrl : "views/comunicacao/ramal/ramal.show.html",
			controller : "RamalUnidadesController as ramalCtrl",
			ncyBreadcrumb: {
				 	parent: 'ramal.listar',
				    label: 'Visualizar'
				  }
		})
		
		.state('ramal.unidadesCoordenadoria', {
			url : "/:idCoordenadoria/ramal/unidades",
			templateUrl : "views/comunicacao/ramal/unidades.html",
			controller : "RamalUnidadesController as ramalCtrl",
			ncyBreadcrumb: {
				 	parent: 'ramal.listar',
				    label: 'Visualizar'
				  }
		})
});