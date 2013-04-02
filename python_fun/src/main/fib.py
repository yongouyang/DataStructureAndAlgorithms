import datetime


class Fib:
    def recursively(self, n):
        if n == 0:
            return 0
        elif n <= 2:
            return 1
        else:
            return self.recursively(n - 1) + self.recursively(n - 2)

    def whileLoop(self, n):
        if n == 0:
            return 0
        previous, fib, currentIndex = 0, 1, 1
        while currentIndex < n:
            previous, fib = fib, previous + fib
            currentIndex += 1

        return fib

    def timedRecursively(self, n):
        start = datetime.datetime.now()
        result = self.recursively(n)
        end = datetime.datetime.now()
        elapsed = end - start
        print "Working out Fibonacci({})={} recursively took {} seconds".format(str(n), str(result), str(elapsed.total_seconds()))
        return result

    def timedWhileLoop(self, n):
        start = datetime.datetime.now()
        result = self.whileLoop(n)
        end = datetime.datetime.now()
        elapsed = end - start
        print "Working out Fibonacci({})={} with while loop took {} seconds".format(str(n), str(result), str(elapsed.total_seconds()))
        return result