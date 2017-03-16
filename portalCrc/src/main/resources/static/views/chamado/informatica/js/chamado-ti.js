app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		
	.state('chamadoTi', {
		abstract : true,
		url : '/chamadoTi',
		templateUrl : 'views/chamado/informatica/chamado.ti.index.html',
		redirectTo : 'chamadoTi.listar',
		ncyBreadcrumb: {
			    label: 'chamadoTi'
			  }
	})
	
		.state('chamadoTi.listar', {
			url : "/listar",
			templateUrl : "views/chamado/informatica/lista.html",
			controller : "ChamadoTiListarController as chamadoCtrl",
			ncyBreadcrumb: {
				 	parent: 'chamadoTi.cadastrar',
				    label: 'lista chamados'
				  }
		})
		.state('chamadoTi.cadastrar', {
			url : "/cadastrar",
			templateUrl : "views/chamado/informatica/cadastrar.html",
			controller : "ChamadoTiCadastrarController as chamadoCtrl",
			ncyBreadcrumb: {
			 	parent: 'chamadoTi',
			    label: 'abrir chamado tecnologia da informação'
			  }
		})
		.state('chamadoTi.atendimento', {
			url : "/:idChamadoTi/atendimento",
			templateUrl : "views/chamado/informatica/atendimento.html",
			controller : "ChamadoTiAtendimentoController as chamadoCtrl",
			ncyBreadcrumb: {
				 	parent: 'chamadoTi.listar',
				    label: 'atendimento'
				  }
		})
		.state('chamadoTi.suporteAtendimento', {
			url : "/:idChamadoTi/suporte/atendimento",
			templateUrl : "views/chamado/informatica/suporte/atendimento.html",
			controller : "ChamadoTiAtendimentoSuporteController as chamadoCtrl",
			ncyBreadcrumb: {
				 	parent: 'chamadoTi.suporteListar',
				    label: 'atendimento suporte'
				  }
		})

		.state('chamadoTi.relatorio', {
			url : "/relatorio",
			templateUrl : "views/chamado/informatica/suporte/relatorio.html",
			controller : "ChamadoTiRelatorioController as chamadoCtrl",
			ncyBreadcrumb: {
				 	parent: 'chamadoTi.listar',
				    label: 'relatorio'
				  }
		})
		.state('chamadoTi.suporteListar', {
			url : "/lista/suporte",
			templateUrl : "views/chamado/informatica/suporte/lista.suporte.html",
			controller : "ChamadoTiSuporteListarController as chamadoCtrl",
			ncyBreadcrumb: {
				 	parent: 'chamadoTi.listar',
				    label: 'lista sporte'
				  }
		})
});