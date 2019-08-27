app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		
	.state('setor', {
		url : '/setor',
		templateUrl : 'views/recursosHumanos/setor/setor.index.html',
		ncyBreadcrumb: {
			parent: 'recursos-humanos.menu',
			    label: 'Setor'
			  }
	})
	
		.state('setor.listar', {
			url : "/listar",
			templateUrl : "views/recursosHumanos/setor/setor.list.html",
			controller : "SetorListarController as setorCtrl",
			ncyBreadcrumb: {
				 	parent: 'setor',
				    label: 'Listar'
				  }
		})
		.state('setor.cadastrar', {
			requiresAuthentication: true,
			permissions: ["SETOR", "ADMIN"],
			url : "/cadastrar",
			templateUrl : "views/recursosHumanos/setor/setor.form.html",
			controller : "SetorCadastarController as setorCtrl",
			ncyBreadcrumb: {
			 	parent: 'setor',
			    label: 'Cadastrar'
			  }
		})
		.state('setor.editar', {
			requiresAuthentication: true,
			permissions: ["SETOR", "ADMIN"],
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