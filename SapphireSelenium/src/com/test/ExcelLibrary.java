package com.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellReference;

public class ExcelLibrary {
	public int rowCount = 1;
	public String cellValue;

	public String getExcelData(String SheetName, int rowNum, int cellNum,
			String filePath) {
		Object retVal = null;
		try {
			FileInputStream fis = new FileInputStream(filePath);
			Workbook wb = WorkbookFactory.create(fis);
			Sheet s = wb.getSheet(SheetName);
			Row r = s.getRow(rowNum);
			Cell c = r.getCell(cellNum);
			retVal = getCellValue(c);// c.getCellType();c.getStringCellValue();
			rowCount = s.getLastRowNum();
		} // try
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		}
		return retVal != null ? retVal.toString() : null;
	}// getExcelData close

	public static void writeExcelData(String status, int LastRow,
			String writeFilePath) throws Exception {
		try {
			FileInputStream file = new FileInputStream(writeFilePath);
			HSSFWorkbook workbook = new HSSFWorkbook(file);
			HSSFSheet sheet = workbook.getSheetAt(0);
			Row row = sheet.getRow(LastRow);
			Cell cell2 = row.createCell(2);
			cell2.setCellValue(status);
			System.out.println(status);
			file.close();
			FileOutputStream outFile = new FileOutputStream(writeFilePath);
			workbook.write(outFile);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public void writeTestOutputExcelData(String methodName,
			String testStatus, String writeFilePath) throws Exception {
		try {
			FileInputStream file = new FileInputStream(writeFilePath);
			HSSFWorkbook workbook = new HSSFWorkbook(file);
			HSSFSheet sheet = workbook.getSheetAt(0);
			int LastRow = sheet.getLastRowNum();
			LastRow++;
			Row row = sheet.getRow(LastRow);
			Cell cellValue = row.createCell(0);
			cellValue.setCellValue(methodName);
			Cell cellValueES = row.createCell(1);
			cellValueES.setCellValue(testStatus);

			file.close();
			FileOutputStream outFile = new FileOutputStream(writeFilePath);
			workbook.write(outFile);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public void writeExcelEvaluation(int sheetNameW, int rowNumW, int cellNumW,
			String writeFilePath) throws Exception {
		try {
			FileInputStream file2 = new FileInputStream(writeFilePath);
			Workbook workbook2 = WorkbookFactory.create(file2);
			Sheet sheet = workbook2.getSheet("Sheet1");
			Row rowValue = sheet.getRow(rowNumW);
			Cell cellValue = rowValue.createCell(cellNumW);
			cellValue.setCellValue(sheetNameW);
			// System.out.println(sheetNameW);
			file2.close();
			FileOutputStream outFile2 = new FileOutputStream(writeFilePath);
			workbook2.write(outFile2);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public double readExcelEvaluation(String SheetNameEvl,
			String cellReferenceEvl, String filePathEvl) throws Exception {
		FileInputStream fisReadExl = new FileInputStream(filePathEvl);
		Workbook wbReadExl = WorkbookFactory.create(fisReadExl); // or new
																	// XSSFWorkbook("c:/temp/test.xls")
		Sheet sheetReadExl = wbReadExl.getSheet(SheetNameEvl);
		FormulaEvaluator evaluatorReadExl = wbReadExl.getCreationHelper()
				.createFormulaEvaluator();
		// suppose your formula is in B3
		CellReference cellReference = new CellReference(cellReferenceEvl);
		Row rowReadExl = sheetReadExl.getRow(cellReference.getRow());
		Cell cellReadExl = rowReadExl.getCell(cellReference.getCol());
		CellValue cellValueReadExl = evaluatorReadExl.evaluate(cellReadExl);
		double DCellValueReadExl = new Double(cellValueReadExl.getNumberValue())
				.doubleValue();
		double newDCellValueReadExl = Math.round(DCellValueReadExl * 1000.0) / 1000.0;
		return newDCellValueReadExl;
	}

	public void evaluateAllExl(String SheetNameEvl, String cellReferenceEvl,
			String filePathEvl) throws Exception {
		FileInputStream fis = new FileInputStream(filePathEvl);
		Workbook wb = WorkbookFactory.create(fis); // or new
													// XSSFWorkbook("c:/temp/test.xls")
		Sheet sheet = wb.getSheet(SheetNameEvl);
		FormulaEvaluator evaluator = wb.getCreationHelper()
				.createFormulaEvaluator();
		// suppose your formula is in B3
		CellReference cellReference = new CellReference(cellReferenceEvl);
		Row row = sheet.getRow(cellReference.getRow());
		Cell cell = row.getCell(cellReference.getCol());
		for (int sheetNum = 0; sheetNum < wb.getNumberOfSheets(); sheetNum++) {
			Sheet sheetNo = wb.getSheetAt(sheetNum);
			for (Row r : sheetNo) {
				for (Cell c : r) {
					if (c.getCellType() == Cell.CELL_TYPE_FORMULA) {
						evaluator.evaluateFormulaCell(c);
						cell.setCellValue(evaluator.evaluateFormulaCell(c));
					}
				}
			}
		}
	}// evaluateAll close

	private Object getCellValue(Cell c) {
		switch (c.getCellType()) {
		case Cell.CELL_TYPE_NUMERIC:
			return c.getNumericCellValue();
		case Cell.CELL_TYPE_STRING:
			return c.getStringCellValue();

		}// switch close
		return null;
	}

	/*
	 * public void cellToString(HSSFCell cell) {
	 * 
	 * Cell cellValue = null; switch (cellValue.getCellType()) { case
	 * Cell.CELL_TYPE_BOOLEAN:
	 * System.out.println(cellValue.getBooleanCellValue()); break; case
	 * Cell.CELL_TYPE_NUMERIC:
	 * System.out.println(cellValue.getNumericCellValue()); break; case
	 * Cell.CELL_TYPE_STRING:
	 * System.out.println(cellValue.getStringCellValue()); break; case
	 * Cell.CELL_TYPE_BLANK: break; case Cell.CELL_TYPE_ERROR: break;
	 * 
	 * // CELL_TYPE_FORMULA will never happen case Cell.CELL_TYPE_FORMULA:
	 * break; } }
	 */
	public static void main(String arg[]) {
		ExcelLibrary lib = new ExcelLibrary();
		for (int i = 1; i <= lib.rowCount; i++) {

			System.out.println("1");
			String un = lib.getExcelData("loginlogout", i, 0,
					"SapphireFiles\\AutomationTestData.xls");
			System.out.println("2");
			String pw = lib.getExcelData("loginlogout", i, 1,
					"SapphireFiles\\AutomationTestData.xls");
			System.out.println("3");
			System.out.println(un);
			System.out.println(pw);

		}

	}
}
