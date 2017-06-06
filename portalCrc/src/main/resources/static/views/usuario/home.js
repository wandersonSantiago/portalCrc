app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		.state('administrador', {
			abstract : true,
			url : '/admin',
			templateUrl : 'views/usuario/usuario.index.html',
			ncyBreadcrumb: {
				aprent : "home.menu",
				    label: 'Administrador'
				  }
		})
		.state('administrador.menu', {
			url : "/admin/menu",
			templateUrl : "views/usuario/home.html",
			controller : "UsuarioListarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'administrador',
				    label: 'Menu Administrador'
				  }
		})
	
	


		
});