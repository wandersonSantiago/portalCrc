app.controller("TemaCadastarController", TemaCadastarController);
app.controller("TemaEditarController", TemaEditarController);
app.controller("TemaListarController", TemaListarController);

TemaCadastarController.$inject = ['TemaService', 'toastr', '$rootScope', '$scope'];
TemaEditarController.$inject = ['$stateParams', '$state', 'TemaService', 'toastr', '$rootScope', '$scope'];
TemaListarController.$inject = ['$stateParams', '$state', 'TemaService', 'toastr', '$rootScope', '$scope'];

function TemaCadastarController( TemaService, toastr, $rootScope, $scope){
	
	var self = this;
	
	self.findCep = findCep;
	self.submit = submit;
	
	function submit(tema) {
		TemaService.salvar(self.tema).
			then(function(response){
				toastr.info('Salvo com Sucesso!!!');
				self.tema = null;
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
			});
		};
	
	function tiposDeTema(){
		 TemaService.listarTipoTema().
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
function TemaEditarController($stateParams, $state,TemaService, toastr, $rootScope, $scope){
	

	var self = this;
	var idTema = $stateParams.idTema;
	self.findCep = findCep;
	self.submit = submit;
	
	

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
	
	function tiposDeTema(){
		 TemaService.listarTipoTema().
			then(function(f){
				self.tipos = f;				
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
			});
		};
		
	 		
	function buscarPorId(id){
			if(!id)return;
			TemaService.buscarPorId(id).
			then(function(p){
				self.tema = p;
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