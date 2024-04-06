package org.compiladores;

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
        return "Token{" +
                "tokenType=" + tokenType +
                ", lexeme='" + lexeme + '\'' +
                "line " + line +
                "column " + column +
                '}';
    }
}

