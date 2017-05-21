app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		
	.state('ponto', {
		abstract : true,
		url : '/ponto',
		templateUrl : 'views/informatica/controleIp/ponto/ponto.index.html',
		redirectTo : 'ponto.listar',
		ncyBreadcrumb: {
			parent: 'informatica.controleIp',
			    label: 'ponto'
			  }
	})
	
		.state('ponto.listar', {
			url : "/listar",
			templateUrl : "views/informatica/controleIp/ponto/ponto.list.html",
			controller : "PontoListarController as pontoCtrl",
			ncyBreadcrumb: {
				 	parent: 'ponto.cadastrar',
				    label: 'lista ponto'
				  }
		})
		.state('ponto.ativos', {
			url : "/ativos",
			templateUrl : "views/informatica/controleIp/ponto/ponto.list.html",
			controller : "PontoListarAtivosController as pontoCtrl",
			ncyBreadcrumb: {
				 	parent: 'ponto.cadastrar',
				    label: 'ativos'
				  }
		})
		.state('ponto.inativos', {
			url : "/inativos",
			templateUrl : "views/informatica/controleIp/ponto/ponto.list.html",
			controller : "PontoListarInativosController as pontoCtrl",
			ncyBreadcrumb: {
				 	parent: 'ponto.cadastrar',
				    label: 'inativos'
				  }
		})
		.state('ponto.cadastrar', {
			url : "/cadastrar",
			templateUrl : "views/informatica/controleIp/ponto/ponto.form.html",
			controller : "PontoCadastarController as pontoCtrl",
			ncyBreadcrumb: {
			 	parent: 'ponto',
			    label: 'Cadastrar ponto'
			  }
		})
		.state('ponto.editar', {
			url : "/:idPonto/editar",
			templateUrl : "views/informatica/controleIp/ponto/ponto.form.html",
			controller : "PontoEditarController as pontoCtrl",
			ncyBreadcrumb: {
				 	parent: 'ponto.listar',
				    label: 'Editar'
				  }
		})
		.state('ponto.menu', {
			url : "/menu",
			templateUrl : "views/informatica/controleIp/ponto/home.html",
			ncyBreadcrumb: {
				 	parent: 'ponto.listar',
				    label: 'Visualizar'
				  }
		})

});