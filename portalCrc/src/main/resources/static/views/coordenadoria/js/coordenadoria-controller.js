app.controller("CoordenadoriaCadastarController", CoordenadoriaCadastarController);
app.controller("CoordenadoriaEditarController", CoordenadoriaEditarController);
app.controller("CoordenadoriaListarController", CoordenadoriaListarController);
app.controller("CoordenadoriaShowController", CoordenadoriaShowController);

CoordenadoriaCadastarController.$inject = ['CoordenadoriaService', 'SecretariaService', 'toastr', '$rootScope', '$scope'];
CoordenadoriaEditarController.$inject = ['$stateParams', '$state', 'CoordenadoriaService', 'SecretariaService', 'toastr', '$rootScope', '$scope'];
CoordenadoriaListarController.$inject = ['$stateParams', '$state', 'CoordenadoriaService', 'toastr', '$rootScope', '$scope'];
CoordenadoriaShowController.$inject = ['$stateParams', '$state', 'CoordenadoriaService', 'toastr', '$rootScope', '$scope'];

function CoordenadoriaCadastarController( CoordenadoriaService, SecretariaService, toastr, $rootScope, $scope){
	
	var self = this;
	self.submit = submit;
	
	listar();
	
	function submit(coordenadoria) {
		CoordenadoriaService.salvar(self.coordenadoria).
			then(function(response){
				toastr.info("Salvo com Sucesso!!!");
				self.coordenadoria = null;
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
			});
		};
	
	function listar(){
		SecretariaService.listar().
			then(function(f){
				self.secretarias = f;			
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
			});
		};
}
function CoordenadoriaEditarController($stateParams, $state, CoordenadoriaService, SecretariaService, toastr, $rootScope, $scope){
	
	var self =this;
	var idCoordenadoria = $stateParams.idCoordenadoria;
	self.submit = submit;
	
	listar();
	
	function submit(coordenadoria) {
		CoordenadoriaService.alterar(self.coordenadoria).
			then(function(response){
				toastr.info("Alterado com Sucesso!!!");
				self.coordenadoria = null;
				$state.go('coordenadoria.listar');
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
			});
		};
	
	function listar(){
		SecretariaService.listar().
			then(function(f){
				self.secretarias = f;			
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
			});
		};
		
	function buscarPorId(id){
			if(!id)return;
			CoordenadoriaService.buscarPorId(id).
			then(function(p){
				self.coordenadoria = p;
		}, function(errResponse){
			sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
			});
		};
	
		if(idCoordenadoria){
			buscarPorId(idCoordenadoria);			
		}		
}
function CoordenadoriaListarController($stateParams, $state, CoordenadoriaService, toastr, $rootScope, $scope){
	var self = this;
	
	listar();
	
	 function listar(){
		 CoordenadoriaService.listar().
			then(function(f){
				self.coordenadorias = f;			
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
			});
		};
}
function CoordenadoriaShowController( $stateParams, $state, CoordenadoriaService, toastr, $rootScope, $scope){
	
}