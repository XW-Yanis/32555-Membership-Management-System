package req;

/**
 * @author Xiang Weng
 */
public class MemberRequest {
    private int currentPage;
    private int pageSize;
    private String searchKey;

    public int startIdx(){
        return (currentPage-1)*pageSize;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }
}
