import unittest
from src.business_logic import can_use_makerspace

'''
Feasible Paths for can_use_makerspace:

Path 1:
- Condition: patron_type == "ERROR"
- Flow: lines 2 → 3-4 → 5 → 6 → 12 → 14

Path 2:
- Condition: patron_type == "Minor"
- Flow: lines 2 → 3-4 → 5 → 7 → 8 → 12 → 14

Path 3:
- Condition: patron_type == "Elderly"
- Flow: lines 2 → 3-4 → 5 → 7 → 8 → 12 → 14

Path 4:
- Condition: patron_type == "Adult", has training, owes fees
- Flow: lines 2 → 3-4 → 5 → 7 → 10-11 → 12 → 13 → 14

Path 5:
- Condition: patron_type == "Adult", has training, no fees
- Flow: lines 2 → 3-4 → 5 → 7 → 10-11 → 12 → 14
'''

class TestPathCoverage(unittest.TestCase):

    # Path 1
    def test_path1_error_age(self):
        # Path 1: patron_type == "ERROR"
        self.assertFalse(can_use_makerspace(-10, 0, True))

    # Path 2
    def test_path2_minor(self):
        # Path 2: patron_type == "Minor"
        self.assertFalse(can_use_makerspace(5, 0, True))

    # Path 3
    def test_path3_elderly(self):
        # Path 3: patron_type == "Elderly"
        self.assertFalse(can_use_makerspace(100, 0, True))

    # Path 4
    def test_path4_adult_with_fees(self):
        # Path 4: patron_type == "Adult", has training, owes fees
        self.assertFalse(can_use_makerspace(30, 100, True))

    # Path 5
    def test_path5_adult_no_fees(self):
        # Path 5: patron_type == "Adult", has training, no fees
        self.assertTrue(can_use_makerspace(30, 0, True))
