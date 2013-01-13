#!/usr/bin/perl

# regex.pl - demonstrates some simple Perl regular expressions

# pulls IP addresses from stdin
#   note: not 100% correct; will take *any* digits separated by dots...
while(<>){
    $_ =~ m/(\d+\.\d+\.\d+\.\d+)/;
    if($1){
	print "IP: ".$1."\n";
    }
}
