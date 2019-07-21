# moneyTransfer

API Details-

1.Create Account-
1.1 Request URL- http://localhost:9085/api/v1/account
1.2 Request Method- POST
1.3 Request Body-
{
	"type":"SAVING",
	"currency":"EUR"
}
1.4 Response-
{
    "body": {
        "id": 2,
        "type": "SAVING",
        "balance": 0,
        "currency": "EUR"
    },
    "header": {
        "status": "SUCCESS",
        "operation": "CREATE"
    }
}

2. Check Balance-
2.1 Request URL- http://localhost:9085/api/v1/account/1/balance
2.2 Request Method- GET
2.3 Response-
{
    "body": {
        "id": 1,
        "type": "SAVING",
        "balance": 0,
        "currency": "EUR"
    },
    "header": {
        "status": "SUCCESS",
        "operation": "CHECK_BALANCE"
    }
}

3. Deposit-
3.1 Request URL- http://localhost:9085/api/v1/account/2/deposit
3.2 Request Method- POST
3.3 Request Body-
{
	"amount":100.00,
	"currency":"EUR"
}
3.4 Response-
{
    "body": {
        "id": 1,
        "type": "SAVING",
        "balance": 200,
        "currency": "EUR"
    },
    "header": {
        "status": "SUCCESS",
        "operation": "DEPOSIT"
    }
}

4. Withdraw-
4.1 Request URL- http://localhost:9085/api/v1/account/2/withdraw
4.2 Request Method- POST
4.3 Request Body-
{
	"amount":100.00,
	"currency":"EUR"
}
4.4 Response-
{
    "body": {
        "id": 2,
        "type": "SAVING",
        "balance": 0,
        "currency": "EUR"
    },
    "header": {
        "status": "SUCCESS",
        "operation": "WITHDRAW"
    }
}

5. Fund Transfer-
5.1 Request URL- http://localhost:9085/api/v1/account/1/transfer/2
5.2 Request Method- POST
5.3 Request Body-
{
	"amount":100.00,
	"currency":"EUR"
}
5.4 Response-
{
    "body": {
        "to": {
            "id": 2,
            "type": "SAVING",
            "balance": 100,
            "currency": "EUR"
        },
        "from": {
            "id": 1,
            "type": "SAVING",
            "balance": 100,
            "currency": "EUR"
        }
    },
    "header": {
        "status": "SUCCESS",
        "operation": "FUND_TRANSFER"
    }
}



Steps to run project-
Step 1-
Run following command to build projec-
mvn clean install

Step 2-
Goto traget folder and run following command-
java -jar moneyTarnsferApp-0.0.1.jar