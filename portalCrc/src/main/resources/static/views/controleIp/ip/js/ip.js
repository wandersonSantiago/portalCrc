app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		
	.state('ip', {
		abstract : true,
		url : '/ip',
		templateUrl : 'views/controleIp/ip/ip.index.html',
		redirectTo : 'ip.listar',
		ncyBreadcrumb: {
			    label: 'ip'
			  }
	})
	
		.state('ip.listar', {
			url : "/listar",
			templateUrl : "views/controleIp/ip/ip.list.html",
			controller : "IpListarController as ipCtrl",
			ncyBreadcrumb: {
				 	parent: 'ip.cadastrar',
				    label: 'lista ip'
				  }
		})
		.state('ip.cadastrar', {
			url : "/cadastrar",
			templateUrl : "views/controleIp/ip/ip.form.html",
			controller : "IpCadastarController as ipCtrl",
			ncyBreadcrumb: {
			 	parent: 'ip',
			    label: 'Cadastrar ip'
			  }
		})
		.state('ip.editar', {
			url : "/:idIp/editar",
			templateUrl : "views/controleIp/ip/ip.form.html",
			controller : "IpEditarController as ipCtrl",
			ncyBreadcrumb: {
				 	parent: 'ip.listar',
				    label: 'Editar'
				  }
		})
		.state('ip.visualizar', {
			url : "/:idIp",
			templateUrl : "views/controleIp/ip/ip.show.html",
			controller : "IpVisualizarController as ipCtrl",
			ncyBreadcrumb: {
				 	parent: 'ip.listar',
				    label: 'Visualizar'
				  }
		})

});