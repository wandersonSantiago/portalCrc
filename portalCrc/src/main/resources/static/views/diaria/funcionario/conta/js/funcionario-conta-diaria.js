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
			url : "/lista",
			templateUrl : "views/diaria/funcionario/conta/funcionario.conta.list.html",
			controller : "FuncionarioContaDiariaListarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'funcionarioContaDiaria',
				    label: 'lista funcionario'
				  }
		})
					
		.state('funcionarioContaDiaria.buscar', {
			url : "/conta/buscar",
			templateUrl : "views/diaria/funcionario/conta/funcionario.buscar.html",
			controller : "FuncionarioContaBuscarListarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'funcionarioContaDiaria.unidade',
				    label: 'buscar funcionario'
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