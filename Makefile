all: README_no_toc.md
	pandoc -f gfm --toc -s README_no_toc.md -o README.md
	pandoc -f gfm --toc -s README_no_toc.md -o README.pdf
