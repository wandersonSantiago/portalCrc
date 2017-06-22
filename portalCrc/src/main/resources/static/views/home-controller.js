
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
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
		};
		
	;

		function listar() {
			ManutencaoEquipamentoService.listarAtivosPrioridade().then(function(f) {
				$rootScope.manutencaoEquipamentos = f;
			}, function(errResponse) {
				sweetAlert({
					timer : 3000,
					text : errResponse.data.message,
					type : "info",
					width : 300,
					higth : 300,
					padding : 20
				});
			});
		}
		;
}
function HomeShowController( $stateParams, $state,  toastr, $rootScope, $scope){
	
}