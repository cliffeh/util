#!/usr/bin/python

'''a simple Python script demonstrating list manipulation'''

# initialize a list (note multiple types)
l = ['spam', 'eggs', 100, 1234, 'abc']

# iterate over the list
for el in l:
    print el

# find the list's length
print len(l)

# iterate over the first two elements
for el in l[0:2]:
    print el

# iterate over the last three elements; note that the
# second arg is the position before which to stop
for el in l[1:3]:
    print el

