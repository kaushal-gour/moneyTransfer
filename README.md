# moneyTransfer

API Details-

1.Create Account-
1.1 Request URL-
http://localhost:9085/api/v1/account
1.2 Request Method-
POST
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
2.1 Request URL-
http://localhost:9085/api/v1/account/1/balance
2.2 Request Method-
GET
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
3.1 Request URL-
http://localhost:9085/api/v1/account/2/deposit
3.2 Request Method-
POST
3.3 Request Body-
{
	"amount":100.00,
	"currency":"EUR"
}
3.4 Response-

4. Withdraw-
4