#!/usr/bin/ruby

class BlogEntry
  attr_accessor :title, :time, :fulltext, :mood
  def initialize( title, mood, fulltext)
    @time = Time.now
    @title, @mood, @fulltext = title, mood, fulltext
  end
end

entry = BlogEntry.new
# entry.title = "this is the title"
# entry.time = Time.now
# entry.mood = :sick
# entry.fulltext = "I can't believe Mt. Hood was stolen! I am speechless! It was stolen by a giraffe who drove away in his Cadillac Seville very nonchalant!!"

entry2 = BlogEntry.new("I Left my Hoodie on the Mountain!", 
                       :confused, 
                       "I am never going back to that mountain and I hope a giraffe steals it.")

blog = [entry, entry2]
blog.sort_by { |entry| entry.time }.reverse 

