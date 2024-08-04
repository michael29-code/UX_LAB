package com.example.ux_lab;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProductListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private List<Product> productList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_product_list, findViewById(R.id.content_frame));
        setContentView(R.layout.activity_product_list);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productList = new ArrayList<>();
        productList.add(new Product("THE BOOK 1", "Yoasobi", "THE BOOK adalah album mini atau extended play (EP) debut serta rilis fisik pertama yang direkam oleh Yoasobi. Album mini ini dirilis pada tanggal 6 Januari 2021 melalui Sony Music Entertainment Japan, bertepatan dengan rilis singel ketujuh mereka yang berjudul 'Kaibutsu'. 'Encore' digunakan sebagai singel promosi dari album ini", R.drawable.yoasobi1, "2022"));
        productList.add(new Product("THE BOOK 2", "Yoasobi", "The Book 2 adalah EP kedua dalam bahasa Jepang (ketiga secara keseluruhan) oleh duo Jepang Yoasobi. Album ini dirilis pada tanggal 1 Desember 2021, melalui Sony Music Entertainment Japan, sebelas bulan setelah EP debut mereka, The Book (2021). EP ini terdiri dari delapan lagu, termasuk semua singel mereka yang dirilis pada tahun 2021, serta menampilkan lagu baru 'Moshi mo Inochi ga Egaketara'.", R.drawable.yoasobi2, "2023"));
        productList.add(new Product("THE BOOK 3", "Yoasobi", "The Book 3 adalah EP ketiga dalam bahasa Jepang (keenam secara keseluruhan) oleh duo Jepang Yoasobi. Album ini dirilis pada tanggal 4 Oktober 2023, melalui Sony Music Entertainment Japan, satu tahun sepuluh bulan setelah EP kedua mereka, The Book 2 (2021). Melanjutkan konsep 'reading CD' seperti album-album sebelumnya, EP ini mencakup genre electropop dan synth-pop, terdiri dari sepuluh lagu yang semuanya ditulis dan diproduksi oleh salah satu anggota duo tersebut, Ayase.", R.drawable.yoasobi3, "2024"));

        productAdapter = new ProductAdapter(this, productList);
        recyclerView.setAdapter(productAdapter);
    }
}
