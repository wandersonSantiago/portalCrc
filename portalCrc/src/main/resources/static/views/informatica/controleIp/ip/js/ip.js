app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		
	.state('ip', {
		abstract : true,
		url : '/ip',
		templateUrl : 'views/informatica/controleIp/ip/ip.index.html',
		redirectTo : 'ip.listar',
		ncyBreadcrumb: {
			parent: 'informatica.controleIp',
			    label: 'ip'
			  }
	})
	
		.state('ip.listar', {
			url : "/listar",
			templateUrl : "views/informatica/controleIp/ip/ip.list.html",
			controller : "IpListarController as ipCtrl",
			ncyBreadcrumb: {
				 	parent: 'ip.cadastrar',
				    label: 'lista'
				  }
		})
		.state('ip.ativos', {
			url : "/ativos",
			templateUrl : "views/informatica/controleIp/ip/ip.list.html",
			controller : "IpListarAtivosController as ipCtrl",
			ncyBreadcrumb: {
				 	parent: 'ip.cadastrar',
				    label: 'ativos'
				  }
		})
		.state('ip.inativos', {
			url : "/inativos",
			templateUrl : "views/informatica/controleIp/ip/ip.list.html",
			controller : "IpListarInativosController as ipCtrl",
			ncyBreadcrumb: {
				 	parent: 'ip.cadastrar',
				    label: 'inativos'
				  }
		})
		.state('ip.cadastrar', {
			requiresAuthentication: true,
			permissions: ["IP", "ADMIN"],
			url : "/cadastrar",
			templateUrl : "views/informatica/controleIp/ip/ip.form.html",
			controller : "IpCadastarController as ipCtrl",
			ncyBreadcrumb: {
			 	parent: 'ip',
			    label: 'Cadastrar ip'
			  }
		})
		.state('ip.editar', {
			requiresAuthentication: true,
			permissions: ["IP", "ADMIN"],
			url : "/:idIp/editar",
			templateUrl : "views/informatica/controleIp/ip/ip.form.html",
			controller : "IpEditarController as ipCtrl",
			ncyBreadcrumb: {
				 	parent: 'ip.listar',
				    label: 'Editar'
				  }
		})
		.state('ip.cmd', {
			requiresAuthentication: true,
			permissions: ["IP", "ADMIN"],
			url : "/:idIp/CMD",
			templateUrl : "views/informatica/controleIp/ip/ip.cmd.html",
			controller : "IpCMDController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'ip.listar',
				    label: 'CMD'
				  }
		})
		.state('ip.menu', {
			url : "/tipo/ip",
			templateUrl : "views/informatica/controleIp/ip/home.html",
			ncyBreadcrumb: {
				 	parent: 'ip.listar',
				    label: 'Visualizar'
				  }
		})

});