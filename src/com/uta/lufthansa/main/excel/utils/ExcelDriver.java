package com.uta.lufthansa.main.excel.utils;

public class ExcelDriver {
	// data.getExcelData(GlobalConstants.EXCEL_DATA_FILE, "Sheet1", 2, 1);

	public String getExcelData(String filePath, String sheetName, int rowNumber, int colNumber) throws IOException {

		XSSFWorkbook workbook = new XSSFWorkbook(filePath);
		System.out.println("SheetCount : " + workbook.getNumberOfSheets());

		XSSFSheet sheet = workbook.getSheet(sheetName);

		String retValue = sheet.getRow(rowNumber).getCell(colNumber).getStringCellValue();
		System.out.println(retValue);

		workbook.close();
		return retValue;

	}

	public void setExcelData(String filePath, String sheetName, int rowNumber, int colNumber, String value)
			throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook(filePath);
		System.out.println("SheetCount : " + workbook.getNumberOfSheets());

		XSSFSheet sheet = workbook.getSheet(sheetName);

		XSSFRow row = sheet.getRow(rowNumber);
		XSSFCell cell = row.getCell(colNumber, XSSFRow.RETURN_BLANK_AS_NULL);

		if (cell == null)
			cell = row.createCell(colNumber);
		cell.setCellValue(value);

		workbook.write(new FileOutputStream(filePath));
		workbook.close();

	}
}
