package com.example.med4ukm;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CommonFAQs extends AppCompatActivity {

    ArrayList<ModelClass> arrayList;
    RecyclerView recyclerView;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqs);

       // getSupportActionBar().setTitle("Common FAQ");


        recyclerView=findViewById(R.id.recycler_view);
        arrayList=new ArrayList<>();

        arrayList.add(new ModelClass("1. Bilakah waktu Klinik Pesakit Luar?", "Isnin - Jumaat : 8.00 pagi - 4.45 petang        Hari Sabtu, Ahad & Cuti Umum adalah ditutup.", false));
        arrayList.add(new ModelClass("2. Mengapa kaunter pendaftaran ditutup pada jam 4.45 petang?", "Untuk menyelesaikan perkhidmatan pesakit yang sedia ada dan persediaan untuk menutup premis.", false));
        arrayList.add(new ModelClass("3. Apakah perkhidmatan lain yang disediakan oleh PKU?", "Antara perkhidmatan lain adalah Klinik Berhenti Merokok, Klinik NCD untuk penyakit kronik, Klinik Lawatan Pakar Diet dan Pakar Sakit Puan.", false));
        arrayList.add(new ModelClass("4. Apakah waktu perkhidmatan Unit Kecemasan?", "Waktu perkhidmatan Unit Kecemasan adalah daripada jam 8.00 pagi hinggaa 10.00 malam dari hari Isnin hingga Jumaat , dan hari Sabtu & Ahad dari jam 8.00 pagi hingga 1.00 tengah hari.",false));
        arrayList.add(new ModelClass("5. Apakah contoh kes-kes kecemasan yang dirawat?", "Antaranya adalah sesak nafas, sakit dada, sawan, kemalangan, demam panas dan gigitan haiwan berbisa.", false));
        arrayList.add(new ModelClass("6. Bolehkah bekalan ubat diambil oleh wakil pesakit?", "Tidak boleh, kecuali ubat ulangan yang disertakan dengan perskripsi ulangan.", false));
        arrayList.add(new ModelClass("7. Adakah saya perlu berpuasa sebelum ujian darah dilakukan?","Bergantung kepada jenis ujian. ", false));
        arrayList.add(new ModelClass("8. Berapa lama jangka masa ujian darah dapat disiapakan?", "Ujian darah yang dihantan ke makmal memerlukan 3 hari waktu bekerja manakala ujian darah yang dilakukan di makmal perubatan PKU mengambil masa antara 15 hingga 30 minit.", false));
        arrayList.add(new ModelClass("9. Adakah perkhidmatan imunisasi dewasa disediakan?", "Ada dan bayaran akan dikenakan.", false));
        arrayList.add(new ModelClass("10. Adakah orang awam boleh mendapat rawatan perubatan di PKU?", "Boleh, tetapi caj rawatan perlu dibiayai sendiri.", false));
        arrayList.add(new ModelClass("11. Adakah rawatan pakar ini dikenakan bayaran?", "Tidak, rawatan adalah secara percuma.", false));
        arrayList.add(new ModelClass("12. Adakah rawatan memutihkan gigi disediakan di Unit Pergigian?", "Tidak.",false));
        arrayList.add(new ModelClass("13. Anda prosedur untuk membuat gigi palsu?", "Anda perlu mendapatkan temujanji di kaunter pergigian untuk berjumpa doktor gigi", false));


        adapter = new Adapter(arrayList,CommonFAQs.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();




    }
}