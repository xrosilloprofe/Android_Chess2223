package es.ieslavereda.android_chess_2223;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import com.diogonunes.jcolor.Attribute;
import androidx.appcompat.widget.AppCompatImageView;

import es.ieslavereda.android_chess_2223.pieces.Piece;

public class Cell extends AppCompatImageView {
    private Piece piece;
    private Board board;
    private int original;
    private int color;
    private Coordinate coordinate;

    public Cell(Context context, Coordinate coordinate, Board board) {
        super(context);
        this.board = board;
        this.coordinate = coordinate;
        this.piece = null;
        this.original = (
                (coordinate.getRow() - 1 + coordinate.getColumn() - 'A') % 2 == 0)
                ?
                R.color.cell_white
                :
                R.color.cell_black;
        this.color = original;
        DisplayMetrics displayMetrics = new DisplayMetrics();

        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int widh = displayMetrics.widthPixels;

        setMaxWidth(widh/10);
        setMinimumWidth(widh/10);
        setMaxHeight(widh/10);
        setMinimumHeight(widh/10);

        setPadding(0,0,0,0);
        setScaleType(ScaleType.FIT_CENTER);

        setAdjustViewBounds(true);
        updateCellView();
    }

    public Piece getPiece() {
        return piece;
    }
    public Board getBoard() {
        return board;
    }
    public int getColor() {
        return color;
    }
    public Coordinate getCoordinate() {
        return coordinate;
    }
    public void setPiece(Piece piece) {
        this.piece = piece;
        if(piece!=null) setImageResource(piece.getType().getShape());
        else setImageResource(0);
    }

    private void updateCellView() {
        setBackgroundColor(getResources().getColor(color,getContext().getTheme()));
    }

    public boolean isEmpty() {
        return (piece == null);
    }
    public void highlight() {
        if (isEmpty()) {

            if (original == R.color.cell_white)
                color = R.color.HIGHLIGHT_WHITE;
            else
                color = R.color.HIGHLIGHT_BLACK;

        } else {
            color = (original == R.color.cell_white)
                    ?
                    R.color.HIGHLIGHT_KILL_WHITE
                    :
                    R.color.HIGHLIGHT_KILL_BLACK;
        }

        updateCellView();
    }
    public void resetColor() {
        color = original;
        updateCellView();
    }


    public enum Color {
        WHITE_CELL(Attribute.BACK_COLOR(180, 180, 180)),
        BLACK_CELL(Attribute.BACK_COLOR(100, 100, 100)),
        HIGHLIGHT_KILL_WHITE(Attribute.BACK_COLOR(180, 0, 0)),
        HIGHLIGHT_KILL_BLACK(Attribute.BACK_COLOR(130, 0, 0)),
        HIGHLIGHT_WHITE(Attribute.BACK_COLOR(180, 180, 0)),
        HIGHLIGHT_BLACK(Attribute.BACK_COLOR(130, 130, 0));

        private Attribute attribute;

        Color(Attribute attribute) {
            this.attribute = attribute;
        }

        public Attribute getAttribute() {
            return attribute;
        }
    }

}