app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		
	.state('funcionarioDiaria', {
		abstract : true,
		url : '/diaria/funcionario',
		templateUrl : 'views/diaria/diaria.index.html',
		ncyBreadcrumb: {
		 	parent: 'diaria',
			    label: 'funcionario'
			  }
	})
		
		.state('funcionarioDiaria.verificar', {
			requiresAuthentication: true,
			permissions: ["DIARIA_FINANCAS", "DIARIA_USUARIO", "ADMIN"],
			url : "/verificar",
			params: {
				idDiaria: null,
			  },
			controller : "ListarFuncionarioDiariaController as ctrl",
	        templateUrl : "views/diaria/funcionario/verificacao.diaria.form.html",
	     	ncyBreadcrumb: {
			 	parent: 'funcionarioDiaria',
			    label: 'Funcionarios'
			  }
		})
		
		.state('funcionarioDiaria.unidade', {
			requiresAuthentication: true,
			permissions: ["DIARIA_FINANCAS", "ADMIN"],
			url : "/funcionario/extrato/unidade",
			params: {
				idDiaria: null,
			  },
			templateUrl : "views/diaria/funcionario/funcionario.diaria.list.html",
			controller : "FuncionarioDiariaUnidadeListarController as ctrl",
			ncyBreadcrumb: {
			 	parent: 'funcionarioDiaria',
			    label: 'Extrato '
			  }
		})
		/*.state('funcionarioDiaria.coordenadoria', {
			requiresAuthentication: true,
			permissions: ["DIARIA_FINANCAS", "ADMIN"],
			url : "/funcionario/listar/coordenadoria",
			params: {
				idDiaria: null,
			  },
			templateUrl : "views/diaria/funcionario/funcionario.diaria.list.html",
			controller : "FuncionarioDiariaCoordenadoriaListarController as ctrl",
			ncyBreadcrumb: {
			 	parent: 'funcionarioDiaria',
			    label: 'Listar diárias das coordenadoria '
			  }
		})*/
		/*.state('funcionarioDiaria.secretaria', {
			requiresAuthentication: true,
			permissions: ["DIARIA_FINANCAS", "ADMIN"],
			url : "/funcionario/listar/secretaria",
			params: {
				idDiaria: null,
			  },
			templateUrl : "views/diaria/funcionario/funcionario.diaria.list.html",
			controller : "FuncionarioDiariaSecretariaListarController as ctrl",
			ncyBreadcrumb: {
			 	parent: 'funcionarioDiaria',
			    label: 'Listar diárias da secretaria '
			  }
		})*/
		.state('funcionarioDiaria.cadastrar', {
			requiresAuthentication: true,
			permissions: ["DIARIA_FINANCAS", "ADMIN"],
			url : "/funcionario/cadastrar",
			params: {
				idDiaria: null,
			  },
			templateUrl : "views/diaria/funcionario/funcionario.diaria.form.html",
			controller : "FuncionarioDiariaCadastrarController as ctrl",
			ncyBreadcrumb: {
			 	parent: 'funcionarioDiaria',
			    label: 'Cadastrar Diaria'
			  }
		})
		.state('funcionarioDiaria.cadastrarFinancas', {
			requiresAuthentication: true,
			permissions: ["DIARIA_FINANCAS", "ADMIN"],
			url : "/funcionario/cadastrar",
			params: {
				idDiaria: null,
			  },
			templateUrl : "views/diaria/funcionario/funcionario.diaria.form.html",
			controller : "FuncionarioFinancasDiariaCadastrarController as ctrl",
			ncyBreadcrumb: {
			 	parent: 'funcionarioDiaria',
			    label: 'Cadastrar Diaria'
			  }
		})
		.state('funcionarioDiaria.editar', {
			url : "/funcionario",
			params: {
				idFuncionario: null,
			  },
			templateUrl : "views/diaria/funcionario/funcionario.diaria.form.html",
			controller : "FuncionarioDiariaEditarController as funcionarioCtrl",
			ncyBreadcrumb: {
				 	parent: 'funcionarioDiaria',
				    label: 'Editar diaria'
				  }
		})
		.state('funcionarioDiaria.visualizar', {
			url : "/funcionario",
			params: {
				idFuncionario: null,
			  },
			templateUrl : "views/diaria/funcionario/funcionario.show.html",
			controller : "FuncionarioDiariaShowController as funcionarioCtrl",
			ncyBreadcrumb: {
				 	parent: 'funcionarioDiaria',
				    label: 'Visualizar diaria'
				  }
		})

});