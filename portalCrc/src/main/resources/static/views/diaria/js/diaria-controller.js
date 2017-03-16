app.controller("DiariaCadastarController", DiariaCadastarController);
app.controller("DiariaEditarController", DiariaEditarController);
app.controller("DiariaListarController", DiariaListarController);
app.controller("DiariaRelatorioController", DiariaRelatorioController);

DiariaCadastarController.$inject = ['DiariaService', '$state', 'toastr', '$rootScope', '$scope'];
DiariaEditarController.$inject = ['$stateParams', '$state', 'DiariaService', 'toastr', '$rootScope', '$scope'];
DiariaListarController.$inject = ['$stateParams', '$state', 'DiariaService', 'toastr', '$rootScope', '$scope'];
DiariaRelatorioController.$inject = ['$stateParams', '$state', 'DiariaService', 'toastr', '$rootScope', '$scope'];

function DiariaCadastarController( DiariaService, $state, toastr, $rootScope, $scope){
	var self = this;
	
	self.submit = submit;
	listarMes();
	
	
	function submit(diaria) {
		DiariaService.salvar(self.diaria).
			then(function(response){
				toastr.info("Salvo com Sucesso!!!");
				self.diaria = null;
				$state.go('diaria.listar');
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
		};
	function listarMes(){
		 DiariaService.listarMes().
			then(function(f){
				$scope.listarMes = f;					
				}, function(errResponse){
			});
		};
}
function DiariaEditarController($stateParams, $state, DiariaService, toastr, $rootScope, $scope){
	
	var self = this;
	self.submit = submit;
	var idDiaria = $stateParams.idDiaria;
	
	listarMes();
	
	
	function submit(diaria) {
		self.diaria.mes = null;
		DiariaService.alterar(self.diaria).
			then(function(response){
				toastr.success("Salvo com Sucesso!!!");
				self.diaria = null;
				$state.go('diaria.listar');
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
		};
	function listarMes(){
		 DiariaService.listarMes().
			then(function(f){
				$scope.listarMes = f;					
				}, function(errResponse){
			});
		};
	function buscarPorId(id){
		if(!id)return;
		DiariaService.buscarPorId(id).
		then(function(p){
			self.diaria = p;
	}, function(errResponse){
		sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
	};

	if(idDiaria){
		buscarPorId(idDiaria);		
	};
	
	
}
function DiariaListarController($stateParams, $state, DiariaService, toastr, $rootScope, $scope){
	var self = this;
	self.encerrar = encerrar;
	self.getPage=0;
	self.totalPages = 0;
	self.totalElements = 0;
	$scope.maxResults = 15;
	listar(0 , 12);
	diariasEmAberto();
	 function listar(pages, maxResults){
		 	self.totalPages = [];
			self.getPage=pages;
		 DiariaService.listar(pages, maxResults).
			then(function(e){	
				self.listaDiarias = e.content;
				$scope.totalPages = e.totalPages;
				self.totalElements = e.totalElements;
				for(i = 0; i < $scope.totalPages ; i++){
					self.totalPages.push(i);
				}				
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
		};
		
	function diariasEmAberto(){
			 DiariaService.diariasEmAberto().
				then(function(f){
					self.diarias = f;					
					}, function(errResponse){
				});
			};
		
	function encerrar(objeto) {
			swal({
				  title: 'Encerrar Lançamentos!!!',
				  text: "Após esta ação as Unidades não conseguiram realizar mais lançamentos no Mês selecionado ",
				  type: 'warning',
				  showCancelButton: true,
				  confirmButtonColor: '#3085d6',
				  cancelButtonColor: '#d33',
				  confirmButtonText: 'Encerrar'
				}).then(function () {
					 DiariaService.encerrar(objeto).
					then(function(response){
						toastr.success("Encerrado com Sucesso!!!");
						diariasEmAberto();
						}, function(errResponse){
							sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
					});	
					 
				})		
		};	
}
function DiariaRelatorioController( $stateParams, $state, DiariaService, toastr, $rootScope, $scope){
	
}