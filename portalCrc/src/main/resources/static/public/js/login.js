app.controller( 'loginController', function($state, $location, $rootScope, auth) {
			
			var self = this;
			self.falhaLogin = true;
			self.credentials = {};
			
			self.tab = function(state) {
				
				return $state.current && state === $state.current.controller;
			};

			self.authenticated = function() {
			
				return auth.authenticated;
			}

			self.login = function() {
				auth.authenticate(self.user, function(authenticated) {
				
					if (authenticated) {
						$rootScope.logado = true;
						$location.path("#/");
						
						self.error = false;
					} else {
						self.falhaLogin = false;
						
						self.error = true;
					}
				})
			};

			self.logout = auth.clear;

		});
