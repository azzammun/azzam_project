import unittest
from unittest import mock
import user_input


class TestUserInput(unittest.TestCase):

    # is_int 

    def test_is_int_valid_and_invalid(self):
        
        self.assertTrue(user_input.is_int("483"))        # positive integer
        
        self.assertTrue(user_input.is_int("-238"))        # negative integer
        
        self.assertFalse(user_input.is_int("7.3923"))      # float string fails
        
        self.assertFalse(user_input.is_int("dfijd"))       # non-numeric string fails

    # is_float
    def test_is_float_valid_and_invalid(self):
        
        self.assertTrue(user_input.is_float("42"))         # integer string is valid float
        
        self.assertTrue(user_input.is_float("6.3443"))       # float
        
        self.assertTrue(user_input.is_float("1e-3"))         # scientific notation
        
        self.assertFalse(user_input.is_float("udhfd"))            # non-numeric string fails

    # read_string 

    @mock.patch("builtins.input", return_value="hello world")
    def test_read_string_returns_input(self, mock_input):
        
        result = user_input.read_string("Type something: ")             # prompt text
        self.assertEqual(result, "hello world")
        mock_input.assert_called_once_with("Type something: ")

    # read_integer 

    @mock.patch("builtins.input", side_effect=["lol", "7"])
    def test_read_integer_retry_until_valid(self, mock_input):
        
        result = user_input.read_integer("Enter an integer: ")   # "lol" invalid, "7" valid
        self.assertEqual(result, 7)

    @mock.patch("builtins.input", return_value="99")
    def test_read_integer_valid_first_try(self, mock_input):
        
        result = user_input.read_integer("Enter an integer: ")  # "99" valid input
        self.assertEqual(result, 99)

    #  read_float 

    @mock.patch("builtins.input", side_effect=["Goodgame", "6.28"])
    def test_read_float_retry_until_valid(self, mock_input):
        
        result = user_input.read_float("Enter a float: ")   # "Goodgame" invalid, "6.28" valid
        self.assertAlmostEqual(result, 6.28)

    @mock.patch("builtins.input", return_value="9.81")
    def test_read_float_valid_first_try(self, mock_input):
        
        result = user_input.read_float("Enter a float: ")  # "9.81" valid input
        self.assertAlmostEqual(result, 9.81)

    #  read_integer_range 

    @mock.patch("builtins.input", side_effect=["3", "15"])
    def test_read_integer_range_outside_then_valid(self, mock_input):
        
        result = user_input.read_integer_range("Enter 10-100: ", 10, 100)   # "3" out of range, "15" valid
        self.assertEqual(result, 15)

    @mock.patch("builtins.input", return_value="12")
    def test_read_integer_range_inside_bounds(self, mock_input):
        
        result = user_input.read_integer_range("Enter 10-20: ", 10, 100) # "12" valid 
        self.assertEqual(result, 12)

    #  read_float_range 

    @mock.patch("builtins.input", side_effect=["12.5", "7.7"])
    def test_read_float_range_outside_then_valid(self, mock_input):
        
        result = user_input.read_float_range("Enter 0.0-10.0: ", 0.0, 10.0)  # "12.5" out of range, "7.7" valid
        self.assertAlmostEqual(result, 7.7)

    @mock.patch("builtins.input", return_value="8.8")
    def test_read_float_range_inside_bounds(self, mock_input):
        
        result = user_input.read_float_range("Enter 5.0-10.0: ", 5.0, 10.0) # "8.8" valid
        self.assertAlmostEqual(result, 8.8)
