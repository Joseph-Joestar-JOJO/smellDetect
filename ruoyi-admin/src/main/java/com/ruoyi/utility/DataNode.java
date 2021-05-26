package com.ruoyi.utility;

public class DataNode {
    private boolean isVisited;
    private int category;
    public String name;

    public DataNode(String name)
    {
        this.isVisited = false;
        this.category = 0;
        this.name = name;
    }

    public void setVisitLabel(boolean isVisited)
    {
        this.isVisited = isVisited;
    }

    public void setCatagory(int category)
    {
        this.category = category;
    }

    public boolean getVisitLabel()
    {
        return isVisited;
    }

    public int getCategory()
    {
        return category;
    }
    
}