app.controller("UnidadeCadastarController", UnidadeCadastarController);
app.controller("UnidadeEditarController", UnidadeEditarController);
app.controller("UnidadeListarController", UnidadeListarController);
app.controller("UnidadeShowController", UnidadeShowController);

UnidadeCadastarController.$inject = ['UnidadeService', 'CoordenadoriaService', 'buscaCepService', 'toastr', '$rootScope', '$scope'];
UnidadeEditarController.$inject = ['$stateParams', '$state', 'UnidadeService', 'CoordenadoriaService', 'buscaCepService', 'toastr', '$rootScope', '$scope'];
UnidadeListarController.$inject = ['$stateParams', '$state', 'UnidadeService', 'toastr', '$rootScope', '$scope'];
UnidadeShowController.$inject = ['$stateParams', '$state', 'UnidadeService', 'toastr', '$rootScope', '$scope'];

function UnidadeCadastarController( UnidadeService, CoordenadoriaService, buscaCepService ,toastr, $rootScope, $scope){
	
	var self = this;
	
	self.findCep = findCep;
	self.submit = submit;
	tiposDeUnidade();
	listarCoordenadorias();
	
	function findCep() {		
		var cep = self.unidade.dadosUnidade.endereco.cep;
		buscaCepService.get({'cep': cep}).$promise
		.then(function success(result){
			self.unidade.dadosUnidade.endereco = result;
		}).catch(function error(msg) {
			sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
		});		
    };

	function submit(unidade) {
		UnidadeService.salvar(self.unidade).
			then(function(response){
				toastr.info('Salvo com Sucesso!!!');
				self.unidade = null;
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
			});
		};
	
	function tiposDeUnidade(){
		 UnidadeService.listarTipoUnidade().
			then(function(f){
				self.tipos = f;				
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
			});
		};
		
	 function listarCoordenadorias(){
		 CoordenadoriaService.listar().
			then(function(f){
				self.coordenadorias = f;			
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
			});
		};
		
}
function UnidadeEditarController($stateParams, $state,UnidadeService, CoordenadoriaService, buscaCepService ,toastr, $rootScope, $scope){
	

	var self = this;
	var idUnidade = $stateParams.idUnidade;
	self.findCep = findCep;
	self.submit = submit;
	tiposDeUnidade();
	listarCoordenadorias();
	
	function findCep() {		
		var cep = self.unidade.dadosUnidade.endereco.cep;
		buscaCepService.get({'cep': cep}).$promise
		.then(function success(result){
			self.unidade.dadosUnidade.endereco = result;
		}).catch(function error(msg) {
			sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
		});		
    };

	function submit(unidade) {
		UnidadeService.alterar(self.unidade).
			then(function(response){
				toastr.info('Alterado com Sucesso!!!');
				self.unidade = null;
				$state.go('unidade.listar');
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
			});
		};
	
	function tiposDeUnidade(){
		 UnidadeService.listarTipoUnidade().
			then(function(f){
				self.tipos = f;				
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
			});
		};
		
	 function listarCoordenadorias(){
		 CoordenadoriaService.listar().
			then(function(f){
				self.coordenadorias = f;			
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
			});
		};
		
	function buscarPorId(id){
			if(!id)return;
			UnidadeService.buscarPorId(id).
			then(function(p){
				self.unidade = p;
		}, function(errResponse){
			sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
		});
	};
	
		if(idUnidade){
			buscarPorId(idUnidade);			
		}
}
function UnidadeListarController($stateParams, $state, UnidadeService, toastr, $rootScope, $scope){
	
	var self =this;
	
	listar();
	
	 function listar(){
		 UnidadeService.listar().
			then(function(f){
				self.unidades = f;	
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
			});
		};
}
function UnidadeShowController( $stateParams, $state, UnidadeService, toastr, $rootScope, $scope){
	
}