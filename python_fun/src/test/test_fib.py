import unittest
from fib import Fib


class TestFib(unittest.TestCase):
    def setUp(self):
        self.fib = Fib()

    def test_recursively(self):
        self.assertEqual(self.fib.recursively(0), 0)
        self.assertEqual(self.fib.recursively(1), 1)
        self.assertEqual(self.fib.recursively(2), 1)
        self.assertEqual(self.fib.recursively(3), 2)
        self.assertEqual(self.fib.recursively(4), 3)
        self.assertEqual(self.fib.recursively(5), 5)
        self.assertEqual(self.fib.recursively(6), 8)
        self.assertEqual(self.fib.recursively(7), 13)
        self.assertEqual(self.fib.recursively(8), 21)
        self.assertEqual(self.fib.recursively(9), 34)
        self.assertEqual(self.fib.recursively(10), 55)

    def test_whileLoop(self):
        self.assertEqual(self.fib.whileLoop(0), 0)
        self.assertEqual(self.fib.whileLoop(1), 1)
        self.assertEqual(self.fib.whileLoop(2), 1)
        self.assertEqual(self.fib.whileLoop(3), 2)
        self.assertEqual(self.fib.whileLoop(4), 3)
        self.assertEqual(self.fib.whileLoop(5), 5)
        self.assertEqual(self.fib.whileLoop(6), 8)
        self.assertEqual(self.fib.whileLoop(7), 13)
        self.assertEqual(self.fib.whileLoop(8), 21)
        self.assertEqual(self.fib.whileLoop(9), 34)
        self.assertEqual(self.fib.whileLoop(10), 55)

    def test_timedRecursively(self):
        self.assertEqual(self.fib.timedRecursively(40), 102334155)

    def test_timedWhileLoop(self):
        self.assertEqual(self.fib.timedWhileLoop(40), 102334155)
        self.assertEqual(self.fib.timedWhileLoop(50), 12586269025)
        self.assertEqual(self.fib.timedWhileLoop(60), 1548008755920)

unittest.main()