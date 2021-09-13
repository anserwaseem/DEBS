# DEBS

## 1.	Introduction
Implementation of double entry bookkeeping system. 

## 2.	Objective
To design a real-time double entry bookkeeping system for small to medium sized businesses.

## 3.	Problem Description
Only twenty years ago, small to medium sized businesses faced daunting choices for selecting bookkeeping systems. For any small business, keeping a record of all transactions plays an essential role in building a solid foundation for financial health.
Our mission is to remove the barriers to financial clarity that every business owner faces.
Double-entry bookkeeping involves making at least two entries for every transaction. A debit in one account and a corresponding credit in another account. The sum of all debits should always equal the sum of all credits, providing a simple way to check for errors.

## 4.	Target Audience and their roles
Small to medium sized businesses.

## 5.	Functional requirements of your project
Following functionalities are provided:

### Data Entry
-	Add Customer: It adds customer record, with his details like shop name, address, phone, etc.
-	Delete Customer: It deletes customer record (although customer’s transactions would not be deleted).
-	Update Customer: It updates customer’s details.
-	Account Ledger: It shows all transactions happened in a particular account.
-	Chart Of Accounts: It shows all accounts categorized by their groups (Assets, Liabilities, Capital, Revenue, Expenses, etc.) to get a clear insight of the financial health of business.

### Transactions
-	Create Transaction: It allows to add a new transaction for unlimited number of accounts (to balance credit & debit side of transaction).
-	Delete Transaction: It deletes a transaction.
-	Commit Transaction: It commits/saves a transaction after checking possible errors.
-	List Transactions: It shows all transactions with their respective entries.

### Inventory
-	Add Item: It adds a new item in the stock.
-	Update Item: It updates (decreases or increases) the number of items in stock.
-	Delete Item: It deletes the item from stock.

### Reports
-	Trial Balance: It shows a bookkeeping worksheet in which the balance of all ledgers is compiled into ‘debit’ and ‘credit’ account column totals that are equal.
