import unittest
from unittest import mock
import number_guess



class TestNumberGuessGame(unittest.TestCase):

    #  get_guess() tests 
    @mock.patch("builtins.input", side_effect=["59"])
    @mock.patch("builtins.print")
    def test_single_valid_guess(self, mock_print, mock_input):
        """User enters a valid number on the first try."""
        result = number_guess.get_guess()
        self.assertEqual(result, 59)

    @mock.patch("builtins.input", side_effect=["231", "90"])
    @mock.patch("builtins.print")
    def test_retry_out_of_range_then_valid(self, mock_print, mock_input):
        """
        First input out-of-range triggers the retry.
        Second input is valid.
        """
        result = number_guess.get_guess()
        self.assertEqual(result, 90)

    @mock.patch("builtins.input", side_effect=["932", "2323", "45"])
    @mock.patch("builtins.print")
    def test_double_out_of_range_input(self, mock_print, mock_input):
        """
        Test what happens if the user enters two numbers both out of range.
        Currently, get_guess() only retries once, so the second input is returned.
        """
        result = number_guess.get_guess()
        self.assertTrue(0 <= result <= 100, "get_guess() did not loop until valid!")

    # guess comparison higher
    def test_guess_higher_than_answer(self):
        """Check if guess is higher than answer."""
        self.assertTrue(number_guess.guess_too_high(90, 55))
        self.assertFalse(number_guess.guess_too_high(55, 55))

    # guess comparison lower
    def test_guess_lower_than_answer(self):
        """Check if guess is lower than answer."""
        self.assertTrue(number_guess.guess_too_low(20, 55))
        self.assertFalse(number_guess.guess_too_low(55, 55))

    # guess comparison correct
    def test_guess_is_exactly_correct(self):
        """Check if guess matches the answer exactly."""
        self.assertTrue(number_guess.guess_correct(55, 55))
        self.assertFalse(number_guess.guess_correct(54, 55))

    # play_game() 
    @mock.patch("builtins.input", side_effect=["24", "y", "17", "n"])
    @mock.patch("number_guess.select_answer", side_effect=[24, 17])
    @mock.patch("builtins.print")
    def test_win_first_round_then_play_again(self, mock_print, mock_select, mock_input):
        """Simulate winning first round, then playing again and winning second round."""
        number_guess.play_game()
        self.assertEqual(mock_select.call_count, 2)

    @mock.patch(
        "builtins.input",
        side_effect=["20", "32", "45", "14", "93", "67", "37", "74", "81", "97", "n"]
    )
    @mock.patch("number_guess.select_answer", return_value=50)
    @mock.patch("builtins.print")
    def test_hit_max_attempts_triggers_game_over(self, mock_print, mock_select, mock_input):
        """Simulate 10 wrong guesses to trigger 'Too many guesses!' branch."""
        number_guess.play_game()
        self.assertEqual(mock_select.call_count, 1)
        self.assertEqual(mock_input.call_count, 11) 

    @mock.patch(
    "builtins.input",
    side_effect=["10", "20", "30", "40", "50", "51", "n"]  
    )
    @mock.patch("number_guess.select_answer", return_value= 51)
    @mock.patch("builtins.print")
    def test_guess_correct_after_some_attempts(self, mock_print, mock_select, mock_input):
        """Correct guess after several wrong guesses to cover 49->40 branch."""
        number_guess.play_game()
        self.assertEqual(mock_select.call_count, 1)

