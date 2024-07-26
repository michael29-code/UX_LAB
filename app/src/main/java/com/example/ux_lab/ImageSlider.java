package com.example.ux_lab;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import java.util.ArrayList;

public class ImageSlider extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        com.denzcoskun.imageslider.ImageSlider imageSlider = findViewById(R.id.imageSlider);

        ArrayList<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.jumbotron1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.jumbotron2, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.jumbotron3, ScaleTypes.FIT));

        imageSlider.setImageList(slideModels, ScaleTypes.FIT);
    }
}
