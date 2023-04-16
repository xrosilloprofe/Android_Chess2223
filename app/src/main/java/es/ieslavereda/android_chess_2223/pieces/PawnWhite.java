package es.ieslavereda.android_chess_2223.pieces;

import es.ieslavereda.android_chess_2223.Cell;
import es.ieslavereda.android_chess_2223.Coordinate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PawnWhite extends Pawn{
    public PawnWhite(Cell cell){
        super(Type.WHITE_PAWN, cell);
    }

    @Override
    public List<Coordinate> getNextMovements() {
        coordinates = new ArrayList<>();
        cell = getCell();
        Coordinate position = cell.getCoordinate();
        Coordinate c;

        c = position.up();
        checkPawnMove(c);

        if(position.getRow()==7){
            c = position.up().up();
            checkPawnMove(c);
        }

        c=position.diagonalUpLeft();
        checkPawnKiller(c);
        c=position.diagonalUpRight();
        checkPawnKiller(c);

        return coordinates;

    }

    @Override
    public void transform() {
        new QueenWhite(getCell());
        cell=null;
    }
}
