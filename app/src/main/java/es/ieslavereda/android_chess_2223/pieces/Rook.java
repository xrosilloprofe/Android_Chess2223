package es.ieslavereda.android_chess_2223.pieces;


import es.ieslavereda.android_chess_2223.Board;
import es.ieslavereda.android_chess_2223.Cell;
import es.ieslavereda.android_chess_2223.Coordinate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class Rook extends Piece {

    public Rook(Type type, Cell cell) {
        super(type, cell);
    }

    @Override
    public List<Coordinate> getNextMovements() {
        return Rook.getNextMovementsAsRook(this);
    }

    public static List<Coordinate> getNextMovementsAsRook(Piece p){
        List<Coordinate> coordinates = new ArrayList<>();
        Cell cell = p.getCell();
        Board board = cell.getBoard();
        Piece.Color color = p.getColor();
        Coordinate original = cell.getCoordinate();
        Coordinate c;

        //UP
        c=original.up();
        while(board.getCell(c)!= null && board.getCell(c).isEmpty()) {
            coordinates.add(c);
            c = c.up();
        }
        if(board.getCell(c)!=null && board.getCell(c).getPiece().getColor()!=color)
            coordinates.add(c);

        //DOWN
        c=original.down();
        while(board.getCell(c)!= null && board.getCell(c).isEmpty()) {
            coordinates.add(c);
            c = c.down();
        }
        if(board.getCell(c)!=null && board.getCell(c).getPiece().getColor()!=color)
            coordinates.add(c);

        //LEFT
        c=original.left();
        while(board.getCell(c)!= null && board.getCell(c).isEmpty()) {
            coordinates.add(c);
            c = c.left();
        }
        if(board.getCell(c)!=null && board.getCell(c).getPiece().getColor()!=color)
            coordinates.add(c);

        //RIGHT
        c=original.right();
        while(board.getCell(c)!= null && board.getCell(c).isEmpty()) {
            coordinates.add(c);
            c = c.right();
        }
        if(board.getCell(c)!=null && board.getCell(c).getPiece().getColor()!=color)
            coordinates.add(c);

        return coordinates;
    }


}
