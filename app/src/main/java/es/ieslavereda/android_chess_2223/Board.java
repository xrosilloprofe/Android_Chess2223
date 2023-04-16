package es.ieslavereda.android_chess_2223;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import es.ieslavereda.android_chess_2223.pieces.*;

import java.util.*;

public class Board extends TableLayout {

    public static final short BOARD_DIMENSION = 8;
    private Cell[][] cells;

    public Board(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        cells = new Cell[8][8];

        // Fila superior
        addTextViews();

        // Parte media
        TableRow row;
        for (int r = 1; r <= 8; r++) {
            row = new TableRow(getContext());
            row.addView(getTextView("" + r));

            for (int c = 'A'; c <= 'H'; c++) {
                Cell cell = new Cell(getContext(), new Coordinate((char) c, r), this);

                cells[r - 1][c - 'A'] = cell;
                row.addView(cell);
            }

            row.addView(getTextView("" + r));
            addView(row);
        }


        // Fila inferior
        addTextViews();

        startPieces();

    }

    private void addTextViews() {
        TableRow row;

        row = new TableRow(getContext());
        row.addView(getTextView(""));

        for (int i = 0; i < 8; i++)
            row.addView(getTextView(String.valueOf((char) ('A' + i))));

        row.addView(getTextView(""));

        addView(row);
    }

    private TextView getTextView(String text) {
        DisplayMetrics displayMetrics = new DisplayMetrics();

        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int widh = displayMetrics.widthPixels;

        TextView txtView = new TextView(getContext());

        txtView.setText(text);
        txtView.setTextColor(getResources().getColor(R.color.white, getContext().getTheme()));
        txtView.setBackgroundColor(getResources().getColor(R.color.black, getContext().getTheme()));
        txtView.setWidth(widh / 10);
        txtView.setHeight(widh / 10);
        txtView.setGravity(Gravity.CENTER);

        return txtView;
    }

    public Cell getCell(Coordinate coordinate) {
        if (coordinate.getRow() < 1 || coordinate.getRow() > 8)
            return null;
        if (coordinate.getColumn() < 'A' || coordinate.getColumn() > 'H')
            return null;

        return cells[coordinate.getRow() - 1][coordinate.getColumn() - 'A'];
    }

    public void highlight(List<Coordinate> coordinates) {
        for (Coordinate c : coordinates) {
            getCell(c).highlight();
        }
    }

    public void resetColors() {
        for (Cell[] row : cells)
            for (Cell c : row)
                c.resetColor();
    }

    public void startPieces() {
        new RookWhite(getCell(new Coordinate('A', 8)));
        new RookWhite(getCell(new Coordinate('H', 8)));
        new KnightWhite(getCell(new Coordinate('B', 8)));
        new KnightWhite(getCell(new Coordinate('G', 8)));
        new BishopWhite(getCell(new Coordinate('C', 8)));
        new BishopWhite(getCell(new Coordinate('F', 8)));
        new KingWhite(getCell(new Coordinate('D', 8)));
        new QueenWhite(getCell(new Coordinate('E', 8)));

        new RookBlack(getCell(new Coordinate('A', 1)));
        new RookBlack(getCell(new Coordinate('H', 1)));
        new KnightBlack(getCell(new Coordinate('B', 1)));
        new KnightBlack(getCell(new Coordinate('G', 1)));
        new BishopBlack(getCell(new Coordinate('C', 1)));
        new BishopBlack(getCell(new Coordinate('F', 1)));
        new KingBlack(getCell(new Coordinate('D', 1)));
        new QueenBlack(getCell(new Coordinate('E', 1)));


        for (int i = 0; i < 8; i++) {
            new PawnBlack(getCell(new Coordinate((char) ('A' + i), 2)));
            new PawnWhite(getCell(new Coordinate((char) ('A' + i), 7)));
        }


    }

    public void setCellListener(View.OnClickListener viewOnClickListener) {
        for (Cell[] cellsRow : cells)
            for (Cell c : cellsRow)
                c.setOnClickListener(viewOnClickListener);
    }

    @Override
    public String toString() {
        String output = "       A  B  C  D  E  F  G  H\n";
        for (int row = 0; row <= 7; row++) {
            output += "    " + (row + 1) + " ";
            for (int col = 0; col <= 7; col++)
                output += cells[row][col];
            output += " " + (row + 1) + "\n";
        }
        output += "        A  B  C  D  E  F  G  H\n";
        return output;
    }

}
