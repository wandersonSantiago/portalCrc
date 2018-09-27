app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		
	.state('auditorio', {
		url : "/auditorio",
		templateUrl : "views/auditorio/auditorio.index.html",
		ncyBreadcrumb: {
			parent: 'home',
			    label: 'Auditorio'
			  }
	})
	
		.state('auditorio.listar', {
			url : "/listar",
			templateUrl : "views/auditorio/auditorio.list.html",
			controller : "AuditorioListarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'auditorio',
				    label: 'Listar'
				  }
		})
		.state('auditorio.cadastrar', {
			url : "/cadastrar",
			templateUrl : "views/auditorio/auditorio.form.html",
			controller : "AuditorioCadastarController as ctrl",
			ncyBreadcrumb: {
			 	parent: 'auditorio',
			    label: 'Cadastrar'
			  }
		})
		.state('auditorio.editar', {
			url : "/:idAuditorio/editar",
			templateUrl : "views/auditorio/auditorio.form.html",
			controller : "AuditorioEditarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'auditorio.listar',
				    label: 'Editar'
				  }
		})
		.state('auditorio.visualizar', {
			url : "/:idAuditorio",
			templateUrl : "views/auditorio/auditorio.show.html",
			controller : "AuditorioVisualizarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'auditorio.listar',
				    label: 'Visualizar'
				  }
		})

});