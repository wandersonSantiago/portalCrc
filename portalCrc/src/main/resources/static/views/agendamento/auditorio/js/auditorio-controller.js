app.controller("AuditorioCadastarController", AuditorioCadastarController);
app.controller("AuditorioEditarController", AuditorioEditarController);
app.controller("AuditorioListarController", AuditorioListarController);
app.controller("AuditorioShowController", AuditorioShowController);

AuditorioCadastarController.$inject = ['AuditorioService','toastr', '$rootScope', '$scope'];
AuditorioEditarController.$inject = ['$stateParams', '$state', 'AuditorioService','toastr', '$rootScope', '$scope'];
AuditorioListarController.$inject = ['AuditorioService', 'toastr', '$rootScope', '$scope'];
AuditorioShowController.$inject = ['$stateParams', '$state', 'AuditorioService', 'toastr', '$rootScope', '$scope'];

function AuditorioCadastarController( AuditorioService,toastr, $rootScope, $scope){
	var self = this;
		self.submit =submit;
			
		
		function submit() {
			AuditorioService.salvar(self.auditorio).
			then(function(response){
				toastr.info('Salvo com Sucesso!!!');
				self.Auditorio = null;
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
			});
		};		
	
				
}
function AuditorioEditarController( $stateParams, $state, AuditorioService, toastr, $rootScope, $scope){
	var self = this;
		self.submit = submit;
		var idAuditorio = $stateParams.idAuditorio;
		
		
		
		function submit(Auditorio) {
			AuditorioService.alterar(self.auditorio).
			then(function(response){
				toastr.info('Alterado com sucesso!!!');
				self.auditorio = null;
				$state.go('auditorio.listar');
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
			});
		};
				
		function buscarPorId(id){
			if(!id)return;
			AuditorioService.buscarPorId(id).
			then(function(p){
				self.auditorio = p;
		}, function(errResponse){
			sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
			});
		};
		
		if(idAuditorio){
			buscarPorId(idAuditorio);			
		}
}

function AuditorioListarController(AuditorioService, toastr, $rootScope, $scope){
	var self = this;
	listar();
	
	 function listar(){
		 AuditorioService.listar().
			then(function(f){
				self.auditorios = f;
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
			});
		};
}

function AuditorioShowController($stateParams, $state, AuditorioService, toastr, $rootScope, $scope){
	
}