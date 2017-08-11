optimusInventoryApp.controller('DebtorsController', ['DebtorService', function (debtorService) {
    var self = this;
    self.isSuccess = false;
    self.isError = false;
    self.errorMessage;
    self.successMessage;
    self.allDebtors = [];


    self.resetError = function () {
        self.isError = false;
    };
    self.resetSuccess = function () {
        self.isSuccess = false;
    };

    self.getAllDebtors = function () {
        debtorService.getAllDebtors().then(function (response) {
            for (var i = 0; i < response.data.length; i++) {
                if (response.data[i].amount > 0) {
                    self.allDebtors.push(response.data[i]);
                }
            }
        }, function (error) {
            self.isError = true;
            self.errorMessage = error.data.message;
        });
    };

    self.viewTransactions = function (index) {
        self.debtor = self.allDebtors[index];
    }

    self.setDebtor = function (index) {
        self.debtor = self.allDebtors[index];
    }
    self.payAmount = function () {
        var _debtor = self.debtor;
        _debtor.amount -= self.amountToPay;
        debtorService.updateDebtorById(_debtor, _debtor.id).then(function (response) {
            for (var i = 0; i < self.allDebtors; i++) {
                if (self.allDebtors[i].id == response.data.id) {
                    self.allDebtors[i] = response.data.id;
                }
            }
            self.isSuccess = true;
            self.successMessage = "Debtor updates successfully";
        }, function (error) {
            self.isError = true;
            self.errorMessage = error.data.message;
        });
    };
    self.getSalesByDebtor = function (index) {
        self.transactions = self.allDebtors[index].sales;
    }
    self.getAllDebtors();
}]);