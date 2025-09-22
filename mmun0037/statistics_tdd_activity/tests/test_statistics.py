import unittest
from src import statistics  

class TestStatistics(unittest.TestCase):

    def test_sum(self):
        self.assertEqual(statistics.sum([1, 2, 3]), 6)
        self.assertEqual(statistics.sum([0, 0, 0]), 0)
        self.assertEqual(statistics.sum([-1, 1]), 0)

    def test_mean(self):
        self.assertEqual(statistics.mean([1, 2, 3]), 2)
        self.assertEqual(statistics.mean([5, 5, 5, 5]), 5)
        self.assertEqual(statistics.mean([2, 4]), 3)

    def test_minimum(self):
        self.assertEqual(statistics.minimum([1, 2, 3]), 1)
        self.assertEqual(statistics.minimum([-5, 0, 5]), -5)

    def test_maximum(self):
        self.assertEqual(statistics.maximum([1, 2, 3]), 3)
        self.assertEqual(statistics.maximum([-5, 0, 5]), 5)
