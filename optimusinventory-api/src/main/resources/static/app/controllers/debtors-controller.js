optimusInventoryApp.controller('DebtorsController', ['DebtorService', function (debtorService) {
    var self = this;
    self.isSuccess = false;
    self.isError = false;
    self.errorMessage;
    self.successMessage;
    self.allDebtors = [];
    self.allDebtorSales = [];


    self.resetError = function () {
        self.isError = false;
    };
    self.resetSuccess = function () {
        self.isSuccess = false;
    };

    self.getAllDebtors = function () {
        self.allDebtors = [];
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
        self.debtor.amount -= self.amountToPay;
        debtorService.updateDebtorById(self.debtor, self.debtor.id).then(function (response) {
            for (var i = 0; i < self.allDebtors; i++) {
                if (self.allDebtors[i].id == response.data.id) {
                    self.allDebtors[i] = response.data;
                    self.debtor = null;
                }
            }
            self.getAllDebtors();

            self.isSuccess = true;
            self.successMessage = "Debtor updates successfully";
        }, function (error) {
            self.isError = true;
            self.errorMessage = error.data.message;
        });
    };

    self.viewSale = function (i) {
        self.sale = self.debtor.sales[i];
        self.sale.date = new Date(self.sale.date).toLocaleDateString();
    }
    self.getSalesByDebtor = function (index) {
        self.transactions = self.allDebtors[index].sales;
    }
    self.getAllDebtors();
}]);