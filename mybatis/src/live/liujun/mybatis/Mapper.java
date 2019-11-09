package live.liujun.mybatis;

public class Mapper {
    private String resultType;
    private String sql;

    public Mapper() {
    }

    public Mapper(String resultType, String sql) {
        this.resultType = resultType;
        this.sql = sql;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    @Override
    public String toString() {
        return "mapper{" +
                "resultType='" + resultType + '\'' +
                ", sql='" + sql + '\'' +
                '}';
    }
}
