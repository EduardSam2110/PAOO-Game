package PaooGame.utils;

import PaooGame.Tiles.LevelManager;
import PaooGame.entities.HealthBar;
import PaooGame.entities.Player;
import PaooGame.entities.Score;

import java.io.File;
import java.sql.*;

public class LoadSave {
    private static Connection c;

    // Use constants for database file name and table name
    private static final String DATABASE_FILE = "savegame.db";
    private static final String TABLE_NAME = "LOADSAVE";

    public static void InitDataBase() {
        try {
            File file = new File(DATABASE_FILE);
            boolean fileExists = file.exists();

            // Conectare la baza de date
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + DATABASE_FILE);
            c.setAutoCommit(true);

            if (!fileExists) {
                // daca nu exista, o creez
                createTable();
                initElements();
                System.out.println("Database created");
            } else {
                System.out.println("Database exists");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static void createTable() throws SQLException {
        Statement stmt = c.createStatement();
        String sql = "CREATE TABLE " + TABLE_NAME + " " +
                "( ID INT PRIMARY KEY NOT NULL, " +
                " XPOS INT NOT NULL, " +
                " YPOS INT NOT NULL, " +
                " SCORE INT, " +
                " HEALTH INT, " +
                " LEVEL INT NOT NULL," +
                " CAMERAPOS INT )";
        stmt.execute(sql);
        stmt.close();
    }

    private static void initElements() throws SQLException {
        Statement stmt = c.createStatement();
        String sql = "INSERT INTO " + TABLE_NAME + " (ID, XPOS,YPOS,SCORE,HEALTH,LEVEL,CAMERAPOS) " +
                "VALUES (1,90,450,0,3,1,0)";
        stmt.executeUpdate(sql);
        stmt.close();
        System.out.println("Records created successfully");
    }

    public static void CloseConnection() {
        try {
            if (c!= null)
                c.close();
            System.out.println("Database closed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void SaveGameState(Player p) {
        try (PreparedStatement pstmt = c.prepareStatement("UPDATE " + TABLE_NAME + " set XPOS =?, YPOS =?, SCORE =?, HEALTH =?, LEVEL=?, CAMERAPOS=? where ID=1")) {
            pstmt.setInt(1, (int) p.getHitBox().x);
            pstmt.setInt(2, (int) p.getHitBox().y);
            pstmt.setInt(3, (int) Score.current_score);
            pstmt.setInt(4, HealthBar.health);
            pstmt.setInt(5, LevelManager.level);
            pstmt.setInt(6, Camera.xCamera);
            pstmt.executeUpdate();
            System.out.println("Updated successfully");
            CloseConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void LoadGame(Player p) {
        try (Statement stmt = c.createStatement(); ResultSet rs = stmt.executeQuery("SELECT * FROM " + TABLE_NAME)) {
            while (rs.next()) {
                int xpos = rs.getInt("XPOS");
                int ypos = rs.getInt("YPOS");
                int score = rs.getInt("SCORE");
                int health = rs.getInt("HEALTH");
                int lvl = rs.getInt("LEVEL");
                int xCam = rs.getInt("CAMERAPOS");

                p.LoadFromSave((float) xpos, (float) ypos, health, score, lvl, xCam);
            }
            System.out.println("Operation done successfully");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
}