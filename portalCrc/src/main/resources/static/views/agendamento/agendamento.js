app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		.state('agendamento', {
			abstract : true,
			url : '/agendamento',
			templateUrl : 'views/agendamento/agendamento.index.html',
			ncyBreadcrumb: {
				parent : "home.menu",
				    label: 'Agendamento'
				  }
		})
		.state('agendamento.menu', {
			url : "/menu",
			templateUrl : "views/agendamento/home.html",
			controller : "UsuarioListarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'agendmento',
				    label: 'Agendamento'
				  }
		})
	
	


		
});