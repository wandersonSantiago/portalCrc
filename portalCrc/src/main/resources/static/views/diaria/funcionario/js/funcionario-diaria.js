app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		
	.state('funcionarioDiaria', {
		abstract : true,
		url : '/diaria/funcionario',
		templateUrl : 'views/diaria/funcionario/funcionario.diaria.index.html',
		redirectTo : 'funcionario.listar',
		ncyBreadcrumb: {
		 	parent: 'diaria.menu',
			    label: 'funcionario'
			  }
	})
		
		.state('funcionarioDiaria.verificar', {
			url : "/:idDiaria/verificar",
			controller : "ListarFuncionarioDiariaController as ctrl",
	        templateUrl : "views/diaria/funcionario/verificacao.diaria.form.html",
	     	ncyBreadcrumb: {
			 	parent: 'funcionarioDiaria',
			    label: 'Buscar funcionarios'
			  }
		})
		
		.state('funcionarioDiaria.unidade', {
			url : "/:idDiaria/funcionario/listar/unidade",
			templateUrl : "views/diaria/funcionario/funcionario.diaria.list.html",
			controller : "FuncionarioDiariaUnidadeListarController as ctrl",
			ncyBreadcrumb: {
			 	parent: 'funcionarioDiaria',
			    label: 'Listar diárias das unidades '
			  }
		})
		.state('funcionarioDiaria.coordenadoria', {
			url : "/:idDiaria/funcionario/listar/coordenadoria",
			templateUrl : "views/diaria/funcionario/funcionario.diaria.list.html",
			controller : "FuncionarioDiariaCoordenadoriaListarController as ctrl",
			ncyBreadcrumb: {
			 	parent: 'funcionarioDiaria',
			    label: 'Listar diárias das coordenadoria '
			  }
		})
		.state('funcionarioDiaria.secretaria', {
			url : "/:idDiaria/funcionario/listar/secretaria",
			templateUrl : "views/diaria/funcionario/funcionario.diaria.list.html",
			controller : "FuncionarioDiariaSecretariaListarController as ctrl",
			ncyBreadcrumb: {
			 	parent: 'funcionarioDiaria',
			    label: 'Listar diárias da secretaria '
			  }
		})
		.state('funcionarioDiaria.cadastrar', {
			url : "/:idDiaria/funcionario/cadastrar",
			templateUrl : "views/diaria/funcionario/funcionario.diaria.form.html",
			controller : "FuncionarioDiariaCadastrarController as ctrl",
			ncyBreadcrumb: {
			 	parent: 'funcionarioDiaria',
			    label: 'Cadastrar Diaria'
			  }
		})
		.state('funcionarioDiaria.cadastrarFinancas', {
			url : "/:idDiaria/funcionario/cadastrar",
			templateUrl : "views/diaria/funcionario/funcionario.diaria.form.html",
			controller : "FuncionarioFinancasDiariaCadastrarController as ctrl",
			ncyBreadcrumb: {
			 	parent: 'funcionarioDiaria',
			    label: 'Cadastrar Diaria'
			  }
		})
		.state('funcionarioDiaria.editar', {
			url : "/:idFuncionario/",
			templateUrl : "views/diaria/funcionario/funcionario.diaria.form.html",
			controller : "FuncionarioDiariaEditarController as funcionarioCtrl",
			ncyBreadcrumb: {
				 	parent: 'funcionarioDiaria',
				    label: 'Editar diaria'
				  }
		})
		.state('funcionarioDiaria.visualizar', {
			url : "/:idFuncionario",
			templateUrl : "views/diaria/funcionario/funcionario.show.html",
			controller : "FuncionarioDiariaShowController as funcionarioCtrl",
			ncyBreadcrumb: {
				 	parent: 'funcionarioDiaria',
				    label: 'Visualizar diaria'
				  }
		})

});