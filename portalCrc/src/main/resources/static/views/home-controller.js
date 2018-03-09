
app.controller("HomeListarController", HomeListarController);
app.controller("HomeShowController", HomeShowController);



function HomeListarController($stateParams, $state, $rootScope,   $q, toastr, $rootScope, $scope, UsuarioService, ManutencaoEquipamentoService, ChamadoTiService){
	var self = this;
	listarUsuarioLogado();
	listar()
	
	
	 function listarUsuarioLogado(){
		 UsuarioService.listarUsuarioLogado().
			then(function(f){
				$rootScope.usuario = f.usuario;	
				self.usuario = f.usuario;
				}, function(errResponse){
			});
		};
		
	;

		function listar() {
			ManutencaoEquipamentoService.listarAtivosPrioridade().then(function(f) {
				$rootScope.manutencaoEquipamentos = f;
			}, function(errResponse) {
				
			});
		}
		;
		
		function listarChamadoTi(){
			 ChamadoTiService.listaSuporte().
				then(function(f){
					self.chamados = f;	
					$rootScope.qtdChamadosSuporteTi = f.length;
					}, function(errResponse){
						sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
				});
			};
	/*		
		var stompClient, listener = $q.defer();
		
		 function sincPromise(){return listener.promise;}
		 
		 
		 sincPromise().then(null, null, function(message) {
			 $rootScope.qtdChamadosSuporteTi = message.result;
			 $scope.valor = message.valor;			
			 listarChamadoTi();
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
	     
	     self.sincronizar = function(){
	    	 stompClient.send("/calcApp/add", {}, {});
	     };
	     
		  function disconnect() {
		      stompClient.disconnect();
		      console.log("Disconnected");
		  }
		
	     $scope.$on('$destroy',function(event){
	    	 disconnect();
	    	 
	     });     */
	    
}
function HomeShowController( $stateParams, $state,  toastr, $rootScope, $scope){
	
}