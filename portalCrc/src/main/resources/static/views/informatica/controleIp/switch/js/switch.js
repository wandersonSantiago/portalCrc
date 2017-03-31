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
		.state('switch.cadastrar', {
			url : "/cadastrar",
			templateUrl : "views/informatica/controleIp/switch/switch.form.html",
			controller : "SwitchCadastarController as switchCtrl",
			ncyBreadcrumb: {
			 	parent: 'switch',
			    label: 'Cadastrar switch'
			  }
		})
		.state('switch.editar', {
			url : "/:idSwitch/editar",
			templateUrl : "views/informatica/controleIp/switch/switch.form.html",
			controller : "SwitchEditarController as switchCtrl",
			ncyBreadcrumb: {
				 	parent: 'switch.listar',
				    label: 'Editar'
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