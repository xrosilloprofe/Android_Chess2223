package es.ieslavereda.android_chess_2223.pieces;

import es.ieslavereda.android_chess_2223.Board;
import es.ieslavereda.android_chess_2223.Cell;
import es.ieslavereda.android_chess_2223.Coordinate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class Bishop extends Piece {

    public Bishop(Type type, Cell cell) {
        super(type, cell);
    }

    @Override
    public List<Coordinate> getNextMovements() {
        return Bishop.getNextMovementsAsBishop(this);
    }

    public static List<Coordinate> getNextMovementsAsBishop(Piece p){
        List<Coordinate> coordinates = new ArrayList<>();
        Cell cell = p.getCell();
        Board board = cell.getBoard();
        Piece.Color color = p.getColor();
        Coordinate original = cell.getCoordinate();
        Coordinate c;

        //DIAGONAL UP LEFT
        c=original.diagonalUpLeft();
        while(board.getCell(c)!= null && board.getCell(c).isEmpty()) {
            coordinates.add(c);
            c = c.diagonalUpLeft();
        }
        if(board.getCell(c)!=null && board.getCell(c).getPiece().getColor()!=color)
            coordinates.add(c);

        //DIAGONAL UP RIGHT
        c=original.diagonalUpRight();
        while(board.getCell(c)!= null && board.getCell(c).isEmpty()) {
            coordinates.add(c);
            c = c.diagonalUpRight();
        }
        if(board.getCell(c)!=null && board.getCell(c).getPiece().getColor()!=color)
            coordinates.add(c);

        //DIAGONAL DOWN LEFT
        c=original.diagonalDownLeft();
        while(board.getCell(c)!= null && board.getCell(c).isEmpty()) {
            coordinates.add(c);
            c = c.diagonalDownLeft();
        }
        if(board.getCell(c)!=null && board.getCell(c).getPiece().getColor()!=color)
            coordinates.add(c);

        //DIAGONAL DOWN RIGHT
        c=original.diagonalDownRight();
        while(board.getCell(c)!= null && board.getCell(c).isEmpty()) {
            coordinates.add(c);
            c = c.diagonalDownRight();
        }
        if(board.getCell(c)!=null && board.getCell(c).getPiece().getColor()!=color)
            coordinates.add(c);

        return coordinates;
    }


}
