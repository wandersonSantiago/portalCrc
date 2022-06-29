app.controller("ReservaCadastarController", ReservaCadastarController);
app.controller("ReservaEditarController", ReservaEditarController);
app.controller("ReservaListarController", ReservaListarController);
app.controller("ReservaShowController", ReservaShowController);

ReservaCadastarController.$inject = ['ReservaService','AuditorioService','FuncionarioUnidadeService','FuncionarioService','toastr', '$rootScope', '$scope', 'UnidadeService'];
ReservaEditarController.$inject = ['$stateParams', '$state','AuditorioService', 'ReservaService','FuncionarioUnidadeService','FuncionarioService','toastr', '$rootScope', '$scope', 'UnidadeService'];
ReservaListarController.$inject = ['ReservaService','toastr', '$rootScope', '$scope', 'blockUI'];
ReservaShowController.$inject = ['$stateParams', '$state', 'ReservaService', 'toastr', '$rootScope', '$scope'];

function ReservaCadastarController( ReservaService,AuditorioService,FuncionarioUnidadeService,FuncionarioService,toastr, $rootScope, $scope, UnidadeService){
	var self = this;
		self.submit = submit;
		
		auditorio();
		//funcionario();	
		listarUnidades();		
		self.funcionarios = funcionarios;
		
		
		function submit() {
			ReservaService.salvar(self.reserva).
			then(function(response){
				toastr.info('Salvo com Sucesso!!!');
				self.reserva = null;
				}, function(errResponse){
					sweetAlert({ timer : 30000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
		};
		
		
		 function listarUnidades(){
			 UnidadeService.listar().
				then(function(f){
					self.unidades = f;	
					}, function(errResponse){
						sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
				});
			};
			
			
			
			 function auditorio(){
				 AuditorioService.listar().
					then(function(f){
						self.auditorios = f;									
						}, function(errResponse){
							sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
					});
				};
			
			
			
			/* function funcionario(){
				 FuncionarioService.listarPorUnidade().
					then(function(f){
						self.funcionarios = f;									
						}, function(errResponse){
							sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
					});
				};*/
				
				
				function funcionarios(texto){
				 	return  FuncionarioService.buscarPorTexto(texto).
				 	 then(function(e){
				 		return e.content;
				 	 }, function(errResponse){
				 	 });
				 }				
							
}
function ReservaEditarController( $stateParams, $state, AuditorioService, ReservaService,FuncionarioUnidadeService,FuncionarioService,toastr, $rootScope, $scope, UnidadeService){
		
	var self = this;
	self.submit = submit;
		var idReserva = $stateParams.idReserva;			
		
		auditorio();
		listarUnidades();
		funcionario();
		
		function submit(Reserva) {
			ReservaService.alterar(self.reserva).
			then(function(response){
				toastr.info('Alterado com sucesso!!!');
				self.reserva = null;
				$state.go('Reserva.listar');
				}, function(errResponse){
					sweetAlert({ timer : 30000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
	//	};
		
		
		//function submit(form) {	
			//if(form.$invalid){
				//sweetAlert({title: "Por favor preencha os campos obrigatorios", 	type : "error", timer : 100000,   width: 500,  padding: 20});	
				//return;
			//}
			//;
			self.reserva.horaReservaInicio = atribuiADataAHora(new Date(self.reserva.horaReservaInicio));
			self.reserva.horaReservaTermino = atribuiADataAHora(new Date(self.reserva.horaReservaInicio));
			reservaService.alterar(self.reservaService).then(
					function(response) {				
						toastr.info("Alterado com Sucesso!!!");	
						$state.go('reserva.listar');
					}, function(errResponse) {
						sweetAlert({
							text : errResponse.data.message,
							type : "info",
							width : 300,
							higth : 300,
							padding : 20
						});
					});
		};
			
		function atribuiADataAHora(data , hora){
			return new Date(data.getFullYear(), data.getMonth(), data.getDate(), hora.getHours(), hora.getMinutes(), 0, 0);
		}
	
						
		function buscarPorId(id){
			if(!id)return;
			ReservaService.buscarPorId(id).
			then(function(p){
				
				self.reserva = p;
				self.reserva.dataReserva = new Date( p.dataReserva);  
				self.reserva.horaReservaInicio = new Date(p.horaReservaInicio);
				self.reserva.horaReservaTermino = new Date(p.horaReservaTermino);
			}, function(errResponse){
			sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
			});
		};
		
		if(idReserva){
			buscarPorId(idReserva);			
		}
		
		 function listarUnidades(){
			 UnidadeService.listar().
				then(function(f){
					self.unidades = f;	
					}, function(errResponse){
						sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
				});
			};
			
			
			 function auditorio(){
				 AuditorioService.listar().
					then(function(f){
						self.auditorios = f;									
						}, function(errResponse){
							sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
					});
				};		
			
			 function funcionario(){
				 FuncionarioService.listarPorUnidade().
					then(function(f){
						self.funcionarios = f;									
						}, function(errResponse){
							sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
					});
				};
			
			
}


function ReservaListarController(ReservaService, toastr, $rootScope, $scope , blockUI){

	var self = this;	
	self.buscarPorTexto = buscarPorTexto;
	self.totalElementos = {};
	self.totalPaginas = null;
	self.paginaCorrente = 0;
	
	buscarPorTexto('');
	
	     
	    function buscarPorTexto(texto){
	    	$scope.mensagemErro = null;
	    	 blockUI.start();	    	 
	    	 self.paginaCorrente == '0'? self.paginaCorrente = 0 : self.paginaCorrente = self.paginaCorrente - 1;    	 
	    	 ReservaService.buscarPorTexto(texto, self.paginaCorrente).
	    	 then(function(e){
	    		 $scope.mensagemErro = null;
	    		 self.reservas = e.content;	
	    		 self.totalElementos = e.totalElements;
	    		 self.totalPaginas = e.totalPages;
	    		 self.paginaCorrente = e.number + 1;
	    		 blockUI.stop();
	    	 }, function(errResponse){
	    		 blockUI.stop();
	    		 if(errResponse.status == 404){
	    			 $scope.mensagemErro = errResponse.data.message;
	    		 }else{
	    			 $scope.mensagemErro =errResponse.data.message;
	    		 }
			 });
	    }
}	
	
	
//var self = this;
//listar();
//
// function listar(){
//	 ReservaService.listar().
//		then(function(f){
//			self.reservas = f;
//			
//			}, function(errResponse){
//				sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
//		});
//	};
//	
//	
//	
//	
//	
//}	
	
	
function ReservaShowController( $stateParams, $state, ReservaService, AuditorioService, FuncionarioService, toastr, $rootScope, $scope, UnidadeService){
	
}