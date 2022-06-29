app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		.state('permissao', {
			abstract : true,
			url : '/permissao',
			templateUrl : 'views/usuario/usuario.index.html',
			redirectTo : 'usuario.listar',
			ncyBreadcrumb: {
				parent: 'home.menu',
				    label: 'home.menu'
				  }
		})
		.state('permissao.listar', {
			url : "",
			templateUrl : "views/usuario/permissao/permissao.list.html",
			controller : "PermissaoListarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'permissao.cadastrar',
				    label: 'Listar permissões'
				  }
		})
		.state('permissao.cadastrar', {
			requiresAuthentication: true,
			permissions: ["ADMIN"],
			url : "/cadastrar",
			templateUrl : "views/usuario/permissao/permissao.form.html",
			controller : "PermissaoCadastarController as ctrl",
			ncyBreadcrumb: {
			 	parent: 'permissao',
			    label: 'Cadastrar permissões'
			  }
		})
		.state('permissao.editar', {
			requiresAuthentication: true,
			permissions: ["ADMIN"],
			url : "/:idPermissao/editar",
			templateUrl : "views/usuario/permissao/permissao.form.html",
			controller : "PermissaoEditarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'permissao.listar',
				    label: 'Editar permissões'
				  }
		})
		

});