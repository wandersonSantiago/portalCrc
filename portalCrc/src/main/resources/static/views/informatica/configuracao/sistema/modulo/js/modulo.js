app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		
	.state('modulo', {
		abstract : true,
		url : '/modulo',
		templateUrl : 'views/informatica/configuracao/sistema/modulo/modulo.index.html',
		redirectTo : 'modulo.listar',
		ncyBreadcrumb: {
			    label: 'modulo'
			  }
	})
	
		.state('modulo.listar', {
			url : "/listar",
			templateUrl : "views/informatica/configuracao/sistema/modulo/modulo.list.html",
			controller : "ModuloListarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'modulo.cadastrar',
				    label: 'lista modulos'
				  }
		})
		.state('modulo.cadastrar', {
			requiresAuthentication: true,
			permissions: ["ADMIN"],
			url : "/cadastrar",
			templateUrl : "views/informatica/configuracao/sistema/modulo/modulo.form.html",
			controller : "ModuloCadastarController as ctrl",
			ncyBreadcrumb: {
			 	parent: 'modulo',
			    label: 'Cadastrar modulo'
			  }
		})
		.state('modulo.editar', {
			requiresAuthentication: true,
			permissions: ["ADMIN"],
			url : "/:idModulo/editar",
			templateUrl : "views/informatica/configuracao/sistema/modulo/modulo.form.html",
			controller : "ModuloEditarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'modulo.listar',
				    label: 'Editar'
				  }
		})
		

});