package es.ieslavereda.android_chess_2223.pieces;

import es.ieslavereda.android_chess_2223.Cell;
import es.ieslavereda.android_chess_2223.Coordinate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Queen extends Piece {
    public Queen(Type type, Cell cell){
        super(type, cell);
    }

    @Override
    public List<Coordinate> getNextMovements() {
        coordinates = new ArrayList<>();
        coordinates.addAll(Bishop.getNextMovementsAsBishop(this));
        coordinates.addAll(Rook.getNextMovementsAsRook(this));
        return coordinates;
    }


}
