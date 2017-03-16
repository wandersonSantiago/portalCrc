app.controller("TelefoneCadastarController", TelefoneCadastarController);
app.controller("TelefoneEditarController", TelefoneEditarController);
app.controller("TelefoneListarController", TelefoneListarController);

TelefoneCadastarController.$inject = ['TelefoneService', 'SetorService', 'toastr' ];
TelefoneEditarController.$inject = ['TelefoneService', 'SetorService', 'toastr', '$state', '$stateParams'];
TelefoneListarController.$inject = ['TelefoneService', 'toastr'];


function TelefoneCadastarController( TelefoneService, SetorService , toastr ){ 
	
	var self = this;	
	
	self.submit = submit;
	setores();
	
	function submit(telefone) {
		TelefoneService.salvar(self.telefone).
			then(function(response){
				toastr.info("Salvo com Sucesso!!!");
				self.telefone = null;
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

function TelefoneEditarController( TelefoneService, SetorService , toastr, $state, $stateParams){ 
	
	var self = this;	
	
	var idTelefone = $stateParams.idTelefone;
	
	self.submit = submit;
	setores();
	
	function submit(telefone) {
		TelefoneService.alterar(self.telefone).
			then(function(response){
				toastr.info("Alterado com Sucesso!!!");
				$state.go('telefone.listar');
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
			TelefoneService.buscarPorId(id).
			then(function(p){
				self.telefone = p;
				}, function(errResponse){
			});
		};

		if(idTelefone){
			buscarPorId(idTelefone);		
		}
		
}

function TelefoneListarController( TelefoneService, toastr){ 
	
	var self = this;	
	
	listar();	

	 function listar(){
		 TelefoneService.listar().
			then(function(f){
				self.telefones = f;
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
		};
	
}