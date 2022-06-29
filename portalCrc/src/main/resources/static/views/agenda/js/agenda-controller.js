app.controller("AgendaCadastarController", AgendaCadastarController);
app.controller("AgendaEditarController", AgendaEditarController);
app.controller("AgendaListarController", AgendaListarController);
app.controller("AgendaShowController", AgendaShowController);

AgendaCadastarController.$inject = ['AgendaService',  'toastr', '$rootScope', '$scope','moment',  'calendarConfig'];
AgendaEditarController.$inject = ['$stateParams', '$state', 'AgendaService', 'toastr', '$rootScope', '$scope'];
AgendaListarController.$inject = ['$stateParams', '$state', 'AgendaService', 'toastr', '$rootScope', '$scope'];
AgendaShowController.$inject = ['$stateParams', '$state', 'AgendaService', 'toastr', '$rootScope', '$scope'];

function AgendaCadastarController( AgendaService, toastr, $rootScope, $scope, moment,  calendarConfig){
	var self = this;
	
	self.submit = submit;
	
	function submit(secretaria) {
		AgendaService.salvar(self.secretaria).
			then(function(response){
				toastr.info("Salvo com Sucesso!!!");
				self.secretaria = null;
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
		};
		var vm = this;

	    //These variables MUST be set as a minimum for the calendar to work
	    vm.calendarView = 'month';
	    vm.viewDate = new Date();
	    
	    vm.events = [
	      {
	        title: 'An event',
	        color: calendarConfig.colorTypes.warning,
	        startsAt: moment().startOf('week').subtract(2, 'days').add(8, 'hours').toDate(),
	        endsAt: moment().startOf('week').add(1, 'week').add(9, 'hours').toDate(),
	        draggable: true,
	        resizable: true,
	      }, {
	        title: '<i class="glyphicon glyphicon-asterisk"></i> <span class="text-primary">Another event</span>, with a <i>html</i> title',
	        color: calendarConfig.colorTypes.info,
	        startsAt: moment().subtract(1, 'day').toDate(),
	        endsAt: moment().add(5, 'days').toDate(),
	        draggable: true,
	        resizable: true,
	      }, {
	        title: 'This is a really long event title that occurs on every year',
	        color: calendarConfig.colorTypes.important,
	        startsAt: moment().startOf('day').add(7, 'hours').toDate(),
	        endsAt: moment().startOf('day').add(19, 'hours').toDate(),
	        recursOn: 'year',
	        draggable: true,
	        resizable: true,
	      }
	    ];

	    vm.cellIsOpen = true;

	    vm.addEvent = function() {
	      vm.events.push({
	        title: 'New event',
	        startsAt: moment().startOf('day').toDate(),
	        endsAt: moment().endOf('day').toDate(),
	        color: calendarConfig.colorTypes.important,
	        draggable: true,
	        resizable: true
	      });
	    };

	    vm.eventClicked = function(event) {
	      alert.show('Clicked', event);
	    };

	    vm.eventEdited = function(event) {
	      alert.show('Edited', event);
	    };

	    vm.eventDeleted = function(event) {
	      alert.show('Deleted', event);
	    };

	    vm.eventTimesChanged = function(event) {
	      alert.show('Dropped or resized', event);
	    };

	    vm.toggle = function($event, field, event) {
	      $event.preventDefault();
	      $event.stopPropagation();
	      event[field] = !event[field];
	    };

	    vm.timespanClicked = function(date, cell) {

	      if (vm.calendarView === 'month') {
	        if ((vm.cellIsOpen && moment(date).startOf('day').isSame(moment(vm.viewDate).startOf('day'))) || cell.events.length === 0 || !cell.inMonth) {
	          vm.cellIsOpen = false;
	        } else {
	          vm.cellIsOpen = true;
	          vm.viewDate = date;
	        }
	      } else if (vm.calendarView === 'year') {
	        if ((vm.cellIsOpen && moment(date).startOf('month').isSame(moment(vm.viewDate).startOf('month'))) || cell.events.length === 0) {
	          vm.cellIsOpen = false;
	        } else {
	          vm.cellIsOpen = true;
	          vm.viewDate = date;
	        }
	      }

	    };
}
function AgendaEditarController($stateParams, $state, AgendaService, toastr, $rootScope, $scope){
	
	var self = this;
	self.submit = submit;
	var idAgenda = $stateParams.idAgenda;
	
	function submit(secretaria) {
		AgendaService.alterar(self.secretaria).
		then(function(response){
			toastr.info("Alterado com Sucesso!!!");
			self.secretaria = null;
			$state.go('secretaria.listar');
			}, function(errResponse){
				sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
	};
	
	function buscarPorId(id){
		if(!id)return;
		AgendaService.buscarPorId(id).
		then(function(p){
			self.secretaria = p;
	}, function(errResponse){
		sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
	};

	if(idAgenda){
		buscarPorId(idAgenda);		
	};
	
	
}
function AgendaListarController($stateParams, $state, AgendaService, toastr, $rootScope, $scope){
	var self = this;
	listar();
	
	 function listar(){
		 AgendaService.listar().
			then(function(f){
				self.secretarias = f;				
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
		};
}
function AgendaShowController( $stateParams, $state, AgendaService, toastr, $rootScope, $scope){
	
}