app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		
	.state('emprestimo', {
		abstract : true,	
		url : '/emprestimo',
		templateUrl : "views/agendamento/emprestimo/emprestimo.index.html",
		redirectTo : 'emprestimo.listar',
		ncyBreadcrumb: {
			parent: 'agendamento.menu',
			    label: 'Emprestimo'
			  }
		})
	
		.state('emprestimo.listar', {
			url : "/listar",
			templateUrl : "views/agendamento/emprestimo/emprestimo.list.html",
			controller : "EmprestimoListarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'emprestimo',
				    label: 'Listar'
				  }
		})
		
		
		.state('emprestimo.cadastrar', {			
			requiresAuthentication: true,
			permissions: ["EMPRESTIMO", "ADMIN"],
			url : "/cadastrar",
			templateUrl : "views/agendamento/emprestimo/emprestimo.form.html",
			controller : "EmprestimoCadastrarController as ctrl",
			ncyBreadcrumb: {
			 	parent: 'emprestimo',
			    label: 'Cadastrar'
			  }
		})
		.state('emprestimo.editar', {
			requiresAuthentication: true,
			permissions: ["EMPRESTIMO", "ADMIN"],
			url : "/:idEmprestimo/editar",
			templateUrl : "views/agendamento/emprestimo/emprestimo.form.html",
			controller : "EmprestimoEditarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'emprestimo.listar',
				    label: 'Editar'
				  }
		})
				
		.state('emprestimo.visualizar', {
			url : "/:idEmprestimo",
			templateUrl : "views/agendamento/emprestimo/emprestimo.show.html",
			controller : "EmprestimoVisualizarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'emprestimo.listar',
				    label: 'Visualizar'
				  }
		})
		

});