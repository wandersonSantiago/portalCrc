app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
	
	.state('reserva', {
		abstract : true,	
		url : '/reserva',
		templateUrl : "views/agendamento/reserva/reserva.index.html",
		redirectTo : 'reserva.listar',
		ncyBreadcrumb: {
			parent: 'agendamento.menu',
			    label: 'Reserva'
			  }
	})
	
		.state('reserva.listar', {
			url : "/listar",
			templateUrl : "views/agendamento/reserva/reserva.list.html",
			controller : "ReservaListarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'reserva',
				    label: 'Listar'
				  }
		})
		.state('reserva.cadastrar', {
			
			requiresAuthentication: true,
			permissions: ["RESERVA", "ADMIN"],
			
			url : "/cadastrar",
			templateUrl : "views/agendamento/reserva/reserva.form.html",
			controller : "ReservaCadastarController as ctrl",
			ncyBreadcrumb: {
			 	parent: 'reserva',
			    label: 'Cadastrar'
			  }
		})
		.state('reserva.editar', {
			
			
			requiresAuthentication: true,
			permissions: ["RESERVA", "ADMIN"],
			
			
			url : "/:idReserva/editar",
			templateUrl : "views/agendamento/reserva/reserva.form.html",
			controller : "ReservaEditarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'reserva.listar',
				    label: 'Editar'
				  }
		})
		.state('reserva.visualizar', {
			url : "/:idReserva",
			templateUrl : "views/agendamento/reserva/reserva.show.html",
			controller : "ReservaVisualizarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'reserva.listar',
				    label: 'Visualizar'
				  }
		})

});