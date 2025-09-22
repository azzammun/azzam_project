import unittest
from src.business_logic import can_borrow_carpentry_tool

'''
MC/DC Analysis for can_borrow_carpentry_tool:

Conditions:
C1: fees owed > 0
C2: age <= 18
C3: age >= 90
C4: carpentry training done

Theoretical combinations of conditions:
Test 1: No fees, adult, training done → True
Test 2: Fees owed, adult, training done → False
Test 3: No fees, age <= 18, training done → False
Test 4: No fees, age >= 90, training done → False
Test 5: No fees, adult, training not done → False
Test 6: Fees owed, age <= 18, training done → False
Test 7: Fees owed, adult, training not done → False
Test 8: No fees, age <= 18, training not done → False

Feasible tests (can actually write with realistic inputs):
Test 1: No fees, adult, training done → True
Test 2: Fees owed, adult, training done → False
Test 3: No fees, too young, training done → False
Test 4: No fees, too old, training done → False
Test 5: No fees, adult, training not done → False

Step 3: Optimal set for MC/DC (covers each condition independently):
Chosen set: Tests 1, 2, 3, 4, 5
'''

class TestCanBorrowCarpentryToolMC(unittest.TestCase):

    # Test 1: everything fine → can borrow
    def test_can_borrow_tool_1(self):
        self.assertTrue(can_borrow_carpentry_tool(patron_age=30, length_of_loan=7, outstanding_fees=0, carpentry_tool_training=True))

    # Test 2: has fees → cannot borrow
    def test_can_borrow_tool_2(self):
        self.assertFalse(can_borrow_carpentry_tool(patron_age=30, length_of_loan=7, outstanding_fees=10, carpentry_tool_training=True))

    # Test 3: too young → cannot borrow
    def test_can_borrow_tool_3(self):
        self.assertFalse(can_borrow_carpentry_tool(patron_age=18, length_of_loan=7, outstanding_fees=0, carpentry_tool_training=True))

    # Test 4: too old → cannot borrow
    def test_can_borrow_tool_4(self):
        self.assertFalse(can_borrow_carpentry_tool(patron_age=90, length_of_loan=7, outstanding_fees=0, carpentry_tool_training=True))

    # Test 5: no training → cannot borrow
    def test_can_borrow_tool_5(self):
        self.assertFalse(can_borrow_carpentry_tool(patron_age=30, length_of_loan=7, outstanding_fees=0, carpentry_tool_training=False))
