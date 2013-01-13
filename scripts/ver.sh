#!/bin/bash
#
# ver.sh - a simple script for determining whether a given directory is under
#          version control and, if so, the repository type and location
#
usage="usage: $0 dir1 [dir2 [...]]"

[ -z "$1" ] && (echo $usage; exit 1)

while [ ! -z "$1" ] ; do
    if [ -d "$1" ] ; then
	str=""
	if [ -d "$1/.git" ] ; then   #git
	    str="git `grep url $1/.git/config | awk -F '=' '{print $2}'`"
	elif [ -d "$1/.svn" ] ; then # Subversion
	    str="svn `svn info $1 | grep URL | awk '{print $2}'`"
	else # not version controlled
	    str="not version controlled"
	fi
	echo "$1 $str"
    else
	echo "warning: '$1' is not a directory" 1>&2
    fi

    # grab the next arg
    shift
done
