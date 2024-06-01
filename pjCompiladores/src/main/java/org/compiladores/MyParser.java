package org.compiladores;

import java_cup.runtime.*;

import java.util.Stack;

public class MyParser extends java_cup.runtime.lr_parser {
    public MyParser(Scanner s) {
        super(s);
    }

    @Override
    public short[][] production_table() {
        return new short[0][];
    }

    @Override
    public short[][] action_table() {
        return new short[0][];
    }

    @Override
    public short[][] reduce_table() {
        return new short[0][];
    }

    @Override
    public int start_state() {
        return 0;
    }

    @Override
    public int start_production() {
        return 0;
    }

    @Override
    public int EOF_sym() {
        return 0;
    }

    @Override
    public int error_sym() {
        return 0;
    }

    @Override
    public Symbol do_action(int i, lr_parser lrParser, Stack stack, int i1) throws Exception {
        return null;
    }

    @Override
    protected void init_actions() throws Exception {

    }

    @Override
    public void report_error(String message, Object info) {
        report_syntax_error(message, info);
    }

    @Override
    public void report_fatal_error(String message, Object info) {
        report_syntax_error(message, info);
        throw new RuntimeException(message);
    }

    @Override
    public void syntax_error(Symbol cur_token) {
        String message = "Syntax error";
        report_syntax_error(message, cur_token);
    }

    private void report_syntax_error(String message, Object info) {
        if (info instanceof Symbol) {
            Symbol symbol = (Symbol) info;
            int line = symbol.left;
            int column = symbol.right;
            System.err.printf("Error sintáctico en línea %d, columna %d: %s%n", line, column, message);
        } else {
            System.err.println(message);
        }
    }
}
