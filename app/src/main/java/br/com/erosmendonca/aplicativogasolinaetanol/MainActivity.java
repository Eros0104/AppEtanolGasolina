package br.com.erosmendonca.aplicativogasolinaetanol;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private TextView textViewGasValue;
    private TextView textViewEthanolValue;

    private SeekBar seekBarGasValue;
    private SeekBar seekBarEthanolValue;

    private TextView melhorOpcaoTextView;


    private ImageView melhorOpcaoImageView;

    private double precoGasolina;
    private double precoEtanol;
    private double razao;


    private NumberFormat currencyFormat =
            NumberFormat.getCurrencyInstance();
    private NumberFormat percentFormat =
            NumberFormat.getPercentInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewGasValue = (TextView) findViewById(R.id.preco_da_gasolina_textview);
        textViewEthanolValue = (TextView) findViewById(R.id.preco_do_etanol_textview);

        seekBarGasValue = (SeekBar) findViewById(R.id.preco_da_gasolina_seekbar);
        seekBarEthanolValue = (SeekBar) findViewById(R.id.preco_do_etanol_seekbar);

        melhorOpcaoTextView = (TextView) findViewById(R.id.melhor_opcao_textview);
        melhorOpcaoImageView = (ImageView) findViewById(R.id.melhor_opcao_imageview);

        precoGasolina = precoEtanol = 3;
        calcular();
        seekBarGasValue.setOnSeekBarChangeListener(observer);
        seekBarEthanolValue.setOnSeekBarChangeListener(observer);

    }
    private void calcular (){
        razao = precoEtanol / precoGasolina;
        textViewGasValue.setText(currencyFormat.format(precoGasolina));
        textViewGasValue.setText(currencyFormat.format(precoEtanol));
        if (razao >= 0.7){
            melhorOpcaoImageView.setImageResource(R.drawable.gasolina);
            melhorOpcaoTextView.setText(getString(R.string.melhor_opcao, getString(R.string.gasolina), percentFormat.format(razao)));
        }
        else{
            melhorOpcaoImageView.setImageResource(R.drawable.etanol);
            melhorOpcaoTextView.setText(getString(R.string.melhor_opcao, getString(R.string.etanol), percentFormat.format(razao)));
        }
    }

    private SeekBar.OnSeekBarChangeListener observer =
            new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                    if (seekBar.getId() == R.id.preco_da_gasolina_seekbar){
                        precoGasolina = progress / 100.;
                    }
                    else{
                        precoEtanol = progress / 100.;
                    }
                    calcular();
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            };
}
