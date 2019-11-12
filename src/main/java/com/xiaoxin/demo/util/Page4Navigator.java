package com.xiaoxin.demo.util;

import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author fuqiangxin
 * @version 1.0
 * @ClassName Page4Navigator--页面导航器
 * @createDate 2019/11/12 08:49
 */
public class Page4Navigator<T> {
    private Page<T> pageFromJPA;//页面jpa
    private int navigatePages;//导航栏总数量
    private int totalPages;//总页数
    private int number;//页数
    private long totalElements;
    private int size;//每页显示数量
    private int numberOfElements;
    private List<T> content;
    private boolean isHasContent;
    private boolean first;//页面
    private boolean last;//尾页
    private boolean isHasNext;//是否下一页
    private boolean isHasPrevious;//是否上一页
    private int[] navigatepageNums;

    //这个空的分页是为了 Redis 从 json格式转换为 Page4Navigator 对象而专门提供的
    public Page4Navigator() {
    }

    public Page4Navigator(Page<T> pageFromJPA, int navigatePages) {
        this.pageFromJPA = pageFromJPA;
        this.navigatePages = navigatePages;
        totalPages = pageFromJPA.getTotalPages();
        number = pageFromJPA.getNumber();
        totalElements = pageFromJPA.getTotalElements();
        size = pageFromJPA.getSize();
        numberOfElements = pageFromJPA.getNumberOfElements();
        content = pageFromJPA.getContent();
        isHasContent = pageFromJPA.hasContent();
        first = pageFromJPA.isFirst();
        last = pageFromJPA.isLast();
        isHasNext = pageFromJPA.hasNext();
        isHasPrevious = pageFromJPA.hasPrevious();
        calcNavigatepageNums();
    }

    public void calcNavigatepageNums() {
        int[] navigatepageNums;
        int totalPages = getTotalPages();
        int num = getNumber();
        //当总页数小于或等于导航数时
        if (totalPages <= navigatePages) {
            navigatepageNums = new int[totalPages];
            for (int i = 0; i < totalPages; i++) {
                navigatepageNums[i] = i + 1;
            }
        } else {//当总页数大于导航数时
            navigatepageNums = new int[navigatePages];
            int startNum = num - navigatePages / 2;
            int endNum = num + navigatePages / 2;
            if (startNum < 1) {//首页
                startNum = 1;//当页面小于1时即设置页面为首页
                for (int i = 0; i < navigatePages; i++) {
                    navigatepageNums[i] = startNum++;
                }
            } else if (endNum > totalPages) {//尾页
                endNum = totalPages;//当页面大于最大页数时设置页面为尾页
                for (int i = navigatePages - 1; i >= 0; i--) {
                    navigatepageNums[i] = endNum--;
                }
            } else {//中间页
                for (int i = 0; i < navigatePages; i++) {
                    navigatepageNums[i] = startNum++;
                }
            }
        }
        this.navigatepageNums = navigatepageNums;
    }

    public Page<T> getPageFromJPA() {
        return pageFromJPA;
    }

    public void setPageFromJPA(Page<T> pageFromJPA) {
        this.pageFromJPA = pageFromJPA;
    }

    public int getNavigatePages() {
        return navigatePages;
    }

    public void setNavigatePages(int navigatePages) {
        this.navigatePages = navigatePages;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public boolean isHasContent() {
        return isHasContent;
    }

    public void setHasContent(boolean hasContent) {
        isHasContent = hasContent;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public boolean isHasNext() {
        return isHasNext;
    }

    public void setHasNext(boolean hasNext) {
        isHasNext = hasNext;
    }

    public boolean isHasPrevious() {
        return isHasPrevious;
    }

    public void setHasPrevious(boolean hasPrevious) {
        isHasPrevious = hasPrevious;
    }

    public int[] getNavigatepageNums() {
        return navigatepageNums;
    }

    public void setNavigatepageNums(int[] navigatepageNums) {
        this.navigatepageNums = navigatepageNums;
    }
}
