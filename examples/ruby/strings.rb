#!/usr/bin/ruby
# -*- coding: undecided -*-

"Cliffy"
"Cliffy".reverse # => "yffilC"
"Cliffy".length  # => 6
"Cliffy" * 5     # => "CliffyCliffyCliffyCliffyCliffy"

# to_s (to-string method)
40.to_s.reverse  # => 04

line = "cliff: http://thingly.net"
name, url = line.split(': ')
url = url.strip # strip leading/trailing whitespace

