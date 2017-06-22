app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
	
	.state('funcionarioUnidade', {
		abstract : true,
		url : '/funcionarioUnidade',
		templateUrl : 'views/recursosHumanos/funcionario/unidade/funcionario.unidade.index.html',
		redirectTo : 'funcionario.listar',
		ncyBreadcrumb: {
			parent: 'funcionario.listar',
			    label: 'Funcionario Unidade'
			  }
	})
	
	.state('funcionarioUnidade.unidade', {
		url : "/:idFuncionario/unidade",
		templateUrl : "views/recursosHumanos/funcionario/unidade/funcionario.unidade.form.html",
		controller : "FuncionarioUnidadeCadastarController as funcionarioUnidadeCtrl",
		ncyBreadcrumb: {
			 	parent: 'funcionarioUnidade',
			    label: 'cadastrar funcionario na unidade'
			  }
	})
	
	.state('funcionarioUnidade.listar', {
		url : "/:idFuncionario/unidade/consulta",
		templateUrl : "views/recursosHumanos/funcionario/unidade/funcionario.unidade.list.html",
		controller : "FuncionarioUnidadeListarController as ctrl",
		ncyBreadcrumb: {
			 	parent: 'funcionarioUnidade',
			    label: 'Consultar'
			  }
	})

});