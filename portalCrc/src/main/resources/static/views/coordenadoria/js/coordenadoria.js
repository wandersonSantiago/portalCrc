app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider

	.state('coordenadoria', {
		abstract : true,
		url : '/coordenadoria',
		templateUrl : 'views/coordenadoria/coordenadoria.index.html',
		redirectTo : 'coordenadoria.listar',
		ncyBreadcrumb : {
			label : 'coordenadoria'
		}
	})

	.state('coordenadoria.listar', {
		url : "/listar",
		templateUrl : "views/coordenadoria/coordenadoria.list.html",
		controller : "CoordenadoriaListarController as coordenadoriaCtrl",
		ncyBreadcrumb : {
			parent : 'coordenadoria.cadastrar',
			label : 'lista coordenadoria'
		}
	}).state('coordenadoria.cadastrar', {
		url : "/cadastrar",
		templateUrl : "views/coordenadoria/coordenadoria.form.html",
		controller : "CoordenadoriaCadastarController as coordenadoriaCtrl",
		ncyBreadcrumb : {
			parent : 'coordenadoria',
			label : 'Cadastrar coordenadoria'
		}
	}).state('coordenadoria.editar', {
		url : "/:idCoordenadoria/editar",
		templateUrl : "views/coordenadoria/coordenadoria.form.html",
		controller : "CoordenadoriaEditarController as coordenadoriaCtrl",
		ncyBreadcrumb : {
			parent : 'coordenadoria.listar',
			label : 'Editar'
		}
	}).state('coordenadoria.visualizar', {
		url : "/:idCoordenadoria",
		templateUrl : "views/coordenadoria/coordenadoria.show.html",
		controller : "CoordenadoriaVisualizarController as coordenadoriaCtrl",
		ncyBreadcrumb : {
			parent : 'coordenadoria.listar',
			label : 'Visualizar'
		}
	})

});