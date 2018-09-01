####Coursera Scala Functional programming Principles Course weekly assignments
####Coursera Scala Objects Weeke 3
###higher order Functions
###function currying
###Sample scala problems
    Parenthesis balancing
    Print Pascal triangle
    Count change
###Sample Java problems

### Implement with plain recursion
    Sum of numbers
    Sum of square of numbers
    Sum of Cube of Numbers
    Sum of Factorial of Numbers
        
### Implement with higher order functions and lambda
### Change the recursion for factorial to tail recursion using Tri Function  
### Tail Recursion demo :-      

	Head Recursion 

		factorial(5)
		5 * factorial(4)
		5 * 4 * factorial(3)
		5 * 4 * 3 * factorial(2)
		5 * 4 * 3 * 2 * factorial(1)
		5 * 4 * 3 * 2 * 1 * factorial(0)
		5 * 4 * 3 * 2 * 1 * 1
		5 * 4 * 3 * 2 * 1
		5 * 4 * 3 * 2
		5 * 4 * 6
		5 * 24
		120


	Tail Recursion 

		factorialTailRec(5,1)
		factorialTailRec(4,5)
		factorialTailRec(3,20)
		factorialTailRec(2,60)
		factorialTailRec(1,120)
		120