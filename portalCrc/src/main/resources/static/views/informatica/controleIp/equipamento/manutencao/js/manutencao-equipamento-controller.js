app.controller("ManutencaoEquipamentoCadastarController", ManutencaoEquipamentoCadastarController);
app.controller("ManutencaoEquipamentoEditarController", ManutencaoEquipamentoEditarController);
app.controller("ManutencaoEquipamentoListarController", ManutencaoEquipamentoListarController);
app.controller("ManutencaoEquipamentoShowController", ManutencaoEquipamentoShowController);
app.controller("ManutencaoEquipamentoServicoController", ManutencaoEquipamentoServicoController);

function ManutencaoEquipamentoCadastarController($stateParams, EquipamentoService , ManutencaoEquipamentoService, toastr, $rootScope, $scope) {
	var self = this;
	var idEquipamento = $stateParams.idEquipamento;
	self.submit = submit;	

	function submit(manutencaoEquipamento) {
		self.manutencaoEquipamento.equipamento = self.equipamento;
		ManutencaoEquipamentoService.salvar(self.manutencaoEquipamento).then(function(response) {
			toastr.info("Salvo com Sucesso!!!");
			self.manutencaoEquipamento = null;
		}, function(errResponse) {
			sweetAlert({
				timer : 3000,
				text : errResponse.data.message,
				type : "info",
				width : 300,
				higth : 300,
				padding : 20
			});
		});
	};
	
	function buscarPorId(id) {
		if (!id)
			return;
		EquipamentoService.buscarPorId(id).then(function(p) {
			self.equipamento = p;
		}, function(errResponse) {
			sweetAlert({
				timer : 3000,
				text : errResponse.data.message,
				type : "info",
				width : 300,
				higth : 300,
				padding : 20
			});
		});
	}	;

	if (idEquipamento) {
		buscarPorId(idEquipamento);
	};
	// inicio Função data
		$scope.saidaOpen = function() {
			$scope.saida.opened = true;
		};

		$scope.saida = {
			opened : false
		};
		$scope.entradaOpen = function() {
			$scope.entrada.opened = true;
		};

		$scope.entrada = {
			opened : false
		};
		$scope.format = "dd/MM/yyyy";
		// termino função data
	
}
function ManutencaoEquipamentoEditarController($stateParams,
		$state, ManutencaoEquipamentoService, toastr, $rootScope, $scope) {

	var self = this;
	self.submit = submit;
	var idManutencaoEquipamento = $stateParams.idManutencaoEquipamento;


	function submit(manutencaoEquipamento) {
		ManutencaoEquipamentoService.alterar(self.manutencaoEquipamento).then(function(response) {
			toastr.info("Alterado com Sucesso!!!");
			self.manutencaoEquipamento = null;
			$state.go('manutencaoEquipamento.lista');
		}, function(errResponse) {
			sweetAlert({
				timer : 3000,
				text : errResponse.data.message,
				type : "info",
				width : 300,
				higth : 300,
				padding : 20
			});
		});
	}
	;

	
	function buscarPorId(id) {
		if (!id)
			return;
		ManutencaoEquipamentoService.buscarPorId(id).then(function(p) {
			self.manutencaoEquipamento = p;
		}, function(errResponse) {
			sweetAlert({
				timer : 3000,
				text : errResponse.data.message,
				type : "info",
				width : 300,
				higth : 300,
				padding : 20
			});
		});
	}
	;

	if (idManutencaoEquipamento) {
		buscarPorId(idManutencaoEquipamento);
	}
	;

}
function ManutencaoEquipamentoListarController($stateParams, $state, ManutencaoEquipamentoService,
		toastr, $rootScope, $scope) {
	var self = this;
	listarAgendado();
	 $scope.agendado = true;
	 
	 
	function listarRealizado() {
		ManutencaoEquipamentoService.listarRealizado().then(function(f) {
			self.manutencaoEquipamentos = f;
		}, function(errResponse) {
			sweetAlert({
				timer : 3000,
				text : errResponse.data.message,
				type : "info",
				width : 300,
				higth : 300,
				padding : 20
			});
		});
	}
	;
	function listarAgendado() {
		ManutencaoEquipamentoService.listarAgendado().then(function(f) {
			self.manutencaoEquipamentos = f;
		}, function(errResponse) {
			sweetAlert({
				timer : 3000,
				text : errResponse.data.message,
				type : "info",
				width : 300,
				higth : 300,
				padding : 20
			});
		});
	}
	;
	 self.ativaBotaoStatus =  function(botao){
    	 if(botao === 'realizado'){
    		 $scope.realizado = true;
    		 $scope.agendado = false;
    		 listarRealizado();
    	 }else if(botao === 'agendado'){
    		 $scope.realizado = false;
    		 $scope.agendado = true;
    		 listarAgendado();
    	 }
     };

}
function ManutencaoEquipamentoShowController($stateParams, $state, ManutencaoEquipamentoService,
		toastr, $rootScope, $scope) {

	var self = this;
	var idManutencaoEquipamento = $stateParams.idManutencaoEquipamento;

	function buscarPorId(id) {
		if (!id)
			return;
		ManutencaoEquipamentoService.buscarPorId(id).then(function(p) {
			self.manutencaoEquipamento = p;
		}, function(errResponse) {
			sweetAlert({
				timer : 3000,
				text : errResponse.data.message,
				type : "info",
				width : 300,
				higth : 300,
				padding : 20
			});
		});
	};

	if (idManutencaoEquipamento) {
		buscarPorId(idManutencaoEquipamento);
	};

	
}

function ManutencaoEquipamentoServicoController($stateParams, $state, ManutencaoEquipamentoService,
		toastr, $rootScope, $scope) {

	var self = this;
	var idManutencaoEquipamento = $stateParams.idManutencaoEquipamento;
	self.submit = submit;
	
	
	function buscarPorId(id) {
		if (!id)
			return;
		ManutencaoEquipamentoService.buscarPorId(id).then(function(p) {
			self.manutencaoEquipamento = p;
		}, function(errResponse) {
			sweetAlert({
				timer : 3000,
				text : errResponse.data.message,
				type : "info",
				width : 300,
				higth : 300,
				padding : 20
			});
		});
	};

	if (idManutencaoEquipamento) {
		buscarPorId(idManutencaoEquipamento);
	};
	
	


	function submit(manutencaoEquipamento) {
		self.manutencaoEquipamento.servicos = {equipamento : self.manutencaoEquipamento.equipamento , descricao : self.manutencaoEquipamento.servicos.descricao};
		ManutencaoEquipamentoService.alterar(self.manutencaoEquipamento).then(function(response) {
			toastr.info("Alterado com Sucesso!!!");
			self.manutencaoEquipamento = null;
			$state.go('manutencaoEquipamento.ativo');
		}, function(errResponse) {
			sweetAlert({
				timer : 3000,
				text : errResponse.data.message,
				type : "info",
				width : 300,
				higth : 300,
				padding : 20
			});
		});
	}
	;
	
}