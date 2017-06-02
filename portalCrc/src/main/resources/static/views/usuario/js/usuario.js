app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		.state('usuario', {
			abstract : true,
			url : '/usuario',
			templateUrl : 'views/usuario/usuario.index.html',
			redirectTo : 'usuario.listar',
			ncyBreadcrumb: {
				parent: 'home.menu',
				    label: 'home'
				  }
		})
		.state('usuario.listar', {
			url : "",
			templateUrl : "views/usuario/usuario.list.html",
			controller : "UsuarioListarController as usuarioCtrl",
			ncyBreadcrumb: {
				 	parent: 'usuario.cadastrar',
				    label: 'Lista'
				  }
		})
		.state('usuario.cadastrar', {
			url : "/cadastrar",
			templateUrl : "views/usuario/usuario.form.html",
			controller : "UsuarioCadastarController as usuarioCtrl",
			ncyBreadcrumb: {
			 	parent: 'usuario',
			    label: 'Cadastrar'
			  }
		})
		.state('usuario.editar', {
			url : "/:idUsuario/editar",
			templateUrl : "views/usuario/usuario.form.html",
			controller : "UsuarioEditarController as usuarioCtrl",
			ncyBreadcrumb: {
				 	parent: 'usuario.listar',
				    label: 'Editar'
				  }
		})
		.state('usuario.permissao', {
			url : "/permissao",
			templateUrl : "views/usuario/usuario.permissao.html",
			controller : "UsuarioPermissaoController as Ctrl",
			ncyBreadcrumb: {
				 	parent: 'usuario.listar',
				    label: 'Permissao'
				  }
		})

});