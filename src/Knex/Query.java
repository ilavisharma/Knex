package Knex;

public class Query {
    String sql, tableName;

    public Query(String table) {
        this.tableName= table;
        sql= "";
    }

    public Query select(String ...columns) {
        String statement= "SELECT ";
        for (String column : columns) {
            statement = statement.concat(column + ", ");
        }
        statement= removeLastComma(statement).concat(" FROM "+tableName);
        sql= sql.concat(statement);
        return this;
    }

    public Query where(String condition) {

        sql= sql.concat(" WHERE "+condition);
        return this;
    }

    public Query update(String[] columnNames, Object[] values) throws KnexException {
        String updateClause= "UPDATE "+tableName+" SET ";

        if (columnNames.length != values.length) throw new KnexException("Length of the arguments do not match");

        for (int i=0; i<columnNames.length; i++) {

            String column= columnNames[i];
            Object value= values[i];

            if (value instanceof String) {
//                value is string
                updateClause= updateClause.concat(column+" = '"+value+"', ");

            } else if (value instanceof Integer) {
//                value is integer
                updateClause= updateClause.concat(column+" = "+value+", ");

            } else if (value instanceof Float || value instanceof Double) {
//                value is float or double
                updateClause= updateClause.concat(column+" = "+value+", ");

            } else {
                throw new KnexException("The datatype "+value.getClass().getSimpleName()+" is not supported");
            }
        }
        sql= sql.concat(removeLastComma(updateClause));

        return this;
    }

    public Query insert(String[] columnNames, Object[] ...rowValues) throws KnexException {
        String insertClause= "INSERT INTO "+tableName+ " (";
        for (String name: columnNames) {
            insertClause= insertClause.concat(name+", ");
        }
        insertClause= removeLastComma(insertClause).concat(") VALUES");

        for (Object[] row: rowValues) {
            insertClause= insertClause.concat(" (");
            for (Object value: row) {
                insertClause = getString(insertClause, value);
            }
            insertClause= removeLastComma(insertClause).concat("),");
        }
        insertClause= removeLastComma(insertClause).concat(")");
        sql= sql.concat(insertClause);

        return this;
    }

    public Query delete() {
        String deleteClause= "DELETE FROM "+tableName;
        sql= sql.concat(deleteClause);
        return this;
    }

    public Query orderByDesc(String column) {
        String orderClause= " ORDER BY "+column+ " DESC";
        sql= sql.concat(orderClause);
        return this;
    }

    public Query orderByAsc(String column) {
        String orderClause= " ORDER BY "+column+ " ASC";
        sql= sql.concat(orderClause);
        return this;
    }

    public Query groupBy(String column) throws KnexException {
        if (sql.contains("ORDER BY"))
            throw new KnexException("orderBy should come at last");
        String groupClause= " GROUP BY "+column;
        sql= sql.concat(groupClause);
        return this;
    }

//    SOME HELPERS
    public String getRawSql() {
        return sql;
    }

    private String getString(String insertClause, Object value) throws KnexException {
        if (value instanceof String) {
            insertClause= insertClause.concat("'"+value+"', ");
        } else if (value instanceof Integer) {
            insertClause= insertClause.concat(value+", ");
        } else if (value instanceof Float || value instanceof Double) {
            insertClause= insertClause.concat(value+", ");
        } else {
            throw new KnexException("The datatype "+value.getClass().getSimpleName()+" is not supported");
        }
        return insertClause;
    }

    private String removeLastComma(String string) {
        return string.substring(0, string.length()-2);
    }
}
