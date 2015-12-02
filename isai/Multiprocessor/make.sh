#! /bin/sh

# This file creates the pdf of the latex file
# Need help? Mail: isai.barajas@oracle.com
# - Isai
  
# ---------------------------------------------------
# Make file

# Normal File
FILE=MPP_Report.tex

# User added input?
if [ -t 0 ] && [ $# -ne 0 ]; then
  echo "Making PDF from input: ${1}"
  pdflatex -interaction=batchmode ${1}
else
  echo "Making PDF from file: ${FILE}"
  pdflatex -interaction=batchmode ${FILE}
fi
