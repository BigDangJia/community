package com.peng.community.entity;

public class Page {

    //当前页码
    private int current = 1;
    //显示每一页的上线条数量
    private int limit = 10;

    //数据总数(用户计算总页数)
    private int rows;

    //查询路径（用于分页连接）
    private String path;

    public int getCurrent() {
        return current;
    }

    //在使用分页的时候，当用户在设置数值的时候我们也需要进行对传入的参数等信息进行判断
    public void setCurrent(int current) {
        if(current>0){
            this.current = current;
        }else{
            this.current=1;
        }

    }

    public int getLimit() {
        return limit;
    }

    //对于分页的数据内容，我们也需要进行配置
    public void setLimit(int limit) {
        if(limit >=1&&limit<=100){
            this.limit = limit;
        }else {
            //如果范围操作规定，就默认设置为10
            this.limit = 10;
        }
    }

    public int getRows() {
        return rows;
    }

    //这个总的数值最小为0不能为负数
    public void setRows(int rows) {
        if(rows >=0){
            this.rows = rows;
        }
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 获取当前页的起始行，因为在使用limit关键字查询的时候，我们需要知道的就是这个，起始行和查询多少条，而不是当前页
     *
     * @return
     */
    public int getOffset(){
        //我们有一个公式就是当前页码-1 乘以 分一页多少行
        return (current-1)*limit;
    }

    /**
     * 获取总的页数
     * 我们可以使用你总行数除以每一页的数量，看看是否能除尽，如果可以直接除，除不尽就在+1
     * @return
     */
    public int getTotal(){
        int total=rows%limit== 0 ? rows/limit : rows/limit+1;
        return total;
    }

    //获取起始页码  我们需要控制展示的页码
    public int getFrom(){
        int from = current-2;
        return from <1 ? 1:from;
    }

    public int getTo(){
        int to = current+2;
        //获取出来总的页码数
        int total = getTotal();
        return to>total ? total : to;

    }




}
