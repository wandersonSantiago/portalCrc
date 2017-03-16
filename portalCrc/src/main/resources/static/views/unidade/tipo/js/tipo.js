app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		
	.state('tipo', {
		abstract : true,
		url : '/tipo',
		templateUrl : 'views/unidade/tipo/tipo.index.html',
		redirectTo : 'tipo.listar',
		ncyBreadcrumb: {
			    label: 'Tipo'
			  }
	})
	
		.state('tipo.listar', {
			url : "/listar",
			templateUrl : "views/unidade/tipo/tipo.list.html",
			controller : "TipoListarController as tipoCtrl",
			ncyBreadcrumb: {
				 	parent: 'tipo.cadastrar',
				    label: 'lista Tipos'
				  }
		})
		.state('tipo.cadastrar', {
			url : "/cadastrar",
			templateUrl : "views/unidade/tipo/tipo.form.html",
			controller : "TipoCadastarController as tipoCtrl",
			ncyBreadcrumb: {
			 	parent: 'tipo',
			    label: 'Cadastrar Usuarios'
			  }
		})
		.state('tipo.editar', {
			url : "/:idTipo/editar",
			templateUrl : "views/unidade/tipo/tipo.form.html",
			controller : "TipoEditarController as tipoCtrl",
			ncyBreadcrumb: {
				 	parent: 'tipo.listar',
				    label: 'Editar'
				  }
		})
		.state('tipo.visualizar', {
			url : "/:idUsuario",
			templateUrl : "views/unidade/tipo/tipo.show.html",
			controller : "TipoVisualizarController as tipoCtrl",
			ncyBreadcrumb: {
				 	parent: 'tipo.listar',
				    label: 'Visualizar'
				  }
		})

});