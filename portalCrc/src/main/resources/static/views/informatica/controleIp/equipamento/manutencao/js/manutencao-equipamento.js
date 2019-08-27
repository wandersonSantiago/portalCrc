app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		
	.state('manutencaoEquipamento', {
		abstract : true,
		url : '/equipamento/manutencao',
		templateUrl : 'views/informatica/controleIp/equipamento/equipamento.index.html',
		redirectTo : 'manutencaoManutencaoEquipamento.listar',
		ncyBreadcrumb: {
			parent: 'informatica.controleIp',
			    label: 'Manutencao'
			  }
		})
	
		.state('manutencaoEquipamento.listar', {
			url : "/manutencao/listar",
			templateUrl : "views/informatica/controleIp/equipamento/manutencao/manutencao-equipamento.list.html",
			controller : "ManutencaoEquipamentoListarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'equipamento.listar',
				    label: 'lista'
				  }
		})
		
		
		.state('manutencaoEquipamento.salvar', {
			requiresAuthentication: true,
			permissions: ["EQUIPAMENTO", "ADMIN"],
			url : "/:idEquipamento/informatica/salvar",
			templateUrl : "views/informatica/controleIp/equipamento/manutencao/manutencao-equipamento.form.html",
			controller : "ManutencaoEquipamentoCadastarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'equipamento.listar',
				    label: 'Editar'
				  }
		})
		.state('manutencaoEquipamento.show', {
			url : "/:idManutencaoEquipamento/show",
			templateUrl : "views/informatica/controleIp/equipamento/manutencao/manutencao-equipamento.show.html",
			controller : "ManutencaoEquipamentoShowController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'manutencaoEquipamento.listar',
				    label: 'Visualizar'
				  }
		})
		
		.state('manutencaoEquipamento.servico', {
			url : "/:idManutencaoEquipamento/servico",
			templateUrl : "views/informatica/controleIp/equipamento/manutencao/manutencao-equipamento.servico.html",
			controller : "ManutencaoEquipamentoServicoController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'manutencaoEquipamento.listar',
				    label: 'Serviço'
				  }
		})

		
});