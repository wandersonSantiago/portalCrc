app.controller("ChamadoTiCadastrarController", ChamadoTiCadastrarController);
app.controller("ChamadoTiListarController", ChamadoTiListarController);
app.controller("ChamadoTiAtendimentoController", ChamadoTiAtendimentoController);
app.controller("ChamadoTiAtendimentoSuporteController", ChamadoTiAtendimentoSuporteController);
app.controller("ChamadoTiRelatorioController", ChamadoTiRelatorioController);
app.controller("ChamadoTiSuporteListarController", ChamadoTiSuporteListarController);



ChamadoTiCadastrarController.$inject = ['ChamadoTiService',  'toastr', '$rootScope', '$scope'];
ChamadoTiListarController.$inject = ['ChamadoTiService', '$rootScope'];
ChamadoTiAtendimentoController.$inject = ['$stateParams', '$state', 'ChamadoTiService', 'toastr', '$rootScope', '$scope'];
ChamadoTiAtendimentoSuporteController.$inject = ['$stateParams', '$state', 'ChamadoTiService', 'toastr', '$rootScope', '$scope'];
ChamadoTiRelatorioController.$inject = ['$stateParams', '$state', 'ChamadoTiService', 'toastr', '$rootScope', '$scope'];
ChamadoTiSuporteListarController.$inject = ['ChamadoTiService', 'toastr', '$rootScope', '$scope'];

function ChamadoTiCadastrarController( ChamadoTiService, toastr, $rootScope, $scope){
	var self = this;
	self.submit = submit;
	
	tituloImpressora();
	titulo();
	prioridade();
	tipoEquipamento();
	
	function submit(chamadoTi) {
		self.chamadoTi.mensagens = [{texto : $scope.texto}];
		ChamadoTiService.salvar(self.chamadoTi).
			then(function(response){
				self.chamadoTi = null;
				$scope.texto = null;
				toastr.success("Salvo com Sucesso!!!");
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
		}
	
	 function prioridade(){
		 ChamadoTiService.prioridade().
			then(function(f){
				self.prioridades = f;			
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
		};
		
		function titulo(){
			 ChamadoTiService.titulo().
				then(function(f){
					self.titulos = f;			
					}, function(errResponse){
						sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
				});
			};
		function tituloImpressora(){
			 ChamadoTiService.tituloImpressora().
				then(function(f){
					self.tituloImpressoras = f;			
					}, function(errResponse){
						sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
				});
			};	
			
		function tipoEquipamento(){
			 ChamadoTiService.tipoEquipamento().
				then(function(f){
					self.tipoEquipamentos = f;			
					}, function(errResponse){
						sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
				});
			};
	
}
function ChamadoTiListarController( ChamadoTiService, $rootScope){
		var self = this;
		listar();
		
		function listar(){
			 ChamadoTiService.listaUsuario().
				then(function(f){
					self.chamados = f;	
					$rootScope.qtdChamadosTi = f.length;
					}, function(errResponse){
						sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
				});
			};
	
}
function ChamadoTiAtendimentoController($stateParams, $state, ChamadoTiService, toastr, $rootScope, $scope){
		var self = this;
		self.submit = submit;
		var idChamadoTi = $stateParams.idChamadoTi;
		
		function submit(chamadoTi) {
			self.chamadoTi.mensagens = null;
			self.chamadoTi.mensagens = [{texto : self.texto}];
			ChamadoTiService.salvarMensagem(self.chamadoTi).
			then(function(response){
				toastr.success("Menssagem enviada com Sucesso!!!");
				self.texto = null;
				buscarPorId(idChamadoTi);
					}, function(errResponse){
						sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
				});
		}
	
		function buscarPorId(id){
			if(!id)return;
			ChamadoTiService.buscarPorId(id).
			then(function(p){
				self.chamadoTi = p;			
				}, function(errResponse){
			});
		};
	
		if(idChamadoTi){
			buscarPorId(idChamadoTi);		
		}
	
}
function ChamadoTiAtendimentoSuporteController($stateParams,$state, ChamadoTiService, toastr, $rootScope, $scope){
		
		var self = this;		
		var idChamadoTi = $stateParams.idChamadoTi;
		self.submit = submit;
		self.atenderChamado = atenderChamado;
		self.fecharChamado = fecharChamado;
		
		function submit(chamadoTi, texto) {
			chamadoTi.mensagens = null;
			chamadoTi.mensagens = [{texto : texto}];
			ChamadoTiService.salvarMensagem(chamadoTi).
			then(function(response){
				toastr.success("Menssagem enviada com Sucesso!!!");
				self.texto = null;	
				buscarPorId(idChamadoTi);
					}, function(errResponse){
						sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
				});
		};
	
		function buscarPorId(id){
			if(!id)return;
			ChamadoTiService.buscarPorId(id).
			then(function(p){
				self.chamadoTi = p;			
				}, function(errResponse){
			});
		};
	
		if(idChamadoTi){
			buscarPorId(idChamadoTi);		
		}
		
		function atenderChamado(chamadoTi) {
			self.chamadoTi.mensagens = null;
			swal({
				  title: 'Atender Chamado!!!',
				  text: "Tem que certeza que deseja atender este chamado?",
				  type: 'info',
				  showCancelButton: true,
				  confirmButtonColor: '#3085d6',
				  cancelButtonColor: '#d33',
				  confirmButtonText: 'Atender!'
				}).then(function () {
					ChamadoTiService.atenderChamado(self.chamadoTi).
					then(function(response){
						$scope.texto = null;
						buscarPorId(idChamadoTi);
						}, function(errResponse){
					});			  
				})		
		};
		
		function fecharChamado(chamadoTi) {
			self.chamadoTi.mensagens = null;
			swal({
				  title: 'Encerrar Chamado!!!',
				  text: "Tem que certeza que deseja encerrar este chamado?",
				  type: 'warning',
				  showCancelButton: true,
				  confirmButtonColor: '#3085d6',
				  cancelButtonColor: '#d33',
				  confirmButtonText: 'Encerrar!'
				}).then(function () {
			ChamadoTiService.fecharChamado(self.chamadoTi).
			then(function(response){
				$scope.texto = null;
				buscarPorId(idChamadoTi);
				}, function(errResponse){
			});
		})
	};
	
	
}
function ChamadoTiRelatorioController( ChamadoTiService, toastr, $rootScope, $scope){
	
	
}
function ChamadoTiSuporteListarController( ChamadoTiService, toastr, $rootScope, $scope){
	
		var self = this;
		self.silenciarChamadoFalse = silenciarChamadoFalse;
		self.silenciarChamadoTrue = silenciarChamadoTrue;
		listar();
		
		function listar(){
			 ChamadoTiService.listaSuporte().
				then(function(f){
					self.chamados = f;	
					$rootScope.qtdChamadosSuporteTi = f.length;
					}, function(errResponse){
						sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
				});
			};
			
			function silenciarChamadoFalse(chamadoTi) {
				chamadoTi.mensagens = null;
				ChamadoTiService.silenciarChamadoFalse(chamadoTi).
					then(function(response){		
						toastr.success("Som ativo!!!");
							listar();
						}, function(errResponse){					
							sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
					});
				};
				
			function silenciarChamadoTrue(chamadoTi) {
				chamadoTi.mensagens = null;
				ChamadoTiService.silenciarChamadoTrue(chamadoTi).
					then(function(response){
						toastr.info("Chamado silenciado!!!");						
							listar();
						}, function(errResponse){						
							sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
					});
				};
}