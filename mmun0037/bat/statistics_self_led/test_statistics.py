import unittest
from statistics import *

class TestStatistics(unittest.TestCase):
    # Tests for sum, mean, minimum, maximum functions in statistics.py

    def test_sum(self):
        # sum of several numbers
        self.assertEqual(sum([1, 2, 3, 4]), 10)

        # sum of negative numbers
        self.assertEqual(sum([-1, -2, -3]), -6)

        # sum of empty list
        self.assertEqual(sum([]), 0)

        # sum of single number
        self.assertEqual(sum([5]), 5)


    def test_mean(self):
        # mean of multiple numbers
        self.assertEqual(mean([1, 2, 3, 4]), 2.5)

        # mean of negative numbers
        self.assertEqual(mean([-1, -2, -3]), -2)

        # mean of single number
        self.assertEqual(mean([5]), 5)


    def test_minimum(self):
        # minimum in a list of numbers
        self.assertEqual(minimum([4, 1, 7, 3]), 1)

        # all elements are the same
        self.assertEqual(minimum([2, 2, 2]), 2)

        # negative numbers
        self.assertEqual(minimum([-5, -1, -3]), -5)

        # single-element list
        self.assertEqual(minimum([10]), 10)

        # True branch: element smaller than current min
        self.assertEqual(minimum([5, 3, 4]), 3)
        # False branch: element never smaller than current min
        self.assertEqual(minimum([1, 2, 3, 4]), 1)
        # loop skipped: single-element list
        self.assertEqual(minimum([7]), 7)


    def test_maximum(self):
        # maximum in a list of numbers
        self.assertEqual(maximum([4, 1, 7, 3]), 7)

        # all elements are the same
        self.assertEqual(maximum([2, 2, 2]), 2)

        # negative numbers
        self.assertEqual(maximum([-5, -1, -3]), -1)

        # single-element list
        self.assertEqual(maximum([10]), 10)

        # True branch: element larger than current max
        self.assertEqual(maximum([3, 5, 2]), 5)
        # False branch: element never larger than current max
        self.assertEqual(maximum([4, 3, 2, 1]), 4)
        # loop skipped: single-element list
        self.assertEqual(maximum([9]), 9)
