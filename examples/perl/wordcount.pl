#!/usr/bin/perl -w

use strict;

my %h = ();


while(<>)
{
    chomp;
    
    my @pieces = split('\s+', $_);
    foreach my $piece(@pieces) {
        if($h{$piece}) {
            $h{$piece} = $h{$piece}+1;
        } else {
            $h{$piece} = 1;
        }
    }
}

foreach my $key (sort keys %h) {
    print "$h{$key} $key\n";
}

# while ((my $key, my $value) = each(%h)){
#     print $key."=>".$value."\n";
# }
