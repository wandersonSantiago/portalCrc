app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		
	.state('cargo', {
		abstract : true,
		url : '/cargo',
		templateUrl : 'views/recursosHumanos/cargo/cargo.index.html',
		redirectTo : 'cargo.listar',
		ncyBreadcrumb: {
			    label: 'cargo'
			  }
	})
	
		.state('cargo.listar', {
			url : "/listar",
			templateUrl : "views/recursosHumanos/cargo/cargo.list.html",
			controller : "CargoListarController as cargoCtrl",
			ncyBreadcrumb: {
				 	parent: 'cargo.cadastrar',
				    label: 'lista cargo'
				  }
		})
		.state('cargo.cadastrar', {
			url : "/cadastrar",
			templateUrl : "views/recursosHumanos/cargo/cargo.form.html",
			controller : "CargoCadastarController as cargoCtrl",
			ncyBreadcrumb: {
			 	parent: 'cargo',
			    label: 'Cadastrar cargo'
			  }
		})
		.state('cargo.editar', {
			url : "/:idCargo/editar",
			templateUrl : "views/recursosHumanos/cargo/cargo.form.html",
			controller : "CargoEditarController as cargoCtrl",
			ncyBreadcrumb: {
				 	parent: 'cargo.listar',
				    label: 'Editar'
				  }
		})
		.state('cargo.visualizar', {
			url : "/:idCargo",
			templateUrl : "views/recursosHumanos/cargo/cargo.show.html",
			controller : "CargoVisualizarController as cargoCtrl",
			ncyBreadcrumb: {
				 	parent: 'cargo.listar',
				    label: 'Visualizar'
				  }
		})

});