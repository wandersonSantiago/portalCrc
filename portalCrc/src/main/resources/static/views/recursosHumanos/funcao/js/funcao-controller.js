app.controller("FuncaoCadastarController", FuncaoCadastarController);
app.controller("FuncaoEditarController", FuncaoEditarController);
app.controller("FuncaoListarController", FuncaoListarController);
app.controller("FuncaoShowController", FuncaoShowController);

FuncaoCadastarController.$inject = ['FuncaoService', 'toastr', '$rootScope', '$scope'];
FuncaoEditarController.$inject = ['$stateParams', '$state', 'FuncaoService', 'toastr', '$rootScope', '$scope'];
FuncaoListarController.$inject = ['FuncaoService', 'toastr', '$rootScope', '$scope'];
FuncaoShowController.$inject = ['$stateParams', '$state', 'FuncaoService', 'toastr', '$rootScope', '$scope'];

function FuncaoCadastarController( FuncaoService, toastr, $rootScope, $scope){
	
	var self = this;
	self.submit = submit;
	
	 function submit(funcao){
		 FuncaoService.salvar(self.funcao).
		then(function(c){
			toastr.info('Salvo com Sucesso!!!');
			self.funcao = null;
			}, function(errResponse){
				sweetAlert({ timer : 3000, text : errResponse.data.message, type : "error", width: 300, higth: 100, padding: 20});	return $q.reject(errResponse);
		});
	};
}
function FuncaoEditarController( $stateParams , $state ,FuncaoService,    toastr, $rootScope, $scope){
	
	var self = this;
	self.submit = submit;
	var idFuncao = $stateParams.idFuncao;
	
		
	function submit(funcao){
		 FuncaoService.salvar(self.funcao).
		then(function(c){
			toastr.info('Alterado com Sucesso!!!');
			self.funcao = null;
			$state.go('funcao.listar');
			}, function(errResponse){
				sweetAlert({ timer : 3000, text : errResponse.data.message, type : "error", width: 300, higth: 100, padding: 20});	return $q.reject(errResponse);
		});
	};
	
	buscarPorId = function(id){
		if(!id)return;
		FuncaoService.buscarPorId(id).
			then(function(c){
				self.funcao = c;
				}, function(errResponse){			
					sweetAlert({ timer : 3000, text : errResponse.data.message, type : "error", width: 300, higth: 100, padding: 20});	return $q.reject(errResponse);
			});
		};
		if(idFuncao){
			buscarPorId(idFuncao);
		}
}
function FuncaoListarController( FuncaoService, toastr, $rootScope, $scope){
	var self = this;
	
	listar();
	
	function listar(){
		 FuncaoService.listar().
			then(function(c){
				self.funcoes = c;
				}, function(errResponse){			
					sweetAlert({ timer : 3000, text : errResponse.data.message, type : "error", width: 300, higth: 100, padding: 20});	return $q.reject(errResponse);
			});
		};
}
function FuncaoShowController( FuncaoService, CoordenadoriaService, buscaCepService ,toastr, $rootScope, $scope){
	
}