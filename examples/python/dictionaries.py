#!/usr/bin/python

'''a simple Python script demonstrating dictionary manipulation'''

# empty dictionary
d = {}

# initialized dictionary
d = {'key1':0, 'key2':10}

# set values
d['key1'] = 200

# iterate through dictionary
for key in d:
    print key, '-->', d[key]
