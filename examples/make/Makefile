# build directory
BUILDDIR=

# directories to create
MKDIRS=$(BUILDDIR)

# object files
OBJ=

# generated executables
EXE=

# create necessary directories
$(MKDIRS):
	mkdir -p $@

# remove "tilde files" left behind by emacs, et al
emacs-clean:
	find . -name '*~' -exec rm {} \;

# remove crufty object files and build directories
clean:
	rm -f $(OBJ)
	rm -rf $(BUILDDIR)

# remove everything but the sources
veryclean: clean
	rm -f $(EXE)
