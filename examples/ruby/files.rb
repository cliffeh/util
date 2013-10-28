#!/usr/bin/ruby
# -*- coding: utf-8 -*-

print Dir.entries "."
print Dir["*.rb"]
File.read("files.rb")
# FileUtils.cp("files.rb", "/tmp/files.rb")

File.open("/tmp/testfil", "a") do |f|
  f << "this is a test message"
end

print File.mtime("/tmp/testfil").hour

