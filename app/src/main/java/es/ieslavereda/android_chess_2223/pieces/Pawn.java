package es.ieslavereda.android_chess_2223.pieces;

import es.ieslavereda.android_chess_2223.Board;
import es.ieslavereda.android_chess_2223.Cell;
import es.ieslavereda.android_chess_2223.Coordinate;

public abstract class Pawn extends Piece {
    public Pawn(Type type, Cell cell){
        super(type, cell);
    }

    @Override
    public boolean moveTo(Coordinate origin, Coordinate destiny) {
        boolean moved = super.moveTo(origin, destiny);
        if (moved) {
            cell = getCell();
            Coordinate position = cell.getCoordinate();
            if (position.getRow() == 8 || position.getRow() == 1)
                transform();
        }
        return moved;
    }

    public abstract void transform();

    protected void checkPawnKiller(Coordinate c) {
        Board board = getCell().getBoard();
        if ((board.getCell(c) != null) && (!board.getCell(c).isEmpty())
            && (board.getCell(c).getPiece().getColor() != getColor()))
            coordinates.add(c);
    }

    protected void checkPawnMove(Coordinate c) {
        Board board = getCell().getBoard();
        if ((board.getCell(c) != null) && (board.getCell(c).isEmpty()))
            coordinates.add(c);
    }

}
