
app.controller("HomeListarController", HomeListarController);
app.controller("HomeShowController", HomeShowController);

HomeListarController.$inject = ['$stateParams', '$state', 'toastr', '$rootScope', '$scope', 'UsuarioService'];
HomeShowController.$inject = ['$stateParams', '$state', 'toastr', '$rootScope', '$scope'];

function HomeListarController($stateParams, $state,  toastr, $rootScope, $scope, UsuarioService){
	var self = this;
	listarUsuarioLogado();
	
	 function listarUsuarioLogado(){
		 UsuarioService.listarUsuarioLogado().
			then(function(f){
				$rootScope.usuario = f.usuario;	
				self.usuario = f.usuario;
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
		};
}
function HomeShowController( $stateParams, $state,  toastr, $rootScope, $scope){
	
}