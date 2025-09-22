import unittest

from calculator import Calculator

class TestCalculator(unittest.TestCase):
    def setUp(self):
        self.calc = Calculator()

    def test_initial_answer(self):
        # Test that the initial answer is 0
        self.calc = Calculator()
        self.assertEqual(0, self.calc.get_answer())
    
    def test_add(self):
        # Test adding a number updates the answer correctly
        self.calc.add(5)
        self.assertEqual(5, self.calc.get_answer())

    def test_subtract(self):
        # Test subtracting a number updates the answer correctly
        self.calc.add(10).subtract(3)
        self.assertEqual(7, self.calc.get_answer())

    def test_multiply(self):
        # Test multiplying the answer updates it correctly
        self.calc.add(2).multiply(3)
        self.assertEqual(6, self.calc.get_answer())

    def test_reset(self):
        # Test that reset sets the answer back to 0
        self.calc.add(10).reset()
        self.assertEqual(0, self.calc.get_answer())

    def test_power(self):
        # Test raising the answer to a power works correctly
        self.calc.add(2)
        self.calc.power(3)
        self.assertEqual(8, self.calc.get_answer())

