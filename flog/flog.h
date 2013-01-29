#ifndef _FLOG_H
#define _FLOG_H 1
#include <stdarg.h>
#include <stdio.h>

/* flags:
 *   the lower the log level, the more important the message
 *   the higher the log level, the more we log
 */
/* error flags */
#define F_FATAL   (1<<1)
#define F_ERROR   (1<<2)
#define F_WARN    (1<<3)

/* logging flags */
#define F_INFO    (1<<4)
#define F_VERBOSE (1<<5)
#define F_DEBUG   (1<<6)

/* combined flags for convenience */
#define F_LOG_SILENT    0
#define F_LOG_QUIET     (F_FATAL|F_ERROR) // only output serious errors
#define F_LOG_ANY_ERROR (F_FATAL|F_ERROR|F_WARN)
#define F_LOG_INFORM    (F_FATAL|F_ERROR|F_WARN|F_INFO)
#define F_LOG_VERBOSE   (F_FATAL|F_ERROR|F_WARN|F_INFO|F_VERBOSE)
#define F_LOG_ALL       (F_FATAL|F_ERROR|F_WARN|F_INFO|F_VERBOSE|F_DEBUG)
#define F_LOG_DEFAULT   F_LOG_ANY_ERROR

/* 
 * initialize flog
 *
 * some sane defaults:
 *   out=stdin 
 *   err=stderr
 *   prefix=""
 *   level=F_ANY_ERROR
 */
void flog_init(FILE *out, FILE *err, const char *prefix, int level /*, int opts */);

void flog(int level, const char *fmt, ...);

/* returns a string representing the most severe log level seen */
const char *flog_level_to_str(int level);

void flog_cleanup();

/* convenience macros */
#define flog_fatal(fmt, ...)   flog(F_FATAL,   fmt, ##__VA_ARGS__)
#define flog_error(fmt, ...)   flog(F_ERROR,   fmt, ##__VA_ARGS__)
#define flog_warn(fmt, ...)    flog(F_WARN,    fmt, ##__VA_ARGS__)
#define flog_info(fmt, ...)    flog(F_INFO,    fmt, ##__VA_ARGS__)
#define flog_verbose(fmt, ...) flog(F_VERBOSE, fmt, ##__VA_ARGS__)
#define flog_debug(fmt, ...)   flog(F_DEBUG,   fmt, ##__VA_ARGS__)

#endif
