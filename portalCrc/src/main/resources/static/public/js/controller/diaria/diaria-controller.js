app.controller('diariaController', function($scope,  diariaService, $location,  $routeParams) {

	var self = this;
	
	var idDiaria = $routeParams.idDiaria;

	$scope.listaDiariaExcel = [];
	
self.salva = function(diaria) {
	diariaService.salva(self.diaria).
		then(function(response){
			$location.path("/diaria/em-aberto");
			}, function(errResponse){
		});
	}
	
	self.altera = function(diaria) {
		diariaService.altera(self.diaria).
		then(function(response){
			self.diaria = null;
			$location.path("/diaria/lista/unidade");
			}, function(errResponse){
				
		});
	}

	 self.lista = function(){
		 diariaService.lista().
			then(function(f){
				self.listaDiaria = f;					
				}, function(errResponse){
			});
		};
		
		 self.listaMes = function(){
			 diariaService.listaMes().
				then(function(f){
					$scope.listaMes = f;					
					}, function(errResponse){
				});
			};
			
	self.diariasEmAberto = function(){
		 diariaService.diariasEmAberto().
			then(function(f){
				self.listaDiaria = f;					
				}, function(errResponse){
			});
		};
	
		
	self.encerrar = function(objeto) {
		swal({
			  title: 'Encerrar Lançamentos!!!',
			  text: "Após esta ação as Unidades não conseguiram realizar mais lançamentos no Mês selecionado ",
			  type: 'warning',
			  showCancelButton: true,
			  confirmButtonColor: '#3085d6',
			  cancelButtonColor: '#d33',
			  confirmButtonText: 'Encerrar'
			}).then(function () {
				 diariaService.encerrar(objeto).
				then(function(response){					
					self.diariasEmAberto();
					}, function(errResponse){
				});	
				 
			})		
	};	
		
	self.buscarPorId = function(id){
			if(!id)return;
			diariaService.buscarPorId(id).
			then(function(p){
				$scope.diaria = p;
		}, function(errResponse){
			});
		};
	
		if(idDiaria){
			self.buscarPorId(idDiaria);
			
		}		


});