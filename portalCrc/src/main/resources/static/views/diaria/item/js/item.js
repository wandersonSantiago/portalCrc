app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		
	.state('item', {
		abstract : true,
		url : '/item',
		templateUrl : 'views/diaria/item/item.index.html',
		redirectTo : 'item.listar',
		ncyBreadcrumb: {
			parent: 'diaria.menu',
			    label: 'item'
			  }
	})
	
		.state('item.unidade', {
			url : "/:idDiaria/listar/unidade",
			templateUrl : "views/diaria/item/item.master.list.html",
			controller : "ItemDiariaUnidadeListController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'item',
				    label: 'listar itens'
				  }
		})
		.state('item.coordenadoria', {
			url : "/:idDiaria/listar/coordenadoria",
			templateUrl : "views/diaria/item/item.master.list.html",
			controller : "ItemDiariaCoordenadoriaListController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'item',
				    label: 'listar itens coordenadoria'
				  }
		})
		.state('item.secretaria', {
			url : "/:idDiaria/listar/secretaria",
			templateUrl : "views/diaria/item/item.master.list.html",
			controller : "ItemDiariaSecretariaListController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'item',
				    label: 'listar itens secretaria'
				  }
		})
		
		.state('item.cadastrar', {
			url : "/:idDiaria/cadastrar",
			 views: {
	                '': {
	                	controller : "ItemDiariaCadastrarController as ctrl",
	                    templateUrl : "views/diaria/item/item.form.html",
	                },
	                'dadosPerfilCadastro': {
	                	controller : "ItemDiariaCadastrarController as ctrl",
	                    templateUrl : "views/diaria/item/item.list.html",
	                },
	            },	
			
			ncyBreadcrumb: {
			 	parent: 'item',
			    label: 'Cadastrar item'
			  }
		})
		
		.state('item.financasCadastrar', {
			url : "/:idDiaria/cadastrar/",
			 views: {
	                '': {
	                	controller : "ItemDiariaFinancasCadastrarController as ctrl",
	                    templateUrl : "views/diaria/item/item.form.html",
	                }               
	            },	
			
			ncyBreadcrumb: {
			 	parent: 'item',
			    label: 'Cadastrar item'
			  }
		})
		
		.state('item.editar', {
			url : "/:idItem/editar",
			 views: {
	                '': {
	                	controller : "ItemDiariaEditarController as ctrl",
	                    templateUrl : "views/diaria/item/item.form.html",
	                },
	                'dadosPerfilCadastro': {
	                	controller : "ItemDiariaEditarController as ctrl",
	                    templateUrl : "views/diaria/item/item.list.html",
	                },
	            },	
			
			ncyBreadcrumb: {
			 	parent: 'item',
			    label: 'Editar item'
			  }
		})
		
			.state('item.cadastrarFuncionario', {
			url : "/:idDiaria/funcionario/cadastrar",
			templateUrl : "views/diaria/funcionario/funcionario.diaria.form.html",
			controller : "FuncionarioDiariaCadastrarController as ctrl",
			ncyBreadcrumb: {
			 	parent: 'funcionarioDiaria',
			    label: 'Cadastrar Diaria'
			  }
		})
		
		.state('item.visualizar', {
			url : "/:idFuncionarioDiaria/diaria",
			templateUrl : "views/diaria/item/item.show.html",
			controller : "ItemDiariaShowController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'item',
				    label: 'Visualizar item'
				  }
		})
		
		.state('item.usuario', {
			url : "/diaria/:idDiaria/usuario",
			templateUrl : "views/diaria/item/item.show.html",
			controller : "ItemDiariaUsuarioController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'item',
				    label: 'usuario item'
				  }
		})

});