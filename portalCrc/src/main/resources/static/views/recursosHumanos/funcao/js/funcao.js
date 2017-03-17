app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		
	.state('funcao', {
		abstract : true,
		url : '/funcao',
		templateUrl : 'views/recursosHumanos/funcao/funcao.index.html',
		redirectTo : 'funcao.listar',
		ncyBreadcrumb: {
			    label: 'funcao'
			  }
	})
	
		.state('funcao.listar', {
			url : "/listar",
			templateUrl : "views/recursosHumanos/funcao/funcao.list.html",
			controller : "FuncaoListarController as funcaoCtrl",
			ncyBreadcrumb: {
				 	parent: 'funcao.cadastrar',
				    label: 'lista funcao'
				  }
		})
		.state('funcao.cadastrar', {
			url : "/cadastrar",
			templateUrl : "views/recursosHumanos/funcao/funcao.form.html",
			controller : "FuncaoCadastarController as funcaoCtrl",
			ncyBreadcrumb: {
			 	parent: 'funcao',
			    label: 'Cadastrar funcao'
			  }
		})
		.state('funcao.editar', {
			url : "/:idFuncao/editar",
			templateUrl : "views/recursosHumanos/funcao/funcao.form.html",
			controller : "FuncaoEditarController as funcaoCtrl",
			ncyBreadcrumb: {
				 	parent: 'funcao.listar',
				    label: 'Editar'
				  }
		})
		.state('funcao.visualizar', {
			url : "/:idFuncao",
			templateUrl : "views/recursosHumanos/funcao/funcao.show.html",
			controller : "FuncaoVisualizarController as funcaoCtrl",
			ncyBreadcrumb: {
				 	parent: 'funcao.listar',
				    label: 'Visualizar'
				  }
		})

});