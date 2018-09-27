app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		
	.state('emprestimo', {
		url :"/emprestimo",
		templateUrl : 'views/auditorio/emprestimo/emprestimo.index.html',
		ncyBreadcrumb: {
			parent: 'home',
			    label: 'Emprestimo'
			    	
			  }
	})
	
		.state('emprestimo.listar', {
			url : "/listar",
			templateUrl : "views/auditorio/emprestimo/emprestimo.list.html",
			controller : "EmprestimoListarController as Ctrl",
			ncyBreadcrumb: {
				 	parent: 'emprestimo',
				    label: 'Listar'
				  }
		})
		.state('emprestimo.cadastrar', {
			url : "/cadastrar",
			templateUrl : "views/auditorio/emprestimo/emprestimo.form.html",
			controller : "EmprestimoCadastarController as ctrl",
			ncyBreadcrumb: {
			 	parent: 'emprestimo',
			    label: 'Cadastrar'
			  }
		})
		.state('emprestimo.editar', {
			url : "/:idEmprestimo",
			templateUrl : "views/auditorio/emprestimo/emprestimo.form.html",
			controller : "EmprestimoEditarController as emprestimoCtrl",
			ncyBreadcrumb: {
				 	parent: 'emprestimo.listar',
				    label: 'Editar'
				  }
		})
		.state('emprestimo.visualizar', {
			url : "/:idEmprestimo",
			templateUrl : "views/auditorio/emprestimo/emprestimo.show.html",
			controller : "EmprestimoVisualizarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'emprestimo.listar',
				    label: 'Visualizar'
				  }
		})

});