# Student name: Muhammad Roayna Azzam Muntaqo
# Student ID: 35052473

import unittest
from src.calculator import Calculator

class TestCalculator(unittest.TestCase):
    """Unit tests for the Calculator class following TDD."""

    def setUp(self):
        """Create a fresh Calculator instance before each test."""
        self.calc = Calculator()

    def test_initial_answer(self):
        """Test that the initial answer is zero."""
        self.assertEqual(self.calc.get_answer(), 0)

    def test_addition(self):
        """Test the add method."""
        self.calc.add(7)
        self.assertEqual(self.calc.get_answer(), 7)

    def test_subtraction(self):
        """Test the subtract method."""
        self.calc.add(15).subtract(6)
        self.assertEqual(self.calc.get_answer(), 9)

    def test_multiplication(self):
        """Test the multiply method."""
        self.calc.add(3).multiply(3)
        self.assertEqual(self.calc.get_answer(), 9)

    def test_power(self):
        """Test the power method."""
        self.calc.add(2).power(3)
        self.assertEqual(self.calc.get_answer(), 8)

    def test_reset(self):
        """Test the reset method."""
        self.calc.add(10).reset()
        self.assertEqual(self.calc.get_answer(), 0)

    def test_method_chaining(self):
        """Test multiple operations chained together."""
        self.calc.add(2).multiply(3).subtract(1).power(2)
        self.assertEqual(self.calc.get_answer(), 25)


if __name__ == "__main__":
    unittest.main()
