app.controller("ChamadoTiCadastrarController", ChamadoTiCadastrarController);
app.controller("ChamadoTiListarController", ChamadoTiListarController);
app.controller("ChamadoTiAtendimentoController", ChamadoTiAtendimentoController);
app.controller("ChamadoTiAtendimentoSuporteController", ChamadoTiAtendimentoSuporteController);
app.controller("ChamadoTiRelatorioController", ChamadoTiRelatorioController);
app.controller("ChamadoTiSuporteListarController", ChamadoTiSuporteListarController);
app.controller("ChamadoTiAbertoController", ChamadoTiAbertoController);




function ChamadoTiCadastrarController( ChamadoTiService, toastr, $rootScope, $scope, TemaService , SistemaService, ModuloService){
	var self = this;
	self.submit = submit;
	self.buscarTema = buscarTema;
	self.buscarTituloPorModulo = buscarTituloPorModulo;
	self.modulos = modulos;
	prioridade();
	tipoEquipamento();
	sistemas();
	
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
		
		function buscarTema(tipo){
			 TemaService.buscarPorTipo(tipo).
				then(function(f){					
					self.titulos = f;			
					}, function(errResponse){
						sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
				});
			};
			
			function buscarTituloPorModulo(idModulo){
				 TemaService.buscarPorModulo(idModulo).
					then(function(f){
						self.titulos = f;			
						}, function(errResponse){
							sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
					});
				};
			
		function tipoEquipamento(){
			 ChamadoTiService.tipoEquipamento().
				then(function(f){
					f.splice(3);
					self.tipoEquipamentos = f;			
					}, function(errResponse){
						sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
				});
			};
			
			function sistemas(){
				 SistemaService.listar().
					then(function(f){
						self.sistemas = f;				
						}, function(errResponse){
							sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
					});
				};
				
			 function modulos(idSistema){
				 ModuloService.buscarPorSistema(idSistema).
					then(function(f){
						self.modulos = f;			
						}, function(errResponse){
							sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
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
function ChamadoTiAtendimentoSuporteController(EquipamentoService, $stateParams,$state, ChamadoTiService, toastr, $rootScope, $scope){
		
		var self = this;		
		var idChamadoTi = $stateParams.idChamadoTi;
		self.submit = submit;
		self.atenderChamado = atenderChamado;
		self.fecharChamado = fecharChamado;
		self.salvarServicos = salvarServicos;
		listarEquipamento();
		
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
	
		function salvarServicos(descricao , equipamento) {
			self.chamadoTi.mensagens = null;
			self.chamadoTi.servicos = {descricao : descricao, equipamento : equipamento};
			ChamadoTiService.salvarServicos(self.chamadoTi).
			then(function(response){
				toastr.success("Serviço salvo com Sucesso!!!");
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
		
		 function listarEquipamento(){
			 EquipamentoService.listar().
				then(function(f){
					self.equipamentos = f;
					}, function(errResponse){
						sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
				});
			};
		
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
function ChamadoTiRelatorioController( ChamadoTiService, toastr, $rootScope, $scope, TemaService, SistemaService, ModuloService){
	
	var self = this;
	self.getPage=0;
	self.totalPages = 0;
	self.totalElements = 0;
	$scope.maxResults = 15;
	self.relatorioPorData = relatorioPorData;
	self.relatorioPorDataPorTitulo = relatorioPorDataPorTitulo;
	relatorioChamadoSuporte(0, 20);
	tipoEquipamento();
	

	self.buscarTema = buscarTema;
	self.buscarTituloPorModulo = buscarTituloPorModulo;
	self.modulos = modulos;
	sistemas();
	
	 $scope.ativaTabela = false;
     $scope.ativaGrafico = false;     
     $scope.porTitulo = false;
     $scope.porPeriodo = true;
	
     self.ativaBotaoTabelaGrafico =  function(botao){
    	 if(botao === false){
    		 $scope.ativaTabela = true;
    		 $scope.ativaGrafico = false;
    	 }else if(botao === true){
    		 $scope.ativaGrafico = true;
    		 $scope.ativaTabela = false;
    	 }
     };
     
     $scope.ativaBuscaRelatorio =  function(botao){
    	 if(botao == 'periodo'){
    		 $scope.porTitulo = false;
    	     $scope.porPeriodo = true;
    	 }else if(botao == 'titulo'){
    		 $scope.porTitulo = true;
    	     $scope.porPeriodo = false;;
    	 }
     };
    
     
     function relatorioChamadoSuporte(pages, maxResults){
    	 
    	 	self.totalPages = [];
			self.getPage=pages;
			ChamadoTiService.relatorioChamadoSuporte(pages, maxResults).
			then(function(f){
				 $scope.ativaTabela = true;
				$scope.relatorioChamadoSuporte = f.content;
				$scope.totalPages = f.totalPages;
				self.totalElements = f.totalElements;
				for(i = 0; i < $scope.totalPages ; i++){
					self.totalPages.push(i);
				}
				}, function(errResponse){
			});
     };
     
     function relatorioPorData(dataInicial , dataFinal){
    	 ChamadoTiService.relatorioPorData(dataInicial, dataFinal).
			then(function(f){	
				 $scope.ativaTabela = true;
				$scope.relatorioChamadoSuporte = f;		
				chart(f);
				}, function(errResponse){
			});
  	     };
  	     
  	   function relatorioPorDataPorTitulo(dataInicial , dataFinal, idTitulo){
  		 ChamadoTiService.relatorioPorDataPorTitulo(dataInicial, dataFinal, idTitulo).
				then(function(f){
					 $scope.ativaTabela = true;
					$scope.relatorioChamadoSuporte = f;					
					}, function(errResponse){
				});
	     };
	     
	    				
	     function buscarTema(tipo){
			 TemaService.buscarPorTipo(tipo).
				then(function(f){
					self.titulos = f;			
					}, function(errResponse){
						sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
				});
			};
			
			function buscarTituloPorModulo(idModulo){
				 TemaService.buscarPorModulo(idModulo).
					then(function(f){
						self.titulos = f;			
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
			
			function sistemas(){
				 SistemaService.listar().
					then(function(f){
						self.sistemas = f;				
						}, function(errResponse){
							sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
					});
				};
				
			 function modulos(idSistema){
				 ModuloService.buscarPorSistema(idSistema).
					then(function(f){
						self.modulos = f;			
						}, function(errResponse){
							sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
					});
				};
    
  	     function chart(chamado){
  	    	for(i = 0 ; i < chamado.length ; i++){
		    	 
		    	 $scope.labels = [new Date(chamado[i].dataAbertura)];
		    	  $scope.series = ['Qauntidade de chamados', 'Quantidade de mensagens'];
		    	  $scope.data = [
		    	    [chamado.length],
		    	    [chamado[i].mensagens.length]
		    	  ];
		    	  $scope.onClick = function (points, evt) {
		    	    console.log(points, evt);
		    	  };
		    	  $scope.datasetOverride = [{ yAxisID: 'y-axis-1' }, { yAxisID: 'y-axis-2' }];
		    	  $scope.options = {
		    	    scales: {
		    	      yAxes: [
		    	        {
		    	          id: 'y-axis-1',
		    	          type: 'linear',
		    	          display: true,
		    	          position: 'left'
		    	        },
		    	        {
		    	          id: 'y-axis-2',
		    	          type: 'linear',
		    	          display: true,
		    	          position: 'right'
		    	        }
		    	      ]
		    	    }
		    	  };
		     }
  	     }
  	// inicio Função data
 		$scope.saidaOpen = function() {
 			$scope.saida.opened = true;
 		};

 		$scope.saida = {
 			opened : false
 		};
 		$scope.entradaOpen = function() {
 			$scope.entrada.opened = true;
 		};

 		$scope.entrada = {
 			opened : false
 		};
 		$scope.format = "dd/MM/yyyy";
 		// termino função data
	
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
function ChamadoTiAbertoController( $timeout, ChamadoTiService, toastr, $rootScope, $scope){
	
	var self = this;
	var vid = document.getElementById("myAudio");
	$rootScope.atualizarListaChamado = false;
	
	
	
	self.listar = function(){		
		 ChamadoTiService.listaSuporte().
			then(function(f){
			var	qtdChamados = f;	
			var silenciar = true;
			for(i = 0 ; i < qtdChamados.length ; i++ ){
				if(qtdChamados[i].silenciar === false){
					silenciar = false;
				}
				
			}
				$rootScope.qtdChamadosSuporteTi = qtdChamados.length;
				if($rootScope.qtdChamadosSuporteTi > 0 && silenciar === false){

					console.log(new Date());
					self.enableAutoplay(); 
					sweetAlert({ timer : 3000,  text : "Novo Chamado",  type : "info", width: 300, higth: 300, padding: 20});					
				}
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
		 
		};
		
		self.enableAutoplay = function() { 
		    vid.autoplay = true;
		    vid.load();
		    
		}	
		
		self.atualizaListaChamadoSuporte = function(){
			$rootScope.atualizarListaChamado = true;
			if($rootScope.atualizarListaChamado === true ){
					self.verificaMensagemLidaAtualizada();
				}
		};
		
		self.verificaMensagemLida = function(){			
			if($rootScope.atualizarListaChamado === false && $rootScope.authenticated){
				self.listar();
				timeoutLida = setTimeout(self.verificaMensagemLida, 120000);			
			}
		};
		
		self.verificaMensagemLidaAtualizada = function(){
			$rootScope.atualizarListaChamado = false;
			clearTimeout(timeoutLida);
			self.verificaMensagemLida();			
		};
		
		/*self.verificaMensagemLida(); */
}