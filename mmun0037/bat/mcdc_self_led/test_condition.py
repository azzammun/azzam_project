import unittest
from condition import condition

class Testmc_dc(unittest.TestCase):
    """
    1: A=F, B=F, C=F, D=F, Outcome=F
    2: A=F, B=F, C=F, D=T, Outcome=F
    3: A=F, B=F, C=T, D=F, Outcome=F
    4: A=F, B=F, C=T, D=T, Outcome=F
    5: A=F, B=T, C=F, D=F, Outcome=F
    6: A=F, B=T, C=F, D=T, Outcome=F
    7: A=F, B=T, C=T, D=F, Outcome=F
    8: A=F, B=T, C=T, D=T, Outcome=F
    9: A=T, B=F, C=F, D=F, Outcome=F
    10: A=T, B=F, C=F, D=T, Outcome=F
    11: A=T, B=F, C=T, D=F, Outcome=F
    12: A=T, B=F, C=T, D=T, Outcome=T
    13: A=T, B=T, C=F, D=F, Outcome=F
    14: A=T, B=T, C=F, D=T, Outcome=T
    15: A=T, B=T, C=T, D=F, Outcome=F
    16: A=T, B=T, C=T, D=T, Outcome=T

    I choose this set of tests using MC/DC:
    4, 10, 11, 12, 14
    """

    def test_case_4(self):
        # A=F, B=F, C=T, D=T → Outcome=F
        self.assertFalse(condition(False, False, True, True))

    def test_case_10(self):
        # A=T, B=F, C=F, D=T → Outcome=F
        self.assertFalse(condition(True, False, False, True))

    def test_case_11(self):
        # A=T, B=F, C=T, D=F → Outcome=F
        self.assertFalse(condition(True, False, True, False))

    def test_case_12(self):
        # A=T, B=F, C=T, D=T → Outcome=T
        self.assertTrue(condition(True, False, True, True))

    def test_case_14(self):
        # A=T, B=T, C=F, D=T → Outcome=T
        self.assertTrue(condition(True, True, False, True))
