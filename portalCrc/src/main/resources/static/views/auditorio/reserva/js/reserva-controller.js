app.controller("ReservaCadastarController", ReservaCadastarController);
app.controller("ReservaEditarController", ReservaEditarController);
app.controller("ReservaListarController", ReservaListarController);
app.controller("ReservaShowController", ReservaShowController);

ReservaCadastarController.$inject = ['ReservaService','toastr', '$rootScope', '$scope', 'UnidadeService'];
ReservaEditarController.$inject = ['$stateParams', '$state', 'ReservaService','toastr', '$rootScope', '$scope', 'UnidadeService'];
ReservaListarController.$inject = ['ReservaService', 'toastr', '$rootScope', '$scope'];
ReservaShowController.$inject = ['$stateParams', '$state', 'ReservaService', 'toastr', '$rootScope', '$scope'];

function ReservaCadastarController( ReservaService,toastr, $rootScope, $scope, UnidadeService){
	var self = this;
		self.submit =submit;
			
		listarUnidades();
		
		function submit() {
			ReservaService.salvar(self.reserva).
			then(function(response){
				toastr.info('Salvo com Sucesso!!!');
				self.reserva = null;
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
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
				
}
function ReservaEditarController( $stateParams, $state, ReservaService, toastr, $rootScope, $scope, UnidadeService){
		
	var self = this;
	self.submit = submit;
		var idReserva = $stateParams.idReserva;			
		
		listarUnidades();
		
		function submit(Reserva) {
			ReservaService.alterar(self.reserva).
			then(function(response){
				toastr.info('Alterado com sucesso!!!');
				self.reserva = null;
				$state.go('Reserva.listar');
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
			});
		};
		
						
		function buscarPorId(id){
			if(!id)return;
			ReservaService.buscarPorId(id).
			then(function(p){
				
				self.reserva = p;
				self.reserva.dataReserva = new Date( p.dataReserva);  
				self.reserva.horaReserva = new Date(p.horaReserva);
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
}


function ReservaListarController( ReservaService, toastr, $rootScope, $scope){
	var self = this;
	listar();
	
	 function listar(){
		 ReservaService.listar().
			then(function(f){
				self.reservas = f;
				
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
			});
		};
}
function ReservaShowController( $stateParams, $state, ReservaService, toastr, $rootScope, $scope, UnidadeService){
	
}