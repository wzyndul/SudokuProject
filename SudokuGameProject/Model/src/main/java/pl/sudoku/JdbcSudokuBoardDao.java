package pl.sudoku;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.sudoku.exception.DaoException;

public class JdbcSudokuBoardDao implements Dao<SudokuBoard>, AutoCloseable {
    private final Logger log = LoggerFactory.getLogger(JdbcSudokuBoardDao.class);
    private Connection conn;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JdbcSudokuBoardDao() throws DaoException {
        String dbUrl = "jdbc:derby:myDB;create=true";
        try {
            conn = DriverManager.getConnection(dbUrl);
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        try (Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE boards ("
                    + "ID_BOARD INT NOT NULL GENERATED ALWAYS AS IDENTITY, "
                    + "BOARD_NAME VARCHAR(100) NOT NULL UNIQUE, "
                    + "CONSTRAINT PK_boards PRIMARY KEY (ID_BOARD))");
        } catch (SQLException e) {
            if (e.getSQLState().equals("X0Y32")) {
                log.info("taka tabela juz istnieje");
            } else {
                throw new RuntimeException(e);
            }
        }
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE fields ("
                    + "ID_BOARD INT NOT NULL, "
                    + "FOREIGN KEY (ID_BOARD) REFERENCES boards (ID_BOARD), "
                    + "x INT NOT NULL, "
                    + "y INT NOT NULL, "
                    + "val INT NOT NULL)");

        } catch (SQLException e) {
            if (e.getSQLState().equals("X0Y32")) {
                log.info("taka tabela juz istnieje");
            } else {
                throw new RuntimeException(e);
            }
        }
    }


    @Override
    public SudokuBoard read() throws DaoException {
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(new String(
                     "SELECT x, y, val FROM fields f JOIN boards b ON"
                             + " f.ID_BOARD = b.ID_BOARD WHERE BOARD_NAME = '" + name + "'"))) {
            SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
            while (rs.next()) {
                sudokuBoard.set(rs.getInt("x"),
                        rs.getInt("y"), rs.getInt("val"));
            }
            return sudokuBoard;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void write(SudokuBoard obj) throws DaoException {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(new String("INSERT INTO boards(BOARD_NAME) VALUES ('" + name + "')"),
                    Statement.RETURN_GENERATED_KEYS);
            int id = -1;
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                while (rs.next()) {
                    id = rs.getInt(1);
                }
                for (int row = 0; row < 9; row++) {
                    for (int column = 0; column < 9; column++) {
                        stmt.execute(new String(
                                "INSERT INTO fields(ID_BOARD,x,y,val) "
                                        + "VALUES (" + id + ", " + row + ", " + column + ", "
                                        + obj.get(row, column) + ")"));
                    }
                }
                conn.commit();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            //23505 record with the same unqique value exists
            if (e.getSQLState().equals("23505")) {
                throw new RuntimeException(e);

            }
            try {
                conn.rollback();
            } catch (SQLException e1) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void close() throws Exception {
        try {
            conn.commit();
            conn.close();
            DriverManager.getConnection("jdbc:derby:myDB;shutdown=true");
        } catch (SQLException e) {
            //08006 is code for successful shutdown
            if (!e.getSQLState().equals("08006")) {
                throw new RuntimeException(e);
            }

        }
    }

    public void metodaTestowa() {
        try {
            Statement stmt;
            stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("select * from " + "boards");
            ResultSetMetaData rsmd = results.getMetaData();
            int numberCols = rsmd.getColumnCount();
            for (int i = 1; i <= numberCols; i++) {
                //print Column Names
                System.out.print(rsmd.getColumnLabel(i) + "\t\t");
            }

            System.out.println("\n-------------------------------------------------");

            while (results.next()) {
                int id = results.getInt(1);
                String boardName = results.getString(2);
                System.out.println(id + "\t\t\t\t" + boardName);
            }
            results.close();
            stmt.close();
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
    }

    public void metodaTestowa2() {
        try {
            Statement stmt;
            stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("select * from " + "boards");
            ResultSetMetaData rsmd = results.getMetaData();
            int numberCols = rsmd.getColumnCount();
            for (int i = 1; i <= numberCols; i++) {
                //print Column Names
                System.out.print(rsmd.getColumnLabel(i) + "\t\t");
            }

            System.out.println("\n-------------------------------------------------");

            while (results.next()) {
                int id = results.getInt(1);
                String boardName = results.getString(2);
                System.out.println(id + "\t\t\t\t" + boardName);
            }
            results.close();
            stmt.close();
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
    }


    public ArrayList<String> getBoardsNames() {
        ArrayList<String> names = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
            ResultSet results = stmt.executeQuery("select BOARD_NAME from " + "boards");

            while (results.next()) {
                names.add(results.getString("BOARD_NAME"));
            }
            results.close();
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
        return names;
    }
}



