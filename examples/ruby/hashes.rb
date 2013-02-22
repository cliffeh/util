#!/usr/bin/ruby
# -*- coding: utf-8 -*-

# create a hash
books = {} # or Hash.new(0)

# :splendid is a symbol
books["Gravity's Rainbow"] = :splendid

puts books.keys
puts books.values

books.keys.each { |title| puts title }
books.each { |title, symbol| print title, " => ", symbol, "\n"}
