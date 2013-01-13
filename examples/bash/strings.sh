#!/bin/bash
#
# strings.sh - various bash string operations
#

str='abc123'

# string length
echo ${#str}

# regexp matching (matches up to the 1; returns length of matching string)
echo `expr "$str" : '[a-c]*1'`

# index of first matching character
echo `expr index "$str" 2b`

# substrings by index/length - ${string[:position[:length]]}
echo ${str:2:3}

# substrings by regexp (from front) - expr "$string" : '\($substring\)'
echo `expr "$str" : '\(...\)'`

# substrings by regexp (from back) - expr "$string" : '.*\($substring\)'
echo `expr "$str" : '.*\(...\)'`

# strip shortest substring from front - ${string#substring}
echo ${str#ab}

# strip longest substring from front - ${string##substring}
echo ${str#ab}

# string shortest substring from back - ${string%substring}
# string longest substring from back - ${string%%substring}
