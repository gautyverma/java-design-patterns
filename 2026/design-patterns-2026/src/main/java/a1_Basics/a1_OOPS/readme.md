1. Create differ class for credit and debit card, both have some common attribute like cardHolderName, cardNumber.
2. To Avoid duplication of code create a parent class Card and inherit it properties.
3. pay method is different for both the class, so I define it in parent class as abstract method and implement it in
   child class.
4. Now I added more functionlity like UPI and wallet which can use pay method but these are not card, so I created an
   interface Payment and implemented it in UPI and Wallet class. and as well make changes in creditCard class and debit
   card class to implement Payment interface and implement pay method.
