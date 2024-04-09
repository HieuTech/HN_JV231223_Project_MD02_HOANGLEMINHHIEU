package bussiness.entity;

public class ResultDetail {
    private int resultId;
    private byte indexQuestion, indexChoice;
    private boolean check;

    public ResultDetail() {
    }

    public ResultDetail(int resultId, byte indexQuestion, byte indexChoice, boolean check) {
        this.resultId = resultId;
        this.indexQuestion = indexQuestion;
        this.indexChoice = indexChoice;
        this.check = check;
    }

    public int getResultId() {
        return resultId;
    }

    public void setResultId(int resultId) {
        this.resultId = resultId;
    }

    public byte getIndexQuestion() {
        return indexQuestion;
    }

    public void setIndexQuestion(byte indexQuestion) {
        this.indexQuestion = indexQuestion;
    }

    public byte getIndexChoice() {
        return indexChoice;
    }

    public void setIndexChoice(byte indexChoice) {
        this.indexChoice = indexChoice;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}
