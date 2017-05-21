app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		
	.state('sistema', {
		abstract : true,
		url : '/sistema',
		templateUrl : 'views/informatica/chamado/sistema/sistema.index.html',
		redirectTo : 'sistema.listar',
		ncyBreadcrumb: {
			    label: 'sistema'
			  }
	})
	
		.state('sistema.listar', {
			url : "/listar",
			templateUrl : "views/informatica/chamado/sistema/sistema.list.html",
			controller : "SistemaListarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'sistema.cadastrar',
				    label: 'lista sistemas'
				  }
		})
		.state('sistema.cadastrar', {
			url : "/cadastrar",
			templateUrl : "views/informatica/chamado/sistema/sistema.form.html",
			controller : "SistemaCadastarController as ctrl",
			ncyBreadcrumb: {
			 	parent: 'sistema',
			    label: 'Cadastrar sistema'
			  }
		})
		.state('sistema.editar', {
			url : "/:idSistema/editar",
			templateUrl : "views/informatica/chamado/sistema/sistema.form.html",
			controller : "SistemaEditarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'sistema.listar',
				    label: 'Editar'
				  }
		})
		.state('sistema.visualizar', {
			url : "/:idSistema",
			templateUrl : "views/informatica/chamado/sistema/sistema.show.html",
			controller : "SistemaVisualizarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'sistema.listar',
				    label: 'Visualizar'
				  }
		})

});