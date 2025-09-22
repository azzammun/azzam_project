import unittest
from discount import calculate_discount

class TestPathCoverage(unittest.TestCase):
    # Tests to achieve path coverage for calculate_discount function

    def test_case_1(self):
        # cart_value=180, not senior, purchase_count=22 → medium cart, loyal customer → discount 10
        self.assertEqual(calculate_discount(180, False, 22), 10)

    def test_case_2(self):
        # cart_value=260, senior, purchase_count=8 → high cart, not loyal → discount 15
        self.assertEqual(calculate_discount(260, True, 8), 15)

    def test_case_3(self):
        # cart_value=140, not senior, purchase_count=12 → medium cart, loyal → discount 10
        self.assertEqual(calculate_discount(140, False, 12), 10)

    def test_case_4(self):
        # cart_value=130, senior, purchase_count=13 → medium cart, senior + loyal → discount 20
        self.assertEqual(calculate_discount(130, True, 13), 15)

    def test_case_5(self):
        # cart_value=80, not senior, purchase_count=6 → low cart, not loyal → discount 0
        self.assertEqual(calculate_discount(80, False, 6), 0)

    def test_case_6(self):
        # cart_value=90, not senior, purchase_count=15 → low cart, loyal → discount 5
        self.assertEqual(calculate_discount(90, False, 15), 5)

    def test_case_7(self):
        # cart_value=310, senior, purchase_count=14 → high cart, senior + loyal → discount 20
        self.assertEqual(calculate_discount(310, True, 14), 20)

    def test_case_8(self):
        # cart_value=125, senior, purchase_count=7 → medium cart, senior, not loyal → discount 10
        self.assertEqual(calculate_discount(125, True, 7), 10)

    def test_case_9(self):
        # cart_value=300, not senior, purchase_count=5 → high cart, not senior, not loyal → discount 10
        self.assertEqual(calculate_discount(300, False, 5), 10)



