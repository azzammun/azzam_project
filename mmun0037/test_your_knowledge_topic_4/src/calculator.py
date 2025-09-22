# Student name: Muhammad Roayna Azzam Muntaqo
# Student ID: 35052473

class Calculator:
    """
    A calculator that maintains an internal 'answer' and supports method chaining.
    """

    def __init__(self):
        self._answer = 0  # initial answer

    def get_answer(self):
        """Return the current internal answer."""
        return self._answer

    def reset(self):
        """Reset the internal answer to zero."""
        self._answer = 0
        return self  # enable method chaining

    def add(self, num):
        """Add a number to the internal answer."""
        self._answer += num
        return self  # enable method chaining

    def subtract(self, num):
        """Subtract a number from the internal answer."""
        self._answer -= num
        return self  # enable method chaining

    def multiply(self, num):
        """Multiply the internal answer by a number."""
        self._answer *= num
        return self  # enable method chaining

    def power(self, num):
        """Raise the internal answer to the power of a number."""
        self._answer **= num
        return self  # enable method chaining
