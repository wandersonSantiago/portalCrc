app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		
	.state('switch', {
		abstract : true,
		url : '/switch',
		templateUrl : 'views/informatica/controleIp/switch/switch.index.html',
		redirectTo : 'switch.listar',
		ncyBreadcrumb: {
			parent: 'informatica.controleIp',
			    label: 'switch'
			  }
	})
	
		.state('switch.listar', {
			url : "/listar",
			templateUrl : "views/informatica/controleIp/switch/switch.list.html",
			controller : "SwitchListarController as switchCtrl",
			ncyBreadcrumb: {
				 	parent: 'switch.cadastrar',
				    label: 'lista switch'
				  }
		})
		.state('switch.ativos', {
			url : "/ativos",
			templateUrl : "views/informatica/controleIp/switch/switch.list.html",
			controller : "SwitchListarAtivosController as switchCtrl",
			ncyBreadcrumb: {
				 	parent: 'switch.cadastrar',
				    label: 'ativos'
				  }
		})
		.state('switch.inativos', {
			url : "/inativos",
			templateUrl : "views/informatica/controleIp/switch/switch.list.html",
			controller : "SwitchListarInativosController as switchCtrl",
			ncyBreadcrumb: {
				 	parent: 'switch.cadastrar',
				    label: 'inativos'
				  }
		})
		.state('switch.baixados', {
			url : "/baixados",
			templateUrl : "views/informatica/controleIp/switch/switch.list.html",
			controller : "SwitchListarBaixadosController as switchCtrl",
			ncyBreadcrumb: {
				 	parent: 'switch.cadastrar',
				    label: 'baixados'
				  }
		})
		.state('switch.cadastrar', {
			requiresAuthentication: true,
			permissions: ["SWITCH", "ADMIN"],
			url : "/cadastrar",
			templateUrl : "views/informatica/controleIp/switch/switch.form.html",
			controller : "SwitchCadastarController as switchCtrl",
			ncyBreadcrumb: {
			 	parent: 'switch',
			    label: 'Cadastrar switch'
			  }
		})
		.state('switch.editar', {
			]requiresAuthentication: true,
			permissions: ["SWITCH", "ADMIN"],
			url : "/:idSwitch/editar",
			templateUrl : "views/informatica/controleIp/switch/switch.form.html",
			controller : "SwitchEditarController as switchCtrl",
			ncyBreadcrumb: {
				 	parent: 'switch.listar',
				    label: 'Editar'
				  }
		})
		.state('switch.show', {
			url : "/:idSwitch/show",
			templateUrl : "views/informatica/controleIp/switch/switch.show.html",
			controller : "SwitchShowController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'switch.listar',
				    label: 'show'
				  }
		})
		.state('switch.menu', {
			url : "/menu",
			templateUrl : "views/informatica/controleIp/switch/home.html",
			ncyBreadcrumb: {
				 	parent: 'switch.listar',
				    label: 'Visualizar'
				  }
		})

});