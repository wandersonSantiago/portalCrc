app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		
	.state('funcionario', {
		abstract : true,
		url : '/funcionario',
		templateUrl : 'views/recursosHumanos/funcionario/funcionario.index.html',
		redirectTo : 'funcionario.listar',
		ncyBreadcrumb: {
			parent: 'home.menu',
			    label: 'funcionario'
			  }
	})
	
		.state('funcionario.listar', {
			url : "/listar",
			templateUrl : "views/recursosHumanos/funcionario/funcionario.list.html",
			controller : "FuncionarioListarController as funcionarioCtrl",
			ncyBreadcrumb: {
				 	parent: 'funcionario.cadastrar',
				    label: 'lista funcionario'
				  }
		})
		.state('funcionario.cadastrar', {
			url : "/cadastrar",
			templateUrl : "views/recursosHumanos/funcionario/funcionario.form.html",
			controller : "FuncionarioCadastarController as cadFuncCtrl",
			ncyBreadcrumb: {
			 	parent: 'funcionario',
			    label: 'Cadastrar funcionario'
			  }
		})
		.state('funcionario.editar', {
			url : "/:idFuncionario/editar",
			templateUrl : "views/recursosHumanos/funcionario/funcionario.form.html",
			controller : "FuncionarioEditarController as cadFuncCtrl",
			ncyBreadcrumb: {
				 	parent: 'funcionario.listar',
				    label: 'Editar'
				  }
		})
		.state('funcionario.visualizar', {
			url : "/:idFuncionario",
			templateUrl : "views/recursosHumanos/funcionario/funcionario.show.html",
			controller : "FuncionarioShowController as funcionarioCtrl",
			ncyBreadcrumb: {
				 	parent: 'funcionario.listar',
				    label: 'Visualizar'
				  }
		})

});