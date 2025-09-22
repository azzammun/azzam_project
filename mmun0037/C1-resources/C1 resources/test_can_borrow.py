'''
The method you are testing is:
    can_borrow(item_type, patron_age, length_of_loan, outstanding_fees, gardening_training, carpentry_training)

The data type of each parameter is:
- item_type: string
- patron_age: integer
- length_of_loan: integer
- outstanding_fees: float
- gardening_training: boolean
- carpentry_training: boolean

You can assume that the can_borrow method is already imported into this python module,
so you can call "can_borrow" directly.

Author: Muhammad Roayna Azzam Muntaqo
Student ID: 35052473
'''
import unittest

class TestCanBorrow(unittest.TestCase):

    def test_carpentrytool_underage_short_fees_gardening_completed(self):
        self.assertFalse(can_borrow("CarpentryTool", 16, 2, 10.0, True, False))

    
    def test_gardeningtool_underage_long_no_fees_gardening_notcompleted(self):
        self.assertFalse(can_borrow("GardeningTool", 16, 14, 0.0, False, True))

    
    def test_book_senior_medium_fees_all_completed(self):   
        self.assertFalse(can_borrow("Book", 70, 7, 10.0, True, True))

    
    def test_book_adult_medium_no_fees_no_training(self):
        self.assertTrue(can_borrow("Book", 35, 7, 0.0, False, False))

    
    def test_book_senior_long_no_fees_gardening_completed(self):
        self.assertTrue(can_borrow("Book", 70, 14, 0.0, True, False))

    
    def test_carpentrytool_adult_short_fees_carpentry_completed(self):
        self.assertFalse(can_borrow("CarpentryTool", 35, 2, 10.0, False, True))

    
    def test_carpentrytool_senior_long_no_fees_carpentry_completed(self):
        self.assertTrue(can_borrow("CarpentryTool", 70, 14, 0.0, False, True))

    
    def test_book_senior_short_no_fees_all_completed(self):
        self.assertTrue(can_borrow("Book", 70, 2, 0.0, True, True))

    
    def test_carpentrytool_underage_medium_no_fees_all_completed(self):
        self.assertFalse(can_borrow("CarpentryTool", 16, 7, 0.0, True, True))

    
    def test_gardeningtool_senior_medium_fees_gardening_completed(self):
        self.assertFalse(can_borrow("GardeningTool", 70, 7, 10.0, True, False))

    
    def test_gardeningtool_adult_long_fees_gardening_completed(self):
        self.assertFalse(can_borrow("GardeningTool", 35, 14, 10.0, True, False))

    
    def test_gardeningtool_underage_short_fees_no_training(self):
        self.assertFalse(can_borrow("GardeningTool", 16, 2, 10.0, False, False))

    
    def test_book_underage_medium_no_fees_all_completed(self):
        self.assertFalse(can_borrow("Book", 16, 7, 0.0, True, True))

