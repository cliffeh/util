#!/usr/bin/env python

''' compare.py - compares two files, outputing the differences between them '''

import sys

if len(sys.argv) < 3:
    print "usage:", sys.argv[0], "file1 file2"
    sys.exit(1)

file1 = open(sys.argv[1], "r")
file2 = open(sys.argv[2], "r")

list1 = {}
list2 = {}

for line in file1.readlines():
    list1[line] = line

for line in file2.readlines():
    list2[line] = line

for line in list1:
    if line not in list2:
        print sys.argv[1], ":", line,

for line in list2:
    if line not in list1:
        print sys.argv[2], ":", line,
