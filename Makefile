
md: README_no_toc.md
	pandoc -f gfm --toc -s README_no_toc.md -o README.md

all: md pdf

pdf: README_no_toc.md
	pandoc -f gfm --toc -s README_no_toc.md -o README.pdf
