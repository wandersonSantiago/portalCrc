app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		
	.state('agenda', {
		abstract : true,
		url : '/agenda',
		templateUrl : 'views/agenda/agenda.index.html',
		redirectTo : 'agenda.listar',
		ncyBreadcrumb: {
			    label: 'agenda'
			  }
	})
	
		.state('agenda.listar', {
			url : "/listar",
			templateUrl : "views/agenda/agenda.list.html",
			controller : "AgendaListarController as agendaCtrl",
			ncyBreadcrumb: {
				 	parent: 'agenda.cadastrar',
				    label: 'lista agenda'
				  }
		})
		.state('agenda.cadastrar', {
			url : "/cadastrar",
			templateUrl : "views/agenda/agenda.form.html",
			controller : "AgendaCadastarController as vm",
			ncyBreadcrumb: {
			 	parent: 'agenda',
			    label: 'Cadastrar agenda'
			  }
		})
		.state('agenda.editar', {
			url : "/:idAgenda/editar",
			templateUrl : "views/agenda/agenda.form.html",
			controller : "AgendaEditarController as agendaCtrl",
			ncyBreadcrumb: {
				 	parent: 'agenda.listar',
				    label: 'Editar'
				  }
		})
		.state('agenda.visualizar', {
			url : "/:idAgenda",
			templateUrl : "views/agenda/agenda.show.html",
			controller : "AgendaVisualizarController as agendaCtrl",
			ncyBreadcrumb: {
				 	parent: 'agenda.listar',
				    label: 'Visualizar'
				  }
		})

});