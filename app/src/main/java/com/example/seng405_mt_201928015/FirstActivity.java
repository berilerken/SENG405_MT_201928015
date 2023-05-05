package com.example.seng405_mt_201928015;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


//İlk açtığımızda çıkan sayfamızdır, burada kullanıcı input girer.
public class FirstActivity extends AppCompatActivity {

    private EditText inputText;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        inputText = findViewById(R.id.input_text);
        submit = findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {


            //Bu kısımda bazı özellikler ekledim, kullanıcı sadece 1 veya 2 girebileceği için;
            //1 veya 2 dışında bir rakam girerse Toast ile bir uyarı popup'ı karşılarına çıkıyor (SENG449 da sizden öğrenmiştik)
            //Hiçbir şey yazmayıp göndermeye çalışırlarsa aynı şekilde bir Toast mesajı ile karşılaşılıyor.
            @Override
            public void onClick(View v) {
                String pageNumberString = inputText.getText().toString().trim();
                //Kullanıcı 1 ya da 2 olarak bir input vermeli, herhangi bir değer vermez ise Toast ile bir popup uyarısı geliyor.
                if (TextUtils.isEmpty(pageNumberString)) {
                    Toast.makeText(FirstActivity.this, "Lütfen bir sayfa numarası giriniz (1 ya da 2)", Toast.LENGTH_SHORT).show();
                } else {
                    int pageNumber = Integer.parseInt(pageNumberString);
                    if (pageNumber == 1 || pageNumber == 2) {
                        Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                        intent.putExtra("page_number", pageNumber);
                        startActivity(intent);
                    } else {
                        Toast.makeText(FirstActivity.this, "Lütfen geçerli bir sayfa numarası giriniz!!!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}

