package swtech.pageDesignControl.common.utils;


import com.alibaba.fastjson.JSON;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import swtech.pageDesignControl.common.vo.ExcelParam;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletResponse;

/**
 * excel表格导入
 * yjx
 */

public class ExcelUtil {
    //读取excel
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

    //读取excel兼容xls 和xlsx


    /**
     * 导出excel表格
     * yjx
     */
       /* ------------------------start----------------------*/

    //显示的导出表的标题
    private String title;
    //导出表的列名
    private String[] rowName ;

    private List<Object[]>  dataList = new ArrayList<Object[]>();

    HttpServletResponse  response;

    //构造方法，传入要导出的数据
    public ExcelUtil(String title,String[] rowName,List<Object[]>  dataList){
        this.dataList = dataList;
        this.rowName = rowName;
        this.title = title;
    }

    /*
     * 导出数据
     * */
    public void export(HSSFWorkbook workbook,HSSFSheet sheet,int x) throws Exception{
        try{

            // 产生表格标题行
            HSSFRow rowm = sheet.createRow(0);
            HSSFCell cellTiltle = rowm.createCell(0);

            rowm.setHeight((short) (25 * 35)); //设置高度

            //sheet样式定义【getColumnTopStyle()/getStyle()均为自定义方法 - 在下面  - 可扩展】
            HSSFCellStyle columnTopStyle = this.getColumnTopStyle(workbook);//获取列头样式对象
            HSSFCellStyle style = this.getStyle(workbook);                    //单元格样式对象

            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, (rowName.length-1)));
            cellTiltle.setCellStyle(columnTopStyle);
            cellTiltle.setCellValue(title);

            // 定义所需列数
            int columnNum = rowName.length;
            HSSFRow rowRowName = sheet.createRow(1);                // 在索引2的位置创建行(最顶端的行开始的第二行)

            rowRowName.setHeight((short) (25 * 25)); //设置高度

            // 将列头设置到sheet的单元格中
            for(int n=0;n<columnNum;n++){
                HSSFCell  cellRowName = rowRowName.createCell(n);                //创建列头对应个数的单元格
                cellRowName.setCellType(HSSFCell.CELL_TYPE_STRING);                //设置列头单元格的数据类型
                HSSFRichTextString text = new HSSFRichTextString(rowName[n]);
                cellRowName.setCellValue(text);                                    //设置列头单元格的值
                cellRowName.setCellStyle(columnTopStyle);                        //设置列头单元格样式
            }

            //将查询出的数据设置到sheet对应的单元格中
            for(int i=0;i<dataList.size();i++){

                Object[] obj = dataList.get(i);//遍历每个对象
                HSSFRow row = sheet.createRow(i+2);//创建所需的行数

                row.setHeight((short) (25 * 20)); //设置高度

                for(int j=0; j<obj.length; j++){
                    HSSFCell  cell = null;   //设置单元格的数据类型
                    if(j == 0){
                        cell = row.createCell(j,HSSFCell.CELL_TYPE_NUMERIC);
                        cell.setCellValue(i+1);
                    }else{
                        cell = row.createCell(j,HSSFCell.CELL_TYPE_STRING);
                        if(!"".equals(obj[j]) && obj[j] != null){
                            cell.setCellValue(obj[j].toString());                        //设置单元格的值
                        }
                    }
                    cell.setCellStyle(style);                                    //设置单元格样式
                }
            }
            //让列宽随着导出的列长自动适应
            for (int colNum = 0; colNum < columnNum; colNum++) {
                int columnWidth = sheet.getColumnWidth(colNum) / 256;
                for (int rowNum = 0; rowNum < sheet.getLastRowNum(); rowNum++) {
                    HSSFRow currentRow;
                    //当前行未被使用过
                    if (sheet.getRow(rowNum) == null) {
                        currentRow = sheet.createRow(rowNum);
                    } else {
                        currentRow = sheet.getRow(rowNum);
                    }
                    if (currentRow.getCell(colNum) != null) {
                        HSSFCell currentCell = currentRow.getCell(colNum);
                        if (currentCell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                            int length = currentCell.getStringCellValue().getBytes().length;
                            if (columnWidth < length) {
                                columnWidth = length;
                            }
                        }
                    }
                }
                if(colNum == 0){
                    sheet.setColumnWidth(colNum, (columnWidth-2) * 128);
                }else{
                    sheet.setColumnWidth(colNum, (columnWidth+4) * 256);
                }
            }

            if(workbook !=null&&x==1){
                try
                {
//                    String fileName = "Excel-" + String.valueOf(System.currentTimeMillis()).substring(4, 13) + ".xls";
//                    String headStr = "attachment; filename=\"" + fileName + "\"";
//                    response = getResponse();
//                    response.setContentType("APPLICATION/OCTET-STREAM");
//                    response.setHeader("Content-Disposition", headStr);
//                    OutputStream out = response.getOutputStream();
//                    workbook.write(out);
//                    FileOutputStream out = new FileOutputStream("/Users/wangjun/temp/" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()).toString() +".xls");
                    FileOutputStream out = new FileOutputStream("E:/data/" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()).toString() +".xls");
                    workbook.write(out);
                    out.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }

    }

    /*
     * 列头单元格样式
     */
    public HSSFCellStyle getColumnTopStyle(HSSFWorkbook workbook) {

        // 设置字体
        HSSFFont font = workbook.createFont();
        //设置字体大小
        font.setFontHeightInPoints((short)11);
//        //字体加粗
//        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        //设置字体名字
        font.setFontName("Courier New");
        //设置样式;
        HSSFCellStyle style = workbook.createCellStyle();
        //设置底边框;
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        //设置底边框颜色;
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        //设置左边框;
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        //设置左边框颜色;
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        //设置右边框;
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        //设置右边框颜色;
        style.setRightBorderColor(HSSFColor.BLACK.index);
        //设置顶边框;
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        //设置顶边框颜色;
        style.setTopBorderColor(HSSFColor.BLACK.index);
        //在样式用应用设置的字体;
        style.setFont(font);
        //设置自动换行;
        style.setWrapText(false);
        //设置水平对齐的样式为居中对齐;
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        //设置单元格背景颜色
        style.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        return style;

    }

    /*
     * 列数据信息单元格样式
     */
    public HSSFCellStyle getStyle(HSSFWorkbook workbook) {
        // 设置字体
        HSSFFont font = workbook.createFont();
        //设置字体大小
        //font.setFontHeightInPoints((short)10);
        //字体加粗
        //font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        //设置字体名字
        font.setFontName("Courier New");
        //设置样式;
        HSSFCellStyle style = workbook.createCellStyle();
        //设置底边框;
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        //设置底边框颜色;
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        //设置左边框;
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        //设置左边框颜色;
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        //设置右边框;
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        //设置右边框颜色;
        style.setRightBorderColor(HSSFColor.BLACK.index);
        //设置顶边框;
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        //设置顶边框颜色;
        style.setTopBorderColor(HSSFColor.BLACK.index);
        //在样式用应用设置的字体;
        style.setFont(font);
        //设置自动换行;
        style.setWrapText(false);
        //设置水平对齐的样式为居中对齐;
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        return style;
    }

    /* ------------------------end----------------------*/




    public static void main(String[] args) throws Exception {
//        excel表格读取
        ExcelParam ec=new ExcelParam("E://data/20191114155701.xls",1,1,2,3);
        Object datas[][]=ExcelUtil.reas(ec);
        System.out.println(JSON.toJSONString(datas));
        for(Object[] rowData:datas){
            for(Object cellData:rowData){
                System.out.println(cellData);
            }
            System.out.println("\n");
        }

//        //excel表格导出 测试
//        HSSFWorkbook workbook = new HSSFWorkbook();                        // 创建工作簿对象
//        String title = "武则天登基大典演讲稿";
//        for (int x=0;x<2;x++) {
//            HSSFSheet sheet = workbook.createSheet(title+x);                     // 创建工作表
//            HashMap<String, Object> dataMap = new HashMap<String, Object>();
//            List<HashMap<String, Object>> listMap = new ArrayList<HashMap<String, Object>>();
//            for (int i = 0; i < 30; i++) {
//                dataMap.put("datetime", new Date());
//                dataMap.put("person", "武则天"+i);
//                dataMap.put("type", "文本");
//                dataMap.put("content", "今天朕登基！今天朕登基！今天朕登基！今天朕登基！今天朕登基！今天朕登基！");
//                listMap.add(dataMap);
//            }
//
//            String[] rowsName = new String[]{"序号", "时间", "发言人", "类型", "消息"};
//            List<Object[]> dataList = new ArrayList<Object[]>();
//            Object[] objs = null;
//            for (int i = 0; i < listMap.size(); i++) {
//                HashMap<String, Object> data = listMap.get(i);
//                objs = new Object[rowsName.length];
//                objs[0] = i;
//                objs[1] = data.get("datetime");
//                objs[2] = data.get("person");
//                objs[3] = data.get("type");
//                objs[4] = data.get("content");
//                dataList.add(objs);
//            }
//
//            ExcelUtil ex = new ExcelUtil(title, rowsName, dataList);
//            ex.export(workbook,sheet,x);
//        }

    }

}
