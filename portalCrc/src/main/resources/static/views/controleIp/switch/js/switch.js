app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		
	.state('switch', {
		abstract : true,
		url : '/switch',
		templateUrl : 'views/controleIp/switch/switch.index.html',
		redirectTo : 'switch.listar',
		ncyBreadcrumb: {
			    label: 'switch'
			  }
	})
	
		.state('switch.listar', {
			url : "/listar",
			templateUrl : "views/controleIp/switch/switch.list.html",
			controller : "SwitchListarController as switchCtrl",
			ncyBreadcrumb: {
				 	parent: 'switch.cadastrar',
				    label: 'lista switch'
				  }
		})
		.state('switch.cadastrar', {
			url : "/cadastrar",
			templateUrl : "views/controleIp/switch/switch.form.html",
			controller : "SwitchCadastarController as switchCtrl",
			ncyBreadcrumb: {
			 	parent: 'switch',
			    label: 'Cadastrar switch'
			  }
		})
		.state('switch.editar', {
			url : "/:idSwitch/editar",
			templateUrl : "views/controleIp/switch/switch.form.html",
			controller : "SwitchEditarController as switchCtrl",
			ncyBreadcrumb: {
				 	parent: 'switch.listar',
				    label: 'Editar'
				  }
		})
		.state('switch.visualizar', {
			url : "/:idSwitch/portas",
			templateUrl : "views/controleIp/switch/switch.show.html",
			controller : "SwitchShowController as switchCtrl",
			ncyBreadcrumb: {
				 	parent: 'switch.listar',
				    label: 'Visualizar'
				  }
		})

});