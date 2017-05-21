app.controller("RamalCadastarController", RamalCadastarController);
app.controller("RamalEditarController", RamalEditarController);
app.controller("RamalListarController", RamalListarController);
app.controller("RamalCoordenadoriaController", RamalCoordenadoriaController);
app.controller("RamalUnidadesController", RamalUnidadesController);

RamalCadastarController.$inject = ['RamalService', 'SetorService', 'toastr' ];
RamalEditarController.$inject = ['RamalService', 'SetorService', 'toastr', '$stateParams', '$state'];
RamalListarController.$inject = ['RamalService', 'toastr'];
RamalCoordenadoriaController.$inject = ['UnidadeService', 'toastr'];
RamalUnidadesController.$inject = ['RamalService', 'toastr', '$stateParams', 'UnidadeService'];

function RamalCadastarController( RamalService, SetorService , toastr ){ 
	
	var self = this;
	
	self.submit = submit;
	setores();
	
	function submit(ramal) {
		RamalService.salvar(self.ramal).
			then(function(response){
				toastr.info("Salvo com Sucesso!!!");
				self.ramal = null;
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
				});
		}
	
	function setores(){
		 SetorService.listar().
			then(function(f){
				self.setores = f;
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
		};
}
	
function RamalEditarController( RamalService, SetorService , toastr , $stateParams , $state){ 
	
	var self = this;
	var idRamal = $stateParams.idRamal;
	
	self.submit = submit;
	setores();
	
	function submit(ramal) {
		RamalService.salvar(self.ramal).
			then(function(response){
				toastr.info("Alterado com Sucesso!!!");
				self.ramal = null;
				$state.go("ramal.listar");
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
				});
		}
	
	function setores(){
		 SetorService.listar().
			then(function(f){
				self.setores = f;
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
		};
		
		function buscarPorId(id){
			if(!id)return;
			RamalService.buscarPorId(id).
			then(function(p){
				self.ramal = p;
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
		};

		if(idRamal){
			buscarPorId(idRamal);		
		}
		
}
	
function RamalListarController( UnidadeService, toastr ){ 
	
	var self = this;
	
	listar();

	
	function listar(){
		UnidadeService.listar().
			then(function(f){
				self.listarRamal = f;
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
		};
}
function RamalCoordenadoriaController( UnidadeService, toastr){ 
	
	var self = this;	
	
	listar();	

	 function listar(){
		 UnidadeService.listarUnidadeCoordenadoria().
			then(function(f){
				self.unidades = f;
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
		};
	
}
function RamalUnidadesController( RamalService, toastr, $stateParams, UnidadeService){ 
	
	var self = this;	
	var idUnidade = $stateParams.idUnidade;
	var idCoordenadoria = $stateParams.idCoordenadoria;
	self.buscarUnidades = buscarUnidades;
	self.buscarTodasUnidades = buscarTodasUnidades; 
	
	function buscarTodasUnidades(){
		buscarUnidadesPorCoordenadorias(idCoordenadoria);
	}
	
	function buscarUnidades(tipo){
		 UnidadeService.buscarUnidadesPorTipo(idCoordenadoria, tipo).
			then(function(f){
				self.unidades = f;
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
	};
	
	 function buscarRamalPorUnidade(id){
		 RamalService.buscarRamalPorUnidade(id).
			then(function(f){
				self.ramals = f;
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
		};
	
		if(idUnidade){
			buscarRamalPorUnidade(idUnidade);		
		}
		
		function buscarUnidadesPorCoordenadorias(id){
			 UnidadeService.buscarUnidadePorCoordenadoria(id).
				then(function(f){
					self.unidades = f;
					}, function(errResponse){
						sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
				});
			};
		
			if(idCoordenadoria){
				buscarUnidadesPorCoordenadorias(idCoordenadoria);		
			}
		
}