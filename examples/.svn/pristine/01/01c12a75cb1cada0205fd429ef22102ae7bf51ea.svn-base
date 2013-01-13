#!/usr/bin/python

'''some Python file manipulation examples'''

import os

# open for write access ('a' would be append)
file = open('testfil', 'w')

# write something
file.write('this is a test\n')

# close it
file.close()

# open it for reading
file = open('testfil', 'r')

# read from it, printing out each line; note that the newline remains
# and print() appends a newline, as well
for line in file.readlines():
    print line

# close it
file.close()

# remove it; could also use: os.remove('testfil')
os.unlink('testfil')
