var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope, $http) {
	$scope.manutencoes = new Object();
	$scope.salvar = function() {
		$http.post("http://localhost:8080/manutencoes", {
			'id' : $scope.id,
			'nome' : $scope.nome,
			'categoria' : $scope.categoria,
			'data' : $scope.data,
			'pago' : $scope.pago
		})
	};
	$scope.buscarTodos = function() {
		$http.get("http://localhost:8080/manutencoes").then(function(resposta) {
			$scope.manutencoes = resposta.data
		});
	}
	$scope.buscarTodos();
	$scope.put = function() {
		$http.put("http://localhost:8080/manutencoes/" + $scope.id, {
			'nome' : $scope.nome,
			'categoria' : $scope.categoria,
			'pago' : $scope.pago
		})
	};
});