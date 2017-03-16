app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		
	.state('tipoIp', {
		abstract : true,
		url : '/tipoIp',
		templateUrl : 'views/controleIp/ip/tipo/tipo.ip.index.html',
		redirectTo : 'tipoIp.listar',
		ncyBreadcrumb: {
			    label: 'tipoIp'
			  }
	})
	
		.state('tipoIp.listar', {
			url : "/listar",
			templateUrl : "views/controleIp/ip/tipo/tipo.ip.list.html",
			controller : "TipoIpListarController as tipoIpCtrl",
			ncyBreadcrumb: {
				 	parent: 'tipoIp.cadastrar',
				    label: 'lista tipoIp'
				  }
		})
		.state('tipoIp.cadastrar', {
			url : "/cadastrar",
			templateUrl : "views/controleIp/ip/tipo/tipo.ip.form.html",
			controller : "TipoIpCadastarController as tipoIpCtrl",
			ncyBreadcrumb: {
			 	parent: 'tipoIp',
			    label: 'Cadastrar tipoIp'
			  }
		})
		.state('tipoIp.editar', {
			url : "/:idTipoIp/editar",
			templateUrl : "views/controleIp/ip/tipo/tipo.ip.form.html",
			controller : "TipoIpEditarController as tipoIpCtrl",
			ncyBreadcrumb: {
				 	parent: 'tipoIp.listar',
				    label: 'Editar'
				  }
		})
		.state('tipoIp.visualizar', {
			url : "/:idTipoIp",
			templateUrl : "views/controleIp/ip/tipo/tipo.ip.show.html",
			controller : "TipoIpVisualizarController as tipoIpCtrl",
			ncyBreadcrumb: {
				 	parent: 'tipoIp.listar',
				    label: 'Visualizar'
				  }
		})

});