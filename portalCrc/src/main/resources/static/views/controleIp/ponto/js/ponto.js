app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		
	.state('ponto', {
		abstract : true,
		url : '/ponto',
		templateUrl : 'views/controleIp/ponto/ponto.index.html',
		redirectTo : 'ponto.listar',
		ncyBreadcrumb: {
			    label: 'ponto'
			  }
	})
	
		.state('ponto.listar', {
			url : "/listar",
			templateUrl : "views/controleIp/ponto/ponto.list.html",
			controller : "PontoListarController as pontoCtrl",
			ncyBreadcrumb: {
				 	parent: 'ponto.cadastrar',
				    label: 'lista ponto'
				  }
		})
		.state('ponto.cadastrar', {
			url : "/cadastrar",
			templateUrl : "views/controleIp/ponto/ponto.form.html",
			controller : "PontoCadastarController as pontoCtrl",
			ncyBreadcrumb: {
			 	parent: 'ponto',
			    label: 'Cadastrar ponto'
			  }
		})
		.state('ponto.editar', {
			url : "/:idPonto/editar",
			templateUrl : "views/controleIp/ponto/ponto.form.html",
			controller : "PontoEditarController as pontoCtrl",
			ncyBreadcrumb: {
				 	parent: 'ponto.listar',
				    label: 'Editar'
				  }
		})
		.state('ponto.visualizar', {
			url : "/:idPonto",
			templateUrl : "views/controleIp/ponto/ponto.show.html",
			controller : "PontoVisualizarController as pontoCtrl",
			ncyBreadcrumb: {
				 	parent: 'ponto.listar',
				    label: 'Visualizar'
				  }
		})

});