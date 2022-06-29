app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		
	.state('auditorio', {
		url : '/auditorio',
		templateUrl : 'views/agendamento/auditorio/auditorio.index.html',
		ncyBreadcrumb: {
			parent: 'agendamento.menu',
			    label: 'Auditorio'
			  }
	})
	
		.state('auditorio.listar', {
			url : "/listar",
			templateUrl : "views/agendamento/auditorio/auditorio.list.html",
			controller : "AuditorioListarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'auditorio',
				    label: 'Listar'
				  }
		})
		.state('auditorio.cadastrar', {
			
			
			requiresAuthentication: true,
			permissions: ["AUDITORIO", "ADMIN"],
			
			
			
			url : "/cadastrar",
			templateUrl : "views/agendamento/auditorio/auditorio.form.html",
			controller : "AuditorioCadastarController as ctrl",
			ncyBreadcrumb: {
			 	parent: 'auditorio',
			    label: 'Cadastrar'
			  }
		})
		.state('auditorio.editar', {
			
			
			requiresAuthentication: true,
			permissions: ["AUDITORIO", "ADMIN"],
			
			
			
			url : "/:idAuditorio/editar",
			templateUrl : "views/agendamento/auditorio/auditorio.form.html",
			controller : "AuditorioEditarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'auditorio.listar',
				    label: 'Editar'
				  }
		})
		.state('auditorio.visualizar', {
			url : "/:idAuditorio",
			templateUrl : "views/agendamento/auditorio/auditorio.show.html",
			controller : "AuditorioVisualizarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'auditorio.listar',
				    label: 'Visualizar'
				  }
		})

});