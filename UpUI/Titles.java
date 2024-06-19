package UpUI;

public class Titles {
    public int num;
    public String QuestionTypes;
    public String Titles;
    public String Scores;
    public String A;
    public String B;
    public String C;
    public String D;
    public String jiexi;
    public String daan;

    public Titles() {
    }

    public Titles(String QuestionTypes, String Titles, String Scores, String A, String B, String C, String D, String jiexi) {
        this.QuestionTypes = QuestionTypes;
        this.Titles = Titles;
        this.Scores = Scores;
        this.A = A;
        this.B = B;
        this.C = C;
        this.D = D;
        this.jiexi = jiexi;
    }

    public Titles(String QuestionTypes, String Titles, String Scores, String A, String B, String C, String D, String jiexi, String daan) {
        this.QuestionTypes = QuestionTypes;
        this.Titles = Titles;
        this.Scores = Scores;
        this.A = A;
        this.B = B;
        this.C = C;
        this.D = D;
        this.jiexi = jiexi;
        this.daan = daan;
    }

    public Titles(int num, String QuestionTypes, String Titles, String Scores, String A, String B, String C, String D, String jiexi, String daan) {
        this.num = num;
        this.QuestionTypes = QuestionTypes;
        this.Titles = Titles;
        this.Scores = Scores;
        this.A = A;
        this.B = B;
        this.C = C;
        this.D = D;
        this.jiexi = jiexi;
        this.daan = daan;
    }

    /**
     * 获取
     * @return QuestionTypes
     */
    public String getQuestionTypes() {
        return QuestionTypes;
    }

    /**
     * 设置
     * @param QuestionTypes
     */
    public void setQuestionTypes(String QuestionTypes) {
        this.QuestionTypes = QuestionTypes;
    }

    /**
     * 获取
     * @return Titles
     */
    public String getTitles() {
        return Titles;
    }

    /**
     * 设置
     * @param Titles
     */
    public void setTitles(String Titles) {
        this.Titles = Titles;
    }

    /**
     * 获取
     * @return Scores
     */
    public String getScores() {
        return Scores;
    }

    /**
     * 设置
     * @param Scores
     */
    public void setScores(String Scores) {
        this.Scores = Scores;
    }

    /**
     * 获取
     * @return A
     */
    public String getA() {
        return A;
    }

    /**
     * 设置
     * @param A
     */
    public void setA(String A) {
        this.A = A;
    }

    /**
     * 获取
     * @return B
     */
    public String getB() {
        return B;
    }

    /**
     * 设置
     * @param B
     */
    public void setB(String B) {
        this.B = B;
    }

    /**
     * 获取
     * @return C
     */
    public String getC() {
        return C;
    }

    /**
     * 设置
     * @param C
     */
    public void setC(String C) {
        this.C = C;
    }

    /**
     * 获取
     * @return D
     */
    public String getD() {
        return D;
    }

    /**
     * 设置
     * @param D
     */
    public void setD(String D) {
        this.D = D;
    }

    /**
     * 获取
     * @return jiexi
     */
    public String getJiexi() {
        return jiexi;
    }

    /**
     * 设置
     * @param jiexi
     */
    public void setJiexi(String jiexi) {
        this.jiexi = jiexi;
    }

    public String toString() {
        return "TItles{QuestionTypes = " + QuestionTypes + ", Titles = " + Titles + ", Scores = " + Scores + ", A = " + A + ", B = " + B + ", C = " + C + ", D = " + D + ", jiexi = " + jiexi + "}";
    }

    /**
     * 获取
     * @return daan
     */
    public String getDaan() {
        return daan;
    }

    /**
     * 设置
     * @param daan
     */
    public void setDaan(String daan) {
        this.daan = daan;
    }

    /**
     * 获取
     * @return num
     */
    public int getNum() {
        return num;
    }

    /**
     * 设置
     * @param num
     */
    public void setNum(int num) {
        this.num = num;
    }
}