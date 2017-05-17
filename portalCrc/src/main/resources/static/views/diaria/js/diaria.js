app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		
	.state('diaria', {
		abstract : true,
		url : '/diaria',
		templateUrl : 'views/diaria/diaria.index.html',
		redirectTo : 'diaria.listar',
		ncyBreadcrumb: {
			parent: 'home.menu',
			    label: 'diaria'
			  }
	})
	
	.state('diaria.menu', {
			url : "/menu",
			templateUrl : "views/diaria/home.html",
			controller : "DiariaListarController as diariaCtrl",
			ncyBreadcrumb: {
				 	parent: 'diaria',
				    label: 'menu'
				  }
		})
		.state('diaria.listar', {
			url : "/listar",
			templateUrl : "views/diaria/diaria.list.html",
			controller : "DiariaListarController as diariaCtrl",
			ncyBreadcrumb: {
				 	parent: 'diaria.menu',
				    label: 'lista diaria'
				  }
		})
		.state('diaria.cadastrar', {
			url : "/cadastrar",
			templateUrl : "views/diaria/diaria.form.html",
			controller : "DiariaCadastarController as diariaCtrl",
			ncyBreadcrumb: {
			 	parent: 'diaria.menu',
			    label: 'Cadastrar diaria'
			  }
		})
		.state('diaria.editar', {
			url : "/:idDiaria/editar",
			templateUrl : "views/diaria/diaria.form.html",
			controller : "DiariaEditarController as diariaCtrl",
			ncyBreadcrumb: {
				 	parent: 'diaria.menu',
				    label: 'Editar'
				  }
		})
		.state('diaria.relatorio', {
			url : "/relatorio",
			templateUrl : "views/diaria/relatorio/relatorio.html",
			controller : "DiariaListarController as diariaCtrl",
			ncyBreadcrumb: {
				 	parent: 'diaria.menu',
				    label: 'Relatório'
				  }
		})

});