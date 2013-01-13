#!/bin/bash
#
# getopts.sh - demonstrates using getopts for command-line args
#
usage="usage: $0 [-u user] [-f file] [-v]"

while getopts ":u:d:f:v" options; do
    case $options in
        u ) user=$OPTARG;;
        d ) dir=$OPTARG;;
        f ) file=$OPTARG;;
        v ) verbose='true';;
        h ) echo $usage;;
        * ) echo $usage
            exit 1;;
    esac
done
