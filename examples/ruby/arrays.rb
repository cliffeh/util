#!/usr/bin/ruby

# array subtraction
cities  = %w[ London
              Oslo
              Paris
              Amsterdam
              Berlin ]
visited = %w[Berlin Oslo]
 
puts "I still need " +
     "to visit the " +
     "following cities:",
     cities - visited

[12, 47, 35].max      # => 47

ticket = [12, 47, 35]
ticket.sort!          # => [12, 35, 47], and ticket var is now sorted

poem = "My toast has flown from my hand
And my toast has gone to the moon.
But when I saw it on television,
Planting our flag on Halley's comet,
More still did I want to eat it.
"
print poem

poem['toast'] = 'honeydew'
print poem # only replaces first 'toast' with 'honeydew'!

print poem.lines.to_a.reverse.join
