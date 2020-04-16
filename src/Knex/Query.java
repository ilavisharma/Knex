package Knex;

import java.util.HashMap;

public class Query {
    String sql, tableName;

    public Query(String table) {
        this.tableName= table;
        sql= "";
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
        sql= statement.concat(" FROM "+tableName);
        return this;
    }

    public Query where(String columnName, String value) {
        String whereClause= " WHERE "+columnName+"="+"'"+value+"'";
        sql= sql.concat(whereClause);
        return this;
    }

    public Query where(String columnName, Integer value) {
        String whereClause= " WHERE "+columnName+"="+value;
        sql= sql.concat(whereClause);
        return this;
    }

    public Query update(HashMap<String, Object> newValues) throws KnexException {
        String updateClause= "UPDATE "+tableName+" SET ";

        for (String column: newValues.keySet()) {
            Object value= newValues.get(column);
            if (value instanceof String) {
//                value is string
                updateClause= updateClause.concat(column+" = '"+value+"', ");
            } else if (value instanceof Integer) {
//                value is integer
                updateClause= updateClause.concat(column+" = "+value+", ");
            } else if (value instanceof Float) {
//                value is float
                updateClause= updateClause.concat(column+" = "+value+", ");
            } else if (value instanceof Double) {
//                value is float
                updateClause= updateClause.concat(column+" = "+value+", ");
            } else {
                throw new KnexException("The datatype "+value.getClass().getSimpleName()+" is not supported");
            }
        }
        sql= sql.concat(updateClause.substring(0, updateClause.length()-2));

        return this;
    }


    public String getRawSql() {
        return sql;
    }
}
