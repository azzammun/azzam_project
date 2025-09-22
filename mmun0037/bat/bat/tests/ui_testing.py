import unittest
from unittest.mock import patch
from src.bat_ui import BatUI
from src.data_mgmt import DataManager

class TestBatUIMainMenu(unittest.TestCase):
    def setUp(self):
        
        self.data_manager = DataManager()  # Initialize DataManager
        self.ui = BatUI(self.data_manager)

    @patch('src.user_input.read_integer_range', side_effect=[1])
    def test_main_menu_loan_item(self, mock_input):                      # Mock input to select "Loan Item"
        self.ui.run_current_screen()                                     # Run the main menu screen
        self.assertEqual(self.ui.get_current_screen(), "LOAN ITEM")      # Check if it navigated to "Loan Item" screen

    @patch('src.user_input.read_integer_range', side_effect=[2])
    def test_main_menu_return_item(self, mock_input):
        self.ui.run_current_screen()
        self.assertEqual(self.ui.get_current_screen(), "RETURN ITEM")       # Check if it navigated to "Return Item" screen

    @patch('src.user_input.read_integer_range', side_effect=[3])
    def test_main_menu_search_patron(self, mock_input):
        self.ui.run_current_screen()
        self.assertEqual(self.ui.get_current_screen(), "SEARCH FOR PATRON")     # Check if it navigated to "Search for Patron" screen

    @patch('src.user_input.read_integer_range', side_effect=[4])
    def test_main_menu_register_patron(self, mock_input):
        self.ui.run_current_screen()
        self.assertEqual(self.ui.get_current_screen(), "REGISTER PATRON")   # Check if it navigated to "Register Patron" screen

    @patch('src.user_input.read_integer_range', side_effect=[5])
    def test_main_menu_access_makerspace(self, mock_input):
        self.ui.run_current_screen()
        self.assertEqual(self.ui.get_current_screen(), "ACCESS MAKERSPACE")  # Check if it navigated to "Access Makerspace" screen

    @patch('src.user_input.read_integer_range', side_effect=[6])
    def test_main_menu_quit(self, mock_input):
        self.ui.run_current_screen()
        self.assertEqual(self.ui.get_current_screen(), "QUIT")            # Check if it navigated to "Quit" screen

    @patch('src.user_input.read_integer_range', side_effect=[87, 4])
    def test_main_menu_invalid_then_valid_input(self, mock_input):
        # Keep looping until a valid option is selected
        while self.ui.get_current_screen() == "MAIN MENU":
            self.ui.run_current_screen()
        self.assertEqual(self.ui.get_current_screen(), "REGISTER PATRON")       # Check if it navigated to "Register Patron" screen after invalid input


