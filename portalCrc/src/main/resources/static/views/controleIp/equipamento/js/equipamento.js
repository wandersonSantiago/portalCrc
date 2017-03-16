app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		
	.state('equipamento', {
		abstract : true,
		url : '/equipamento',
		templateUrl : 'views/controleIp/equipamento/equipamento.index.html',
		redirectTo : 'equipamento.listar',
		ncyBreadcrumb: {
			    label: 'equipamento'
			  }
	})
	
		.state('equipamento.listar', {
			url : "/listar",
			templateUrl : "views/controleIp/equipamento/equipamento.list.html",
			controller : "EquipamentoListarController as equipamentoCtrl",
			ncyBreadcrumb: {
				 	parent: 'equipamento.cadastrar',
				    label: 'lista equipamento'
				  }
		})
		.state('equipamento.cadastrar', {
			url : "/cadastrar",
			templateUrl : "views/controleIp/equipamento/equipamento.form.html",
			controller : "EquipamentoCadastarController as equipamentoCtrl",
			ncyBreadcrumb: {
			 	parent: 'equipamento',
			    label: 'Cadastrar equipamento'
			  }
		})
		.state('equipamento.editar', {
			url : "/:idEquipamento/editar",
			templateUrl : "views/controleIp/equipamento/equipamento.form.html",
			controller : "EquipamentoEditarController as equipamentoCtrl",
			ncyBreadcrumb: {
				 	parent: 'equipamento.listar',
				    label: 'Editar'
				  }
		})
		.state('equipamento.visualizar', {
			url : "/:idEquipamento",
			templateUrl : "views/controleIp/equipamento/equipamento.show.html",
			controller : "EquipamentoVisualizarController as equipamentoCtrl",
			ncyBreadcrumb: {
				 	parent: 'equipamento.listar',
				    label: 'Visualizar'
				  }
		})

});