hw = MPP_Report

tex = $(shell find . -name "*.tex" -print)

all: $(hw).pdf

$(hw).pdf: $(tex)
	pdflatex $(hw).tex
	pdflatex $(hw).tex

clean:
	rm -f *.aux *.log *.out *.bbl *.blg


