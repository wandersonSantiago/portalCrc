app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		
	.state('secretaria', {
		abstract : true,
		url : '/secretaria',
		templateUrl : 'views/secretaria/secretaria.index.html',
		redirectTo : 'secretaria.listar',
		ncyBreadcrumb: {
			    label: 'secretaria'
			  }
	})
	
		.state('secretaria.listar', {
			url : "/listar",
			templateUrl : "views/secretaria/secretaria.list.html",
			controller : "SecretariaListarController as secretariaCtrl",
			ncyBreadcrumb: {
				 	parent: 'secretaria.cadastrar',
				    label: 'lista secretaria'
				  }
		})
		.state('secretaria.cadastrar', {
			url : "/cadastrar",
			templateUrl : "views/secretaria/secretaria.form.html",
			controller : "SecretariaCadastarController as secretariaCtrl",
			ncyBreadcrumb: {
			 	parent: 'secretaria',
			    label: 'Cadastrar secretaria'
			  }
		})
		.state('secretaria.editar', {
			url : "/:idSecretaria/editar",
			templateUrl : "views/secretaria/secretaria.form.html",
			controller : "SecretariaEditarController as secretariaCtrl",
			ncyBreadcrumb: {
				 	parent: 'secretaria.listar',
				    label: 'Editar'
				  }
		})
		.state('secretaria.visualizar', {
			url : "/:idSecretaria",
			templateUrl : "views/secretaria/secretaria.show.html",
			controller : "SecretariaVisualizarController as secretariaCtrl",
			ncyBreadcrumb: {
				 	parent: 'secretaria.listar',
				    label: 'Visualizar'
				  }
		})

});