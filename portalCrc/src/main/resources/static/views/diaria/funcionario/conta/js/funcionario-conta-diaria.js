app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		
	.state('funcionarioContaDiaria', {
		abstract : true,
		url : '/diaria/funcionario',
		templateUrl : 'views/diaria/diaria.index.html',
		ncyBreadcrumb: {
		 	parent: 'diaria',
			    label: 'Conta'
			  }
	})
	
		.state('funcionarioContaDiaria.listar', {
			requiresAuthentication: true,
			permissions: ["DIARIA_FINANCAS", "ADMIN"],
			url : "/conta/lista",
			templateUrl : "views/diaria/funcionario/conta/funcionario.conta.list.html",
			controller : "FuncionarioContaDiariaListarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'funcionarioContaDiaria',
				    label: 'Conta / Consultar'
				  }
		})
					
		.state('funcionarioContaDiaria.buscar', {
			requiresAuthentication: true,
			permissions: ["DIARIA_FINANCAS", "ADMIN"],
			url : "/conta/buscar",
			templateUrl : "views/diaria/funcionario/conta/funcionario.buscar.html",
			controller : "FuncionarioContaBuscarListarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'funcionarioContaDiaria',
				    label: 'Conta / Buscar'
				  }
		})
		
		.state('funcionarioContaDiaria.cadastrar', {
			permissions: ["DIARIA_FINANCAS", "DIARIA_USUARIO", "ADMIN"],
			url : "/conta/cadastrar",
			params: {
				idFuncionario: null
			  },
			templateUrl : "views/diaria/funcionario/conta/funcionario.conta.form.html",
			controller : "FuncionarioContaDiariaCadastrarController as contaCtrl",
			ncyBreadcrumb: {
			 	parent: 'funcionarioContaDiaria',
			    label: 'Conta / Nova'
			  }
		})
				
		.state('funcionarioContaDiaria.editar', {
			permissions: ["DIARIA_FINANCAS", "DIARIA_USUARIO", "ADMIN"],
			url : "/conta/editar",
			params: {
				idConta: null
			  },
			templateUrl : "views/diaria/funcionario/conta/funcionario.conta.form.html",
			controller : "FuncionarioContaDiariaEditarController as contaCtrl",
			ncyBreadcrumb: {
				 	parent: 'funcionarioContaDiaria',
				    label: 'Conta / Editar'
				  }
		})
		
});