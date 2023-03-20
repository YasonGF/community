package top.tswinter.nowcoder_community.entity;


public class Page  {    // 分页

    private int current = 1;        // 当前页

    private int limit = 10;         // 显示上限

    private int rows = 0;           // 数据总数

    private String path;

    public int getOffset() {        // 当前页的起始行
        return (current-1)*limit;
    }

    public int getTotal() {         // 总页数
        if(rows%limit==0)   return rows/limit;
        else  return rows/limit + 1;
    }

    public int getFrom(){
        int from =  current-2;
        if(from < 1) from = 1;
        return from;
    }

    public int getTo(){
        int to =  current+2;
        int total = getTotal();
        if(to > total) to = total;
        return to;
    }




    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        if(current >= 1)
        this.current = current;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        if(limit >= 1 && limit <= 100)
            this.limit = limit;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        if(rows >= 0)
        this.rows = rows;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
