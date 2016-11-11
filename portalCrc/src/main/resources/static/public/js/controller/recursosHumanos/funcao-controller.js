app.controller('funcaoController', function($scope, funcaoService, $routeParams){
	
	var self = this;
		
	var idFuncao = $routeParams.idFuncao;
	
	
	
	 self.salva = function(funcao){
		 funcaoService.salva(self.funcao).
		then(function(c){
			self.funcao = null;
			}, function(errResponse){
		});
	}
	 
	 self.altera = function(funcao){
		 funcaoService.altera(self.funcao).
			then(function(c){
				self.cargo = null;
				}, function(errResponse){
			});
		}
	 
	 self.lista = function(){
		 funcaoService.lista().
			then(function(c){
				self.listaFuncao = c;
				}, function(errResponse){
			
			});
		};
		self.buscaPorId = function(id){
			if(!id)return;
			funcaoService.buscaPorId(id).
				then(function(c){
					self.funcao = c;
					}, function(errResponse){
				
				});
			};
			if(idFuncao){
				self.buscaPorId(idFuncao);
			}
		 
	
});