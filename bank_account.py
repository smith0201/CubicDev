class BankAccount:
    def __init__(self, acc_no, holder_name, balance, password):
        self.acc_no = acc_no
        self.holder_name = holder_name
        self.balance = balance
        self.password = password

    def deposit(self, amount):
        self.balance += amount
        print("Deposited:", amount)

    def withdraw(self, amount):
        if amount < self.balance:
            self.balance -= amount
            print("Withdrawn:", amount)
        else:
            print("Insufficient balance")

    def show_details(self):
        print("Account No:", self.acc_no)
        print("Name:", self.holder_name)
        print("Balance:", self.balance)
        print("Password:", self.password)
