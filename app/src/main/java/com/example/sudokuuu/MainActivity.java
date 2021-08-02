package com.example.sudokuuu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnSubmit , btnReset , btnPractice;
    TableLayout tableLayout;
    TableRow[] tr;
    EditText[] et;

    /*EditText et11,et12,et13,et14,et15,et16,et17,et18,et19;
    EditText et21,et22,et23,et24,et25,et26,et27,et28,et29;
    EditText et31,et32,et33,et34,et35,et36,et37,et38,et39;
    EditText et41,et42,et43,et44,et45,et46,et47,et48,et49;
    EditText et51,et52,et53,et54,et55,et56,et57,et58,et59;
    EditText et61,et62,et63,et64,et65,et66,et67,et68,et69;
    EditText et71,et72,et73,et74,et75,et76,et77,et78,et79;
    EditText et81,et82,et83,et84,et85,et86,et87,et88,et89;
    EditText et91,et92,et93,et94,et95,et96,et97,et98,et99;*/

    int[][] mat;
    int indexET = 0 , indexTR = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitialiseVaribles();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Iniitialising mat elements to 0;
                mat = new int[9][9];
                for(int i=0;i<9;i++)
                {
                    for(int j=0;j<9;j++)
                        mat[i][j]=0;
                }

                //Assign inputted values
                int flag=0;

                for( int i = 0 ; i < 9 ; i++ )
                {
                    for(int j = 0 ; j < 9 ; j++ )
                    {
                        if( !et[ (i*9 + j) ].getText().toString().isEmpty() )
                        {
                            et[ (i*9 + j) ].setBackgroundResource(R.drawable.boxes_filled);
                            if( Integer.parseInt( et[(i*9 + j)].getText().toString() )>9 || Integer.parseInt( et[(i*9 + j)].getText().toString() ) <1)
                            {
                                flag++;
                            }
                            else
                            {
                                mat[i][j] = Integer.parseInt(et[(i * 9 + j)].getText().toString());
                            }
                        }
                    }
                }

                if(flag>0)
                {
                    Toast.makeText(MainActivity.this, "Enter no.s from 1-9 only!"  , Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "ASSIGNED VALUES !", Toast.LENGTH_LONG).show();

                    //Solve the sudoku and print result
                    SudokuSolver( mat , 9 , 0 , 0 );
                }
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for(int k=0 ; k<9 ; k++)
                {
                    for(int l=0 ; l<9 ; l++)
                    {
                        et[ (k*9 + l) ].setText(null);
                        et[ (k*9 + l) ].setBackgroundResource(R.drawable.number_border);
                    }
                }
            }
        });

        btnPractice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this , PracticeSudoku.class));
            }
        });

    }

    private void InitialiseVaribles()
    {
        btnSubmit = findViewById(R.id.btnSolve);
        btnReset = findViewById(R.id.btnReset);
        btnPractice = findViewById(R.id.btnPractice);

        tableLayout = findViewById(R.id.tableLayout);

        et = new EditText[81];
        tr = new TableRow[9];

        indexET = 0;
        indexTR = 0;
        /*for(int i = 0 ; i < tableLayout.getChildCount(); i++)
        {
            View view = tableLayout.getChildAt(i);

            if(view instanceof EditText)
            {
                et[index++] = (EditText) view;
            }
        }*/

        //TableLayout contains Array of 9 tablerows each containing 9 edittexts...(total et = 81)
        for (int j = 0; j < tableLayout.getChildCount() ; j++)
        {
            View view = tableLayout.getChildAt(j);

            if (view instanceof TableRow)
            {
                tr[indexTR] = (TableRow) view;

                for( int i=0 ; i < tr[indexTR].getChildCount() ; i++ )
                {
                    View v = tr[indexTR].getChildAt(i);

                    if(v instanceof EditText)
                    {
                        et[indexET++] = (EditText) v;
                    }
                }
                indexTR++;
            }
        }

        //This would have been tedious so we created array of edittexts as above
        /*et11 = findViewById(R.id.et11);
        et12 = findViewById(R.id.et12);
        et13 = findViewById(R.id.et13);
        et14 = findViewById(R.id.et14);
        et15 = findViewById(R.id.et15);
        et16 = findViewById(R.id.et16);
        et17 = findViewById(R.id.et17);
        et18 = findViewById(R.id.et18);
        et19 = findViewById(R.id.et19);

        et21 = findViewById(R.id.et21);
        et22 = findViewById(R.id.et22);
        et23 = findViewById(R.id.et23);
        et24 = findViewById(R.id.et24);
        et25 = findViewById(R.id.et25);
        et26 = findViewById(R.id.et26);
        et27 = findViewById(R.id.et27);
        et28 = findViewById(R.id.et28);
        et29 = findViewById(R.id.et29);

        et31 = findViewById(R.id.et31);
        et32 = findViewById(R.id.et32);
        et33 = findViewById(R.id.et33);
        et34 = findViewById(R.id.et34);
        et35 = findViewById(R.id.et35);
        et36 = findViewById(R.id.et36);
        et37 = findViewById(R.id.et37);
        et38 = findViewById(R.id.et38);
        et39 = findViewById(R.id.et39);

        et41 = findViewById(R.id.et41);
        et42 = findViewById(R.id.et42);
        et43 = findViewById(R.id.et43);
        et44 = findViewById(R.id.et44);
        et45 = findViewById(R.id.et45);
        et46 = findViewById(R.id.et46);
        et47 = findViewById(R.id.et47);
        et48 = findViewById(R.id.et48);
        et49 = findViewById(R.id.et49);

        et51 = findViewById(R.id.et51);
        et52 = findViewById(R.id.et52);
        et53 = findViewById(R.id.et53);
        et54 = findViewById(R.id.et54);
        et55 = findViewById(R.id.et55);
        et56 = findViewById(R.id.et56);
        et57 = findViewById(R.id.et57);
        et58 = findViewById(R.id.et58);
        et59 = findViewById(R.id.et59);

        et61 = findViewById(R.id.et61);
        et62 = findViewById(R.id.et62);
        et63 = findViewById(R.id.et63);
        et64 = findViewById(R.id.et64);
        et65 = findViewById(R.id.et65);
        et66 = findViewById(R.id.et66);
        et67 = findViewById(R.id.et67);
        et68 = findViewById(R.id.et68);
        et69 = findViewById(R.id.et69);

        et71 = findViewById(R.id.et71);
        et72 = findViewById(R.id.et72);
        et73 = findViewById(R.id.et73);
        et74 = findViewById(R.id.et74);
        et75 = findViewById(R.id.et75);
        et76 = findViewById(R.id.et76);
        et77 = findViewById(R.id.et77);
        et78 = findViewById(R.id.et78);
        et79 = findViewById(R.id.et79);

        et81 = findViewById(R.id.et81);
        et82 = findViewById(R.id.et82);
        et83 = findViewById(R.id.et83);
        et84 = findViewById(R.id.et84);
        et85 = findViewById(R.id.et85);
        et86 = findViewById(R.id.et86);
        et87 = findViewById(R.id.et87);
        et88 = findViewById(R.id.et88);
        et89 = findViewById(R.id.et89);

        et91 = findViewById(R.id.et91);
        et92 = findViewById(R.id.et92);
        et93 = findViewById(R.id.et93);
        et94 = findViewById(R.id.et94);
        et95 = findViewById(R.id.et95);
        et96 = findViewById(R.id.et96);
        et97 = findViewById(R.id.et97);
        et98 = findViewById(R.id.et98);
        et99 = findViewById(R.id.et99);*/
    }

    public Boolean isSafeToPut(int[][] mat , int num , int i , int j , int n)
    {
        for(int k=0 ; k<n ; k++)
        {
            if( mat[i][k]==num || mat[k][j]==num )
            {
                return false;
            }
        }
        int rootn = 3;
        int i1=(i/rootn)*rootn;
        int j1=(j/rootn)*rootn;
        for(int a=i1;a<i1+3;a++)
        {
            for(int b=j1;b<j1+3;b++)
            {
                if(mat[a][b]==num)
                {
                    return false;
                }
            }
        }
        return true;
    }


    public Boolean SudokuSolver(int[][] mat , int n , int i , int j)
    {
        //Base Cases
        if( i==n )
        {
            for(int k=0 ; k<n ; k++)
            {
                for(int l=0 ; l<n ; l++)
                {
                    String s = String.valueOf(mat[k][l]);
                    et[ (k*9 + l) ].setText(s);
                }
            }

            Toast.makeText(this, "Solved ", Toast.LENGTH_SHORT).show();

            return true;
        }

        if( j==n )
        {
            return SudokuSolver(mat , n , i+1 , 0);
        }

        if( mat[i][j] != 0 )
        {
            return SudokuSolver(mat , n , i, j+1 );
        }

        //Recusrsive Cases
        for(int num=1;num<=n;num++)
        {
            if(isSafeToPut(mat,num,i,j,n))
            {
                mat[i][j]=num;
                Boolean KyaAageBaatBani = SudokuSolver(mat,n,i,j+1);

                if(KyaAageBaatBani)
                {
                    return true;
                }
                else
                {
                    mat[i][j]=0;
                }
            }
        }

        return false;
    }

}
