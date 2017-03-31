app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		
	.state('tipoIp', {
		abstract : true,
		url : '/tipoIp',
		templateUrl : 'views/informatica/controleIp/ip/ip.index.html',
		redirectTo : 'tipoIp.listar',
		ncyBreadcrumb: {
			parent: 'informatica.controleIp',
			    label: 'tipoIp'
			  }
	})
	
		.state('tipoIp.listar', {
			url : "/listar",
			templateUrl : "views/informatica/controleIp/ip/tipo/tipo.ip.list.html",
			controller : "TipoIpListarController as tipoIpCtrl",
			ncyBreadcrumb: {
				 	parent: 'tipoIp.cadastrar',
				    label: 'lista tipoIp'
				  }
		})
		.state('tipoIp.cadastrar', {
			url : "/cadastrar",
			templateUrl : "views/informatica/controleIp/ip/tipo/tipo.ip.form.html",
			controller : "TipoIpCadastarController as tipoIpCtrl",
			ncyBreadcrumb: {
			 	parent: 'tipoIp',
			    label: 'Cadastrar tipoIp'
			  }
		})
		.state('tipoIp.editar', {
			url : "/:idTipoIp/editar",
			templateUrl : "views/informatica/controleIp/ip/tipo/tipo.ip.form.html",
			controller : "TipoIpEditarController as tipoIpCtrl",
			ncyBreadcrumb: {
				 	parent: 'tipoIp.listar',
				    label: 'Editar'
				  }
		})
		.state('tipoIp.menu', {
			url : "/menu",
			templateUrl : "views/informatica/controleIp/ip/tipo/home.html",
			ncyBreadcrumb: {
				 	parent: 'tipoIp.listar',
				    label: 'Visualizar'
				  }
		})

});