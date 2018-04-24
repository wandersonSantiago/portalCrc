app.controller("DiariaCadastarController", DiariaCadastarController);
app.controller("DiariaEditarController", DiariaEditarController);
app.controller("DiariaListarController", DiariaListarController);
app.controller("DiariaRelatorioController", DiariaRelatorioController);


function DiariaCadastarController( DiariaService, $state, toastr, $rootScope, $scope){
	var self = this;
	
	self.submit = submit;
	$scope.botao = "Cadastrar";
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
	$scope.botao = "Editar";
	var idDiaria = $stateParams.idDiaria;
	
	listarMes();
	
	
	function submit(diaria) {
		DiariaService.alterar(self.diaria).
			then(function(response){
				toastr.success("Alterado com Sucesso!!!");
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
function DiariaListarController(ItemDiariaService, $stateParams, $state, DiariaService, toastr, $rootScope, $scope){
	var self = this;
	self.encerrar = encerrar;
	self.buscarFuncionarioDiaria = buscarFuncionarioDiaria;
	diariasEmAberto();
	$rootScope.escondeMenu = true;
		
	function diariasEmAberto(){
			 DiariaService.diariasEmAberto().
				then(function(f){
					self.diaria = f;		
					buscarFuncionarioDiaria(f.id, $rootScope.user.funcionario.id);
					}, function(errResponse){
				});
			};
		
			function buscarFuncionarioDiaria(idDiaria, idFuncionario) {
				ItemDiariaService.buscarPorIdFuncionario(idDiaria, idFuncionario).then(
						function(f) {
							$scope.funcionario = f;				
						}, function(errResponse) {
							$scope.funcionario = null;
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
		
		  $scope.labels = ['2006', '2007', '2008', '2009', '2010', '2011', '2012'];
		  $scope.series = ['Series A', 'Series B', 'Series C'];

		  $scope.data = [
		    [65, 59, 80, 81, 56, 55, 40],
		    [28, 48, 40, 19, 86, 27, 90],
		    [28, 48, 40, 19, 86, 27, 90]
		  ];
}
function DiariaRelatorioController( $stateParams, $state, DiariaService, toastr, $rootScope, $scope){
	
}