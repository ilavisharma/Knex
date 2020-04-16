package Knex;

public class Query {
    String sql;

    public Query(String table) {
        sql= " FROM "+table;
    }

    public Query select(String ...columns) {
        String statement= "SELECT ";
        for (int i=0; i<columns.length; i++) {
            if (i==(columns.length-1)) {
                statement= statement.concat(columns[i]);
            } else {
                statement= statement.concat(columns[i]+", ");
            }
        }
        sql= statement.concat(sql);
        return this;
    }

    public Query where(String columnName, String value) {
        String whereClause= " WHERE "+columnName+"="+"'"+value+"'";
        sql= sql.concat(whereClause);
        return this;
    }

    public String getSql() {
        return sql;
    }
}
