app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		
	.state('tema', {
		abstract : true,
		url : '/tema',
		templateUrl : 'views/informatica/configuracao/sistema/modulo/tema/tema.index.html',
		redirectTo : 'tema.listar',
		ncyBreadcrumb: {
			    label: 'tema'
			  }
	})
	
		.state('tema.listar', {
			url : "/listar",
			templateUrl : "views/informatica/configuracao/sistema/modulo/tema/tema.list.html",
			controller : "TemaListarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'tema.cadastrar',
				    label: 'lista temas'
				  }
		})
		.state('tema.cadastrar', {
			requiresAuthentication: true,
			permissions: ["ADMIN"],
			url : "/cadastrar",
			templateUrl : "views/informatica/configuracao/sistema/modulo/tema/tema.form.html",
			controller : "TemaCadastarController as ctrl",
			ncyBreadcrumb: {
			 	parent: 'tema',
			    label: 'Cadastrar tema'
			  }
		})
		.state('tema.editar', {
			requiresAuthentication: true,
			permissions: ["ADMIN"],
			url : "/:idTema/editar",
			templateUrl : "views/informatica/configuracao/sistema/modulo/tema/tema.form.html",
			controller : "TemaEditarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'tema.listar',
				    label: 'Editar'
				  }
		})		

});