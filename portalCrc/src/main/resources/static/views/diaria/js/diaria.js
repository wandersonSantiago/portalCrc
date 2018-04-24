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
			controller : "DiariaListarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'diaria',
				    label: 'Menu'
				  }
		})
		.state('diaria.cadastrar', {
			url : "/cadastrar",
			templateUrl : "views/diaria/diaria.form.html",
			controller : "DiariaCadastarController as diariaCtrl",
			ncyBreadcrumb: {
			 	parent: 'diaria.listar',
			    label: 'Cadastrar'
			  }
		})
		
		//inicio gambiarra para atualizar a pagina
		.state('diaria.update', {
			requiresAuthentication: true,
			permissions: ["DIARIA_USUARIO", "ADMIN"],
			url : "/diaria/item/cadastrar",
			params: {
				idDiaria: null,
				idFuncionario: null
			  },
			 
	          controller : "ItemDiariaCadastrarController as ctrl",
	          templateUrl : "views/diaria/item/item.form.html",
	          ncyBreadcrumb: {
			 	parent: 'diaria.listar',
			    label: 'Cadastrar item'
			  }
		})
		//fim gambiarra atualizar a pagina
		
		.state('diaria.editar', {
			url : "/editar",
			params: {
				idDiaria: null,
			  },
			templateUrl : "views/diaria/diaria.form.html",
			controller : "DiariaEditarController as diariaCtrl",
			ncyBreadcrumb: {
				 	parent: 'diaria.listar',
				    label: 'Editar'
				  }
		})
		.state('diaria.relatorio', {
			url : "/relatorio",
			templateUrl : "views/diaria/relatorio/relatorio.html",
			controller : "DiariaListarController as diariaCtrl",
			ncyBreadcrumb: {
				 	parent: 'diaria.listar',
				    label: 'Relatório'
				  }
		})

});