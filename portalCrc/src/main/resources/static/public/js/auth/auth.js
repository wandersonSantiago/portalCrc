app.factory('auth',	function($rootScope, $http,  $location) {

			enter = function() {
				if ($location.path() != auth.loginPath) {
					auth.path = $location.path();
					if (!auth.authenticated) {
						$location.path(auth.loginPath);
					}
				}					
			}

			var auth = {

				authenticated : false,
			
				loginPath : '/login',
				logoutPath : '/logout',
				homePath : '/',
				path : $location.path(),

				authenticate : function(credentials, callback) {

					var headers = credentials && credentials.username ? {
						authorization : "Basic "
								+ btoa(credentials.username + ":"
										+ credentials.password)
					} : {};	

					$http.get('/rest/usuario/usuario', {
						headers : headers
					}).then(function(response) {
						$rootScope.authorities = response.data.authorities;				
						
						if (response.data.name) {
							auth.authenticated = true;
							$rootScope.logado = true;
						} else {
							auth.authenticated = false;
							$rootScope.logado = false;
						}
						callback && callback(auth.authenticated);
						$location.path(auth.path==auth.loginPath ? auth.homePath : auth.path);
					}, function() {
						auth.authenticated = false;
						callback && callback(false);
					});

				},

				clear : function() {					
					auth.authenticated = false;
					$rootScope.logado = false;
					$location.path(auth.loginPath);
					$http.post(auth.logoutPath, {}).then(function(response) {
						console.log("Logout successo");
						$rootScope.logado = false;
					}, function(errResponse) {
						console.log("Logout falhou");
					});
				},


				init : function(homePath, loginPath, logoutPath) {

					auth.homePath = homePath;
					auth.loginPath = loginPath;
					auth.logoutPath = logoutPath;

					auth.authenticate({}, function(authenticated) {
						if (authenticated) {
							$location.path(auth.path);
							$rootScope.logado = true;
						}
					})

					$rootScope.$on('$routeChangeStart', function() {
						enter();
					});

				}

			};

			return auth;

		});
