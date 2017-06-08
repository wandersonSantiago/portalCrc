app.factory('UsuarioService', function($rootScope, toastr, $http,$q){
	
	
	return{
		
		salvar: function(usuario){
			return $http.post('/rest/usuario/salva', usuario)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		listar: function(){
			return $http.get('rest/usuario/lista')
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		status: function(){
			return $http.get('rest/usuario/status')
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		buscarPorId: function(param){
			return $http.get('rest/usuario/buscaPorId/'+param)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		alterar: function(parametroUsuario){
			return $http.put('rest/usuario/alterar', parametroUsuario)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		listarUsuarioLogado: function(){
			return $http.get('/rest/usuario/usuarios')
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		existeLogin: function(login){
			return $http.get('rest/usuario/existeLogin/'+login)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		salvarFoto: function(form){
			return $http.post('/rest/usuario/foto', form, {
	            transformRequest: angular.identity,
	            headers: {'Content-Type': undefined}
	        }).then(function(response){
				return response.data;
			},function(errResponse){
			return $q.reject(errResponse);
			});
		},
		alterarSenha: function(idUsuario, senha, novaSenha){
			var config = {params: {idUsuario , senha , novaSenha }};
			return $http.put('rest/usuario/'+idUsuario+'/senha/'+senha+'/nova-senha/'+novaSenha)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);

			});
		},
		
		buscarPorTexto : function(params){
			return $http.get('rest/usuario/buscar?q=' +params)
			.then(function(response){
				return response.data;
			},function(errResponse){
			return $q.reject(errResponse);
			});
		},
	}
});