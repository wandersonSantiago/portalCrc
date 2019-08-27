app.controller("TemaCadastarController", TemaCadastarController);
app.controller("TemaEditarController", TemaEditarController);
app.controller("TemaListarController", TemaListarController);


function TemaCadastarController( TemaService, toastr, $rootScope, $scope, SistemaService, ModuloService , ChamadoTiService){
	
	var self = this;
	
	self.submit = submit;
	self.modulos = modulos;
	sistemas();
	tipoEquipamento();
	
	function tipoEquipamento(){
		 ChamadoTiService.tipoEquipamento().
			then(function(f){
				self.tipoEquipamentos = f;			
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
		};
	
	function submit(tema) {
		TemaService.salvar(self.tema).
			then(function(response){
				toastr.info('Salvo com Sucesso!!!');
				self.tema = null;
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
			});
		};
	
	function sistemas(){
		 SistemaService.listar().
			then(function(f){
				self.sistemas = f;				
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
			});
		};
		
	 function modulos(idSistema){
		 ModuloService.buscarPorSistema(idSistema).
			then(function(f){
				self.modulos = f;			
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
			});
		};
		
}
function TemaEditarController($stateParams, $state,TemaService, toastr, $rootScope, $scope, SistemaService , ModuloService , ChamadoTiService){
	

	var self = this;
	var idTema = $stateParams.idTema;
	self.submit = submit;
	sistemas();
	
tipoEquipamento();
	
	function tipoEquipamento(){
		 ChamadoTiService.tipoEquipamento().
			then(function(f){
				self.tipoEquipamentos = f;			
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
		};

	function submit(tema) {
		TemaService.alterar(self.tema).
			then(function(response){
				toastr.info('Alterado com Sucesso!!!');
				self.tema = null;
				$state.go('tema.listar');
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
			});
		};
		
		function sistemas(){
			 SistemaService.listar().
				then(function(f){
					self.sistemas = f;				
					}, function(errResponse){
						sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
				});
			};
			
		 function modulos(idSistema){
			 ModuloService.buscarPorSistema(idSistema).
				then(function(f){
					self.modulos = f;			
					}, function(errResponse){
						sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
				});
			};
			
		 		
	function buscarPorId(id){
			if(!id)return;
			TemaService.buscarPorId(id).
			then(function(p){
				self.tema = p;
				modulos(p.sistema.id);
		}, function(errResponse){
			sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
		});
	};
	
		if(idTema){
			buscarPorId(idTema);			
		}
}
function TemaListarController($stateParams, $state, TemaService, toastr, $rootScope, $scope){
	
	var self =this;
	
	listar();
	
	 function listar(){
		 TemaService.listar().
			then(function(f){
				self.temas = f;	
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
			});
		};
}