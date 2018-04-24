app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		
	.state('item', {
		abstract : true,
		url : '/diaria/item',
		templateUrl : 'views/diaria/diaria.index.html',
		ncyBreadcrumb: {
			parent: 'diaria',
			    label: 'item'
			  }
	})
	
		.state('item.unidade', {
			requiresAuthentication: true,
			permissions: ["DIARIA_TRANSPARENCIA", "ADMIN"],
			url : "/listar/unidade",
			params: {
				idDiaria: null,
			  },
			templateUrl : "views/diaria/item/item.master.list.html",
			controller : "ItemDiariaUnidadeListController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'item',
				    label: 'listar itens'
				  }
		})
		.state('item.coordenadoria', {
			requiresAuthentication: true,
			permissions: ["DIARIA_ITEM_COORDENADORIA", "ADMIN"],
			url : "/listar/coordenadoria",
			params: {
				idDiaria: null,
			  },
			templateUrl : "views/diaria/item/item.master.list.html",
			controller : "ItemDiariaCoordenadoriaListController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'item',
				    label: 'listar itens coordenadoria'
				  }
		})
		.state('item.secretaria', {
			requiresAuthentication: true,
			permissions: ["DIARIA_ITEM_SECRETRIA", "ADMIN"],
			url : "/listar/secretaria",
			params: {
				idDiaria: null,
			  },
			templateUrl : "views/diaria/item/item.master.list.html",
			controller : "ItemDiariaSecretariaListController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'item',
				    label: 'listar itens secretaria'
				  }
		})
		
		.state('item.cadastrar', {
			requiresAuthentication: true,
			permissions: ["DIARIA_USUARIO", "ADMIN"],
			url : "/cadastrar",
			params: {
				idDiaria: null,
				idFuncionario: null
			  },
			 
	          controller : "ItemDiariaCadastrarController as ctrl",
	          templateUrl : "views/diaria/item/item.form.html",
	          ncyBreadcrumb: {
			 	parent: 'item',
			    label: 'Cadastrar item'
			  }
		})
		
		.state('item.financasCadastrar', {
			requiresAuthentication: true,
			permissions: ["DIARIA_FINANCAS"],
			url : "/financas/cadastrar/",
			params: {
				idDiaria: null,
				idFuncionario: null
			  },
	         controller : "ItemDiariaCadastrarController as ctrl",
	         templateUrl : "views/diaria/item/item.form.html",
	         
			ncyBreadcrumb: {
			 	parent: 'item',
			    label: 'Cadastrar item'
			  }
		})
		
		.state('item.editar', {
			requiresAuthentication: true,
			permissions: ["DIARIA_USUARIO", "DIARIA_FINANCAS"],
			url : "/editar",
			params: {
				idItem: null,
			  },
			 
	        controller : "ItemDiariaEditarController as ctrl",
	        templateUrl : "views/diaria/item/item.form.html",
	         
			ncyBreadcrumb: {
			 	parent: 'item',
			    label: 'Editar item'
			  }
		})
		
			.state('item.cadastrarFuncionario', {
			url : "/funcionario/cadastrar",
			params: {
				idDiaria: null,
				idFuncionario: null
			  },
			templateUrl : "views/diaria/funcionario/funcionario.diaria.form.html",
			controller : "FuncionarioDiariaCadastrarController as ctrl",
			ncyBreadcrumb: {
			 	parent: 'item',
			    label: 'Conferir'
			  }
		})
		
		.state('item.visualizar', {
			url : "/:idFuncionarioDiaria/diaria",
			templateUrl : "views/diaria/item/item.show.html",
			controller : "ItemDiariaShowController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'item',
				    label: 'Extrato'
				  }
		})
		
		.state('item.usuario', {
			requiresAuthentication: true,
			permissions: ["DIARIA_USUARIO"],
			url : "/diaria/usuario",
			params: {
				idDiaria: null,
			  },
			templateUrl : "views/diaria/item/item.show.html",
			controller : "ItemDiariaUsuarioController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'item',
				    label: 'Extrato'
				  }
		})

});