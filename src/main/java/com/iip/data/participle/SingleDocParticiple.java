package com.iip.data.participle;

/**
 * @Author Junnor.G
 * @Date 2018/12/17 下午4:49
 * 对于单个文本的分词结果的保存，为了构建与 java fx tableview中table column的数据映射
 */
public class SingleDocParticiple {
    private int id;
    private String text;
    private String participleResult;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getParticipleResult() {
        return participleResult;
    }

    public void setParticipleResult(String participleResult) {
        this.participleResult = participleResult;
    }
}
