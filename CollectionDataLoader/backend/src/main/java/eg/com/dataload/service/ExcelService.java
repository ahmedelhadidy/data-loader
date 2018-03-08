package eg.com.dataload.service;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import eg.com.dataload.model.Entity;
import eg.com.dataload.model.Entity.Colomn;
import eg.com.dataload.model.Entity.Parameters.Param;
import eg.com.dataload.model.ObjectFactory;
import eg.com.dataload.util.CommonUtil;
import eg.com.dataload.util.ExcelRowParser;

@Service()
public class ExcelService {

	private static final String SHEET_ENTITIY_SEC = "Entity";
	private static final String SHEET_PARAM_SEC = "Parameters";
	private static final String SHEET_COLOMN_SEC = "Column";
	private static final String SHEET_END_DEF_SEC = "End Defenetion";
	private static final int FIRST_ROW_NUM = 0;

	public Entity parseXlS(File xlsFile) throws Exception {

		XSSFSheet defenetionSheet = null;

		ObjectFactory entityFactory = new ObjectFactory();
		Entity entity = entityFactory.createEntity();

		try (FileInputStream stream = new FileInputStream(xlsFile) ; XSSFWorkbook myWorkBook = new XSSFWorkbook(stream) ) {
			defenetionSheet = myWorkBook.getSheet("Defenetion");
			int rowNum = FIRST_ROW_NUM;
			XSSFRow row = null;
			String section = null;
			while (true) {
				row = defenetionSheet.getRow(rowNum++);
				if (row == null)
					break;
				if (row.getRowNum() == FIRST_ROW_NUM) {
					ExcelRowParser.parseEntity(row, entity);
				} else {

					String firstCellValue =  ExcelRowParser.getCell(row,0) ;
					if (!CommonUtil.isEmptyString(firstCellValue)
							&& (firstCellValue.equals(SHEET_PARAM_SEC) || firstCellValue.equals(SHEET_COLOMN_SEC) || firstCellValue.equals(SHEET_END_DEF_SEC))) {
						section = firstCellValue;
						continue;
					}

					if (section.equals(SHEET_PARAM_SEC)) {
						Param param = entityFactory.createEntityParametersParam();
						ExcelRowParser.parseParameter(row, param);
						if (entity.getParameters() == null) {
							entity.setParameters(entityFactory.createEntityParameters());
						}
						entity.getParameters().getParam().add(param);
					} else if (section.equals(SHEET_COLOMN_SEC)) {
						boolean isMappingRow = CommonUtil.isEmptyString(ExcelRowParser.getCell(row, 0));
						if (!isMappingRow) {
							Colomn colomn = entityFactory.createEntityColomn();
							ExcelRowParser.parseColomn(row, colomn);
							colomn.setOrder(entity.getColomn().size()+1);
							
							entity.getColomn().add(colomn);
						} else {
							Colomn lastAddedCoomn = entity.getColomn().get(entity.getColomn().size() - 1);
							ExcelRowParser.parseColomnMapping(row, lastAddedCoomn);
						}
					} else if (section.equals(SHEET_END_DEF_SEC)) {
						break;
					}
				}
			}
		}

		return entity;
	}
	
	
	

}
