package PaooGame.utils;

import PaooGame.Tiles.LevelManager;
import PaooGame.entities.HealthBar;
import PaooGame.entities.Player;
import PaooGame.entities.Score;

import java.io.File;
import java.sql.*;
import java.util.logging.Level;

import static PaooGame.Game.levelManager;

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
                System.out.println("Baza de date creata");
            } else {
                System.out.println("Baza de date existenta");
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
                " CAMERAPOS INT, " +
                " XCAMERAPOS INT," +
                " CLIPPERS INT," +
                " SUPERPAW INT ," +
                " ENTITIES_TABLE1 INT," +
                " ENTITIES_TABLE2 INT, " +
                " FINAL_SCORE INT )";

        stmt.execute(sql);
        stmt.close();
    }

    private static void initElements() throws SQLException {
        Statement stmt = c.createStatement();
        String sql = "INSERT INTO " + TABLE_NAME + " (ID, XPOS,YPOS,SCORE,HEALTH,LEVEL,CAMERAPOS,XCAMERAPOS,CLIPPERS,SUPERPAW, ENTITIES_TABLE1, ENTITIES_TABLE2, FINAL_SCORE) " +
                "VALUES (1,90,450,0,3,1,0,0,0,0,1,1,0)";
        stmt.executeUpdate(sql);
        stmt.close();
        System.out.println("Baza de date initializata");
    }

    public static void CloseConnection() {
        try {
            if (c!= null)
                c.close();
            System.out.println("Baza de date inchisa");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void SaveGameState(Player p) {
        try (PreparedStatement pstmt = c.prepareStatement("UPDATE " + TABLE_NAME +
                " set XPOS =?, YPOS =?, SCORE =?, HEALTH =?, LEVEL=?, CAMERAPOS=?, XCAMERAPOS=?, CLIPPERS=?, SUPERPAW=?, ENTITIES_TABLE1=?, ENTITIES_TABLE2=?, FINAL_SCORE=? where ID=1")) {
            pstmt.setInt(1, (int) p.getHitBox().x);
            pstmt.setInt(2, (int) p.getHitBox().y);
            pstmt.setInt(3, (int) Score.current_score);
            pstmt.setInt(4, (int) Player.getInstance().health.lifeCount);
            pstmt.setInt(5, LevelManager.level);
            pstmt.setInt(6, Camera.xCamera);
            pstmt.setInt(7, Camera.xCameraPos);
            pstmt.setInt(8, (LevelManager.clippers.collected == true ? 1 : 0));
            pstmt.setInt(9, (LevelManager.superpaw.collected == true ? 1 : 0));
            pstmt.setInt(10, LevelManager.entities_spawn_table[0]);
            pstmt.setInt(11, LevelManager.entities_spawn_table[1]);
            pstmt.setInt(12, (int) Score.finalScore);

            pstmt.executeUpdate();
            System.out.println("Game Saved");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void LoadGame(Player p) {
        try (Statement stmt = c.createStatement(); ResultSet rs = stmt.executeQuery("SELECT * FROM " + TABLE_NAME)) {
            while (rs.next()) {
                int xpos = rs.getInt("XPOS");
                int ypos = rs.getInt("YPOS");
                int current_score = rs.getInt("SCORE");
                int health = rs.getInt("HEALTH");
                int lvl = rs.getInt("LEVEL");
                int xCam = rs.getInt("CAMERAPOS");
                int xCamPos = rs.getInt("XCAMERAPOS");
                boolean clippersPicked = rs.getInt("CLIPPERS") == 1 ? true : false;
                boolean superPawPicked = rs.getInt("SUPERPAW") == 1 ? true : false;
                int ent1 = rs.getInt("ENTITIES_TABLE1");
                int ent2 = rs.getInt("ENTITIES_TABLE2");
                int final_score = rs.getInt("FINAL_SCORE");

                LevelManager.level = lvl;
                LevelManager.loadedFromSave = true;
                LevelManager.set_entities_table(ent1, ent2);
                levelManager.initALevel();
                p.LoadFromSave((float) xpos, (float) ypos, health, current_score, xCam, xCamPos, clippersPicked, superPawPicked, final_score);
                LevelManager.loadedFromSave = false;
            }
            System.out.println("Game Loaded");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
}