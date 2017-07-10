app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		
	.state('diaria', {
		url : '/diaria',
		templateUrl : 'views/diaria/diaria.index.html',
		redirectTo : 'diaria.listar',
		ncyBreadcrumb: {
			parent: 'financas.menu',
			    label: 'Diaria'
			  }
	})
		.state('diaria.listar', {
			url : "/listar",
			templateUrl : "views/diaria/diaria.list.html",
			controller : "DiariaListarController as diariaCtrl",
			ncyBreadcrumb: {
				 	parent: 'diaria',
				    label: 'listar'
				  }
		})
		.state('diaria.cadastrar', {
			url : "/cadastrar",
			templateUrl : "views/diaria/diaria.form.html",
			controller : "DiariaCadastarController as diariaCtrl",
			ncyBreadcrumb: {
			 	parent: 'diaria',
			    label: 'Cadastrar'
			  }
		})
		.state('diaria.editar', {
			url : "/editar",
			params: {
				idDiaria: null,
			  },
			templateUrl : "views/diaria/diaria.form.html",
			controller : "DiariaEditarController as diariaCtrl",
			ncyBreadcrumb: {
				 	parent: 'diaria',
				    label: 'Editar'
				  }
		})
		.state('diaria.relatorio', {
			url : "/relatorio",
			templateUrl : "views/diaria/relatorio/relatorio.html",
			controller : "DiariaListarController as diariaCtrl",
			ncyBreadcrumb: {
				 	parent: 'diaria',
				    label: 'Relatório'
				  }
		})

});