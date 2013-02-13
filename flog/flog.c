#include "flog.h"

static FILE *flog_out, *flog_err;
static const char *flog_prefix;
static int flog_level;

void flog_init(FILE *out, FILE *err, const char *prefix, int level /*, int opts */)
{
  flog_out = out ? out : stdout;
  flog_err = err ? err : stderr;
  flog_prefix = prefix ? prefix : "";
  flog_level = (level >= 0) ? level : F_LOG_DEFAULT;
}

void flog(int level, const char *fmt, ...)
{
  if(level & flog_level) {
    /* use our error stream for errors, naturally */
    FILE *out = (level & F_LOG_ANY_ERROR) ? flog_err : flog_out;

    /* output our prefix and log level */
    fprintf(out, "%s: ", flog_prefix);
    fprintf(out, "%s: ", flog_level_to_str(level));

    /* output the actual error message */
    va_list args;
    va_start (args, fmt);
    vfprintf(out, fmt, args);
    va_end(args);

    /* we're responsible for appending the newline */
    fputc('\n', out);
  }
}

const char *flog_level_to_str(int level)
{
  if(level&F_FATAL)   return "fatal";
  if(level&F_ERROR)   return "error";
  if(level&F_WARN)    return "warn";
  if(level&F_INFO)    return "info";
  if(level&F_VERBOSE) return "verbose";
  if(level&F_DEBUG)   return "debug";

  /* this probably shouldn't happen, but we've got to return something */
  return "unknown";
}

void flog_cleanup()
{
  fclose(flog_out);
  fclose(flog_err);
}
