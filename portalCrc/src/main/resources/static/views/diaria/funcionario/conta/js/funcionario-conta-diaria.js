app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		
	.state('funcionarioContaDiaria', {
		abstract : true,
		url : '/diaria/funcionario',
		templateUrl : 'views/diaria/funcionario/conta/funcionario.conta.diaria.index.html',
		redirectTo : 'funcionario.listar',
		ncyBreadcrumb: {
		 	parent: 'diaria',
			    label: 'Diaria'
			  }
	})
	
		.state('funcionarioContaDiaria.unidade', {
			url : "/unidade",
			templateUrl : "views/diaria/funcionario/conta/funcionario.list.html",
			controller : "FuncionarioDiariaListarController as funcionarioCtrl",
			ncyBreadcrumb: {
				 	parent: 'funcionarioContaDiaria',
				    label: 'lista funcionario'
				  }
		})
		
		.state('funcionarioContaDiaria.listar', {
			url : "/conta/unidade",
			templateUrl : "views/diaria/funcionario/conta/funcionario.conta.list.html",
			controller : "FuncionarioContaDiariaListarController as contaCtrl",
			ncyBreadcrumb: {
				 	parent: 'funcionarioContaDiaria',
				    label: 'lista funcionario'
				  }
		})
		
		.state('funcionarioContaDiaria.cadastrar', {
			url : "/:idFuncionario/conta/cadastrar",
			templateUrl : "views/diaria/funcionario/conta/funcionario.conta.form.html",
			controller : "FuncionarioContaDiariaCadastrarController as contaCtrl",
			ncyBreadcrumb: {
			 	parent: 'funcionarioContaDiaria.unidade',
			    label: 'Cadastrar'
			  }
		})
				
		.state('funcionarioContaDiaria.editar', {
			url : "/:idConta/conta/editar",
			templateUrl : "views/diaria/funcionario/conta/funcionario.conta.form.html",
			controller : "FuncionarioContaDiariaEditarController as contaCtrl",
			ncyBreadcrumb: {
				 	parent: 'funcionarioContaDiaria.listar',
				    label: 'Editar'
				  }
		})
		
});