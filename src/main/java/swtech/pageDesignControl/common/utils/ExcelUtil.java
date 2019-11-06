package swtech.pageDesignControl.common.utils;

import com.alibaba.fastjson.JSON;
import swtech.pageDesignControl.common.vo.ExcelParam;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import java.io.File;

/**
 * excel表格导入
 * yjx
 */
public class ExcelUtil {
    public static Object[][] reas(ExcelParam excelparam){
        String filePath=excelparam.getFilePath();
        int endRow=excelparam.getEndRow();
        int endCell =excelparam.getEndCell();
        int startCell=excelparam.getStartCell();
        int startRow=excelparam.getStartRow();
        Object datas[][]=new Object[endRow-startRow+1][endCell-startCell+1];
        try {
            Workbook workbook =WorkbookFactory.create(new File(filePath));
            Sheet sheet=	workbook.getSheetAt(0);
            for(int i=startRow;i<=endRow;i++){
                Row row=sheet.getRow(i-1);
                for(int j=startCell;j<=endCell;j++){
                    Cell cell=row.getCell(j-1,MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    cell.setCellType(CellType.STRING);
                    String cellValue=cell.getStringCellValue();
                    datas[i-startRow][j-startCell]=cellValue;
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return datas;
    }
    public static void main(String[] args) {
        ExcelParam ec=new ExcelParam("src/main/resources/file/test.xlsx",1,1,2,3);
        Object datas[][]=ExcelUtil.reas(ec);
        System.out.println(JSON.toJSONString(datas));

        for(Object[] rowData:datas){
            for(Object cellData:rowData){
                System.out.println(cellData);
            }
            System.out.println("\n");
        }

    }

}
