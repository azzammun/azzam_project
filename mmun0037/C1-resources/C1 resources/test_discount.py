'''
The method you are testing is:
    calculate_discount(age)

The data type of each parameter is:
- age: integer

You can assume that the calculate_discount method is already imported into this python module,
so you can call "calculate_discount" directly.

Author: Muhammad Roayna Azzam Muntaqo
Student ID: 35052473
'''

import unittest

class TestCalculateDiscount(unittest.TestCase):

    def test_adult_no_discount(self):
        self.assertEqual(calculate_discount(30), 0)

    def test_below_10_percent_threshold(self):
        self.assertEqual(calculate_discount(49), 0)
    
    def test_oldest_age_no_discount(self):
        self.assertEqual(calculate_discount(50), 0)    

    def test_lower_boundary_10_percent_discount(self):
        self.assertEqual(calculate_discount(51), 10)

    def test_middle_range_10_percent_discount(self):
        self.assertEqual(calculate_discount(55), 10)

    def test_upper_boundary_10_percent_discount(self):
        self.assertEqual(calculate_discount(64), 10)

    def test_lower_boundary_15_percent_discount(self):
        self.assertEqual(calculate_discount(65), 15)

    def test_just_above_boundary_15_percent_discount(self):
        self.assertEqual(calculate_discount(66), 15)

    def test_middle_range_15_percent_discount(self):
        self.assertEqual(calculate_discount(75), 15)

    def test_upper_boundary_15_percent_discount(self):
        self.assertEqual(calculate_discount(89), 15)

    def test_free_fee_boundary(self):
        self.assertEqual(calculate_discount(90), 100)

    def test_just_above_free_fee_boundary(self):
        self.assertEqual(calculate_discount(91), 100)

    def test_middle_range_free_fee(self):
        self.assertEqual(calculate_discount(95), 100)

    def test_oldest_age_free_fee(self):
        self.assertEqual(calculate_discount(100), 100)


