app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		.state('usuario', {
			url : '/usuario',
			templateUrl : 'views/usuario/usuario.index.html',
			redirectTo : 'usuario.listar',
			ncyBreadcrumb: {
				parent: 'administrador.menu',
				    label: 'Usuario'
				  }
		})
		.state('usuario.listar', {
			url : "/listar",
			templateUrl : "views/usuario/usuario.list.html",
			controller : "UsuarioListarController as usuarioCtrl",
			ncyBreadcrumb: {
				 	parent: 'usuario',
				    label: 'Lista'
				  }
		})
		.state('usuario.perfil', {
			url : "/perfil",
			templateUrl : "views/usuario/usuario.perfil.html",
			controller : "UsuarioPerfilController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'usuario',
				    label: 'Perfil'
				  }
		})
		.state('usuario.buscar', {
			url : "/pesquisar",
			templateUrl : "views/usuario/usuario.buscar.html",
			controller : "UsuarioBuscarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'usuario',
				    label: 'Pesquisar'
				  }
		})
		.state('usuario.cadastrar', {
			requiresAuthentication: true,
			permissions: ["USUARIO_UNIDADE","ADMIN"],
			url : "/cadastrar",
			templateUrl : "views/usuario/usuario.form.html",
			controller : "UsuarioCadastarController as usuarioCtrl",
			ncyBreadcrumb: {
			 	parent: 'usuario',
			    label: 'Cadastrar'
			  }
		})
		.state('usuario.editar', {
			requiresAuthentication: true,
			permissions: ["USUARIO_UNIDADE","ADMIN"],
			url : "/:idUsuario/editar",
			templateUrl : "views/usuario/usuario.form.html",
			controller : "UsuarioEditarController as usuarioCtrl",
			ncyBreadcrumb: {
				 	parent: 'usuario.listar',
				    label: 'Editar'
				  }
		})
		.state('usuario.permissao', {
			requiresAuthentication: true,
			permissions: ["USUARIO_UNIDADE","ADMIN"],
			url : "/:idUsuario/permissao",
			templateUrl : "views/usuario/usuario.permissao.html",
			controller : "UsuarioPermissaoController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'usuario.listar',
				    label: 'Permissao'
				  }
		})

});