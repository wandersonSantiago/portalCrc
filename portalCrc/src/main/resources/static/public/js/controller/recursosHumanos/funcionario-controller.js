app.controller('funcionarioController', function($scope,  funcionarioService,  $routeParams) {

	var self = this;
	
	var idFuncionario = $routeParams.idFuncionario;
	
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