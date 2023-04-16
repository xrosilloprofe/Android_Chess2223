package es.ieslavereda.android_chess_2223;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Board board;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        board = findViewById(R.id.board);
        textView = findViewById(R.id.textView);

        board.setCellListener(this);

    }


    @Override
    public void onClick(View view) {
        if (view instanceof Cell) {
            String cell = ((Cell) view).getCoordinate().toString();
            textView.setText(((Cell) view).getCoordinate().toString());

            Cell c = (Cell) view;
            if(c.getPiece() != null)
                board.highlight(c.getPiece().getNextMovements());
        } else {
            textView.setText(((TextView) view).getText());
        }
    }
}