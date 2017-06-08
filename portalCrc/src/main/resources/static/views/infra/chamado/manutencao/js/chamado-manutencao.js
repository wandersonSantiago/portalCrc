app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		
	.state('chamadoManutencao', {
		abstract : true,
		url : '/chamadoManutencao',
		templateUrl : 'views/infra/chamado/manutencao/chamado.manutencao.index.html',
		redirectTo : 'chamadoManutencao.listar',
		ncyBreadcrumb: {
				parent: 'chamadoManutencao.menu',
			    label: 'chamado'
			  }
	})
	
		.state('chamadoManutencao.listar', {
			url : "/listar",
			templateUrl : "views/infra/chamado/manutencao/lista.html",
			controller : "ChamadoManutencaoListarController as chamadoCtrl",
			ncyBreadcrumb: {
				 	parent: 'chamadoManutencao',
				    label: 'listar'
				  }
		})
		.state('chamadoManutencao.cadastrar', {
			url : "/cadastrar",
			templateUrl : "views/infra/chamado/manutencao/cadastrar.html",
			controller : "ChamadoManutencaoCadastrarController as chamadoCtrl",
			ncyBreadcrumb: {
			 	parent: 'chamadoManutencao',
			    label: 'Novo'
			  }
		})
		.state('chamadoManutencao.atendimento', {
			url : "/:idChamadoManutencao/atendimento",
			templateUrl : "views/infra/chamado/manutencao/atendimento.html",
			controller : "ChamadoManutencaoAtendimentoController as chamadoCtrl",
			ncyBreadcrumb: {
				 	parent: 'chamadoManutencao',
				    label: 'atendimento'
				  }
		})
		.state('chamadoManutencao.suporteAtendimento', {
			url : "/:idChamadoManutencao/suporte/atendimento",
			templateUrl : "views/infra/chamado/manutencao/suporte/atendimento.html",
			controller : "ChamadoManutencaoAtendimentoSuporteController as chamadoCtrl",
			ncyBreadcrumb: {
				 	parent: 'chamadoManutencao',
				    label: 'atendimento'
				  }
		})

		.state('chamadoManutencao.relatorio', {
			url : "/relatorio",
			templateUrl : "views/infra/chamado/manutencao/suporte/relatorio.html",
			controller : "ChamadoManutencaoRelatorioController as chamadoCtr",
			ncyBreadcrumb: {
				 	parent: 'chamadoManutencao',
				    label: 'relatorio'
				  }
		})
		.state('chamadoManutencao.suporteListar', {
			url : "/lista/suporte",
			templateUrl : "views/infra/chamado/manutencao/suporte/lista.suporte.html",
			controller : "ChamadoManutencaoSuporteListarController as chamadoCtrl",
			ncyBreadcrumb: {
				 	parent: 'chamadoManutencao',
				    label: 'listar suporte'
				  }
		})
		
		.state('chamadoManutencao.menu', {
			url : "/menu",
			templateUrl : "views/infra/chamado/manutencao/home.html",
			controller : "ChamadoManutencaoSuporteListarController as chamadoCtrl",
			ncyBreadcrumb: {
				 	parent: 'infraestrutura.menu',
				    label: 'Chamado'
				  }
		})
});