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
			url : "/listar",
			templateUrl : "views/usuario/usuario.list.html",
			controller : "UsuarioListarController as usuarioCtrl",
			ncyBreadcrumb: {
				 	parent: 'usuario.cadastrar',
				    label: 'Lista'
				  }
		})
		.state('usuario.perfil', {
			url : "/perfil",
			templateUrl : "views/usuario/usuario.perfil.html",
			controller : "UsuarioPerfilController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'usuario',
				    label: 'Perfil Usuário'
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