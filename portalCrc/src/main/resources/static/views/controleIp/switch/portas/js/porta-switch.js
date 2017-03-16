app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		
	.state('portaSwitch', {
		abstract : true,
		url : '/portaSwitch',
		templateUrl : 'views/controleIp/switch/portas/porta.switch.index.html',
		redirectTo : 'portaPortaSwitch.listar',
		ncyBreadcrumb: {
			    label: 'portaPortaSwitch'
			  }
	})
	
		.state('portaSwitch.listar', {
			url : "/listar",
			templateUrl : "views/controleIp/switch/portas/porta.switch.list.html",
			controller : "PortaSwitchListarController as portaSwitchCtrl",
			ncyBreadcrumb: {
				 	parent: 'portaPortaSwitch.cadastrar',
				    label: 'lista portaPortaSwitch'
				  }
		})
		.state('portaSwitch.cadastrar', {
			url : "/cadastrar",
			templateUrl : "views/controleIp/switch/portas/porta.switch.form.html",
			controller : "PortaSwitchCadastarController as portaSwitchCtrl",
			ncyBreadcrumb: {
			 	parent: 'portaPortaSwitch',
			    label: 'Cadastrar portaPortaSwitch'
			  }
		})
		.state('portaSwitch.editar', {
			url : "/:idPortaSwitch/editar",
			templateUrl : "views/controleIp/switch/portas/porta.switch.form.html",
			controller : "PortaSwitchEditarController as portaSwitchCtrl",
			ncyBreadcrumb: {
				 	parent: 'portaPortaSwitch.listar',
				    label: 'Editar'
				  }
		})
		.state('portaSwitch.visualizar', {
			url : "/:idPortaSwitch/portas",
			templateUrl : "views/controleIp/switch/portas/porta.switch.show.html",
			controller : "PortaSwitchShowController as portaSwitchCtrl",
			ncyBreadcrumb: {
				 	parent: 'portaPortaSwitch.listar',
				    label: 'Visualizar'
				  }
		})

});