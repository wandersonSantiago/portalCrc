app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		
	.state('sistema', {
		abstract : true,
		url : '/sistema',
		templateUrl : 'views/informatica/configuracao/sistema/sistema.index.html',
		redirectTo : 'sistema.listar',
		ncyBreadcrumb: {
			    label: 'sistema'
			  }
	})
	
		.state('sistema.listar', {
			url : "/listar",
			templateUrl : "views/informatica/configuracao/sistema/sistema.list.html",
			controller : "SistemaListarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'sistema.cadastrar',
				    label: 'lista sistemas'
				  }
		})
		.state('sistema.cadastrar', {
			requiresAuthentication: true,
			permissions: ["ADMIN"],
			url : "/cadastrar",
			templateUrl : "views/informatica/configuracao/sistema/sistema.form.html",
			controller : "SistemaCadastarController as ctrl",
			ncyBreadcrumb: {
			 	parent: 'sistema',
			    label: 'Cadastrar sistema'
			  }
		})
		.state('sistema.editar', {
			requiresAuthentication: true,
			permissions: ["ADMIN"],
			url : "/:idSistema/editar",
			templateUrl : "views/informatica/configuracao/sistema/sistema.form.html",
			controller : "SistemaEditarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'sistema.listar',
				    label: 'Editar'
				  }
		})
		.state('sistema.visualizar', {
			url : "/:idSistema",
			templateUrl : "views/informatica/configuracao/sistema/sistema.show.html",
			controller : "SistemaVisualizarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'sistema.listar',
				    label: 'Visualizar'
				  }
		})

});