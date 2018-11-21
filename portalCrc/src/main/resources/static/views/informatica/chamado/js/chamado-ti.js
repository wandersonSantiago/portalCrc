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
			requiresAuthentication: true,
			permissions: ["CHAMADO_USUARIO", "ADMIN"],
			url : "/listar",
			templateUrl : "views/informatica/chamado/lista.html",
			controller : "ChamadoTiListarController as chamadoCtrl",
			ncyBreadcrumb: {
				 	parent: 'chamadoTi.cadastrar',
				    label: 'lista'
				  }
		})
		.state('chamadoTi.cadastrar', {
			requiresAuthentication: true,
			permissions: ["CHAMADO_USUARIO", "ADMIN"],
			url : "/cadastrar",
			templateUrl : "views/informatica/chamado/cadastrar.html",
			controller : "ChamadoTiCadastrarController as chamadoCtrl",
			ncyBreadcrumb: {
			 	parent: 'chamadoTi',
			    label: 'novo'
			  }
		})
		.state('chamadoTi.atendimento', {
			requiresAuthentication: true,
			permissions: ["CHAMADO_USUARIO", "ADMIN"],
			url : "/:idChamadoTi/atendimento",
			templateUrl : "views/informatica/chamado/atendimento.html",
			controller : "ChamadoTiAtendimentoController as chamadoCtrl",
			ncyBreadcrumb: {
				 	parent: 'chamadoTi.listar',
				    label: 'atendimento'
				  }
		})
		

		.state('chamadoTi.relatorio', {
			requiresAuthentication: true,
			permissions: ["CHAMADO_INFORMATICA_TECNICO", "ADMIN"],
			url : "/relatorio",
			templateUrl : "views/informatica/chamado/suporte/relatorio.html",
			controller : "ChamadoTiRelatorioController as chamadoCtr",
			ncyBreadcrumb: {
				 	parent: 'chamadoTi.listar',
				    label: 'relatorio'
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