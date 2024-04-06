package org.compiladores;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public final class Token {
    private TokenConstant tokenType;
    private String lexeme;
    private String line;
    private  String column;
    public Token(TokenConstant tokenType, String lexeme, String line, String column) {
        this.tokenType = tokenType ;
        this.lexeme = lexeme;
        this.line = line;
        this.column = column;
    }

    public TokenConstant getTokenType() {
        return tokenType;
    }

    public void setTokenType(TokenConstant tokenType) {
        this.tokenType = tokenType;
    }

    public String getLexeme() {
        return lexeme;
    }

    public void setLexeme(String lexeme) {
        this.lexeme = lexeme;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    @Override
    public String toString() {
       // saveToDatabase();
        return "Token{" +
                "tokenType=" + tokenType +
                ", lexeme='" + lexeme + '\'' +
                "line " + line +
                "column " + column +
                '}';
    }

    // Method to save Token toString() output to MSSQL database
    public void saveToDatabase() {
        String url = "jdbc:sqlserver://umgdb.crnhqurpxrtw.us-east-1.rds.amazonaws.com:1433;databaseName=compiladores;user=admin;password=Seguridad2024*;encrypt=true;trustServerCertificate=true;";
        String sql = "INSERT INTO Token (tokenType, lexeme) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tokenType.toString());
            stmt.setString(2, lexeme);
            stmt.executeUpdate();
            System.out.println("Token saved to database successfully.");
        } catch (SQLException e) {
            System.out.println("Error saving token to database: " + e.getMessage());
        }
    }
}

