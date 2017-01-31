app.controller('funcionarioController', function($scope, buscaCepService, funcionarioService,  $routeParams) {

	var self = this;
	
	var idFuncionario = $routeParams.idFuncionario;
	
self.findCep = function () {
		
		self.cep = $scope.cadFuncCtrl.funcionario.pessoa.endereco.cep;
		buscaCepService.get({'cep': self.cep}).$promise
		.then(function success(result){
			$scope.cadFuncCtrl.funcionario.pessoa.endereco = result;
		}).catch(function error(msg) {
		});
		
    }
self.salva = function(funcionario) {
		funcionarioService.salva(self.funcionario).
		then(function(response){
			self.funcionario = null;
			}, function(errResponse){
		});
	}
	
	self.altera = function(funcionario) {
		funcionarioService.altera(self.funcionario).
		then(function(response){
			self.funcionario = null;
			}, function(errResponse){
		});
	}

	 self.lista = function(){
		 funcionarioService.lista().
			then(function(f){
				self.listaFuncionarios = f;				
				}, function(errResponse){
			});
		};
	self.listaPorUnidade = function(){
		 funcionarioService.listaPorUnidade().
			then(function(f){
				self.listaFuncionarios = f;	
				console.log(f);
				}, function(errResponse){
			});
		};
		 self.estadoCivil = function(){
			 funcionarioService.estadoCivil().
				then(function(f){
					self.listaEstadoCivil = f;									
					}, function(errResponse){
				});
			};
		
	self.buscarPorId = function(id){
			if(!id)return;
			funcionarioService.buscarPorId(id).
			then(function(p){
				self.funcionario = p;
		}, function(errResponse){
			});
		};
	
		if(idFuncionario){
			self.buscarPorId(idFuncionario);
			
		}

});