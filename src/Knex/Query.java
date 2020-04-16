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
        sql= statement.concat(" FROM "+tableName).substring(0, statement.length()-2);
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
