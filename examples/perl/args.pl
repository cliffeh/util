#!/usr/bin/perl

if($#ARGV < 0) {
    print stderr "usage: args.pl arg1 [arg2 [...]]\n";
    exit 1;
}

foreach $arg(@ARGV) {
    print $arg."\n";
}
