hw = MPP_Report
sections = sections

tex = $(shell find . -name "*.tex" -print)

all: $(hw).pdf

$(hw).pdf: $(tex)
	pdflatex $(hw).tex
	pdflatex $(hw).tex

clean:
	rm -f $(hw).pdf *toc *.aux *.log *.out *.bbl *.blg
	cd $(sections); rm -f *toc *.aux *.log *.out *.bbl *.blg


