package swtech.pageDesignControl.common.vo;

import lombok.Data;

@Data
public class ExcelParam {
    private String filePath;
    private int startRow;
    private int startCell;
    private int endRow;
    private int endCell;

    public ExcelParam(String filePath) {
        this.filePath = filePath;
    }

    public ExcelParam(String filePath, int startRow, int startCell, int endRow, int endCell) {
        this.filePath = filePath;
        this.startRow = startRow;
        this.startCell = startCell;
        this.endRow = endRow;
        this.endCell = endCell;
    }
}
