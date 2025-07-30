import datetime

class TaxPayer:
    def __init__(self, taxpayer_id, name, income_details, deductions=0):
        self.taxpayer_id = taxpayer_id
        self.name = name
        self.income_details = income_details  # Dict with keys: salary, business, capital_gains
        self.deductions = deductions
        self.taxable_income = 0
        self.total_tax = 0

    def calculate_total_income(self):
        return sum(self.income_details.values())


class TaxCalculator:
    def __init__(self, slabs):
        self.slabs = slabs  # List of tuples: [(limit, rate), ...]

    def compute_taxable_income(self, taxpayer: TaxPayer):
        gross_income = taxpayer.calculate_total_income()
        taxpayer.taxable_income = max(0, gross_income - taxpayer.deductions)
        return taxpayer.taxable_income

    def calculate_tax(self, taxpayer: TaxPayer):
        income = self.compute_taxable_income(taxpayer)
        tax = 0
        previous_limit = 0

        for limit, rate in self.slabs:
            if income > limit:
                tax += (limit - previous_limit) * rate
                previous_limit = limit
            else:
                tax += (income - previous_limit) * rate
                break

        taxpayer.total_tax = self.apply_rebate(taxpayer, tax)
        return taxpayer.total_tax

    def apply_rebate(self, taxpayer: TaxPayer, tax: float):
        # Apply a rebate if taxpayer is eligible (e.g., income below certain threshold)
        if taxpayer.taxable_income < 500000:
            rebate = min(tax, 12500)
            return tax - rebate
        return tax


class TaxReportGenerator:
    def __init__(self):
        self.report_log = []

    def generate_summary(self, taxpayer: TaxPayer):
        report = {
            'Taxpayer ID': taxpayer.taxpayer_id,
            'Name': taxpayer.name,
            'Total Income': taxpayer.calculate_total_income(),
            'Deductions': taxpayer.deductions,
            'Taxable Income': taxpayer.taxable_income,
            'Total Tax': taxpayer.total_tax,
            'Timestamp': datetime.datetime.now()
        }
        self.report_log.append(report)
        self.print_report(report)

    def print_report(self, report):
        print("\n=== Tax Report ===")
        for key, value in report.items():
            print(f"{key}: {value}")


# Master Controller to run the entire tax system
class TaxSystem:
    def __init__(self):
        self.taxpayers = []
        self.calculator = TaxCalculator(slabs=[
            (250000, 0.0),
            (500000, 0.05),
            (1000000, 0.20),
            (float('inf'), 0.30)
        ])
        self.report_generator = TaxReportGenerator()

    def register_taxpayer(self, taxpayer: TaxPayer):
        self.taxpayers.append(taxpayer)

    def process_all_taxpayers(self):
        for tp in self.taxpayers:
            self.calculator.calculate_tax(tp)                  # Calls compute_taxable_income → apply_rebate
            self.report_generator.generate_summary(tp)         # Calls print_report internally


# Sample execution
if __name__ == "__main__":
    tax_system = TaxSystem()

    # Create and register taxpayers
    t1 = TaxPayer("TX001", "Alice", {'salary': 700000, 'business': 50000, 'capital_gains': 100000}, deductions=150000)
    t2 = TaxPayer("TX002", "Bob", {'salary': 300000, 'business': 0, 'capital_gains': 0}, deductions=50000)
    t3 = TaxPayer("TX003", "Charlie", {'salary': 1200000, 'business': 250000, 'capital_gains': 300000}, deductions=200000)

    tax_system.register_taxpayer(t1)
    tax_system.register_taxpayer(t2)
    tax_system.register_taxpayer(t3)

    # Process and generate reports
    tax_system.process_all_taxpayers()
