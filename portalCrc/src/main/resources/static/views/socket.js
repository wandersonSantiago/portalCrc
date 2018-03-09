
app.controller("Socket", Socket);



function Socket($stateParams, $state, $rootScope, $q, toastr, $rootScope, $scope){
	
				
		var stompClient, listener = $q.defer();
		
		 function sincPromise(){return listener.promise;}
		 
		 
		 sincPromise().then(null, null, function(message) {
			 $rootScope.qtdChamadosSuporteTi = message.result;
			 $scope.valor = message.valor;			 
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
	    	 
	     });     
	    
}