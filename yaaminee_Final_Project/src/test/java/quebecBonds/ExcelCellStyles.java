package quebecBonds;

import java.util.HashMap;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelCellStyles {
	
	XSSFWorkbook workbook;
	XSSFCellStyle cellStyle;
	HashMap<String,CellStyle> cellStyles = new HashMap<>();
	XSSFDataFormat format;
	
	ExcelCellStyles(XSSFWorkbook workbook) {
		this.workbook = workbook;
		createDifferentStyles();	
	}
	
	private void createDifferentStyles() {
	cellStyle = workbook.createCellStyle();	
	cellStyle.setAlignment(HorizontalAlignment.CENTER);
	cellStyle.setWrapText(true);
	cellStyle.setBorderTop(BorderStyle.DOUBLE);
	cellStyle.setBorderLeft(BorderStyle.DOUBLE);
	cellStyle.setBorderRight(BorderStyle.DOUBLE);
	cellStyle.setBorderBottom(BorderStyle.DOUBLE);
	cellStyles.put("Double line border",cellStyle);
	
	cellStyle = workbook.createCellStyle();	
	format = workbook.createDataFormat();
	cellStyle.setAlignment(HorizontalAlignment.CENTER);
	cellStyle.setWrapText(true);
	cellStyle.setBorderTop(BorderStyle.DOUBLE);
	cellStyle.setBorderLeft(BorderStyle.DOUBLE);
	cellStyle.setBorderRight(BorderStyle.DOUBLE);
	cellStyle.setBorderBottom(BorderStyle.DOUBLE);
	cellStyle.setDataFormat(format.getFormat("#,##0.00000"));
	cellStyles.put("Double line border & Decimal format",cellStyle);
	
	//Setup the font to bold and double line border
	cellStyle = workbook.createCellStyle();	
	XSSFFont font = workbook.createFont();
	font.setBold(true);
	cellStyle.setFont(font);
	cellStyle.setAlignment(HorizontalAlignment.CENTER);
	cellStyle.setWrapText(true);
	cellStyle.setBorderTop(BorderStyle.DOUBLE);
	cellStyle.setBorderLeft(BorderStyle.DOUBLE);
	cellStyle.setBorderRight(BorderStyle.DOUBLE);
	cellStyle.setBorderBottom(BorderStyle.DOUBLE);
	cellStyles.put("Double line border & Bold font", cellStyle);
	
	cellStyle = workbook.createCellStyle();	
	format = workbook.createDataFormat();
	cellStyle.setDataFormat(format.getFormat("#,##0.00"));
	cellStyle.setAlignment(HorizontalAlignment.CENTER);
	cellStyle.setWrapText(true);
	cellStyle.setBorderTop(BorderStyle.DOUBLE);
	cellStyle.setBorderLeft(BorderStyle.DOUBLE);
	cellStyle.setBorderRight(BorderStyle.DOUBLE);
	cellStyle.setBorderBottom(BorderStyle.DOUBLE);
	cellStyles.put("Double line border & Decimal 2 digits",cellStyle);
	
	}
	
	public  CellStyle getCellStyle (String style){
		CellStyle cs = cellStyles.get(style);
		return cs;
	}
}
