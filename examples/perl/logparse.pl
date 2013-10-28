#!/usr/bin/perl -w

use strict;

# 2013/07/24 13:56:41 Changing gid back to original
while(<>)
{
    chomp;
    
    # YYYY/MM/DD HH:MM:SS message
    m/(\d\d\d\d)\/(\d\d)\/(\d\d)\s+(\d\d):(\d\d):(\d\d)\s+(.*)/;
    my $year = $1;
    my $month = $2;
    my $day = $3;

    my $hour = $4;
    my $minute = $5;
    my $second = $6;

    my $message = $7;

    print "YEAR: $1 ; MESSAGE: $7\n";
}
