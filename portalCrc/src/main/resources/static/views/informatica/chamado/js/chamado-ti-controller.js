app.controller("ChamadoTiCadastrarController", ChamadoTiCadastrarController);
app.controller("ChamadoTiListarController", ChamadoTiListarController);
app.controller("ChamadoTiAtendimentoController", ChamadoTiAtendimentoController);
app.controller("ChamadoTiRelatorioController", ChamadoTiRelatorioController);


function ChamadoTiCadastrarController( $q, ChamadoTiService, toastr, $rootScope, $scope, TemaService , SistemaService, ModuloService, blockUI){
	var self = this;
	self.submit = submit;
	self.buscarTema = buscarTema;
	self.buscarTituloPorModulo = buscarTituloPorModulo;
	self.modulos = modulos;
	$scope.obj = {};
	prioridade();
	tipoEquipamento();
	sistemas();
		
	function submit(formC){
		if(formC.$invalid){
			sweetAlert({title: "Por favor preencha os campos obrigatorios", 	type : "error", timer : 100000,   width: 500,  padding: 20});	
			return;
		}
	 	var file = $scope.obj.flow.files[0];	 	
	 	blockUI.start();
    	var form = new FormData();
    	if(file){
    		if(file.size > 131072 ){
   	 		 swal({ timer : 30000, text: "A imagem deve ter no maximo 1.2Mb" ,  type : "error", width: 500, higth: 100, padding: 20}).catch(swal.noop);
   	 		 return;
   	 		}
        	form.append('file', file.file);	    	    		
    	}
    	form.append('chamado',new Blob([JSON.stringify(self.chamadoTi)], {
            type: "application/json"
        }) )
		ChamadoTiService.salvar(form)
	   	 .then(function(response){
	   		blockUI.stop();
	   		$scope.obj.flow.cancel();
	   		sincronizar(self.chamadoTi);
	   		self.chamadoTi = null;
	   		toastr.success("Salvo com Sucesso!!!");	
	   		$scope.formC.$setPristine();
	   	 	},
		function(errResponse){			 
	   	 	blockUI.stop();
			 swal({ timer : 30000, text: errResponse.data.message ,  type : "error", width: 500, higth: 100, padding: 20}).catch(swal.noop);
			});
	  };
	
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
				if(!idModulo){
					return null;
				}
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
				 self.moduloList = [];
				 ModuloService.buscarPorSistema(idSistema).
					then(function(f){
						self.moduloList = f;			
						}, function(errResponse){
							sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
					});
				};
				
				// inicializa Socket do chamdo
				var stompClient, listener = $q.defer();
				 function sincPromise(){return listener.promise;}				 
				 sincPromise().then(null, null, function(message) {
					 if($rootScope.user.funcionario.unidadeAtual.id == message.idUnidade){
						 for(i = 0 ; i < $rootScope.user.permissoes.length ; i++){        		
			        		 if($rootScope.user.permissoes[i].descricao == "CHAMADO_INFORMATICA_TECNICO"){
								 self.enableAutoplay(); 
								 $rootScope.mensagem = message.result;
								 $rootScope.qtdChamadosTi = "1+";
								 if(!message.prioridade){
									 sweetAlert({text : $rootScope.mensagem + "... " + message.prioridade,  type : "info", width: 600, higth: 300, padding: 20});
								 }
							 }
			        	}
						 if(message.atendido == true && message.user == $rootScope.user){
							 self.enableAutoplay(); 
							 $rootScope.qtdChamadosTi = "1+";
						 }
					 }					 			
				 });
			    
			    function showResult(message) {
			        var response = document.getElementById('calResponse');
			        var p = document.createElement('p');
			        p.style.wordWrap = 'break-word';
			        p.appendChild(document.createTextNode(message));
			        response.appendChild(p);
			    }
			    function connect() {
			        var socket = new SockJS('/add');
					 stompClient = Stomp.over(socket); 
			        stompClient.connect({}, function(frame) {
			            console.log('Connected: ' + frame);
			            stompClient.subscribe('/topic/showResult', function(calResult){
			           	 listener.notify(JSON.parse(calResult.body));
			            });
			        });
			    }
			    
			    connect();
			    
			    sincronizar = function(chamado){			    	
			   	 stompClient.send("/calcApp/add", {}, JSON.stringify({
			   		 'idUnidade' : $rootScope.user.funcionario.unidadeAtual.id ,
			   		 'user' : $rootScope.user.login,
			   		 'result' : chamado.titulo.descricao,
			   		 'prioridade' : chamado.prioridade}));
			   	
			    };
			    
				/*  function disconnect() {
				      stompClient.disconnect();
				      console.log("Disconnected");
				  }
				
			    $scope.$on('$destroy',function(event){
			   	 disconnect();
			   	 
			    });     */
			     
			    //finaliza socket do chamado
			    
			    //inicializa para verificação de audio
				var vid = document.getElementById("myAudio");			
			
					self.enableAutoplay = function() { 
					    vid.autoplay = true;
					    vid.load();
					    
					}	
					//finaliza para verificação de audio
	
}
function ChamadoTiAtendimentoController($q, EquipamentoService, $stateParams, $state, ChamadoTiService, toastr, $rootScope, $scope){
	var self = this;		
	var idChamadoTi = $stateParams.idChamadoTi;
	self.submit = submit;
	self.atenderChamado = atenderChamado;
	self.fecharChamado = fecharChamado;
	self.salvarServicos = salvarServicos;
	self.marcarComoLido = marcarComoLido;
	listarEquipamento();
	
		function submit(chamadoTi) {
			self.mensagem.chamadoTi = self.chamadoTi;
			ChamadoTiService.salvarMensagem(self.mensagem).
			then(function(response){
				 buscarPorId(self.chamadoTi.id);
				//self.chamadoTi.mensagens.push(response);
				self.mensagem.texto = null;
				sincronizar(self.chamadoTi);
					}, function(errResponse){
						sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
				});
		}
	
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
					$state.go('chamadoTi.listar');
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
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
	
	var stompClient, listener = $q.defer();
	
	 function sincPromise(){return listener.promise;}	
	 
	 sincPromise().then(null, null, function(message) {
		 if($rootScope.user.funcionario.unidadeAtual.id == message.idUnidade){
			 if(message.status == true && message.solicitante == $rootScope.user.login ||
					 message.atendente == $rootScope.user.login && message.user != $rootScope.user.login){
					 if(!message.silenciar){
						 self.enableAutoplay(); 
					 }
					 buscarPorId(self.chamadoTi.id);	 
			 }
		 }					 			
	 });
	 
	   function connect() {
	        var socket = new SockJS('/add');
			 stompClient = Stomp.over(socket); 
	        stompClient.connect({}, function(frame) {
	            console.log('Connected: ' + frame);
	            stompClient.subscribe('/topic/showResult', function(calResult){
	           	 listener.notify(JSON.parse(calResult.body));
	            });
	        });
	    }
	    
	    connect();
	    
      
   sincronizar = function(chamado){			    	
  	 stompClient.send("/calcApp/add", {}, JSON.stringify({
  		 'idUnidade' : $rootScope.user.funcionario.unidadeAtual.id ,
  		 'user' : $rootScope.user.login,
  		 'solicitante' : chamado.usuarioSolicitante.login,
  		 'atendente' : chamado.usuarioAtendente.login,
  		 'status' : chamado.status,
  		 'silenciar' : chamado.silenciar}));
  	
   };
   
   var vid = document.getElementById("myAudio");			
	
	self.enableAutoplay = function() { 
	    vid.autoplay = true;
	    vid.load();
	    
	}
	
	function marcarComoLido(chamado){
		ChamadoTiService.marcarComoLido(chamado).
		then(function(response){
				}, function(errResponse){
			});
	}
	
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
function ChamadoTiListarController( ChamadoTiService, toastr, $rootScope, $scope,TemaService , SistemaService, ModuloService, blockUI){
	
		var self = this;
		self.silenciarChamado = silenciarChamado;
		self.buscarTema = buscarTema;
		self.buscarTituloPorModulo = buscarTituloPorModulo;
		self.modulos = modulos;
		statusList();
		tipoEquipamentoList();
		prioridadeList();
		sistemas();
		
		self.sort = sort;	
		$scope.filter = filter;
		$scope.pesquisar = pesquisar;
		self.page = {page : 0 ,linesPerPage : 5 , orderBy : 'id' , direction : 'DESC'};
		$scope.chamadoFilter = {page : self.page}
		$scope.imprimir = 'PAGINA';
		
		filter($scope.chamadoFilter);
		
		function sort(orderBy){		
			$scope.chamadoFilter.page.orderBy = orderBy;
			$scope.chamadoFilter.page.direction == 'ASC' ? $scope.chamadoFilter.page.direction = 'DESC' : $scope.chamadoFilter.page.direction = 'ASC';
			filter($scope.chamadoFilter);
		}
		
		function pesquisar(chamadoFilter, imprimir){
			if(imprimir == 'PAGINA'){
				filter(chamadoFilter);
			}else{
				pdf(chamadoFilter);
			}
		}
		
		 function filter(chamadoFilter){
		    	$scope.mensagemErro = null;	    	 
		    	 blockUI.start();
		    	 chamadoFilter.page.page == '0'? chamadoFilter.page.page = 0 : chamadoFilter.page.page = chamadoFilter.page.page - 1;   
		    	 ChamadoTiService.filter(chamadoFilter).
		    	 then(function(e){
		    		 $scope.mensagemErro = null;
		    		 self.chamados = e.content;	
		    		 $scope.chamadoFilter.page.totalElementos = e.totalElements;
		    		 $scope.chamadoFilter.page.totalPaginas = e.totalPages;
		    		 $scope.chamadoFilter.page.page = e.number + 1;
		    		 blockUI.stop();
		    	 }, function(errResponse){
		    		 blockUI.stop();
		    		 if(errResponse.status == 404){
		    			 $scope.mensagemErro = errResponse.data.message;
		    		 }else{
		    			 $scope.mensagemErro =errResponse.data.message;
		    		 }
				 });
		    }
		    
		    function pdf(chamadoFilter){	
		    	$scope.mensagemErro = null;	    	 
		    	 blockUI.start();
		    	 chamadoFilter.page.page == '0'? chamadoFilter.page.page = 0 : chamadoFilter.page.page = chamadoFilter.page.page - 1;  
		    	 ChamadoTiService.pdf(chamadoFilter)
		   	 .then(function(d){
		   		var file = new Blob([d],{type: 'application/pdf'});
		   		var fileURL = URL.createObjectURL(file);
		   		blockUI.stop();
		   	    window.open(fileURL);
		   	 	 }).catch(function error(msg) {			 	   	 		 
		    	});
	     };	     
						
			function silenciarChamado(chamadoTi) {
				chamadoTi.mensagens = null;
				ChamadoTiService.silenciarChamado(chamadoTi).
					then(function(response){
						toastr.info("Chamado silenciado!!!");						
						filter($scope.chamadoFilter);
						}, function(errResponse){						
							sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
					});
				};
				
				function statusList(){
					 ChamadoTiService.status().
						then(function(f){
							self.status = f;	
							}, function(errResponse){
								sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
						});
					};
					
				function tipoEquipamentoList(){
					 ChamadoTiService.tipoEquipamento().
						then(function(f){
							f.splice(3);
							self.tiposEquipamento = f;	
							}, function(errResponse){
								sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
						});
					};
				function prioridadeList(){
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
					if(!idModulo){
						return null;
					}
					 TemaService.buscarPorModulo(idModulo).
						then(function(f){
							self.titulos = f;			
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
					 self.moduloList = [];
					 ModuloService.buscarPorSistema(idSistema).
						then(function(f){
							self.moduloList = f;			
							}, function(errResponse){
								sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
						});
					};
}