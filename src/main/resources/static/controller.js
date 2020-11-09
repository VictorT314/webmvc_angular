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

});
