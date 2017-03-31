app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		
	.state('chamadoTi', {
		abstract : true,
		url : '/chamadoTi',
		templateUrl : 'views/informatica/chamado/chamado.ti.index.html',
		redirectTo : 'chamadoTi.listar',
		ncyBreadcrumb: {
			parent: 'informatica.menu',
			    label: 'chamado'
			  }
	})
	
		.state('chamadoTi.listar', {
			url : "/listar",
			templateUrl : "views/informatica/chamado/lista.html",
			controller : "ChamadoTiListarController as chamadoCtrl",
			ncyBreadcrumb: {
				 	parent: 'chamadoTi.cadastrar',
				    label: 'lista'
				  }
		})
		.state('chamadoTi.cadastrar', {
			url : "/cadastrar",
			templateUrl : "views/informatica/chamado/cadastrar.html",
			controller : "ChamadoTiCadastrarController as chamadoCtrl",
			ncyBreadcrumb: {
			 	parent: 'chamadoTi',
			    label: 'novo'
			  }
		})
		.state('chamadoTi.atendimento', {
			url : "/:idChamadoTi/atendimento",
			templateUrl : "views/informatica/chamado/atendimento.html",
			controller : "ChamadoTiAtendimentoController as chamadoCtrl",
			ncyBreadcrumb: {
				 	parent: 'chamadoTi.listar',
				    label: 'atendimento'
				  }
		})
		.state('chamadoTi.suporteAtendimento', {
			url : "/:idChamadoTi/suporte/atendimento",
			templateUrl : "views/informatica/chamado/suporte/atendimento.html",
			controller : "ChamadoTiAtendimentoSuporteController as chamadoCtrl",
			ncyBreadcrumb: {
				 	parent: 'chamadoTi.suporteListar',
				    label: 'atendimento suporte'
				  }
		})

		.state('chamadoTi.relatorio', {
			url : "/relatorio",
			templateUrl : "views/informatica/chamado/suporte/relatorio.html",
			controller : "ChamadoTiRelatorioController as chamadoCtrl",
			ncyBreadcrumb: {
				 	parent: 'chamadoTi.listar',
				    label: 'relatorio'
				  }
		})
		.state('chamadoTi.suporteListar', {
			url : "/lista/suporte",
			templateUrl : "views/informatica/chamado/suporte/lista.suporte.html",
			controller : "ChamadoTiSuporteListarController as chamadoCtrl",
			ncyBreadcrumb: {
				 	parent: 'chamadoTi.listar',
				    label: 'lista suporte'
				  }
		})
		
		.state('chamadoTi.menu', {
			url : "/chamado/tecnologia/menu",
			templateUrl : "views/informatica/chamado/home.html",
			controller : "ChamadoTiSuporteListarController as chamadoCtrl",
			ncyBreadcrumb: {
				 	parent: 'chamadoTi.listar',
				    label: 'Menu'
				  }
		})
});