; simple MapReduce example in Scheme
(define (m x) 
  (if (null? x)
      x
      (if (list? x)
	  (cons (m (car x)) (m (cdr x)))
	  (cons x 1))))

(define (r x)
  (if (null? x)
      x
      (if (list? x)
	  (if (null? (cdr x))
	      x
	      (if (string=? (caar x) (caadr x))
		  (r (cons
		      (cons (caar x) (+ (cdar x) (cdadr x))) (cddr x)))
		  (cons (car x) (r (cdr x)))))
	  x)))

; execute
(r (sort (m '("f" "a" "u" "z" "a" "b" "c" "c" "c" "d")) (lambda (x y) (string<? (car x) (car y)))))
