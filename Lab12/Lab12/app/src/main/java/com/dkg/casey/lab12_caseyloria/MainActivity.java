package com.dkg.casey.lab12_caseyloria;

import android.app.Dialog;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


//CASEY LORIA
public class MainActivity extends AppCompatActivity {
    MyView myView;

    Dialog dialogOption;

    public SeekBar redSeekBarValue;
    public SeekBar greenSeekBarValue;
    public SeekBar blueSeekBarValue;

    public TextView redTextView;
    public TextView greenTextView;
    public TextView blueTextView;
    public TextView selectedColor;

    public int redValue ;
    public int greenValue ;
    public int blueValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myView = new MyView(this, null);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        getMenuInflater().inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {

            case R.id.color:
                showColorDialog();
                Toast.makeText(getApplicationContext(),item.getTitle() + " menu item selected",Toast.LENGTH_SHORT).show();
                break;
            case R.id.shrink:
                ((RGBStats)this.getApplication()).SetShrinkExpand(true,false);
                Toast.makeText(getApplicationContext(),item.getTitle() + " menu item selected",Toast.LENGTH_SHORT).show();
                break;
            case R.id.enlarge:
                ((RGBStats)this.getApplication()).SetShrinkExpand(false,true);
                Toast.makeText(getApplicationContext(),item.getTitle() + " menu item selected",Toast.LENGTH_SHORT).show();
                break;
            case R.id.triangle:
                ((RGBStats)this.getApplication()).SetShape(true,false,false);
                Toast.makeText(getApplicationContext(),item.getTitle() + " menu item selected",Toast.LENGTH_SHORT).show();
                break;
            case R.id.circle:
                ((RGBStats)this.getApplication()).SetShape(false,false,true);
                Toast.makeText(getApplicationContext(),item.getTitle() + " menu item selected",Toast.LENGTH_SHORT).show();
                break;
            case R.id.rectangle:
                ((RGBStats)this.getApplication()).SetShape(false,true,false);
                Toast.makeText(getApplicationContext(),item.getTitle() + " menu item selected",Toast.LENGTH_SHORT).show();
                break;
            case R.id.reset:
                Toast.makeText(getApplicationContext(),item.getTitle() + " menu item selected",Toast.LENGTH_SHORT).show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    void showColorDialog(){
        dialogOption = new Dialog(this);
        dialogOption.setContentView(R.layout.color_view);
        dialogOption.setTitle("Select Color");
        dialogOption.show();

        selectedColor = (TextView) dialogOption.findViewById(R.id.selectedColortv);
        selectedColor.setBackgroundColor(myView.getColor());

        redSeekBarValue = (SeekBar) dialogOption.findViewById(R.id.redseekBar);
        greenSeekBarValue = (SeekBar)dialogOption.findViewById(R.id.greenseekBar);
        blueSeekBarValue = (SeekBar)dialogOption.findViewById(R.id.blueseekBar);

        redTextView = (TextView) dialogOption.findViewById(R.id.redselected);
        blueTextView = (TextView) dialogOption.findViewById(R.id.blueselected);
        greenTextView = (TextView)dialogOption.findViewById(R.id.greenselected);

        redTextView.setText(redValue + "");
        blueTextView.setText(blueValue + "");
        greenTextView.setText(greenValue + "");

        redSeekBarValue.setProgress(redValue);
        blueSeekBarValue.setProgress(blueValue);
        greenSeekBarValue.setProgress(greenValue);

        redSeekBarValue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                redValue = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                redTextView.setText(redValue + "");

                int color = Color.rgb(redValue, greenValue, blueValue);
                selectedColor.setBackgroundColor(color);
            }
        });

        greenSeekBarValue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                greenValue = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                greenTextView.setText(greenValue + "");

                int color = Color.rgb(redValue, greenValue, blueValue);
                selectedColor.setBackgroundColor(color);
            }
        });

        blueSeekBarValue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                blueValue = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                blueTextView.setText(blueValue + "");

                int color = Color.rgb(redValue, greenValue, blueValue);
                selectedColor.setBackgroundColor(color);
            }
        });

        Button setColorButton = (Button) dialogOption.findViewById(R.id.setColor);
        setColorButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_main);

                int color = Color.rgb(redValue, greenValue, blueValue);
                ((RGBStats)getApplication()).SetColors(redValue,greenValue,blueValue);
                myView.setColor(color);//Create new instance of graphics view
                myView.postInvalidate();

                dialogOption.dismiss();
            }
        });

    }

}
