const fs = require('fs');
const path = require('path');

// Instructions for converting HTML to DOCX
console.log(`
=== Banking System Project Report ===

The HTML report has been generated successfully!

To convert this HTML file to DOCX format, you have several options:

OPTION 1 - Using Microsoft Word:
1. Open Microsoft Word
2. Go to File > Open
3. Select "Banking_System_Project_Report.html"
4. Word will automatically convert the HTML
5. Save as DOCX format (File > Save As > Word Document)

OPTION 2 - Using Google Docs:
1. Open Google Docs (docs.google.com)
2. Go to File > Open > Upload
3. Upload the HTML file
4. Google Docs will convert it automatically
5. Download as DOCX (File > Download > Microsoft Word)

OPTION 3 - Using LibreOffice Writer:
1. Open LibreOffice Writer
2. Go to File > Open
3. Select the HTML file
4. Save as DOCX format

OPTION 4 - Online Converters:
- Use online HTML to DOCX converters
- Upload the HTML file and download DOCX

The HTML file contains:
✓ Complete project documentation
✓ Technical specifications
✓ Implementation details
✓ System architecture
✓ Testing information
✓ Future enhancements
✓ Professional formatting
✓ Tables and structured content

File location: Banking_System_Project_Report.html
`);

// Check if the HTML file exists
if (fs.existsSync('Banking_System_Project_Report.html')) {
    console.log('\n✅ HTML report file created successfully!');
    console.log('📄 File: Banking_System_Project_Report.html');
    console.log('📊 Ready for conversion to DOCX format');
} else {
    console.log('\n❌ Error: HTML file not found');
}