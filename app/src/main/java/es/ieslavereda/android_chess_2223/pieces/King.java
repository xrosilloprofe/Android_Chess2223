package es.ieslavereda.android_chess_2223.pieces;

import es.ieslavereda.android_chess_2223.Cell;
import es.ieslavereda.android_chess_2223.Coordinate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class King extends Piece{

    public King(Type type, Cell cell) {
        super(type, cell);
    }

    @Override
    public List<Coordinate> getNextMovements() {
        coordinates = new ArrayList<>();
        cell = getCell();
        Coordinate position = cell.getCoordinate();
        Coordinate c;

        c = position.up();
        check(c, coordinates);
        c = position.left();
        check(c, coordinates);
        c = position.down();
        check(c, coordinates);
        c = position.right();
        check(c, coordinates);
        c = position.diagonalDownLeft();
        check(c, coordinates);
        c = position.diagonalDownRight();
        check(c, coordinates);
        c = position.diagonalUpLeft();
        check(c, coordinates);
        c = position.diagonalUpRight();
        check(c, coordinates);

        return coordinates;

    }




}
