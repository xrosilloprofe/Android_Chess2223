package es.ieslavereda.android_chess_2223.pieces;

import es.ieslavereda.android_chess_2223.Cell;
import es.ieslavereda.android_chess_2223.Coordinate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PawnBlack extends Pawn {
    public PawnBlack(Cell cell){
        super(Type.BLACK_PAWN, cell);
    }

    @Override
    public List<Coordinate> getNextMovements() {
        coordinates = new ArrayList<>();
        cell = getCell();
        Coordinate position = cell.getCoordinate();
        Coordinate c;

        c = position.down();
        checkPawnMove(c);
        if(position.getRow()==2){
            c = position.down().down();
            checkPawnMove(c);
        }

        c=position.diagonalDownLeft();
        checkPawnKiller(c);
        c=position.diagonalDownRight();
        checkPawnKiller(c);

        return coordinates;

    }

    @Override
    public void transform() {
        new QueenBlack(getCell());
        cell=null;
    }

}
