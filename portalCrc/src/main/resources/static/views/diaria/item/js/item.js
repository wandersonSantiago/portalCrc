app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		
	.state('item', {
		abstract : true,
		url : '/item',
		templateUrl : 'views/diaria/item/item.index.html',
		redirectTo : 'item.listar',
		ncyBreadcrumb: {
			    label: 'item'
			  }
	})
	
		.state('item.unidade', {
			url : "/:idDiariaUnidade/listar/unidade",
			templateUrl : "views/diaria/item/item.unidade.list.html",
			controller : "ItemDiariaCadastrarController as itemCtrl",
			ncyBreadcrumb: {
				 	parent: 'item.cadastrar',
				    label: 'lista item'
				  }
		})
		.state('item.coordenadoria', {
			url : "/:idDiariaCoordenadoria/listar/coordenadoria",
			templateUrl : "views/diaria/item/item.coordenadoria.list.html",
			controller : "ItemDiariaCadastrarController as itemCtrl",
			ncyBreadcrumb: {
				 	parent: 'item.cadastrar',
				    label: 'lista item'
				  }
		})
		.state('item.secretaria', {
			url : "/:idDiariaSecretaria/listar/secretaria",
			templateUrl : "views/diaria/item/item.secretaria.list.html",
			controller : "ItemDiariaCadastrarController as itemCtrl",
			ncyBreadcrumb: {
				 	parent: 'item.cadastrar',
				    label: 'lista item'
				  }
		})
		.state('item.cadastrar', {
			url : "/:idDiaria/cadastrar",
			templateUrl : "views/diaria/item/item.form.html",
			controller : "ItemDiariaCadastrarController as itemCtrl",
			ncyBreadcrumb: {
			 	parent: 'item',
			    label: 'Cadastrar item'
			  }
		})
		.state('item.editar', {
			url : "/:idItem/editar",
			templateUrl : "views/diaria/item/item.form.html",
			controller : "ItemDiariaEditarController as itemCtrl",
			ncyBreadcrumb: {
				 	parent: 'item.listar',
				    label: 'Editar'
				  }
		})
		.state('item.visualizar', {
			url : "/:idItem",
			templateUrl : "views/diaria/item/item.show.html",
			controller : "ItemDiariaVisualizarController as itemCtrl",
			ncyBreadcrumb: {
				 	parent: 'item.listar',
				    label: 'Visualizar'
				  }
		})

});