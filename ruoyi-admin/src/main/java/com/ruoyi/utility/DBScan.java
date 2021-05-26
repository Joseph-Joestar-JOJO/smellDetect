package com.ruoyi.utility;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class DBScan {
    private double         eps;
    private int            minPts;
    private HashMap<String, Double>	cosine;
    private int            threshold;
    private List<DataNode> dataNodes;
    private int            delta;
    
    public DBScan(HashMap<String, Double> cosine)
    {
        this.eps = 0.02;

        this.minPts = 1;
        this.threshold = 10000;
        this.cosine = cosine;
        this.delta = 0;
    }

    public void setThreshold(int threshold)
    {
        this.threshold = threshold;
    }

    public int getThreshold()
    {
        return threshold;
    }

    public double getEps()
    {
        return eps;
    }

    public int getMinPts()
    {
        return minPts;
    }

    public List<DataNode> getNeighbors(DataNode p, List<DataNode> nodes)
    {
        List<DataNode> neighbors = new ArrayList<DataNode>();
        Double countDistance = 0.0;
        for (DataNode node : nodes)
        {
        	if (p == node) {
				continue;
			}
        	countDistance = cosine.get(p.name+" "+node.name);
            if (countDistance <= eps)
            {
                neighbors.add(node);
            }
        }
        return neighbors;
    }

    public List<DataNode> cluster(List<DataNode> nodes)
    {
        int category = 1;
        for (DataNode node : nodes)
        {
            if (!node.getVisitLabel())
            {
                node.setVisitLabel(true);
                List<DataNode> neighbors = getNeighbors(node, nodes);
                if (neighbors.size() < minPts)
                {
                    node.setCatagory(-1);
                }
                else
                {
                    node.setCatagory(category);
                    expandCluster(neighbors, category, nodes);
                }
            }
            category ++;
        }
        return nodes;
    }

    public void expandCluster(List<DataNode> neighbors, int category, List<DataNode> nodes)
    {
        for (DataNode node : neighbors)
        {
            if (!node.getVisitLabel())
            {
                node.setVisitLabel(true);
                List<DataNode> newNeighbors = getNeighbors(node, nodes);
                if (newNeighbors.size() >= minPts)
                {
                    expandCluster(newNeighbors, category, nodes);
                }
            }
            if (node.getCategory() <= 0)   // not be any of category
            {
                node.setCatagory(category);
            }
        }
    }

    public void showCluster(List<DataNode> nodes)
    {
        for (DataNode node : nodes)
        {
            System.out.println(node.name);
            System.out.println("������� "+ node.getCategory());
        }
    }

    public void addDataNode(DataNode node)
    {
        dataNodes.add(node);
        delta ++;
    }

    public void analysis()
    {
        if (delta >= threshold)
        {
            showCluster(cluster(dataNodes));
            delta = 0;
        }
    }

//    public static void main(String[] args) {
//		DBScan dbScan = new DBScan();
//		dbScan.cluster(dbScan.dataNodes);
//		dbScan.showCluster(dbScan.dataNodes);
//	}
}