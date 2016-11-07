app.controller('setorController', function($scope, setorService, $routeParams) {

	var self = this;
	
	var idSetor = $routeParams.idSetor;
	
	self.salva = function(setor) {
		setorService.salva(self.setor).
		then(function(response){
			self.setor = null;
			}, function(errResponse){
		});
	}
	
	self.altera = function(setor) {
		setorService.altera(self.setor).
		then(function(response){
			self.setor = null;
			}, function(errResponse){
		});
	}

	 self.lista = function(){
		 setorService.lista().
			then(function(f){
				self.listaSetor = f;				
				}, function(errResponse){
			});
		};
		self.listaTipoSetor = function(){
			 setorService.listaTipoSetor().
				then(function(f){
					self.listaTipoSetor = f;		
					console.log(self.listaTipoSetor);
					}, function(errResponse){
				});
			};
		
		self.buscarPorId = function(id){
			if(!id)return;
			setorService.buscarPorId(id).
			then(function(p){
				self.setor = p;
		}, function(errResponse){
			});
		};
		
		if(idSetor){
			self.buscarPorId(idSetor);
			
		}

});