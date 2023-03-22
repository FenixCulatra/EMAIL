package joao.vitor.oliveira.scheidegger.email;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnEnviar = findViewById(R.id.btnEnviar);
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Aqui é pego o os EditTexts onde foi digitado o email, o assunto e o texto que vai ser enviado
                EditText etEmail = findViewById(R.id.etEmail);
                EditText etAssunto = findViewById(R.id.etAssunto);
                EditText etTexto = findViewById(R.id.etTexto);
                //Aqui é extraído os textos e passamos para string
                String email = etEmail.getText().toString();
                String assunto = etAssunto.getText().toString();
                String texto = etTexto.getText().toString();
                //Depois é criado um intent que vai direcionar para algum outro aplicativo
                Intent i = new Intent(Intent.ACTION_SENDTO);
                //Aqui é definido que aplicativo será algum sistema de email
                i.setData(Uri.parse("mailto:"));

                //Aqui é criado um array que extrairá todos os emails digitados, pois sempre podemos digitar mais de um endereço
                String[] emails = new String[]{email};
                //Nas próximas linha é passada as informações: o email (que é passado em forma de array), o assunto e o texto
                i.putExtra(Intent.EXTRA_EMAIL, emails);//
                i.putExtra(Intent.EXTRA_SUBJECT, assunto);
                i.putExtra(Intent.EXTRA_TEXT, texto);

                //Depois tento iniciar a atividade para enviar o email, caso não seja possível, o programa criará um aviso
                try {
                    startActivity(Intent.createChooser(i, "Escolha o APP"));
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(MainActivity.this, "Não há nenhum app que possa realizar tal ação", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}