(function() {

angular.module('app').factory('datacontext',
       ['$http', 'logger', 'breeze',  datacontext]);

function datacontext($http, logger) {
  var log = logger.log;
  var logError = logger.logError;
  
  log("Creating datacontext");
  
  // convert server PascalCase property names to camelCase
  breeze.NamingConvention.camelCase.setAsDefault();

  // create a new manager talking to sample service 
  var host="http://sampleservice.breezejs.com";
  var serviceName = host+"/api/todos";
  var manager = new breeze.EntityManager(serviceName);

  plunkerHelpers.isCorsCapable();
  
  var service = {
    getAllTodos: getAllTodos,
    reset: reset
  };
  return service;
  
  /*** implementation ***/  

  function getAllTodos() {
    log("Getting Todos");
    return breeze.EntityQuery.from("Todos")
          .using(manager).execute()
          .then(success);
  
    function success(data) {
        log("Retrieved " + data.results.length);
        return data.results;
    }
  }
  

}

})();