app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		
	.state('funcionario', {
		url : '/funcionario',
		templateUrl : 'views/recursosHumanos/funcionario/funcionario.index.html',
		redirectTo : 'funcionario.listar',
		ncyBreadcrumb: {
			parent: 'recursos-humanos.menu',
			    label: 'Funcionario'
			  }
	})
	
		.state('funcionario.listar', {
			url : "/listar",
			templateUrl : "views/recursosHumanos/funcionario/funcionario.list.html",
			controller : "FuncionarioListarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'funcionario',
				    label: 'Listar'
				  }
		})
		.state('funcionario.cadastrar', {
			requiresAuthentication: true,
			permissions: ["FUNCIONARIO_UNIDADE", "ADMIN"],
			url : "/cadastrar",
			templateUrl : "views/recursosHumanos/funcionario/funcionario.form.html",
			controller : "FuncionarioCadastarController as cadFuncCtrl",
			ncyBreadcrumb: {
			 	parent: 'funcionario',
			    label: 'Cadastrar'
			  }
		})
		.state('funcionario.editar', {
			requiresAuthentication: true,
			permissions: ["FUNCIONARIO_UNIDADE", "ADMIN"],
			url : "/:idFuncionario/editar",
			templateUrl : "views/recursosHumanos/funcionario/funcionario.form.html",
			controller : "FuncionarioEditarController as cadFuncCtrl",
			ncyBreadcrumb: {
				 	parent: 'funcionario.listar',
				    label: 'Editar'
				  }
		})
		.state('funcionario.visualizar', {
			url : "/:idFuncionario/visualizar",
			templateUrl : "views/recursosHumanos/funcionario/funcionario.show.html",
			controller : "FuncionarioShowController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'funcionario.listar',
				    label: 'Visualizar'
				  }
		})

});