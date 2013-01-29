#!/usr/bin/perl

# create a hash table
my %h = ( "apples"=>1 , "oranges"=>2, "pears"=>3, "bananas"=>4 );

# print a dereferenced value
print "apples=>".$h{"apples"}."\n";
print "---------\n";

# iterate through the hash table
while (($key, $value) = each(%h)){
    print $key."=>".$value."\n";
}
print "---------\n";

# iterate through sorted hash table
foreach $key (sort keys %h) {
    print $key."=>".$h{$key}."\n";
}
