package es.ieslavereda.android_chess_2223.pieces;

import com.diogonunes.jcolor.Attribute;
import es.ieslavereda.android_chess_2223.Board;
import es.ieslavereda.android_chess_2223.Cell;
import es.ieslavereda.android_chess_2223.Coordinate;
import es.ieslavereda.android_chess_2223.R;

import static com.diogonunes.jcolor.Ansi.colorize;

import java.util.List;
import java.util.Set;

public abstract class Piece {

    private Type type;
    protected Cell cell;
    protected List<Coordinate> coordinates;

    public Piece(Type type, Cell cell) {
        this.cell = cell;
        this.type = type;
        putInYourPlace();
    }
    public void putInYourPlace(){
        cell.setPiece(this);
    }
    public Color getColor() {
        return type.color;
    }

    public Cell getCell() {
        return cell;
    }
    public Type getType() {
        return type;
    }

    protected void removePiece() {
        cell.setPiece(null);
        cell = null;
    }

    public abstract List<Coordinate> getNextMovements();

    public void check(Coordinate coordinate, List<Coordinate> coordinates) {
        Board board = getCell().getBoard();

        if (board.getCell(coordinate) != null)
            if (board.getCell(coordinate).isEmpty() ||
                    board.getCell(coordinate).getPiece().getColor() != getColor())
                coordinates.add(coordinate);
    }

    public boolean moveTo(Coordinate origin, Coordinate destiny) {
        Board board = getCell().getBoard();
        //Check whether cell exists or if it is a legal movement
        if (board.getCell(destiny) == null ||
                !board.getCell(origin).getPiece().getNextMovements().contains(destiny))
            return false;

        // If the destination cell contains a piece, it'll be deleted and passed to the deleted pieces
        if (!board.getCell(destiny).isEmpty()) {
            board.getCell(destiny).getPiece().removePiece();
        }

        // Finally, we move the piece by removing it from the old position and positioning to the new one
        getCell().setPiece(null);
        Cell cell = board.getCell(destiny);
        cell.setPiece(this);
        this.cell = cell;
        return true;

    }


    public enum Color {
        BLACK(Attribute.TEXT_COLOR(71, 60, 51)),
        WHITE(Attribute.TEXT_COLOR(254, 200, 104));
        private final Attribute pieceColor;

        Color(Attribute pieceColor) {
            this.pieceColor = pieceColor;
        }

        public Attribute getPieceColor() {
            return pieceColor;
        }

        public Color next() {
            if (this.equals(WHITE))
                return BLACK;
            return WHITE;
        }
    }

    public enum Type {
        BLACK_KING(R.mipmap.ic_n_king_foreground,Color.BLACK),
        BLACK_QUEEN(R.mipmap.ic_n_queen_foreground,Color.BLACK),
        BLACK_ROOK(R.mipmap.ic_n_rook_foreground,Color.BLACK),
        BLACK_BISHOP(R.mipmap.ic_n_bishop_foreground,Color.BLACK),
        BLACK_KNIGHT(R.mipmap.ic_n_king_foreground,Color.BLACK),
        BLACK_PAWN(R.mipmap.ic_n_pawn_foreground,Color.BLACK),
        WHITE_KING(R.mipmap.ic_b_king_foreground,Color.WHITE),
        WHITE_QUEEN(R.mipmap.ic_b_queen_foreground,Color.WHITE),
        WHITE_ROOK(R.mipmap.ic_b_rook_foreground,Color.WHITE),
        WHITE_BISHOP(R.mipmap.ic_b_bishoop_foreground,Color.WHITE),
        WHITE_KNIGHT(R.mipmap.ic_b_knight_foreground,Color.WHITE),
        WHITE_PAWN(R.mipmap.ic_b_pawn_foreground,Color.WHITE);

        private final int shape;
        private final Color color;

        Type(int shape, Color color) {
            this.color = color;
            this.shape = shape;
        }

        public Color getColor() {
            return color;
        }

        public int getShape() {
            return shape;
        }

        @Override
        public String toString() {
            return String.valueOf(shape);
        }
    }


}
