app.controller("ChamadoManutencaoCadastrarController", ChamadoManutencaoCadastrarController);
app.controller("ChamadoManutencaoListarController", ChamadoManutencaoListarController);
app.controller("ChamadoManutencaoAtendimentoController", ChamadoManutencaoAtendimentoController);
app.controller("ChamadoManutencaoAtendimentoSuporteController", ChamadoManutencaoAtendimentoSuporteController);
app.controller("ChamadoManutencaoRelatorioController", ChamadoManutencaoRelatorioController);
app.controller("ChamadoManutencaoSuporteListarController", ChamadoManutencaoSuporteListarController);



function ChamadoManutencaoCadastrarController( ChamadoManutencaoService, toastr, $rootScope, $scope, TemaService){
	var self = this;
	self.submit = submit;
		
	buscarTema('MANUTENCAO');
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
		
		function buscarTema(tipo){
			 TemaService.buscarPorTipo(tipo).
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
			self.chamadoManutencao.descricaoServico = 'Comum';
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
	
	var self = this;
	self.getPage=0;
	self.totalPages = 0;
	self.totalElements = 0;
	$scope.maxResults = 15;
	self.relatorioPorData = relatorioPorData;
	self.relatorioPorDataPorTitulo = relatorioPorDataPorTitulo;
	relatorioChamadoSuporte(0, 20);
	
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
			ChamadoManutencaoService.relatorioChamadoSuporte(pages, maxResults).
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
    	 ChamadoManutencaoService.relatorioPorData(dataInicial, dataFinal).
			then(function(f){	
				 $scope.ativaTabela = true;
				$scope.relatorioChamadoSuporte = f;		
				chart(f);
				}, function(errResponse){
			});
  	     };
  	     
  	   function relatorioPorDataPorTitulo(dataInicial , dataFinal, titulo){
  		 ChamadoManutencaoService.relatorioPorDataPorTitulo(dataInicial, dataFinal, titulo).
				then(function(f){
					 $scope.ativaTabela = true;
					$scope.relatorioChamadoSuporte = f;					
					}, function(errResponse){
				});
	     };
    
  	     function chart(chamado){
  	    	for(i = 0 ; i < chamado.length ; i++){
		    	 console.log(chamado[i]);
		    	 
		    	 $scope.labels = ["January", "February", "March", "April", "May", "June", "July"];
		    	  $scope.series = ['Series A', 'Series B'];
		    	  $scope.data = [
		    	    [65, 59, 80, 81, 56, 55, 40],
		    	    [28, 48, 40, 19, 86, 27, 90]
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
				chamadoManutencao.descricaoServico = 'Comum';
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
				chamadoManutencao.descricaoServico = 'Comum';
				ChamadoManutencaoService.silenciarChamadoTrue(chamadoManutencao).
					then(function(response){
						toastr.info("Chamado silenciado!!!");
							listar();
						}, function(errResponse){						
							sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
					});
				};
}