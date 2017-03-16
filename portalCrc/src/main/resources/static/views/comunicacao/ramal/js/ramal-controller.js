app.controller("RamalCadastarController", RamalCadastarController);
app.controller("RamalEditarController", RamalEditarController);
app.controller("RamalListarController", RamalListarController);

RamalCadastarController.$inject = ['RamalService', 'SetorService', 'toastr' ];
RamalEditarController.$inject = ['RamalService', 'SetorService', 'toastr', '$stateParams', '$state'];
RamalListarController.$inject = ['RamalService', 'toastr'];


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
	
function RamalListarController( RamalService, toastr ){ 
	
	var self = this;
	
	listar();

	
	function listar(){
		RamalService.listar().
			then(function(f){
				self.listarRamal = f;
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
		};
}