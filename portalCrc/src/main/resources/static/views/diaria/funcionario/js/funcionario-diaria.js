app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		
	.state('funcionarioDiaria', {
		abstract : true,
		url : '/diaria/funcionario',
		templateUrl : 'views/diaria/funcionario/funcionario.diaria.index.html',
		redirectTo : 'funcionario.listar',
		ncyBreadcrumb: {
		 	parent: 'diaria.menu',
			    label: 'Diaria'
			  }
	})
	
		/*.state('funcionarioDiaria.unidade', {
			url : "/:idDiariaUnidade/listar/unidade",
			templateUrl : "views/diaria/funcionario/funcionario.unidade.list.html",
			controller : "FuncionarioDiariaCadastrarController as funcionarioCtrl",
			ncyBreadcrumb: {
				 	parent: 'funcionario.cadastrar',
				    label: 'lista funcionario'
				  }
		})*/
		/*.state('funcionarioDiaria.coordenadoria', {
			url : "/:idDiariaCoordenadoria/listar/coordenadoria",
			templateUrl : "views/diaria/funcionario/funcionario.coordenadoria.list.html",
			controller : "FuncionarioDiariaCadastrarController as funcionarioCtrl",
			ncyBreadcrumb: {
				 	parent: 'funcionario.cadastrar',
				    label: 'lista funcionario'
				  }
		})
		.state('funcionarioDiaria.secretaria', {
			url : "/:idDiariaSecretaria/listar/secretaria",
			templateUrl : "views/diaria/funcionario/funcionario.secretaria.list.html",
			controller : "FuncionarioDiariaCadastrarController as funcionarioCtrl",
			ncyBreadcrumb: {
				 	parent: 'funcionario.cadastrar',
				    label: 'lista funcionario'
				  }
		})*/
		.state('funcionarioDiaria.verificar', {
			url : "/:idDiaria/verificar",
			 views: {
	                '': {
	                	controller : "FuncionarioDiariaCadastrarController as funcionarioCtrl",
	                    templateUrl : "views/diaria/funcionario/verificacao.diaria.form.html",
	                },
	                'dadosPerfilCadastro': {
	                	controller : "ListarFuncionarioDiariaController as ctrl",
	                    templateUrl : "views/diaria/funcionario/item.unidade.list.html",
	                },
	            },	
			
			ncyBreadcrumb: {
			 	parent: 'funcionarioDiaria',
			    label: 'Cadastrar Diaria'
			  }
		})
		
		.state('funcionarioDiaria.usuario', {
			url : "/:idDiaria/funcionario/cadastrar",
			templateUrl : "views/diaria/funcionario/usuario.diaria.form.html",
			controller : "FuncionarioDiariaCadastrarController as funcionarioCtrl",
			ncyBreadcrumb: {
			 	parent: 'funcionarioDiaria',
			    label: 'Cadastrar Diaria'
			  }
		})
		.state('funcionarioDiaria.cadastrar', {
			url : "/funcionario/cadastrar",
			templateUrl : "views/diaria/funcionario/usuario.diaria.form.html",
			controller : "FuncionarioDiariaCadastrarController as funcionarioCtrl",
			ncyBreadcrumb: {
			 	parent: 'funcionarioDiaria',
			    label: 'Cadastrar Diaria'
			  }
		})
		.state('funcionarioDiaria.editar', {
			url : "/:idFuncionario/",
			templateUrl : "views/diaria/funcionario/funcionario.diaria.form.html",
			controller : "FuncionarioDiariaEditarController as funcionarioCtrl",
			ncyBreadcrumb: {
				 	parent: 'funcionario.listar',
				    label: 'Editar'
				  }
		})
		.state('funcionarioDiaria.visualizar', {
			url : "/:idFuncionario",
			templateUrl : "views/diaria/funcionario/funcionario.show.html",
			controller : "FuncionarioDiariaVisualizarController as funcionarioCtrl",
			ncyBreadcrumb: {
				 	parent: 'funcionario.listar',
				    label: 'Visualizar'
				  }
		})

});