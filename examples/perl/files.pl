#!/usr/bin/perl

# open each command-line argument file and echo it line-by-line
foreach $filename (@ARGV) {
    open(fh, "<$filename");
    while(<fh>){
	print $_;
    }
}
