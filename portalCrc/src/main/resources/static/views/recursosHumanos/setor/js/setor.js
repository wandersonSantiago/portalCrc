app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		
	.state('setor', {
		abstract : true,
		url : '/setor',
		templateUrl : 'views/recursosHumanos/setor/setor.index.html',
		redirectTo : 'setor.listar',
		ncyBreadcrumb: {
			    label: 'setor'
			  }
	})
	
		.state('setor.listar', {
			url : "/listar",
			templateUrl : "views/recursosHumanos/setor/setor.list.html",
			controller : "SetorListarController as setorCtrl",
			ncyBreadcrumb: {
				 	parent: 'setor.cadastrar',
				    label: 'lista setor'
				  }
		})
		.state('setor.cadastrar', {
			url : "/cadastrar",
			templateUrl : "views/recursosHumanos/setor/setor.form.html",
			controller : "SetorCadastarController as setorCtrl",
			ncyBreadcrumb: {
			 	parent: 'setor',
			    label: 'Cadastrar setor'
			  }
		})
		.state('setor.editar', {
			url : "/:idSetor/editar",
			templateUrl : "views/recursosHumanos/setor/setor.form.html",
			controller : "SetorEditarController as setorCtrl",
			ncyBreadcrumb: {
				 	parent: 'setor.listar',
				    label: 'Editar'
				  }
		})
		.state('setor.visualizar', {
			url : "/:idSetor",
			templateUrl : "views/recursosHumanos/setor/setor.show.html",
			controller : "SetorVisualizarController as setorCtrl",
			ncyBreadcrumb: {
				 	parent: 'setor.listar',
				    label: 'Visualizar'
				  }
		})

});