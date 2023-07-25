package req;

import java.util.Vector;

/**
 * @author Xiang Weng
 */
public class TableDTO {
    private Vector<Vector<Object>> data;
    private int totalCount;

    public void setData(Vector<Vector<Object>> data) {
        this.data = data;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public Vector<Vector<Object>> getData() {
        return data;
    }

    public int getTotalCount() {
        return totalCount;
    }
}
