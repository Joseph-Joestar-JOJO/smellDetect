package com.ruoyi.metrics;

import java.util.List;
public class finalSegmentModel {
    //存放block块的类型，是SynS、DFC、E_SWIFT中的哪一�?
    private segmentTypeEnum type;
    //存放块中各statement对象
    private List  statements;
    //存放提取的静态信�?
    private String words;

    public finalSegmentModel(segmentTypeEnum type, List statements) {
        this.type = type;
        this.statements = statements;
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

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }
}
