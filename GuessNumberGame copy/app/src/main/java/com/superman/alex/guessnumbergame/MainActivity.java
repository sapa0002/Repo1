package com.superman.alex.guessnumbergame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Random random = new Random();

    private EditText editText;
    private Button compareBtn;
    private Button resetBtn;
    private TextView textPrompt;
    private TextView textShots;

    private static final String TXT_PROMPT = "Enter a number between 1 and 1000. You have only 10 shots.";
    private String txtShots;
    private int guessCounter = 10;
    private int theNumber;
    private int userGuess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editText = (EditText) findViewById(R.id.editText);
        compareBtn = (Button) findViewById(R.id.compareBtn);
        resetBtn = (Button) findViewById(R.id.resetBtn);
        textPrompt = (TextView) findViewById(R.id.textPrompt);
        textShots = (TextView) findViewById(R.id.textShots);

        textPrompt.setText(TXT_PROMPT);
        textShots.setText("10");

        theNumber = random.nextInt(1000) + 1;

        compareBtn.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              guessCounter--;
                                              try {
                                                  userGuess = Integer.parseInt(editText.getText().toString());
                                              } catch (NumberFormatException f) {
                                                  f.printStackTrace();
                                              }
                                              try {
                                                  if (theNumber == userGuess) {
                                                      if (guessCounter <= 10 && guessCounter > 5) {
                                                          Toast.makeText(MainActivity.this, "Superior win!", Toast.LENGTH_SHORT).show();
                                                          Toast.makeText(MainActivity.this, "Please Reset!", Toast.LENGTH_SHORT).show();
                                                      } else if (guessCounter <= 5 && guessCounter > 0) {
                                                          Toast.makeText(MainActivity.this, "Excellent win!", Toast.LENGTH_SHORT).show();
                                                          Toast.makeText(MainActivity.this, "Please Reset!", Toast.LENGTH_SHORT).show();
                                                      } else if (guessCounter <= 0) {
                                                          Toast.makeText(MainActivity.this, "Please Reset!", Toast.LENGTH_SHORT).show();
                                                      }
                                                  } else if (theNumber > userGuess) {
                                                      if (theNumber > userGuess && userGuess <= 1000) {
                                                          Toast.makeText(MainActivity.this, "Your number is too low", Toast.LENGTH_SHORT).show();
                                                      } else if (guessCounter <= 0) {
                                                          Toast.makeText(MainActivity.this, "Please Reset!", Toast.LENGTH_SHORT).show();
                                                      }
                                                  } else if (theNumber < userGuess) {
                                                      if (theNumber < userGuess && userGuess <= 1000) {
                                                          Toast.makeText(MainActivity.this, "Your number is too high", Toast.LENGTH_SHORT).show();
                                                      } else if (guessCounter <= 0) {
                                                          Toast.makeText(MainActivity.this, "Please Reset!", Toast.LENGTH_SHORT).show();
                                                      }
                                                  }
                                              } catch (NumberFormatException e) {
                                                  e.printStackTrace();
                                              }
                                              txtShots = Integer.toString(guessCounter);
                                              textShots.setText(txtShots);
                                              if (guessCounter < 0) {
                                                  textShots.setText("Stop!");
                                              }
                                          }
                                      }

        );

        resetBtn.setOnClickListener(new View.OnClickListener()

                                    {
                                        @Override
                                        public void onClick(View v) {
                                            theNumber = random.nextInt(1000) + 1;
                                            if (guessCounter <= 10) {
                                                guessCounter = 10;
                                            }
                                            txtShots = Integer.toString(guessCounter);
                                            textShots.setText(txtShots);
                                        }
                                    }

        );
        resetBtn.setOnLongClickListener(new View.OnLongClickListener()

                                        {
                                            @Override
                                            public boolean onLongClick(View v) {
                                                String sValue = Integer.toString(theNumber);
                                                Toast.makeText(MainActivity.this, sValue, Toast.LENGTH_LONG).show();
                                                return true;
                                            }
                                        }
        );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.userName_dialog:
                UserNameDialogFragment dialog = new UserNameDialogFragment();
                dialog.show(getFragmentManager(), "YO");
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}


































