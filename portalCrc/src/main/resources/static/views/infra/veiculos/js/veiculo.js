app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider

	.state('veiculo', {
		abstract : true,
		url : '/veiculo',
		templateUrl : 'views/infra/veiculos/veiculo.index.html',
		redirectTo : 'veiculo.listar',
		ncyBreadcrumb : {
			parent : 'infraestrutura.menu',
			label : 'veiculo'
		}
	})

	.state('veiculo.listar', {
		url : "/listar",
		templateUrl : "views/infra/veiculos/veiculo.list.html",
		controller : "VeiculoListarController as veiculoCtrl",
		ncyBreadcrumb : {
			parent : 'veiculo.cadastrar',
			label : 'lista veiculo'
		}
	}).state('veiculo.cadastrar', {
		requiresAuthentication: true,
		permissions: ["CADASTRO_VEICULOS", "ADMIN"],
		url : "/cadastrar",
		templateUrl : "views/infra/veiculos/veiculo.form.html",
		controller : "VeiculoCadastarController as veiculoCtrl",
		ncyBreadcrumb : {
			parent : 'veiculo',
			label : 'Cadastrar veiculo'
		}
	}).state('veiculo.editar', {
		requiresAuthentication: true,
		permissions: ["CADASTRO_VEICULOS", "ADMIN"],
		url : "/:idVeiculo/editar",
		templateUrl : "views/infra/veiculos/veiculo.form.html",
		controller : "VeiculoEditarController as veiculoCtrl",
		ncyBreadcrumb : {
			parent : 'veiculo.listar',
			label : 'Editar'
		}
	}).state('veiculo.menu', {
		url : "/menu",
		templateUrl : "views/infra/veiculos/home.html",
		ncyBreadcrumb : {
			parent : 'veiculo.listar',
			label : 'Visualizar'
		}
	})

});