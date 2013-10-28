#!/bin/bash
#
# autosync.sh - a simple script that watches for changes in a directory and
#               automatically syncs them to another location
#
usage="usage: $0 src dest"

function fatal_error() {
    echo "$0: $@" 1>&2
    echo "$usage" 1>&2
    exit 1
}

[[ -n $1 ]] || fatal_error "not enough arguments"
src=$1
shift
[[ -n $1 ]] || fatal_error "not enough arguments"
dest=$1
shift
[[ -z $1 ]] || fatal_error "too many arguments"

while [[ true ]] ; do
    sleep 3
    echo rsync -avz $src $dest
done
