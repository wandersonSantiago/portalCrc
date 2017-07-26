
app.controller("HomeListarController", HomeListarController);
app.controller("HomeShowController", HomeShowController);



function HomeListarController($stateParams, $state,  toastr, $rootScope, $scope, UsuarioService, ManutencaoEquipamentoService){
	var self = this;
	listarUsuarioLogado();
	listar()
	
	
	 function listarUsuarioLogado(){
		 UsuarioService.listarUsuarioLogado().
			then(function(f){
				$rootScope.usuario = f.usuario;	
				self.usuario = f.usuario;
				}, function(errResponse){
			});
		};
		
	;

		function listar() {
			ManutencaoEquipamentoService.listarAtivosPrioridade().then(function(f) {
				$rootScope.manutencaoEquipamentos = f;
			}, function(errResponse) {
				
			});
		}
		;
}
function HomeShowController( $stateParams, $state,  toastr, $rootScope, $scope){
	
}