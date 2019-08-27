app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		
	.state('unidade', {
		abstract : true,
		url : '/unidade',
		templateUrl : 'views/unidade/unidade.index.html',
		redirectTo : 'unidade.listar',
		ncyBreadcrumb: {
			    label: 'unidade'
			  }
	})
	
		.state('unidade.listar', {
			url : "/listar",
			templateUrl : "views/unidade/unidade.list.html",
			controller : "UnidadeListarController as unidadeCtrl",
			ncyBreadcrumb: {
				 	parent: 'unidade.cadastrar',
				    label: 'lista unidades'
				  }
		})
		.state('unidade.cadastrar', {
			requiresAuthentication: true,
			permissions: ["CADASTRAR_UNIDADE","ADMIN"],
			url : "/cadastrar",
			templateUrl : "views/unidade/unidade.form.html",
			controller : "UnidadeCadastarController as unidadeCtrl",
			ncyBreadcrumb: {
			 	parent: 'unidade',
			    label: 'Cadastrar unidade'
			  }
		})
		.state('unidade.editar', {
			requiresAuthentication: true,
			permissions: ["CADASTRAR_UNIDADE","ADMIN"],
			url : "/:idUnidade/editar",
			templateUrl : "views/unidade/unidade.form.html",
			controller : "UnidadeEditarController as unidadeCtrl",
			ncyBreadcrumb: {
				 	parent: 'unidade.listar',
				    label: 'Editar'
				  }
		})
		.state('unidade.visualizar', {
			url : "/:idUnidade",
			templateUrl : "views/unidade/unidade.show.html",
			controller : "UnidadeVisualizarController as unidadeCtrl",
			ncyBreadcrumb: {
				 	parent: 'unidade.listar',
				    label: 'Visualizar'
				  }
		})

});