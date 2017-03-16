app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		.state('usuario', {
			abstract : true,
			url : '/usuario',
			templateUrl : 'views/usuario/usuario.index.html',
			redirectTo : 'usuario.listar',
			ncyBreadcrumb: {
				    label: 'Usuario'
				  }
		})
		.state('entidadeReligiosa.listar', {
			url : "",
			templateUrl : "views/entidadeReligiosa/entidadeReligiosa.list.html",
			controller : "EntidadeReligiosaListarController as entidadeCtrl",
			ncyBreadcrumb: {
				 	parent: 'home',
				    label: 'Entidades Religiosas'
				  }
		})
		.state('entidadeReligiosa.cadastrar', {
			url : "/cadastrar",
			templateUrl : "views/entidadeReligiosa/entidadeReligiosa.form.html",
			controller : "EntidadeReligiosaCadastarController as entidadeCtrl",
			ncyBreadcrumb: {
			 	parent: 'home',
			    label: 'Cadastrar Entidade Religiosa'
			  }
		})
		.state('entidadeReligiosa.editar', {
			url : "/:idEntidade/editar",
			templateUrl : "views/entidadeReligiosa/entidadeReligiosa.form.html",
			controller : "EntidadeReligiosaEditarController as entidadeCtrl",
			ncyBreadcrumb: {
				 	parent: 'entidadeReligiosa.listar',
				    label: 'Editar'
				  }
		})
		.state('entidadeReligiosa.visualizar', {
			url : "/:idEntidade",
			templateUrl : "views/entidadeReligiosa/entidadeReligiosa.show.html",
			controller : "EntidadeReligiosaVisualizarController as entidadeCtrl",
			ncyBreadcrumb: {
				 	parent: 'entidadeReligiosa.listar',
				    label: 'Visualizar'
				  }
		})

});