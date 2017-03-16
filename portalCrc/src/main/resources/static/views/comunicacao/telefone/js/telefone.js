app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		.state('telefone', {
			abstract : true,
			url : '/telefone',
			templateUrl : 'views/comunicacao/telefone/telefone.index.html',
			redirectTo : 'telefone.listar',
			ncyBreadcrumb: {
				    label: 'Telefone'
				  }
		})
		.state('telefone.listar', {
			url : "/listar",
			templateUrl : "views/comunicacao/telefone/telefone.list.html",
			controller : "TelefoneListarController as foneCtrl",
			ncyBreadcrumb: {
				 	parent: 'telefone.cadastrar',
				    label: 'Telefones'
				  }
		})
		.state('telefone.cadastrar', {
			url : "/cadastrar",
			templateUrl : "views/comunicacao/telefone/telefone.form.html",
			controller : "TelefoneCadastarController as foneCtrl",
			ncyBreadcrumb: {
			 	parent: 'telefone',
			    label: 'Cadastrar Telefone'
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
		.state('telefone.visualizar', {
			url : "/:idTelefone",
			templateUrl : "views/comunicacao/telefone/telefone.show.html",
			controller : "TelefoneVisualizarController as foneCtrl",
			ncyBreadcrumb: {
				 	parent: 'telefone.listar',
				    label: 'Visualizar'
				  }
		})

});