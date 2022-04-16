package com.wx.study.designpattern.template;

import java.io.FileInputStream;
import java.util.List;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 21/12/09
 */
public class First {


//    public <T> List<T> query(String sql, RowMapper<T> rowMapper) throws DataAccessException {
//        return query(sql, new RowMapperResultSetExtractor<T>(rowMapper));
//    }
//
//    public <T> T query(final String sql, final ResultSetExtractor<T> rse) throws DataAccessException {
//        Assert.notNull(sql, "SQL must not be null");
//        Assert.notNull(rse, "ResultSetExtractor must not be null");
//        if (logger.isDebugEnabled()) {
//            logger.debug("Executing SQL query [" + sql + "]");
//        }
//
//        return execute(new QueryStatementCallback());
//    }
//
//    class QueryStatementCallback implements StatementCallback<T>, SqlProvider {
//        public T doInStatement(Statement stmt) throws SQLException {
//            ResultSet rs = null;
//            try {
//                rs = stmt.executeQuery(sql);
//                ResultSet rsToUse = rs;
//                if (nativeJdbcExtractor != null) {
//                    rsToUse = nativeJdbcExtractor.getNativeResultSet(rs);
//                }
//                return rse.extractData(rsToUse);
//            }
//            finally {
//                JdbcUtils.closeResultSet(rs);
//            }
//        }
//        @Override
//        public String getSql() {
//            return sql;
//        }
//    }
//
//    public <T> T execute(StatementCallback<T> action) throws DataAccessException {
//        Assert.notNull(action, "Callback object must not be null");
//
//        Connection con = DataSourceUtils.getConnection(getDataSource());
//        Statement stmt = null;
//        try {
//            Connection conToUse = con;
//            if (this.nativeJdbcExtractor != null &&
//                    this.nativeJdbcExtractor.isNativeConnectionNecessaryForNativeStatements()) {
//                conToUse = this.nativeJdbcExtractor.getNativeConnection(con);
//            }
//            stmt = conToUse.createStatement();
//            applyStatementSettings(stmt);
//            Statement stmtToUse = stmt;
//            if (this.nativeJdbcExtractor != null) {
//                stmtToUse = this.nativeJdbcExtractor.getNativeStatement(stmt);
//            }
//            T result = action.doInStatement(stmtToUse);
//            handleWarnings(stmt);
//            return result;
//        }
//        catch (SQLException ex) {
//            // Release Connection early, to avoid potential connection pool deadlock
//            // in the case when the exception translator hasn't been initialized yet.
//            JdbcUtils.closeStatement(stmt);
//            stmt = null;
//            DataSourceUtils.releaseConnection(con, getDataSource());
//            con = null;
//            throw getExceptionTranslator().translate("StatementCallback", getSql(action), ex);
//        }
//        finally {
//            JdbcUtils.closeStatement(stmt);
//            DataSourceUtils.releaseConnection(con, getDataSource());
//        }
//    }
}
