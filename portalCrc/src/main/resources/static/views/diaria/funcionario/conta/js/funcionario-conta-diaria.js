app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		
	.state('funcionarioContaDiaria', {
		abstract : true,
		url : '/diaria/funcionario',
		templateUrl : 'views/diaria/funcionario/conta/funcionario.conta.diaria.index.html',
		redirectTo : 'funcionario.listar',
		ncyBreadcrumb: {
		 	parent: 'home.menu',
			    label: 'Contas'
			  }
	})
	
		.state('funcionarioContaDiaria.listar', {
			url : "/conta/lista",
			templateUrl : "views/diaria/funcionario/conta/funcionario.conta.list.html",
			controller : "FuncionarioContaDiariaListarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'funcionarioContaDiaria',
				    label: 'listar'
				  }
		})
					
		.state('funcionarioContaDiaria.buscar', {
			url : "/conta/buscar",
			templateUrl : "views/diaria/funcionario/conta/funcionario.buscar.html",
			controller : "FuncionarioContaBuscarListarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'funcionarioContaDiaria',
				    label: 'buscar'
				  }
		})
		
		.state('funcionarioContaDiaria.cadastrar', {
			url : "/conta/cadastrar",
			params: {
				idFuncionario: null
			  },
			templateUrl : "views/diaria/funcionario/conta/funcionario.conta.form.html",
			controller : "FuncionarioContaDiariaCadastrarController as contaCtrl",
			ncyBreadcrumb: {
			 	parent: 'funcionarioContaDiaria',
			    label: 'Cadastrar'
			  }
		})
				
		.state('funcionarioContaDiaria.editar', {
			url : "/conta/editar",
			params: {
				idConta: null
			  },
			templateUrl : "views/diaria/funcionario/conta/funcionario.conta.form.html",
			controller : "FuncionarioContaDiariaEditarController as contaCtrl",
			ncyBreadcrumb: {
				 	parent: 'funcionarioContaDiaria',
				    label: 'Editar'
				  }
		})
		
});