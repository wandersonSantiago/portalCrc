app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		
	.state('reserva', {
		url : "/reserva",
		templateUrl : "views/auditorio/reserva/reserva.index.html",
		ncyBreadcrumb: {
			parent: 'home',
			    label: 'Reserva'
			  }
	})
	
		.state('reserva.listar', {
			url : "/listar",
			templateUrl : "views/auditorio/reserva/reserva.list.html",
			controller : "ReservaListarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'reserva',
				    label: 'Listar'
				  }
		})
		.state('reserva.cadastrar', {
			url : "/cadastrar",
			templateUrl : "views/auditorio/reserva/reserva.form.html",
			controller : "ReservaCadastarController as ctrl",
			ncyBreadcrumb: {
			 	parent: 'reserva',
			    label: 'Cadastrar'
			  }
		})
		.state('reserva.editar', {
			url : "/:idReserva",
			templateUrl : "views/auditorio/reserva/reserva.form.html",
			controller : "ReservaEditarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'reserva.listar',
				    label: 'Editar'
				  }
		})
		.state('reserva.visualizar', {
			url : "/:idReserva",
			templateUrl : "views/auditorio/reserva/reserva.show.html",
			controller : "ReservaVisualizarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'reserva.listar',
				    label: 'Visualizar'
				  }
		})

});