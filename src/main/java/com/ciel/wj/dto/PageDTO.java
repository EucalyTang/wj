package com.ciel.wj.dto;

import lombok.Data;

import java.util.List;

@Data
public class PageDTO<T> {

    private List<T> data;//页面数据
    private Integer page;//当前页面的码数
    private Integer endPage;//末尾页的码数！
    private List<Integer> pages;//显示出来的页码按钮的码数
    private Integer size;//一页里多少个数据

    /**
     * @param total 总的问题数
     * @param page          访问第几页的页码数
     * @param size          一页中多少行数据
     * @return 返回size*(pageNum-1)即offset，sql语句中的limit offset,sise：
     **/
    public int initPage(Integer total, Integer page, Integer size) {
        //防止恶意传入数据总数量
        if (total <= 0) {
            total = 0;
        }
        //防止恶意传入size
        if (size <= 0) {
            this.size = 1;
        } else {
            this.size = size;//完成size初始化
        }
        countEndPage(total, size);//完成endPage初始化
        //页面最小是1
        if (page < 1) {
            this.page = 1;
        } else {
            this.page = page;
        }
        //防止page大于endPage
        if (this.page > this.endPage) {
            this.page = this.endPage;
        }
        return this.size * (this.page - 1);//返回offset偏移量！
    }
    /**
     * @param total 总共的数据量
     * @param size          页面数据量
     *                      计算出末尾页码，最后的数据不够一页时也要多加一页
     **/
    private void countEndPage(Integer total, Integer size) {
        this.endPage = 0;
        if (total % size == 0) {
            this.endPage = total / size;
        } else {
            this.endPage = total / size + 1;
        }
    }
}
