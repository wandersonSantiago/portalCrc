app.controller("ChamadoManutencaoCadastrarController", ChamadoManutencaoCadastrarController);
app.controller("ChamadoManutencaoListarController", ChamadoManutencaoListarController);
app.controller("ChamadoManutencaoAtendimentoController", ChamadoManutencaoAtendimentoController);
app.controller("ChamadoManutencaoAtendimentoSuporteController", ChamadoManutencaoAtendimentoSuporteController);
app.controller("ChamadoManutencaoRelatorioController", ChamadoManutencaoRelatorioController);
app.controller("ChamadoManutencaoSuporteListarController", ChamadoManutencaoSuporteListarController);



ChamadoManutencaoCadastrarController.$inject = ['ChamadoManutencaoService',  'toastr', '$rootScope', '$scope'];
ChamadoManutencaoListarController.$inject = ['ChamadoManutencaoService'];
ChamadoManutencaoAtendimentoController.$inject = ['$stateParams', '$state', 'ChamadoManutencaoService', 'toastr', '$rootScope', '$scope'];
ChamadoManutencaoAtendimentoSuporteController.$inject = ['$stateParams', '$state', 'ChamadoManutencaoService', 'toastr', '$rootScope', '$scope'];
ChamadoManutencaoRelatorioController.$inject = ['$stateParams', '$state', 'ChamadoManutencaoService', 'toastr', '$rootScope', '$scope'];
ChamadoManutencaoSuporteListarController.$inject = ['ChamadoManutencaoService', 'toastr', '$rootScope', '$scope'];

function ChamadoManutencaoCadastrarController( ChamadoManutencaoService, toastr, $rootScope, $scope){
	var self = this;
	self.submit = submit;
		
	titulo();
	prioridade();
	
	function submit(chamadoManutencao) {
		self.chamadoManutencao.mensagens = [{texto : $scope.texto}];
		ChamadoManutencaoService.salvar(self.chamadoManutencao).
			then(function(response){
				self.chamadoManutencao = null;
				$scope.texto = null;
				toastr.success("Salvo com Sucesso!!!");
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
		}
	
	 function prioridade(){
		 ChamadoManutencaoService.prioridade().
			then(function(f){
				self.prioridades = f;			
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
		};
		
		function titulo(){
			 ChamadoManutencaoService.titulo().
				then(function(f){
					self.titulos = f;			
					}, function(errResponse){
						sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
				});
			};
		
	
}
function ChamadoManutencaoListarController( ChamadoManutencaoService){
		var self = this;
		listar();
		
		function listar(){
			 ChamadoManutencaoService.listarUsuario().
				then(function(f){
					self.chamados = f;						
					}, function(errResponse){
						sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
				});
			};
	
}
function ChamadoManutencaoAtendimentoController($stateParams, $state, ChamadoManutencaoService, toastr, $rootScope, $scope){
		var self = this;
		self.submit = submit;
		var idChamadoManutencao = $stateParams.idChamadoManutencao;
		
		function submit(chamadoManutencao) {
			self.chamadoManutencao.mensagens = null;
			self.chamadoManutencao.mensagens = [{texto : self.texto}];
			ChamadoManutencaoService.salvarMensagem(self.chamadoManutencao).
			then(function(response){
				toastr.success("Menssagem enviada com Sucesso!!!");
				self.texto = null;
				buscarPorId(idChamadoManutencao);
					}, function(errResponse){
						sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
				});
		}
	
		function buscarPorId(id){
			if(!id)return;
			ChamadoManutencaoService.buscarPorId(id).
			then(function(p){
				self.chamadoManutencao = p;			
				}, function(errResponse){
			});
		};
	
		if(idChamadoManutencao){
			buscarPorId(idChamadoManutencao);		
		}
	
}
function ChamadoManutencaoAtendimentoSuporteController($stateParams,$state, ChamadoManutencaoService, toastr, $rootScope, $scope){
		
		var self = this;		
		var idChamadoManutencao = $stateParams.idChamadoManutencao;
		self.submit = submit;
		self.atenderChamado = atenderChamado;
		self.fecharChamado = fecharChamado;
		self.salvarServicos = salvarServicos;
		
		
		function submit(chamadoManutencao) {
			self.chamadoManutencao.mensagens = null;
			self.chamadoManutencao.mensagens = [{texto : self.texto}];
			ChamadoManutencaoService.salvarMensagem(self.chamadoManutencao).
			then(function(response){
				toastr.success("Menssagem enviada com Sucesso!!!");
				self.texto = null;	
				buscarPorId(idChamadoManutencao);
					}, function(errResponse){
						sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
				});
		};
	
		function buscarPorId(id){
			if(!id)return;
			ChamadoManutencaoService.buscarPorId(id).
			then(function(p){
				self.chamadoManutencao = p;			
				}, function(errResponse){
			});
		};
	
		if(idChamadoManutencao){
			buscarPorId(idChamadoManutencao);		
		}
		
		function atenderChamado(chamadoManutencao) {
			self.chamadoManutencao.mensagens = null;
			swal({
				  title: 'Atender Chamado!!!',
				  text: "Tem que certeza que deseja atender este chamado?",
				  type: 'info',
				  showCancelButton: true,
				  confirmButtonColor: '#3085d6',
				  cancelButtonColor: '#d33',
				  confirmButtonText: 'Atender!'
				}).then(function () {
					ChamadoManutencaoService.atenderChamado(self.chamadoManutencao).
					then(function(response){
						$scope.texto = null;
						buscarPorId(idChamadoManutencao);
						}, function(errResponse){
					});			  
				})		
		};
		
		function fecharChamado(chamadoManutencao) {
			self.chamadoManutencao.mensagens = null;
			swal({
				  title: 'Encerrar Chamado!!!',
				  text: "Tem que certeza que deseja encerrar este chamado?",
				  type: 'warning',
				  showCancelButton: true,
				  confirmButtonColor: '#3085d6',
				  cancelButtonColor: '#d33',
				  confirmButtonText: 'Encerrar!'
				}).then(function () {
			ChamadoManutencaoService.fecharChamado(self.chamadoManutencao).
			then(function(response){
				$scope.texto = null;
				buscarPorId(idChamadoManutencao);
				}, function(errResponse){
			});
		})
	};
	
	function salvarServicos(descricao) {	
		self.chamadoManutencao.mensagens = null;
		self.chamadoManutencao.descricaoServico = descricao;
		ChamadoManutencaoService.salvarServicos(self.chamadoManutencao).
		then(function(response){	
			toastr.success("Serviços salvo com Sucesso!!!");
			buscarPorId(self.chamadoManutencao.id);
			}, function(errResponse){
				sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
		});
	};
	
	
}
function ChamadoManutencaoRelatorioController( ChamadoManutencaoService, toastr, $rootScope, $scope){
	
	
}
function ChamadoManutencaoSuporteListarController( ChamadoManutencaoService, toastr, $rootScope, $scope){
	
		var self = this;
		self.silenciarChamadoFalse = silenciarChamadoFalse;
		self.silenciarChamadoTrue = silenciarChamadoTrue;
		listar();
		
		function listar(){
			 ChamadoManutencaoService.listarSuporte().
				then(function(f){
					self.chamados = f;						
					}, function(errResponse){
						sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
				});
			};
			
			function silenciarChamadoFalse(chamadoManutencao) {
				chamadoManutencao.mensagens = null;
				ChamadoManutencaoService.silenciarChamadoFalse(chamadoManutencao).
					then(function(response){
						toastr.success("Som ativo!!!");		
							listar();
						}, function(errResponse){					
							sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
					});
				};
				
			function silenciarChamadoTrue(chamadoManutencao) {
				chamadoManutencao.mensagens = null;
				ChamadoManutencaoService.silenciarChamadoTrue(chamadoManutencao).
					then(function(response){
						toastr.info("Chamado silenciado!!!");
							listar();
						}, function(errResponse){						
							sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
					});
				};
}