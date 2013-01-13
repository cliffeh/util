; a simple program in Scheme that just performs a few operations

; 1 + 2 + 3 + 4
(+ 1 2 3 4)

; define functions
(define (square x) (* x x))
(define (double x) (+ x x))
(define (quadruple x) (double (double x)))

; call functions
(square 4)
(double 3)
(quadruple 2)

; Fibonacci numbers
(define (fib n)
  (if (< n 2)
      n
      (+ (fib (- n 1))
	 (fib (- n 2)))))

; find the 10th Fibonacci number
(fib 10)
