app.controller('funcionarioUnidadeController', function($scope,  funcionarioUnidadeService, funcionarioService,  $routeParams) {

	var self = this;
	
	var idFuncionarioUnidade = $routeParams.idFuncionarioUnidade;
	var idFuncionario = $routeParams.idFuncionario;
	$scope.lotado = false;
	
self.salva = function(funcionarioUnidade) {
	self.funcionarioUnidade.status = $scope.status;
	self.funcionarioUnidade.funcionario = $scope.funcionarioUnidadeCtrl.funcionario;
	funcionarioUnidadeService.salva(self.funcionarioUnidade).
		then(function(response){
			self.funcionario = null;
			}, function(errResponse){
		});
	}
	
	self.altera = function(funcionarioUnidade) {
		self.funcionarioUnidade.funcionario = $scope.funcionarioUnidadeCtrl.funcionario;
		funcionarioUnidadeService.altera(self.funcionarioUnidade).
		then(function(response){
			self.funcionario = null;
			}, function(errResponse){
		});
	}

	 self.lista = function(){
		 funcionarioUnidadeService.lista().
			then(function(f){
				self.listaFuncionarioUnidades = f;				
				}, function(errResponse){
			});
		};
		
		self.listaStatus = function(){
			 funcionarioUnidadeService.listaStatus().
				then(function(f){
					self.status = f;
					console.log(self.status);
					}, function(errResponse){
				});
			};
			
		self.buscarPorId = function(id){
			if(!id)return;
			funcionarioUnidadeService.buscarPorId(id).
			then(function(p){
				self.FuncionarioUnidade = p;
		}, function(errResponse){
			});
		};
	
		if(idFuncionarioUnidade){
			self.buscarPorId(idFuncionarioUnidade);
			
		}
		
		
		self.buscaFuncionarioPorId = function(id){
			if(!id)return;
			funcionarioService.buscarPorId(id).
			then(function(p){
				self.funcionario = p;
		}, function(errResponse){
			});
		};
	
		if(idFuncionario){
			self.buscaFuncionarioPorId(idFuncionario);			
		}

		self.verificaAtivo = function(){
			if($scope.status == "Lotado"){
				$scope.lotado = true;
			}else{
				$scope.lotado = false;
			}
		}
		
});