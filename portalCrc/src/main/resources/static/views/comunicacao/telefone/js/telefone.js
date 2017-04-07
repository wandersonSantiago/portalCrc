app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		.state('telefone', {
			abstract : true,
			url : '/telefone',
			templateUrl : 'views/comunicacao/telefone/telefone.index.html',
			redirectTo : 'telefone.listar',
			ncyBreadcrumb: {
					parent: 'comunicacao.menu',
				    label: 'Telefone'
				  }
		})
		.state('telefone.listar', {
			url : "/listar",
			templateUrl : "views/comunicacao/telefone/telefone.list.html",
			controller : "TelefoneListarController as foneCtrl",
			ncyBreadcrumb: {
				 	parent: 'telefone.cadastrar',
				    label: 'Lista'
				  }
		})
		.state('telefone.cadastrar', {
			url : "/cadastrar",
			templateUrl : "views/comunicacao/telefone/telefone.form.html",
			controller : "TelefoneCadastarController as foneCtrl",
			ncyBreadcrumb: {
			 	parent: 'telefone',
			    label: 'Cadastrar'
			  }
		})
		.state('telefone.editar', {
			url : "/:idTelefone/editar",
			templateUrl : "views/comunicacao/telefone/telefone.form.html",
			controller : "TelefoneEditarController as foneCtrl",
			ncyBreadcrumb: {
				 	parent: 'telefone.listar',
				    label: 'Editar'
				  }
		})
		.state('telefone.coordenadoria', {
			url : "/coordenadoria",
			templateUrl : "views/comunicacao/telefone/telefone.coordenadoria.html",
			controller : "TelefoneCoordenadoriaController as foneCtrl",
			ncyBreadcrumb: {
				 	parent: 'telefone.listar',
				    label: 'Visualizar'
				  }
		})		
		.state('telefone.unidades', {
			url : "/:idUnidade/unidades",
			templateUrl : "views/comunicacao/telefone/telefone.show.html",
			controller : "TelefoneUnidadesController as foneCtrl",
			ncyBreadcrumb: {
				 	parent: 'telefone.listar',
				    label: 'Visualizar'
				  }
		})
		
		.state('telefone.unidadesCoordenadoria', {
			url : "/:idCoordenadoria/coordenadoria/unidades",
			templateUrl : "views/comunicacao/telefone/unidades.html",
			controller : "TelefoneUnidadesController as foneCtrl",
			ncyBreadcrumb: {
				 	parent: 'telefone.listar',
				    label: 'Visualizar'
				  }
		})

});