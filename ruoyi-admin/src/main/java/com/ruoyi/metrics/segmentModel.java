package com.ruoyi.metrics;

import java.util.List;
public class segmentModel implements Comparable<segmentModel> {

    //存放block块的类型，是SynS、DFC、E_SWIFT中的哪一�?
    private segmentTypeEnum type;
    //存放块中各statement对象
    private List<Integer> statements;

    public segmentModel(segmentTypeEnum type,List statements){
        this.type=type;
        this.statements=statements;
    }
    public segmentTypeEnum getType() {
        return type;
    }

    public void setType(segmentTypeEnum type) {
        this.type = type;
    }

    public List getStatements() {
        return statements;
    }

    public void setStatements(List statements) {
        this.statements = statements;
    }

    public int compareTo(segmentModel o) {
        return (Integer)(this.getStatements().get(0))-(Integer)(o.getStatements().get(0));
    }
}
