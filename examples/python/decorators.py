#!/usr/bin/env python

def foo(fn):
    def wrapper():
        print "before foo"
        fn()
        print "after foo"
    return wrapper

def bar(fn):
    def wrapper():
        print "before bar"
        fn()
        print "after bar"
    return wrapper

@foo
@bar
def my_function():
    print "my function"

my_function()
