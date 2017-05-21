app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		
	.state('equipamento', {
		abstract : true,
		url : '/equipamento',
		templateUrl : 'views/informatica/controleIp/equipamento/equipamento.index.html',
		redirectTo : 'equipamento.listar',
		ncyBreadcrumb: {
			parent: 'informatica.controleIp',
			    label: 'equipamento'
			  }
	})
	
		.state('equipamento.listar', {
			url : "/listar",
			templateUrl : "views/informatica/controleIp/equipamento/equipamento.list.html",
			controller : "EquipamentoListarController as equipamentoCtrl",
			ncyBreadcrumb: {
				 	parent: 'equipamento.cadastrar',
				    label: 'lista equipamento'
				  }
		})
		.state('equipamento.ativo', {
			url : "/ativo",
			templateUrl : "views/informatica/controleIp/equipamento/equipamento.list.html",
			controller : "EquipamentoListarAtivoController as equipamentoCtrl",
			ncyBreadcrumb: {
				 	parent: 'equipamento.cadastrar',
				    label: 'inativo'
				  }
		})
		.state('equipamento.inativo', {
			url : "/inativo",
			templateUrl : "views/informatica/controleIp/equipamento/equipamento.list.html",
			controller : "EquipamentoListarInativoController as equipamentoCtrl",
			ncyBreadcrumb: {
				 	parent: 'equipamento.cadastrar',
				    label: 'ativo'
				  }
		})
		.state('equipamento.baixado', {
			url : "/baixado",
			templateUrl : "views/informatica/controleIp/equipamento/equipamento.list.html",
			controller : "EquipamentoListarBaixadoController as equipamentoCtrl",
			ncyBreadcrumb: {
				 	parent: 'equipamento.cadastrar',
				    label: 'ativo'
				  }
		})
		.state('equipamento.cadastrar', {
			url : "/cadastrar",
			templateUrl : "views/informatica/controleIp/equipamento/equipamento.form.html",
			controller : "EquipamentoCadastarController as equipamentoCtrl",
			ncyBreadcrumb: {
			 	parent: 'equipamento',
			    label: 'Cadastrar equipamento'
			  }
		})
		
		.state('equipamento.editar', {
			url : "/:idEquipamento/informatica/editar",
			templateUrl : "views/informatica/controleIp/equipamento/equipamento.form.html",
			controller : "EquipamentoEditarController as equipamentoCtrl",
			ncyBreadcrumb: {
				 	parent: 'equipamento.listar',
				    label: 'Editar'
				  }
		})
		/*.state('equipamento.visualizar', {
			url : "/:idEquipamento",
			templateUrl : "views/controleIp/equipamento/equipamento.show.html",
			controller : "EquipamentoVisualizarController as equipamentoCtrl",
			ncyBreadcrumb: {
				 	parent: 'equipamento.listar',
				    label: 'Visualizar'
				  }
		})
*/
		.state('equipamento.menu', {
			url : "/menu",
			templateUrl : "views/informatica/controleIp/equipamento/home.html",
			controller : "EquipamentoEditarController as equipamentoCtrl",
			ncyBreadcrumb: {
				 	parent: 'equipamento.listar',
				    label: 'menu'
				  }
		})
});